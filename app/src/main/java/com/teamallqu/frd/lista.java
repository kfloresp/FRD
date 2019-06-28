package com.teamallqu.frd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class lista extends Fragment implements dialog_modificar.idialog_modificar{
    ListView lst;
    ArrayList<c_ficha> lista_ficha;
    Button btnexportar_lista;
    conexionsql conn;
    TextView numitem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista, container, false);
        lst = v.findViewById(R.id.lista_ficha);
        conn = new conexionsql(getActivity(),"db",null,1);
        btnexportar_lista = v.findViewById(R.id.btnexportar);
        numitem = v.findViewById(R.id.nitem_lista);

        //POPUP MENU
        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(getActivity(), view);
                popup.getMenuInflater().inflate(R.menu.pop_option, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    //MENU FILA
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.plmodificar:
                                dialog_modificar dec=new dialog_modificar();
                                dec.show(getActivity().getSupportFragmentManager(),"dec");
                                dec.setTargetFragment(lista.this, 0);
                                Bundle args = new Bundle();
                                args.putInt("numsujeto", lista_ficha.get(position).getNumsujeto());
                                args.putString("numhistoria", lista_ficha.get(position).getNumhistoria());
                                args.putString("edad", lista_ficha.get(position).getEdad());
                                args.putString("talla", lista_ficha.get(position).getTalla());
                                args.putString("peso", lista_ficha.get(position).getPeso());
                                args.putString("imc", lista_ficha.get(position).getImc());
                                args.putString("estado", lista_ficha.get(position).getEstado());
                                args.putString("diagnostico", lista_ficha.get(position).getDiagnostico());
                                args.putString("fecha", lista_ficha.get(position).getFecha());
                                dec.setArguments(args);
                                break;
                            case R.id.pleliminar:
                                //eliminar de DB
                                eliminarfila(position);
                                consultarlista();
                                break;
                        }
                        return true;
                    }
                    //FIN MENU FILA
                });
                popup.show();
                return true;
            }
        });

        btnexportar_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
                String h = sdf.format(new Date());
                saveExcelFile(getActivity(),"Export-"+h.trim()+".xls",lista_ficha);
            }
        });
        consultarlista();
        return v;
    }

    private void eliminarfila(int position) {
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("DELETE FROM ficha WHERE "+utilidades.col_numsujeto+"="+lista_ficha.get(position).getNumsujeto());
        Toast.makeText(getActivity(), "Se elimino ficha.", Toast.LENGTH_LONG).show();
    }

    private void consultarlista() {
        SQLiteDatabase db = conn.getReadableDatabase();
        c_ficha ficha = null;
        lista_ficha = new ArrayList<c_ficha>();
        Cursor cursor = db.rawQuery("SELECT*FROM "+utilidades.TABLAFICHA,null);
        while (cursor.moveToNext()){
             ficha = new c_ficha();
             ficha.setNumsujeto(cursor.getInt(0));
             ficha.setNumhistoria(cursor.getString(1));
             ficha.setEdad(cursor.getString(2));
             ficha.setTalla(cursor.getString(3));
             ficha.setPeso(cursor.getString(4));
             ficha.setImc(cursor.getString(5));
             ficha.setEstado(cursor.getString(6));
             ficha.setDiagnostico(cursor.getString(7));
             ficha.setFecha(cursor.getString(8));
             lista_ficha.add(ficha);

        }
        if (lista_ficha.size()!=0){
            ad_lista adapter = new ad_lista(getActivity(),lista_ficha);
            lst.setAdapter(adapter);
            lst.setVisibility(View.VISIBLE);
            numitem.setText(lista_ficha.size()+ " Items encontrados");
        }else{
            numitem.setText("0 Items encontrados");
            Toast.makeText(getActivity(), "No se encontraron resultados.", Toast.LENGTH_LONG).show();
        }

    }
//---------------------------------------------------------------------------------------------------


    private static boolean saveExcelFile(Context context, String fileName, ArrayList<c_ficha> lista) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Toast.makeText(context, "Memoria no preparada, o solo lectura.", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("Export-"+sdf.format(new Date()));

        // Generate column headings
        Row row = sheet1.createRow(0);
        String [] columnas = {"N_SUJETO","N_HISTORIA","FECHA","EDAD","TALLA(MT)","PESO(KG)","IMC","ESTADO","HBP"};
        for (int i = 0; i<columnas.length;i++){
            c = row.createCell(i);
            c.setCellValue(columnas[i]);
            c.setCellStyle(cs);
    //columnas automaticas
        }

        int filaInicio = 1;
        for (int f = 0; f < lista.size(); f++) {
            Row fila = sheet1.createRow(filaInicio);
            filaInicio++;
            Cell celda0 = fila.createCell(0);
            Cell celda1 = fila.createCell(1);
            Cell celda2 = fila.createCell(2);
            Cell celda3 = fila.createCell(3);
            Cell celda4 = fila.createCell(4);
            Cell celda5 = fila.createCell(5);
            Cell celda6 = fila.createCell(6);
            Cell celda7 = fila.createCell(7);
            Cell celda8 = fila.createCell(8);
            celda0.setCellValue(String.valueOf(lista.get(f).getNumsujeto()));
            celda1.setCellValue(String.valueOf(lista.get(f).getNumhistoria()));
            celda2.setCellValue(String.valueOf(lista.get(f).getFecha()));
            celda3.setCellValue(String.valueOf(lista.get(f).getEdad()));
            celda4.setCellValue(String.valueOf(lista.get(f).getTalla()));
            celda5.setCellValue(String.valueOf(lista.get(f).getPeso()));
            celda6.setCellValue(String.valueOf(lista.get(f).getImc()));
            celda7.setCellValue(String.valueOf(lista.get(f).getEstado()));
            celda8.setCellValue(String.valueOf(lista.get(f).getDiagnostico()));

        }
        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (10 * 500));
        sheet1.setColumnWidth(2, (10 * 500));
        sheet1.setColumnWidth(3, (10 * 500));
        sheet1.setColumnWidth(4, (10 * 500));
        sheet1.setColumnWidth(5, (10 * 500));
        sheet1.setColumnWidth(6, (10 * 500));
        sheet1.setColumnWidth(7, (10 * 500));
        sheet1.setColumnWidth(8, (10 * 500));
        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Toast.makeText(context, "Excel generado", Toast.LENGTH_SHORT).show();
            success = true;
        } catch (IOException e) {
            Toast.makeText(context, "Ocurrio un problema", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Ocurrio un problema", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    @Override
    public void enviardatos(int sujeto,String edad,String peso,String talla,String fecha,String imc,String estado,String diagnostico,String numhistoria) {
        if (numhistoria.isEmpty()||edad.isEmpty()||
                talla.isEmpty()||peso.isEmpty()){
            Toast.makeText(getActivity(), "Completar todos los campos.", Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase db = conn.getWritableDatabase();
            db.execSQL("UPDATE "+utilidades.TABLAFICHA + " SET "
                    +utilidades.col_numhistoria+" = '"+numhistoria+"',"
                    + utilidades.col_edad+"='"+edad +"',"
                    + utilidades.col_peso+"='"+peso+"',"
                    + utilidades.col_talla+"='"+talla+"',"
                    + utilidades.col_fecha+"='"+fecha+"',"
                    + utilidades.col_imc+"='"+imc+"',"
                    + utilidades.col_estado+"='"+estado+"',"
                    + utilidades.col_diagnostico+"='"+diagnostico+"' WHERE "+utilidades.col_numsujeto+"="+sujeto+""
            );
            Toast.makeText(getActivity(), "Se guardaron cambios.", Toast.LENGTH_LONG).show();
            consultarlista();
    }
}
}

