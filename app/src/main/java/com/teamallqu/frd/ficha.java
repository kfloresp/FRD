package com.teamallqu.frd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ficha extends Fragment {
    EditText numhistoria;
    EditText fecha;
    EditText edad;
    EditText talla;
    EditText peso;
    EditText imc;
    EditText estado;
    Switch diagnostico;
    Button btnguardar;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ficha, container, false);
        numhistoria = v.findViewById(R.id.ethistoria);
        fecha = v.findViewById(R.id.etfecha);
        edad = v.findViewById(R.id.etedad);
        talla = v.findViewById(R.id.ettalla);
        peso = v.findViewById(R.id.etpeso);
        imc = v.findViewById(R.id.etimc);
        estado = v.findViewById(R.id.etestado);
        diagnostico = v.findViewById(R.id.swdiagnostico);

        diagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diagnostico.isChecked()){
                    diagnostico.setText("SI");
                }else{
                    diagnostico.setText("NO");
                }
            }
        });


        btnguardar = v.findViewById(R.id.btnagregarficha);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
        numhistoria.requestFocus();
        return v;
    }

    public void guardar(){
    String txnumhistoria = numhistoria.getText().toString();
    String txedad = edad.getText().toString();
    String txtalla = talla.getText().toString();
    String txpeso = peso.getText().toString();
    String txdiagnostico = diagnostico.getText().toString(); // SWITCH

        if (txnumhistoria.isEmpty()||txedad.isEmpty()||
                txtalla.isEmpty()||txpeso.isEmpty()||txdiagnostico.isEmpty()){
            Toast.makeText(getActivity(), "Completar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            /*Peso inferior al normal	Menos de 18.5
            Normal	18.5 – 24.9
            Peso superior al normal	25.0 – 29.9
            Obesidad	Más de 30.0
            */
            double t = Double.parseDouble(txtalla);
            double p = Double.parseDouble(txpeso);
            double i = p/(t*t);
            if (i<18.5){
                imc.setText(i+"");
                estado.setText("Bajo peso");
            }else if (i>=18.5 && i<=24.9){
                imc.setText(i+"");
                estado.setText("Normal");
            }else if (i>=25 && i<=29.9){
                estado.setText("Sobrepeso");
                imc.setText(i+"");
            }else if (i>=30 && i<=34.9){
                estado.setText("Obesidad I");
                imc.setText(i+"");
            }
            else if (i>=35 && i<=39.9){
                estado.setText("Obesidad II");
                imc.setText(i+"");
            }
            else if (i>=40){
                estado.setText("Obesidad III");
                imc.setText(i+"");
            }

        double imc_corto = Double.parseDouble(imc.getText().toString());

        String tximc = String.format("%.2f",imc_corto);
        String txestado = estado.getText().toString();
        String txfecha = sdf.format(new Date());

        conexionsql conn=new conexionsql(getActivity(),"db",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.col_numhistoria,txnumhistoria);
        values.put(utilidades.col_fecha,txfecha);
        values.put(utilidades.col_edad,txedad);
        values.put(utilidades.col_talla,txtalla);
        values.put(utilidades.col_peso,txpeso);
        values.put(utilidades.col_diagnostico,txdiagnostico);
        values.put(utilidades.col_imc,tximc);
        values.put(utilidades.col_estado,txestado);

        db.insert(utilidades.TABLAFICHA,utilidades.col_numhistoria,values);

        Toast.makeText(getActivity(), "Se guardaron datos.", Toast.LENGTH_SHORT).show();
        db.close();
        numhistoria.setText("");
        fecha.setText("");
        edad.setText("");
        talla.setText("");
        peso.setText("");
        imc.setText("");
        estado.setText("");
        diagnostico.setChecked(true);
        diagnostico.setText("SI");
        numhistoria.requestFocus();
        }
    }



}
