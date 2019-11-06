package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import Json.Veranstaltung;
import Recyclerview.veranstaltungen_adapter;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;


public class Veranstaltungen extends Fragment {

    //Variabeln für Recyclerview
     public static List<Veranstaltung> veranstaltungen;
    //Konstanten für Database-URL
    private static final String server_url = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=";
    private static final String server_url_alternate = "https://script.google.com/macros/s/AKfycbxsIHUVon-jCHfWVg2N2EOwk6aGEVYH_YT1V6uFpWdULpoI5NI/exec?id=";
    private static final String database = "Veranstaltungen";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   //Fragment Preambel
        View rootView = inflater.inflate(R.layout.veranstaltungen, container, false);

        //intern Datenbank anlegen
        veranstaltungen = new ArrayList<>();
        veranstaltungen.add(new Veranstaltung(R.drawable.ahoj_mai_2017, "AHOJ-Jugendgottesdienst", "Who Am I?", "21.05.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltungen.add(new Veranstaltung(R.drawable.ahoj_juli_2017, "AHOJ-Jugendgottesdienst", "Generation Maybe", "16.07.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltungen.add(new Veranstaltung(R.drawable.ahoj_oktober_2017, "AHOJ-Jugendgottesdienst", "Zweifels!Frei?", "15.10.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltungen.add(new Veranstaltung(R.drawable.ahoj_november_2017, "AHOJ-Jugendgottesdienst", "Sorry - Vergeben und Vergessen", "19.11.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltungen.add(new Veranstaltung(R.drawable.ahoj_dezember_2017, "AHOJ-Jugendgottesdienst", "Zielort: Zuhause", "17.12.2017", "Roßmarkt 9, Esslingen"));

        //Recyclerview aufsetzen
        RecyclerView mRecyclerview = rootView.findViewById(R.id.veranstaltungen_card);
        mRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(llm);
        veranstaltungen_adapter adapter = new veranstaltungen_adapter();
        mRecyclerview.setAdapter(adapter);
        return rootView;
    }

}



