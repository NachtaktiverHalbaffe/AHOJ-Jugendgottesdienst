package de.eje_esslingen.ahoj_jugendgottesdienst;                                                    //PreAmbel

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import Fragments.FAQ;
import Fragments.Home;
import Fragments.Impressum;
import Fragments.Team;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);                                      //Actionbar einrichten
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {                                                                    //Drawer öffnen oder schließen
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                                            //Optiondrawer
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager fn=getFragmentManager();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {                                                            //Aktionen für Optionen

        }else if (id ==R.id.action_impressum) {
            fn.beginTransaction().replace(R.id.Fragment, new Impressum()).commit();
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {                                         // Drawer Fragment auswählen

        int id = item.getItemId();
        FragmentManager fn = getFragmentManager();

        if (id == R.id.neuigkeiten) {

        } else if(id==R.id.instagram){
            fn.beginTransaction().replace(R.id.Fragment, new Home()).commit();
        } else if (id == R.id.faq) {
            fn.beginTransaction().replace(R.id.Fragment, new FAQ()).commit();
        } else if(id== R.id.team){
            fn.beginTransaction().replace(R.id.Fragment,new Team()).commit();
        } else if(id == R.id.verantstaltungen) {

        } else if(id==R.id.fahrgemeinschaften) {

        } else if (id == R.id.einstellungen) {

        } else if (id == R.id.impressum) {
            fn.beginTransaction().replace(R.id.Fragment,new Impressum()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
