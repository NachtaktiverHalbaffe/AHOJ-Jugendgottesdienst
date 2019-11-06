package threads;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.IBinder;
import android.util.Log;
import Recyclerview.impulse_adapter;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Impulse;

public class MediaPlayerService extends Service{

    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_PAUSE = "action_pause";
    public static final String ACTION_REWIND = "action_rewind";
    public static final String ACTION_FAST_FORWARD = "action_fast_foward";
    public static final String ACTION_STOP = "action_stop";
    private MediaPlayer mMediaPlayer;
    private MediaSessionManager mManager;
    private MediaSession mSession ;
    private MediaController mController;
    public String titel;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void handleIntent( Intent intent ) {
        if( intent == null || intent.getAction() == null )
            return;

        String action = intent.getAction();
        titel = intent.getStringExtra("Titel");

        if( action.equalsIgnoreCase( ACTION_PLAY ) ) {
            mController.getTransportControls().play();
        } else if( action.equalsIgnoreCase( ACTION_PAUSE ) ) {
            mController.getTransportControls().pause();
        } else if( action.equalsIgnoreCase( ACTION_FAST_FORWARD ) ) {
            mController.getTransportControls().fastForward();
        } else if( action.equalsIgnoreCase( ACTION_REWIND ) ) {
            mController.getTransportControls().rewind();
        }else if( action.equalsIgnoreCase( ACTION_STOP ) ) {
            mController.getTransportControls().stop();
        }
    }

    private Notification.Action generateAction(int icon, String title, String intentAction ) {
        Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
        intent.setAction( intentAction );
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);
        return new Notification.Action.Builder( icon, title, pendingIntent ).build();
    }

    private void buildNotification( Notification.Action action ) {
        Notification.MediaStyle style = new Notification.MediaStyle();
        Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
        intent.setAction( ACTION_STOP );
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);
         Notification.Builder builder = new Notification.Builder( this )
                .setSmallIcon(R.drawable.ic_launcher_not)
                .setContentTitle(titel)
                .setContentText( "Kurzimpuls" )
                .setDeleteIntent( pendingIntent )
                .setStyle(style);

        builder.addAction( generateAction(R.drawable.ic_replay_5_black_24dp, "OnFastForward", ACTION_FAST_FORWARD ) );
        builder.addAction( generateAction(R.drawable.ic_forward_5_black_24dp, "Rewind", ACTION_REWIND ) );
        builder.addAction( action );
        style.setShowActionsInCompactView(0,1,2,3,4);

        NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        notificationManager.notify( 1, builder.build() );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if( mManager == null ) {
            initMediaSessions();
        }

        handleIntent( intent );
        return super.onStartCommand(intent, flags, startId);
    }

    private void initMediaSessions() {
        mMediaPlayer = Impulse.mediaPlayer;
        mSession = impulse_adapter.Session;
        mSession = new MediaSession(getApplicationContext(), "simple player session");
        mController = impulse_adapter.Controller;
        mController =new MediaController(getApplicationContext(), mSession.getSessionToken());

        mSession.setCallback(new MediaSession.Callback(){

                                @Override
                                 public void onPlay() {
                                     super.onPlay();
                                     Log.e( "MediaPlayerService", "onPlay");
                                     buildNotification( generateAction( R.drawable.ic_pause_black_24dp, "Pause", ACTION_PAUSE ) );
                                    mMediaPlayer.start();

                                 }

                                 @Override
                                 public void onPause() {
                                     super.onPause();
                                     Log.e( "MediaPlayerService", "onPause");
                                     mMediaPlayer.pause();
                                     buildNotification(generateAction(R.drawable.ic_play_arrow_black_24dp, "Play", ACTION_PLAY));
                                 }

                                 @Override
                                 public void onSkipToNext() {
                                 super.onSkipToNext();
                                 Log.e( "MediaPlayerService", "onSkipToNext");
                                 int startTime = mMediaPlayer.getCurrentPosition()- 5000;
                                 mMediaPlayer.seekTo(startTime);
                                 }

                                 @Override
                                 public void onFastForward() {
                                     super.onFastForward();
                                     Log.e( "MediaPlayerService", "onFastForward");
                                     int startTime = mMediaPlayer.getCurrentPosition()- 5000;
                                     mMediaPlayer.seekTo(startTime);
                                 }

                                 @Override
                                 public void onRewind() {
                                     super.onRewind();
                                     Log.e( "MediaPlayerService", "onRewind");
                                     int startTime = mMediaPlayer.getCurrentPosition()+5000;
                                     mMediaPlayer.seekTo(startTime);
                                 }

                                 @Override
                                 public void onSkipToPrevious(){
                                     super.onSkipToPrevious();
                                     Log.e( "MediaPlayerService", "onSkipToPrevious");
                                 }

                                 @Override
                                 public void onStop() {
                                     super.onStop();
                                     Log.e( "MediaPlayerService", "onStop");
                                     NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                     notificationManager.cancel( 1 );
                                     Impulse.mediaPlayer.pause();
                                     Intent intent = new Intent( getApplicationContext(), MediaPlayerService.class );
                                     stopService(intent);
                                 }


                             }
        );
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mSession.release();
        return super.onUnbind(intent);
    }
}