package com.teamallqu.frd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class principal extends AppCompatActivity {
    Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
Fragment selectfragment = null;
int indice=0;
Menu m;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportActionBar().setTitle("Ficha nueva");
                    selectfragment = new ficha();
                    indice=0;
                    break;
                case R.id.navigation_dashboard:
                    getSupportActionBar().setTitle("Lista de pacientes");
                    selectfragment = new lista();
                    indice=1;
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

        ConexionSQL conn=new ConexionSQL(this,"db",null,1);
    }

    /*INICIAR TOOLBAR*/
    private void initoolbar() {
        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ficha nueva");
    }

    /*ASIGNAR NAVIGATION A TOOLBAR*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save,menu);
        return true;
    }
    /*ASIGNAR FUNCIONES A BOTONES TOOLBAR*/
    public boolean onOptionsItemSelected(MenuItem menui){
        switch (menui.getItemId()){
            case android.R.id.home: finish();break;
        }
        return true;
    }
}
