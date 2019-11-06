package Recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import Json.Track;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Impulse;
import threads.BlurBuilder;
import threads.MediaPlayerService;


public class impulse_adapter extends RecyclerView.Adapter<impulse_adapter.Impulse_ListViewHolder> {
    private List<Track> impulse_list;
    private static Context mContext;
    private static double startTime = 0;
    private static Handler myHandler = new Handler();
    private static final String CLIENT_ID = "uLPvyQXKNlBGBKxWKyqkCFxwr1dJtpGz";
    public static MediaSession Session;
    public static MediaController Controller;
    private int Position;
    private String cover_url;
    private static Bitmap bitmap;


static class Impulse_ListViewHolder extends RecyclerView.ViewHolder {

    static TextView card_laenge, card_Titel, beschreibung, date;
    static ImageView card_cover;
    static RelativeLayout card;

    //layout_elemente initialisieren
    Impulse_ListViewHolder(View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.card_impulse);
        card.setClickable(true);
        card_Titel = itemView.findViewById(R.id.card_Titel);
        card_laenge = itemView.findViewById(R.id.card_laenge);
        beschreibung = itemView.findViewById(R.id.card_description);
        date= itemView.findViewById(R.id.card_date);
        card_cover= itemView.findViewById(R.id.card_cover);
    }
}

    //adapter-Klasse erstellen
    public impulse_adapter(Context context, List<Track> impulse_list){
        this.impulse_list= impulse_list;
        mContext=context;
    }
    //Content adaptieren
    @Override
    public impulse_adapter.Impulse_ListViewHolder onCreateViewHolder(final ViewGroup viewgroup, final int position) {
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_impulse, null);
        final impulse_adapter.Impulse_ListViewHolder holder = new impulse_adapter.Impulse_ListViewHolder(v);
        Impulse_ListViewHolder.card.setOnClickListener(new View.OnClickListener() {
            //Element als Button
            @Override
            public void onClick(final View v) {
                //Mediaplayer variabeln neu setzen
                final Track track= impulse_list.get(position);
                Position=position;
                cover_url=track.getArtworkURL();
                Impulse.laenge.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(track.getDuration()), TimeUnit.MILLISECONDS.toSeconds(track.getDuration()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(track.getDuration()))));
                Picasso.with(mContext).load(track.getArtworkURL()).placeholder(R.mipmap.ic_launcher).into(Impulse.cover);
                BitmapDrawable drawable = (BitmapDrawable) Impulse.cover.getDrawable();
                bitmap = drawable.getBitmap();
                Bitmap blurredBitmap = BlurBuilder.blur( mContext,impulse_adapter.bitmap);
                Impulse.cover.setImageBitmap(blurredBitmap);
                Impulse.titel.setText(track.getTitle());
                Impulse.mediaPlayer.stop();
                Impulse.mediaPlayer.reset();

                //Sounddateien laden
                try {
                    Impulse.mediaPlayer.setDataSource(track.getStreamURL() + "?client_id=" + CLIENT_ID);
                    Impulse.mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Error: Bitte überprüfe deine Internetverbindung", Toast.LENGTH_LONG).show();
                }
                Impulse.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        Impulse.mediaPlayer.start();
                        Intent intent = new Intent(mContext, MediaPlayerService.class );
                        intent.putExtra("Titel",track.getTitle());
                        intent.setAction( MediaPlayerService.ACTION_PLAY );
                        mContext.startService(intent);
                        Impulse.mediaplayercontrol.setImageResource(R.drawable.ic_pause_circle_filled_white_48dp);
                        Impulse.seekbar.setMax(Impulse.mediaPlayer.getDuration());
                        Impulse.seekbar.setProgress((int) startTime);
                        myHandler.postDelayed(UpdateSongTime, 100);

                    }
                });
            }
        });
        return holder;
    }




    //Daten für Variablen in enzelnen Elementen setzen
    @Override
    public void onBindViewHolder(impulse_adapter.Impulse_ListViewHolder impulse_listviewholder, int position) {
        final Track impuls = this.impulse_list.get(position);
        Impulse_ListViewHolder.card_Titel.setText(impuls.getTitle());
        Impulse_ListViewHolder.beschreibung.setText(impuls.getDescription());
        Impulse_ListViewHolder.card_laenge.setText(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(impuls.getDuration()), TimeUnit.MILLISECONDS.toSeconds(impuls.getDuration()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(impuls.getDuration()))));
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss Z");
        DateTime jodatime = dtf.parseDateTime(impuls.getDate());
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd.MM.yyyy");
        String date= dtfOut.print(jodatime);
        Impulse_ListViewHolder.date.setText(date);
        Picasso.with(mContext).load(impuls.getArtworkURL()).placeholder(R.mipmap.ic_launcher).into(Impulse_ListViewHolder.card_cover);
    }

    //Listengröße erfassen
    @Override
    public int getItemCount() {return impulse_list.size();}
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    //Funktion, um Seekbar und aktuelle Songzeit zu aktualisieren
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = Impulse.mediaPlayer.getCurrentPosition();
            Impulse.aktuelle_zeit.setText(String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            Impulse.seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
            if(Impulse.mediaPlayer.isPlaying()) {Impulse.mediaplayercontrol.setImageResource(R.drawable.ic_pause_circle_filled_white_48dp);}
            else {Impulse.mediaplayercontrol.setImageResource(R.drawable.ic_play_circle_filled_white_48dp);}
        }
    };
}