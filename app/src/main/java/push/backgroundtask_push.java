package push;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import Json.ListItems_Veranstaltungen;
import de.eje_esslingen.ahoj_jugendgottesdienst.Drawer;
import de.eje_esslingen.ahoj_jugendgottesdienst.R;
import fragments.Veranstaltungen;

public class backgroundtask_push extends Service {

    public Notification.Builder notification;
    public NotificationManager nm1;
    public Intent in1;
    public PendingIntent pi1;
    public String date;
    public int day,month,year;
    public List<ListItems_Veranstaltungen> veranstaltungen;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        /*try {                                                                            //Array definieren
            JSONArray rows = object.getJSONArray("Tabellenblatt1");

            for (int r = 0; r < rows.length(); r++) {
                JSONObject row = rows.getJSONObject(r).getJSONObject("data");                                  //Databse mit Daten aus Array bestücken
                ListItems_Veranstaltungen item = new ListItems_Veranstaltungen();
                item.setPlakat(row.optString("Plakat"));
                item.setVeranstaltungen(row.optString("Veranstaltung"));
                item.setMotto(row.optString("Motto"));
                item.setDatum(row.optString("Datum"));
                item.setOrt(row.optString("Ort"));
                veranstaltungen.add(item);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                DateTime d1= DateTime.now();
                DateTime d2 = formatter.parseDateTime(item.getDatum());
                int differenz = Days.daysBetween(d1, d2).getDays();

                if(differenz<3) {
                in1 = new Intent(this, Veranstaltungen.class);
                pi1 = PendingIntent.getActivity(getApplicationContext(), 0, in1, 0);
                notification = new Notification.Builder(backgroundtask_push.this);
                nm1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notification.setContentTitle("Anstehende Veranstaltung");
                notification.setContentText(item.getVeranstaltungen());
                notification.setSmallIcon(R.drawable.ic_today_black_24dp);
                notification.setAutoCancel(true);
                notification.setContentIntent(pi1);
                nm1.notify(24,notification.build());
                }
        }

        } catch (JSONException e) {
            e.printStackTrace();
        } */
        DateTime d1= DateTime.now();
        date =d1.toString();
       // DateTime d2= new DateTime(day,month,year,0,0,0,0);
       // int differenz = Days.daysBetween(d1, d2).getDays();

        //if(differenz<2) {
            in1 = new Intent(this, Drawer.class);
            in1.putExtra("menuFragment", "Veranstaltung");
            pi1 = PendingIntent.getActivity(getApplicationContext(), 0, in1, 0);
            notification = new Notification.Builder(backgroundtask_push.this);
            nm1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notification.setContentTitle("Anstehende Veranstaltung");
            notification.setContentText("Anklickbare Testnachricht");
            notification.setSmallIcon(R.drawable.ic_today_black_24dp);
            notification.setAutoCancel(true);
            notification.setContentIntent(pi1);
            nm1.notify(24, notification.build());
        //}
        stopSelf();
        return super.onStartCommand(intent,flags,startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
