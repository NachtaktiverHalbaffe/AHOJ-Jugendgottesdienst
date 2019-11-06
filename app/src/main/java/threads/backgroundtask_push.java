package threads;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import Json.Veranstaltung;
import de.eje_esslingen.ahoj_jugendgottesdienst.Drawer;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Veranstaltungen;

public class backgroundtask_push extends Service {

    public Notification.Builder notification;
    static public List<Veranstaltung> veranstaltung;
    public NotificationManager nm1;
    public Intent in1;
    public PendingIntent pi1;
    public String date;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //intern Datenbank anlegen

        veranstaltung=new ArrayList<>();
        veranstaltung.add(new Veranstaltung(R.drawable.ahoj_mai_2017, "AHOJ-Jugendgottesdienst", "Who Am I?", "21.05.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltung.add(new Veranstaltung(R.drawable.ahoj_juli_2017, "AHOJ-Jugendgottesdienst", "Generation Maybe", "16.07.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltung.add(new Veranstaltung(R.drawable.ahoj_oktober_2017, "AHOJ-Jugendgottesdienst", "Zweifels!Frei?", "15.10.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltung.add(new Veranstaltung(R.drawable.ahoj_november_2017, "AHOJ-Jugendgottesdienst", "Sorry - Vergeben und Vergessen", "19.11.2017", "Neckarstraße 82/1, Esslingen"));
        veranstaltung.add(new Veranstaltung(R.drawable.ahoj_dezember_2017, "AHOJ-Jugendgottesdienst", "Zielort: Zuhause", "17.12.2017", "Roßmarkt 9"));

        //Tage bis zur Veranstaltung ausrechnen
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        for (int c = 0; c <veranstaltung.size(); c++) {
        DateTime d1= DateTime.now();
            DateTime d2= formatter.parseDateTime(veranstaltung.get(c).datum);
            int differenz =Days.daysBetween(d1,d2).getDays();
            //Notification losschicken falls Veranstaltung bevorsteht
            if(differenz<2 &&differenz>-1 ) {
            in1 = new Intent(this, Drawer.class);
            in1.putExtra("menuFragment", "Veranstaltung");
            pi1 = PendingIntent.getActivity(getApplicationContext(), 0, in1, 0);
            notification = new Notification.Builder(backgroundtask_push.this);
            nm1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notification.setContentTitle(veranstaltung.get(c).veranstaltung +" am Sonntag");
            notification.setContentText(veranstaltung.get(c).datum);
            notification.setSmallIcon(R.drawable.ic_launcher_not);
            notification.setAutoCancel(true);
            notification.setContentIntent(pi1);
            nm1.notify(24, notification.build());
        }}
        stopSelf();
        return super.onStartCommand(intent,flags,startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
