package Fragments;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Json.ListItems_Team;
import Recyclerview.team_adapter;
import Recyclerview.team_adapter_detail;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class Team_Details extends Fragment {

    public static  final String TAG = "Meine Temliste";
    private List<ListItems_Team> team_database = new ArrayList<ListItems_Team>();

    private RecyclerView mRecyclerview;
    private team_adapter_detail Adapter;

    private int i = 0;
    private String count;
    private String jsonListe;
    private String after_id;
    private static final String server_url ="";                                                          //Server-Adresse Database
    private static final String database="Das Team";
    private static final String jsonEnd ="/.json";


    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){     //Fragment Preambel
        View rootView = inflater.inflate(R.layout.team, container, false);

        mRecyclerview =(RecyclerView)rootView.findViewById(R.id.team_card);                                //Recyclerview Darstellung definieren
        mRecyclerview.setHasFixedSize(true);

        final LinearLayoutManager team_llm = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(team_llm);

        updateList(database);
        return rootView;
    }
    public void updateList(String Team){                                                               // Daten von Json holen
        Team = server_url + jsonEnd;
        Adapter= new team_adapter_detail(getActivity(), team_database);
        mRecyclerview.setAdapter(Adapter);


        RequestQueue queue = Volley.newRequestQueue(getActivity());

        Adapter.clearAdapter();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Team, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, response.toString());

                try {
                    JSONObject person = response.getJSONObject("data");
                    after_id = person.getString("after");
                    JSONArray Team = person.getJSONArray("Team");

                    while(i==0){                                                                      // Überprüfung, ob Daten die der angeklickten Cardview sind
                        JSONObject Data = Team.getJSONObject(i).getJSONObject("data");
                        ListItems_Team item = new ListItems_Team();
                        String name =item.setName(Data.getString("Name"));
                        String identifier= name; //team_adapter.getIntent().getExtras().getString("name");
                        if(Objects.equals(identifier, name)){

                            item.setProfilpic(Data.getString("Profilpic"));                           //Daten in Cardview einsetzten
                            item.setName(Data.getString("Name"));
                            item.setHauptfunktion(Data.getString("Hauptfunktion"));
                            item.setAlter(Data.getString("Alter"));
                            item.setWohnort(Data.getString("Wohnort"));
                            item.setAhojverständnis(Data.getString("Ahojverständnis"));
                            item.setMotivation("Motivation");
                            team_database.add(item);
                            i=i++;}
                        else i=0;}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG, "Error" + error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }


}
