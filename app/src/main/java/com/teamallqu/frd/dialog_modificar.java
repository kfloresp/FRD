package com.teamallqu.frd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class dialog_modificar extends AppCompatDialogFragment{
    idialog_modificar listener;
    EditText etxhistoria,etxedad,etxpeso,etxtalla,etxfecha,etximc,etxresultado;
    Switch swxdiagnostico;
    int nsujeto;
    public interface idialog_modificar{
        void enviardatos(int numerosujeto,String edad,String peso,String talla,String fecha,String imc,String estado,String diagnostico,String numhistoria);
    }
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            listener = (idialog_modificar) getTargetFragment();
        }catch (ClassCastException e){
        }

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_modificar,null);
        //iniciar variables
        etxhistoria = view.findViewById(R.id.diag_ethistoria);
        etxedad = view.findViewById(R.id.diag_etedad);
        etxpeso = view.findViewById(R.id.diag_etpeso);
        etxtalla = view.findViewById(R.id.diag_ettalla);
        etxfecha = view.findViewById(R.id.diag_etfecha);
        etximc = view.findViewById(R.id.diag_etimc);
        etxresultado = view.findViewById(R.id.diag_etestado);
        swxdiagnostico = view.findViewById(R.id.diag_swdiagnostico);

        swxdiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swxdiagnostico.isChecked()){
                    swxdiagnostico.setText("SI");
                }else{
                    swxdiagnostico.setText("NO");
                }
            }
        });

        etxhistoria.setText(getArguments().getString("numhistoria"));
        etxedad.setText(getArguments().getString("edad"));
        etxpeso.setText(getArguments().getString("peso"));
        etxtalla.setText(getArguments().getString("talla"));
        etxfecha.setText(getArguments().getString("fecha"));
        etximc.setText(getArguments().getString("imc"));
        etxresultado.setText(getArguments().getString("estado"));
        if (getArguments().getString("diagnostico").equals("SI")){
            swxdiagnostico.setText("SI");
            swxdiagnostico.setChecked(true);

        }else{
            swxdiagnostico.setText("NO");
            swxdiagnostico.setChecked(false);
        }

        nsujeto = getArguments().getInt("numsujeto");
        //boton cancelar
        builder.setView(view).setTitle("Detalle de datos").setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //boton aceptar
        builder.setView(view).setTitle("Detalle de datos").setPositiveButton("Modificar" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String e = etxedad.getText().toString();
                String p =etxpeso.getText().toString();
                String t = etxtalla.getText().toString();
                String f = etxfecha.getText().toString();

                String d = swxdiagnostico.getText().toString();
                String h =  etxhistoria.getText().toString();

                double talla = Double.parseDouble(t);
                double peso = Double.parseDouble(p);
                double imc = peso/(talla*talla);
                if (imc<18.5){
                    etximc.setText(imc+"");
                    etxresultado.setText("Bajo peso");
                }else if (imc>=18.5 && imc<=24.9){
                    etximc.setText(imc+"");
                    etxresultado.setText("Normal");
                }else if (imc>=25 && imc<=29.9){
                    etxresultado.setText("Sobrepeso");
                    etximc.setText(imc+"");
                }else if (imc>=30 && imc<=34.9){
                    etxresultado.setText("Obesidad I");
                    etximc.setText(imc+"");
                }
                else if (i>=35 && i<=39.9){
                    etxresultado.setText("Obesidad II");
                    etximc.setText(i+"");
                }
                else if (i>=40){
                    etxresultado.setText("Obesidad III");
                    etximc.setText(i+"");
                }

                double imc_corto = Double.parseDouble(etximc.getText().toString());
                String r = etxresultado.getText().toString();
                String im = String.format("%.2f",imc_corto);
                listener.enviardatos(nsujeto,e,p,t,f,im,r,d,h);

            }


    });
        return builder.show();
}
}
