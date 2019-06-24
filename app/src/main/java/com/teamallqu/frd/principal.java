package com.teamallqu.frd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class principal extends AppCompatActivity implements ficha.Guardar_ficha{
    Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
Fragment selectfragment = null;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportActionBar().setTitle("Ficha nueva");
                    selectfragment = new ficha();
                    save.setVisible(true);
                    export.setVisible(false);
                    
                    break;
                case R.id.navigation_dashboard:
                    getSupportActionBar().setTitle("Lista de pacientes");
                    selectfragment = new lista();
                    export.setVisible(true);
                    save.setVisible(false);

                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,selectfragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new ficha()).commit();
        initoolbar();


    }

    /*INICIAR TOOLBAR*/
    private void initoolbar() {
        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ficha nueva");
    }

    MenuItem save;
    MenuItem export;
    /*ASIGNAR NAVIGATION A TOOLBAR*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save,menu);
        save = menu.findItem(R.id.isavemenu);
        export = menu.findItem(R.id.iexportmenu);
        export.setVisible(false);
        return true;
    }
    /*ASIGNAR FUNCIONES A BOTONES TOOLBAR*/
    public boolean onOptionsItemSelected(MenuItem menui){
        switch (menui.getItemId()){
            case R.id.isavemenu:
                guardarficha();
                ;break;
            case R.id.iexportmenu:
                exportarlista();
                ;break;
        }
        return true;
    }

    private void exportarlista() {
    }

    private void guardarficha() {
        guardar();
    }

    @Override
    public void guardar() {
        Toast.makeText(this, "guardar", Toast.LENGTH_SHORT).show();
    }
}
