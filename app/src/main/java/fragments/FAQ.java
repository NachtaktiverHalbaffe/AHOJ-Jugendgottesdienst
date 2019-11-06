package fragments;

import android.support.v4.app.Fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.faq, container, false);

        //Interne Database
        faq_frage = new ArrayList<>();
        faq_antwort= new ArrayList<>();

        faq_frage.addAll(Arrays.asList(
                "Was ist AHOJ?",
                "Wann findet der AHOJ immer statt?",
                "Wo findet der AHOJ statt?",
                "Was ist der AHOJ To Go?",
                "Was ist unser Wunsch für den AHOJ?"));
        faq_antwort.addAll(Arrays.asList(
                "Der AHOJ ist ein Jugendgottesdienst, der von einem großen Team aus Ehrenamtlichen und Hauptamtlichen des evang. Jugendwerk Bezirk Esslingen und dem CVJM Esslingen organisiert wird. Den AHOJ gibt es in Esslingen, seit 2011 und ist ein Angebot für alle Jugendliche ab dem Konfirmandenalter.",
                "Der AHOJ findet in der Regel, an jedem dritten Sonntag im Monat um 18 Uhr statt.",
                "In der Johanneskirche in Esslingen (Direkt hinter der Bushaltestelle Charlottenplatz, Neckarstraße 82/1).",
                "Den AHOJ To Go gibt es seit 2015. Dies ist die Möglichkeit für die Gemeinden aus dem Kirchenbezirk Esslingen einen AHOJ auszutragen. Die AHOJ To Go finden unregelmäßig und außerhalb der normalen AHOJ-Jugendgottesdienste statt.",
                "Wir wollen Jugendlichen aus Esslingen Gottes Gute Nachricht weitergeben. Deshalb möchten wir junge, dynamische Gottesdienste feiern."));

        //Recyclerview aufsetzen
        faq_list = rootView.findViewById(R.id.faq_card);
        faq_list.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity());
        faq_list.setLayoutManager(llm);
        //Recyclerview Adapter zuweisen
        adapter=  new faq_adapter();
        faq_list.setAdapter(adapter);

    return rootView;
}
}
