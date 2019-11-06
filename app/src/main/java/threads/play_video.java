package threads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import de.eje_esslingen.ahoj_jugendgottesdienst.R;

public class play_video extends YouTubeBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        YouTubePlayerView youTubePlayerView = findViewById(R.id.player);

        //YouTubevideo starten
        youTubePlayerView.initialize("AIzaSyAsLOsF0cPWjDaKuYX8gQb2pLySZ5ypo54", new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.setFullscreen(true);
                        youTubePlayer.loadVideo(url);
                    }
                    //Fehlermeldung
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(getApplicationContext(),"Error: Bitte überprüfe deine Internetverindung",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
