package Recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import Json.ListItems_Fahrgemeinschaften;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Team_Details;


public class fahrgemeinschaften_adapter extends RecyclerView.Adapter<fahrgemeinschaften_adapter.Fahrgemeinschaften_ListViewHolder> {
    private List<ListItems_Fahrgemeinschaften> fahrgemeinschaften_list;
    private Context mContext;
    private int focusedItem=0;
    private Fahrgemeinschaften_ListViewHolder fahrgemeinschaften_listviewholder;
    private int position;

    public static class Fahrgemeinschaften_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView Veranstaltung;
        static TextView Abholort;
        static TextView Freie_Plätze;
        static RelativeLayout card;

        public Fahrgemeinschaften_ListViewHolder(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            Veranstaltung = (TextView) itemView.findViewById((R.id.veranstaltung));
            Abholort = (TextView) itemView.findViewById((R.id.abholort));
            Freie_Plätze = (TextView) itemView.findViewById((R.id.freie_plätze));
            card = (RelativeLayout) itemView.findViewById(R.id.fahrgemeinschaften_card);
            itemView.setClickable(true);
        }
    }

    public fahrgemeinschaften_adapter(Context context, List<ListItems_Fahrgemeinschaften> fahrgemeinschaften_list){
        this.fahrgemeinschaften_list= fahrgemeinschaften_list;
        this. mContext =context;
    }

    @Override
    public Fahrgemeinschaften_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_team,null);
        Fahrgemeinschaften_ListViewHolder holder = new Fahrgemeinschaften_ListViewHolder(v);

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*String id = ListItems_Fahrgemeinschaften.getId.toString();

                Intent intent = new Intent(mContext, Team_Details.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
                */
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(Fahrgemeinschaften_ListViewHolder fahrgemeinschaften_listviewholder, int position) {
        this.fahrgemeinschaften_listviewholder = fahrgemeinschaften_listviewholder;
        this.position = position;
        ListItems_Fahrgemeinschaften listitems = fahrgemeinschaften_list.get(position);
        fahrgemeinschaften_listviewholder.itemView.setSelected((focusedItem == position));
        fahrgemeinschaften_listviewholder.getLayoutPosition();

        fahrgemeinschaften_listviewholder.Veranstaltung.setText(Html.fromHtml(listitems.getVeranstaltung()));
        fahrgemeinschaften_listviewholder.Abholort.setText(Html.fromHtml(listitems.getAbholort()));
        fahrgemeinschaften_listviewholder.Freie_Plätze.setText(Html.fromHtml(listitems.getFreie_plätze()));
    }

    public void clearAdapter(){
        fahrgemeinschaften_list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null !=fahrgemeinschaften_list ? fahrgemeinschaften_list.size() :0);
    }

}




