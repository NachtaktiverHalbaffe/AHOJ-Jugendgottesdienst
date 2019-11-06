package Recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import Json.Person;
import fragments.Team_Details;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class team_adapter extends RecyclerView.Adapter<team_adapter.Team_ListViewHolder> {
    private List<Person> team_list;
    private Context mContext;

    static class Team_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView name;
        static TextView hauptfunktion;
        static ImageView profilpic;
        static RelativeLayout card;

        Team_ListViewHolder(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            profilpic = itemView.findViewById((R.id.team_profilpic));
            name = itemView.findViewById((R.id.team_name));
            hauptfunktion = itemView.findViewById((R.id.team_hauptfunktion));
            card = itemView.findViewById(R.id.card_team);
            itemView.setClickable(true);
        }
    }

    public team_adapter(Context context,List<Person> team_list){
        this.team_list= team_list;
        this.mContext=context;
    }

    @Override
    public Team_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_team,null);
        Team_ListViewHolder holder = new Team_ListViewHolder(v);

       Team_ListViewHolder.card.setOnClickListener(new View.OnClickListener() {

            @Override
             public void onClick(View v) {
                TextView name = v.findViewById(R.id.team_name);
                String current_name = name.getText().toString();
                Intent intent = new Intent(mContext, Team_Details.class);
                intent.putExtra("name",current_name);
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(Team_ListViewHolder team_listviewholder, int position) {
        final Person person=this.team_list.get(position);
        Team_ListViewHolder.profilpic.setImageResource(person.profilpic);
        Team_ListViewHolder.name.setText(person.name);
        Team_ListViewHolder.hauptfunktion.setText(person.hauptfunktion);
    }

    @Override
    public int getItemCount() {return team_list.size();}

}


