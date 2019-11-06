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

public class team_adapter extends RecyclerView.Adapter<team_adapter.Team_ListViewHolder> {
    private List<ListItems_Team> team_list;
    private Context mContext;
    private ImageLoader mImageLoader;
    private int focusedItem=0;
    private Team_ListViewHolder team_listviewholder;
    private int position;

    public static class Team_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView name;
        static TextView hauptfunktion;
        static NetworkImageView profilpic;
        static RelativeLayout card;

        public Team_ListViewHolder(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            profilpic = (NetworkImageView) itemView.findViewById((R.id.team_profilpic));
            name = (TextView) itemView.findViewById((R.id.team_name));
            hauptfunktion = (TextView) itemView.findViewById((R.id.team_hauptfunktion));
            card = (RelativeLayout) itemView.findViewById(R.id.team);
            itemView.setClickable(true);
        }
    }

    public team_adapter(Context context, List<ListItems_Team> team_list){
        this.team_list= team_list;
       this. mContext =context;
    }

    @Override
    public Team_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_team,null);
        Team_ListViewHolder holder = new Team_ListViewHolder(v);

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
             public void onClick(View v) {
                TextView name = (TextView) v.findViewById(R.id.team_name);
                String current_name = name.getText().toString();

                Intent intent = new Intent(mContext, Team_Details.class);
                intent.putExtra("name",current_name);
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(Team_ListViewHolder team_listviewholder, int position) {
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


