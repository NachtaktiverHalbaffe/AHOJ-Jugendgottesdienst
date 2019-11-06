package Recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.util.List;
import Json.Predigt;
import Json.Video;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Impulse;
import threads.play_video;

import static Recyclerview.predigten_adapter.Predigten_ListViewHolder.thumbnail;


public class predigten_adapter extends RecyclerView.Adapter<predigten_adapter.Predigten_ListViewHolder> {
    private List<Video> predigten_list;
    private Context mContext;


    static class Predigten_ListViewHolder extends RecyclerView.ViewHolder {

        static TextView Titel;
        public static YouTubeThumbnailView thumbnail;
        static RelativeLayout card;

        Predigten_ListViewHolder(View itemView){                                                     //Variablen für cards setzen
            super(itemView);
            Titel = itemView.findViewById(R.id.predigten_titel);
            thumbnail = itemView.findViewById((R.id.predigten_thumbnail));
            card = itemView.findViewById(R.id.card_predigten);
            itemView.setClickable(true);
        }
    }

    public predigten_adapter(Context context,List<Video> predigten_list){
        this.predigten_list= predigten_list;
        this.mContext=context;
    }

    @Override
    public predigten_adapter.Predigten_ListViewHolder onCreateViewHolder(ViewGroup viewgroup, final int position){                  //Content adaptieren
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_predigten,null);
        predigten_adapter.Predigten_ListViewHolder holder = new predigten_adapter.Predigten_ListViewHolder(v);
        final Video video=this.predigten_list.get(position);
        Predigten_ListViewHolder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = video.getID();
                Intent intent = new Intent(mContext, play_video.class);
                intent.putExtra("url",url);
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
    public void onBindViewHolder(predigten_adapter.Predigten_ListViewHolder predigten_listviewholder, int position) {
        final Video video=this.predigten_list.get(position);
        thumbnail.initialize("AIzaSyAsLOsF0cPWjDaKuYX8gQb2pLySZ5ypo54", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(video.getID());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        predigten_adapter.Predigten_ListViewHolder.Titel.setText(video.getTitle());
        Picasso.with(mContext).load(video.getArtworkURL()).into(thumbnail);
    }


    @Override
    public int getItemCount() {return predigten_list.size();}

}


