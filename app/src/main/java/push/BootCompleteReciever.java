package push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by NachtaktiverHalbaffe on 23.05.2016.
 */
public class BootCompleteReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            AlarmManager alarmManager=(AlarmManager)context.getSystemService(context.ALARM_SERVICE);
            Intent startservice= new Intent(context, backgroundtask_push.class);
            PendingIntent startPendingIntent=PendingIntent.getService(context,0,startservice,0);

            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),216000000,startPendingIntent);
        }
    }
}
