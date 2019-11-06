package threads;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;


public class BootCompleteReciever extends BroadcastReceiver {
    //Beim hochfahren des Gerätes Notifications anchalten
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            final Intent startservice = new Intent(context, backgroundtask_push.class);
            final PendingIntent startPendingIntent = PendingIntent.getService(context, 0, startservice, 0);
            final Calendar calendar = Calendar.getInstance();
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            calendar.setTimeInMillis(System.currentTimeMillis());
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 216000000, startPendingIntent);
        }
    }
}

