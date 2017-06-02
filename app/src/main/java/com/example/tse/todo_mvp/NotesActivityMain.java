package com.example.tse.todo_mvp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

public class NotesActivityMain extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private String CLASS_NAME = NotesActivityMain.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);


        // Configuracion del toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Configuracion del Navigation Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null){
            setupDrawerContent(navigationView);
        }

        if (null == savedInstanceState){
            Log.i(CLASS_NAME, "MOSTRAR FRAGMENT NOTAS");
            initFragment(NotesFragment.newInstance());
        }

    }


    private void setupDrawerContent(NavigationView navigationView){
        // Agrego un listener a la seleccion de un item del menu del Drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.statistics_navigation_menu_item:
                        //startActivity(new Intent(NotesActivityMain.this, StatisticsActivity.class));

                        break;
                    default:
                        break;
                }

                // Cierro la navegacion del Drawer cuando un item es seleccionado
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }



    // Agregar el NotesFragment al layout activity_notes_main
    private void initFragment(Fragment notesFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // Con esta linea de estoy diciendo que inicie la transaccion de agregar al elemento que esta definido en
        // el activity_notes_main en el FrameLayout el diseño de fragment que estoy pasando por medio de notesFragment
        transaction.add(R.id.contentFrame, notesFragment);
        transaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
            // Abrir el Navigation Drawer cuando el icono de Home es seleccionado de el toolbar
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
