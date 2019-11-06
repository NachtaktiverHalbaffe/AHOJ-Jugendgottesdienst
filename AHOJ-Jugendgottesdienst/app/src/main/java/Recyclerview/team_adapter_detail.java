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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import Fragments.Team_Details;
import Json.ListItems_Team;
import Json.MySingleton;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class team_adapter_detail extends RecyclerView.Adapter<team_adapter_detail.Team_ListViewHolder_detail> {
    private List<ListItems_Team> team_list;
    private Context mContext;
    private ImageLoader mImageLoader;
    private int focusedItem=0;
    private Team_ListViewHolder_detail team_listviewholder;
    private int position;

    public static class Team_ListViewHolder_detail extends RecyclerView.ViewHolder {

        static TextView name;
        static TextView hauptfunktion;
        static TextView alter;
        static TextView wohnort;
        static TextView ahojverständnis;
        static TextView motivation;
        static NetworkImageView profilpic;


        public Team_ListViewHolder_detail(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            profilpic = (NetworkImageView) itemView.findViewById((R.id.team_profilpic));
            name = (TextView) itemView.findViewById((R.id.team_name));
            hauptfunktion = (TextView) itemView.findViewById((R.id.team_hauptfunktion));
            alter= (TextView)itemView.findViewById(R.id.team_alter);
            wohnort=(TextView) itemView.findViewById(R.id.team_wohnort);
            ahojverständnis=(TextView) itemView.findViewById(R.id.team_ahojverständnis);
            motivation=(TextView)itemView.findViewById(R.id.team_motivation);
            itemView.setClickable(true);
        }
    }

    public team_adapter_detail(Context context, List<ListItems_Team> team_list){
        this.team_list= team_list;
        this. mContext =context;
    }

    @Override
    public Team_ListViewHolder_detail onCreateViewHolder(ViewGroup viewgroup, int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_team_details,null);
        Team_ListViewHolder_detail holder = new Team_ListViewHolder_detail(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(Team_ListViewHolder_detail team_listviewholder, int position) {
        this.team_listviewholder = team_listviewholder;
        this.position = position;
        ListItems_Team listitems = team_list.get(position);
        team_listviewholder.itemView.setSelected((focusedItem == position));
        team_listviewholder.getLayoutPosition();

        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
        team_listviewholder.profilpic.setImageUrl(listitems.getProfilpic(),mImageLoader);
        team_listviewholder.profilpic.setDefaultImageResId(R.drawable.profilpic_placeholder);
        team_listviewholder.name.setText(Html.fromHtml(listitems.getName()));
        team_listviewholder.hauptfunktion.setText(Html.fromHtml(listitems.getHauptfunktion()));
        team_listviewholder.alter.setText(Html.fromHtml(listitems.getAlter()));
        team_listviewholder.wohnort.setText(Html.fromHtml(listitems.getWohnort()));
        team_listviewholder.ahojverständnis.setText(Html.fromHtml(listitems.getAhojverständnis()));
        team_listviewholder.motivation.setText(Html.fromHtml(listitems.getMotivation()));
    }

    public void clearAdapter(){
        team_list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null !=team_list ? team_list.size() :0);
    }

}



