package com.teamallqu.frd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ficha extends Fragment {
    EditText numsujeto;
    EditText nombreape;
    EditText numhistoria;
    EditText fecha;
    EditText edad;
    EditText talla;
    EditText peso;
    EditText imc;
    EditText resultado;
    Switch diagnostico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ficha, container, false);
        numsujeto = v.findViewById(R.id.etnsujeto);
        nombreape = v.findViewById(R.id.etnombre);
        numhistoria = v.findViewById(R.id.ethistoria);
        fecha = v.findViewById(R.id.etfecha);
        edad = v.findViewById(R.id.etedad);
        talla = v.findViewById(R.id.ettalla);
        peso = v.findViewById(R.id.etpeso);
        imc = v.findViewById(R.id.etimc);
        resultado = v.findViewById(R.id.etresultado);
        diagnostico = v.findViewById(R.id.swdiagnostico);
        return v;
    }

    public void guardar(){
    String txnumsujeto = numsujeto.getText().toString();
    String txnombreape = nombreape.getText().toString();
    String txnumhistoria = numhistoria.getText().toString();
    String txfecha = fecha.getText().toString();
    String txedad = edad.getText().toString();
    String txtalla = talla.getText().toString();
    String txpeso = peso.getText().toString();
    String txdiagnostico = diagnostico.getText().toString(); // SWITCH

        if (txnumsujeto.isEmpty()||txnombreape.isEmpty()||txnumhistoria.isEmpty()||txfecha.isEmpty()||txedad.isEmpty()||
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
                resultado.setText("Bajo peso");
            }else if (i>=18.5 && i<=24.9){
                imc.setText(i+"");
                resultado.setText("Normal");
            }else if (i>=25 && i<=29.9){
                resultado.setText("Sobrepeso");
                imc.setText(i+"");
            }else if (i>=30 && i<=34.9){
                resultado.setText("Obesidad I");
                imc.setText(i+"");
            }
            else if (i>=35 && i<=39.9){
                resultado.setText("Obesidad II");
                imc.setText(i+"");
            }
            else if (i>=40 && i<=49.9){
                resultado.setText("Obesidad III");
                imc.setText(i+"");
            }
            else if (i<50){
                resultado.setText("Obesidad IV");
                imc.setText(i+"");
            }
        }

        String tximc = imc.getText().toString();
        String txresultado = resultado.getText().toString();

        conexionsql conn=new conexionsql(getActivity(),"db",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.col_numsujeto,txnumsujeto);
        values.put(utilidades.col_nombreape,txnombreape);
        values.put(utilidades.col_numhistoria,txnumhistoria);
        values.put(utilidades.col_fecha,txfecha);
        values.put(utilidades.col_edad,txedad);
        values.put(utilidades.col_talla,txtalla);
        values.put(utilidades.col_peso,txpeso);
        values.put(utilidades.col_diagnostico,txdiagnostico);
        values.put(utilidades.col_imc,tximc);
        values.put(utilidades.col_resultado,txresultado);

        Long resultante = db.insert(utilidades.TABLAFICHA,utilidades.col_numhistoria,values);
        Toast.makeText(getActivity(), "N HISTORIA"+resultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    // Interfaz Guardar ficha
    public interface Guardar_ficha{

        // Método de la interfaz
        public void guardar();
    }

    // Objeto de la interfaz actualizar, con este objeto llamaremos el
    // método de la interfaz
    Guardar_ficha actualizar;

    // Costructor
    public ficha(){

    }

    // Instancia
    public static ficha newInstance(){
        return new ficha();
    }


}
