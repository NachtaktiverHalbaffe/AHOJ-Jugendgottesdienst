package Recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fragments.FAQ;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;


public class faq_adapter extends RecyclerView.Adapter<faq_adapter.Faq_ListViewHolder>{

    static class Faq_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView frage;
        static TextView antwort;
        RelativeLayout card;

        //Variablen für cards setzen
        Faq_ListViewHolder(View itemView){
            super(itemView);
            frage = itemView.findViewById((R.id.faq_frage));
            antwort = itemView.findViewById((R.id.faq_antwort));
            card = itemView.findViewById((R.id.faq_card));
        }
    }

    //Content adaptieren
    @Override
    public Faq_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, int position){
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_faq,null);
        return new Faq_ListViewHolder(v);
    }

    //Recyclerview Cards mit engültigen Content füllen
    @Override
    public void onBindViewHolder(Faq_ListViewHolder faq_listViewHolder, int position ){

        Faq_ListViewHolder.frage.setText(FAQ.faq_frage.get(position));
        Faq_ListViewHolder.antwort.setText(FAQ.faq_antwort.get(position));
    }

    //Cardnumber feststellen und weitergeben
    @Override
    public int getItemCount(){
    return FAQ.faq_frage.size();
    }
}
