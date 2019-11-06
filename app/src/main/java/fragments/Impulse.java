package fragments;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import Json.SCService;
import Json.SoundCloud;
import Json.Track;
import Recyclerview.impulse_adapter;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Impulse extends Fragment {

    static public List<Track> mListItems;
    static public TextView titel, aktuelle_zeit, laenge;
    public ImageView forward;
    public ImageView back;
    public static ImageView mediaplayercontrol;
    public static ImageView cover;
    public static SeekBar seekbar;
    private double startTime = 0;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private impulse_adapter Adapter;
    public static final String CLIENT_ID = "uLPvyQXKNlBGBKxWKyqkCFxwr1dJtpGz";
    public static MediaPlayer mediaPlayer = new MediaPlayer();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   //Fragment Preambel
        View rootView = inflater.inflate(R.layout.impulse, container, false);

        //layout initialisieren
        mediaplayercontrol = rootView.findViewById(R.id.ic_mediaplayercontrol);
        forward = rootView.findViewById(R.id.ic_forward);
        back = rootView.findViewById(R.id.ic_back);
        titel = rootView.findViewById(R.id.Titel);
        aktuelle_zeit = rootView.findViewById(R.id.aktuelle_zeit);
        laenge = rootView.findViewById(R.id.laenge);
        seekbar = rootView.findViewById(R.id.seekbar);
        cover = rootView.findViewById(R.id.cover);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //Mit Seekbar in der Sounddatei rumskippen
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    mediaPlayer.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        //Start/Pause-Button
        mediaplayercontrol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                togglePlayPause();
            }
        });
        //vorwärts-button
        forward.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                startTime = mediaPlayer.getCurrentPosition() + forwardTime;
                mediaPlayer.seekTo((int) startTime);
            }
        });
        //rückwärts-button
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                startTime = mediaPlayer.getCurrentPosition() - backwardTime;
                mediaPlayer.seekTo((int) startTime);
            }
        });

        if(mediaPlayer.isPlaying()) {mediaplayercontrol.setImageResource(R.drawable.ic_play_circle_filled_white_48dp);}
        if(!mediaPlayer.isPlaying()) {mediaplayercontrol.setImageResource(R.drawable.ic_pause_circle_filled_white_48dp);}

        //Soundcloud Dateien laden
        mListItems = new ArrayList<>();
        final SCService scService = SoundCloud.getService();
        scService.getRecentTracks("last_week").enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (response.isSuccessful()) {
                    List<Track> tracks = response.body();
                    loadTracks(tracks);
                }
            }@Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                showMessage("Netzwerkfehler: " +  t.getMessage());
            }
        });

        //Recyclerview Darstellung definieren
        RecyclerView mRecyclerview = rootView.findViewById(R.id.impulse_card);
        final LinearLayoutManager impulse_llm = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(impulse_llm);
        Adapter= new impulse_adapter(getActivity(),mListItems);
        mRecyclerview.setAdapter(Adapter);
        return rootView;
    }

    //Soundcloud Soundateien zum Gerät hinzufügen
    private void loadTracks(List<Track> tracks) {
        mListItems.clear();
        mListItems.addAll(tracks);
        Adapter.notifyDataSetChanged();
    }
    //Toast zeigen
    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
    //mediaplayer pusieren oder fortsetzen
    private void togglePlayPause() {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaplayercontrol.setImageResource(R.drawable.ic_play_circle_filled_white_48dp);
        } else {
            mediaPlayer.start();
            mediaplayercontrol.setImageResource(R.drawable.ic_pause_circle_filled_white_48dp);
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}




