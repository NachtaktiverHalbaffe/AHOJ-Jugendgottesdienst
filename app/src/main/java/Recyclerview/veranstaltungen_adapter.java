package Recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Veranstaltungen;

public class veranstaltungen_adapter extends RecyclerView.Adapter<veranstaltungen_adapter.Veranstaltungen_ListViewHolder> {

    static class Veranstaltungen_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView veranstaltung;
        static TextView motto;
        static TextView datum;
        static TextView ort;
        static ImageView plakat;


        Veranstaltungen_ListViewHolder(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            plakat = itemView.findViewById((R.id.plakat));
            veranstaltung = itemView.findViewById((R.id.veranstaltung_string));
            motto = itemView.findViewById((R.id.veranstaltung_motto));
            datum= itemView.findViewById(R.id.veranstaltung_datum);
            ort= itemView.findViewById(R.id.veranstaltung_ort);
        }
    }


    @Override
    public Veranstaltungen_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_veranstaltungen,null);
        Veranstaltungen_ListViewHolder holder = new  Veranstaltungen_ListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Veranstaltungen_ListViewHolder veranstaltungen_listviewholder, int position) {
        Veranstaltungen_ListViewHolder.plakat.setImageResource(Veranstaltungen.veranstaltungen.get(position).plakat);
        Veranstaltungen_ListViewHolder.veranstaltung.setText(Veranstaltungen.veranstaltungen.get(position).veranstaltung);
        Veranstaltungen_ListViewHolder.motto.setText(Veranstaltungen.veranstaltungen.get(position).motto);
        Veranstaltungen_ListViewHolder.datum.setText(Veranstaltungen.veranstaltungen.get(position).datum);
        Veranstaltungen_ListViewHolder.ort.setText(Veranstaltungen.veranstaltungen.get(position).ort);

    }

    @Override
    public int getItemCount() {
        return Veranstaltungen.veranstaltungen.size();
    }

}



