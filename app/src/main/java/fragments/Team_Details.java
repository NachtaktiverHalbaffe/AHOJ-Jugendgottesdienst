package fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import Json.Person;
import de.eje_esslingen.ahoj_jugendgottesdienst.Drawer;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;


public class Team_Details extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    static public List<Person> team_database;
    private int i = 0;
    public  String Current_name="name";
    private static final String server_url ="https://spreadsheets.google.com/tq?key=";                                                          //Server-Adresse Database
    private static final String database="Das Team";
    private static final String database_key ="1TrGfgnp8IVYuEpdUqQl-fNFP3p3bErIF0ldGBYabCjE";
    ImageView profilpic;
    TextView name,hauptfunktion,alter,wohnort,mitarbeit_seit,ahojverständnis,motivation;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Layout initialisieren
        team_database = Team.team_database;
        profilpic = (ImageView) findViewById((R.id.team_profilpic_detail));
        name = (TextView) findViewById((R.id.team_name_detail));
        hauptfunktion = (TextView) findViewById((R.id.team_hauptfunktion_detail));
        alter =(TextView) findViewById(R.id.team_alter);
        wohnort=(TextView)findViewById(R.id.team_wohnort);
        mitarbeit_seit=(TextView)findViewById(R.id.team_mitarbeitszeit);
        ahojverständnis=(TextView)findViewById(R.id.team_ahojverständnis);
        motivation=(TextView)findViewById(R.id.team_motivation);

        //Richtige Person aus Datenbak rausnehmen
        Intent intent = getIntent();
        Current_name = intent.getStringExtra(Current_name);
        while (i != 1)
            for (int c = 0; c < team_database.size(); c++)

                    //Daten in layout einsetzen
                    if (Objects.equals(Current_name, team_database.get(c).name)) {
                        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                        DateTime d1= DateTime.now();
                        DateTime d2 = formatter.parseDateTime(team_database.get(c).alter);
                        Period period = new Period(d2, d1, PeriodType.yearMonthDay());
                        int age =period.getYears();
                        DateTime d3 = formatter.parseDateTime(team_database.get(c).mitarbeit_seit);
                        Period period2 = new Period(d3, d1, PeriodType.yearMonthDay());
                        int mitarbeitszeit =period2.getYears();
                        profilpic.setImageResource(team_database.get(c).profilpic);
                        name.setText(team_database.get(c).name);
                        hauptfunktion.setText(team_database.get(c).hauptfunktion);
                        alter.setText(String.valueOf(age));
                        wohnort.setText(team_database.get(c).wohnort);
                        mitarbeit_seit.setText(String.valueOf(mitarbeitszeit)+" Jahre");
                        ahojverständnis.setText(team_database.get(c).ahojverständnis);
                        motivation.setText(team_database.get(c).motivation);
                        i++;
                    }
    }
        //updateList(database);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }
    //Toolbar einstellungsbutton
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Drawer.class);
        myIntent.putExtra("menuFragment","Team");
        startActivityForResult(myIntent, 0);
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    Intent Intent = new Intent(getApplicationContext(), Drawer.class);
                    Intent.putExtra("menuFragment","Impressum");
                    startActivityForResult(Intent, 0);

                }
                else if (id == R.id.action_einstellungen) {
                    Intent Intent = new Intent(getApplicationContext(), Drawer.class);
                    Intent.putExtra("menuFragment","Impressum");
                    startActivityForResult(Intent, 0);
                }
                return true;
        }




    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fn = getSupportFragmentManager();
        if (id == R.id.instagram) {
            fn.beginTransaction().replace(R.id.Fragment, new Home()).commit();
        } else if (id == R.id.faq) {
            fn.beginTransaction().replace(R.id.Fragment, new FAQ()).commit();
        } else if (id == R.id.team) {
            fn.beginTransaction().replace(R.id.Fragment, new Team()).commit();
        } else if (id == R.id.verantstaltungen)
            fn.beginTransaction().replace(R.id.Fragment, new Veranstaltungen()).commit();
            //  else if (id == R.id.fahrgemeinschaften) {
            //      fn.beginTransaction().replace(R.id.Fragment, new Fahrgemeinschaften()).commit();}
            //  else if (id == R.id.einstellungen) {
            //    fn.beginTransaction().replace(R.id.Fragment, new Einstellungen()).commit();}
        else if (id == R.id.impressum) {
            fn.beginTransaction().replace(R.id.Fragment, new Impressum()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
