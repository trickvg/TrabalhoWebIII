package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.fragmentos.ListaCardFragment;
import ifrs.pw3.trabalhowebiii.fragmentos.SobreFragment;
import com.google.firebase.auth.FirebaseAuth;

public class PaginaInicialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setIcon(R.mipmap.ic_launcher);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListaCardFragment fragment = new ListaCardFragment();
        transaction.replace(R.id.fragment_paginaInicial, fragment);
        transaction.commit();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pagina_inicial, menu);

        MenuItem searchItem = menu.findItem(R.id.action_pesquisa);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_login) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_sair) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        if (id == R.id.action_sobre) {
//            Intent intent = new Intent(getApplicationContext(), PaginaSobreActivity.class);
////            startActivity(intent);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SobreFragment fragment = new SobreFragment();
            transaction.replace(R.id.fragment_paginaInicial, fragment);
            transaction.commit();
        }

        if (id == R.id.action_pesquisa) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        PaginaLoginActivity paginaLoginActivity = new PaginaLoginActivity();
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        Menu menu = navigationView.getMenu();
//        MenuItem itemUm = menu.findItem(R.id.nav_cadastro);
//
//
//        if (!paginaLoginActivity.usuarioLogado()) {
//            itemUm.setVisible(false);
//        }

        if (id == R.id.nav_pagina_inicial) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ListaCardFragment fragment = new ListaCardFragment();
            transaction.replace(R.id.fragment_paginaInicial, fragment);
            transaction.commit();

        } else if (id == R.id.nav_add_user) {
            Intent intent = new Intent(getApplicationContext(), CadastrarUsuarioActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_add_eventos) {
            Intent intent = new Intent(getApplicationContext(), PaginaCadastroEventoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_card_list) {
            Intent intent = new Intent(getApplicationContext(), ListarEventoRecycler.class);
            startActivity(intent);

//        } else if (id == R.id.nav_listEventos) {
//            Intent intent = new Intent(getApplicationContext(), CadastrarUsuarioActivity.class);
//            startActivity(intent);

        } else if (id == R.id.nav_send) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        @Override
        public boolean onQueryTextSubmit(String s) {
            Log.d("MSG", "query s => " + s);
//        Intent intent = new Intent(PaginaInicialActivity.this, SearchActivity.class);
            Intent intent = new Intent(PaginaInicialActivity.this, SearchActivity.class);
            intent.putExtra("pesquisa", s);
            startActivity(intent);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            Log.d("MSG", "change s => " + s);
            return false;
        }
    }
