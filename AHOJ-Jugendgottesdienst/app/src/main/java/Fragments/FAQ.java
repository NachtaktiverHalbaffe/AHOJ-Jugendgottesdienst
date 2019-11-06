package Fragments;                                                                                       //PreAmbel

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Arrays;

import Recyclerview.faq_adapter;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class FAQ extends Fragment {

    RecyclerView faq_list;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager llm;

    static public ArrayList<String> faq_frage;
    static public ArrayList<String> faq_antwort;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){   //Fragment Preambel
        View rootView = inflater.inflate(R.layout.faq, container, false);

        faq_frage = new ArrayList<>();                                                                   //Interne Database
        faq_antwort= new ArrayList<>();

        faq_frage.addAll(Arrays.asList(
                "Was ist der AHOJ?",
                "Wann findet der AHOJ immer statt?",
                "Wo findet der AHOJ statt?",
                "Wer veranstaltet den AHOJ?",
                "Darf ich Schrottfragen stellen?",
                "Weil ich zu unkreativ bin.",
                "Magst du Bratwurst?",
                "Bist du Vegetarier?",
                "Sind das genug Fragen?",
                "Und, war das genug?"));
        faq_antwort.addAll(Arrays.asList(
                "Der AHOj ist ein Jugendgottesdienst für junge Menschen ab 14 Jahren.",
                "An jeden dritten Sonntag im Monat um 18 Uhr.",
                "In der Johanneskirche in Esslingen.",
                "Der AHOJ wird vom evangelischen Jugendwerk Bezirk Esslingen und dem CVJM Esslingen organisiert.",
                "Warum?",
                "Das ist ein gutes Argument.",
                "Fleeeiiiisssccchhh!",
                "Nur über meine Leiche!",
                "Das sehen wir gleich.",
                "Jap. Du kannst jetzt seelenruhig sterben gehen."));

        faq_list =(RecyclerView)rootView.findViewById(R.id.faq_card);                                //Recyclerview Darstellung definieren
        faq_list.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity());
        faq_list.setLayoutManager(llm);

        adapter=  new faq_adapter();                                                                 //Recyclerview Adapter zuweisen
        faq_list.setAdapter(adapter);

    return rootView;
}
}
