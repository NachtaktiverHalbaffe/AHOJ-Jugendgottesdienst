package Recyclerview;                                                                                 //PreAmbel

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import Fragments.FAQ;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;


public class faq_adapter extends RecyclerView.Adapter<faq_adapter.Faq_ListViewHolder>{

    public static class Faq_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView frage;
        static TextView antwort;
        RelativeLayout card;

        public Faq_ListViewHolder(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            frage = (TextView) itemView.findViewById((R.id.faq_frage));
            antwort = (TextView) itemView.findViewById((R.id.faq_antwort));
            card = (RelativeLayout) itemView.findViewById((R.id.faq_card));
        }
    }

    @Override
    public Faq_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_faq,null);
        return new Faq_ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Faq_ListViewHolder faq_listViewHolder, int position ){               //Recyclerview Cards mit engültigen
                                                                                                      //Content füllen
        Faq_ListViewHolder.frage.setText(FAQ.faq_frage.get(position));
        Faq_ListViewHolder.antwort.setText(FAQ.faq_antwort.get(position));
    }

    @Override                                                                                         //Cardnumber feststellen und weitergeben
    public int getItemCount(){
    return FAQ.faq_frage.size();
    }
}
