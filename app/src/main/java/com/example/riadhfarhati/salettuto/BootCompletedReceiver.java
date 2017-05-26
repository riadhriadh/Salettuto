package com.example.riadhfarhati.salettuto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by i on 25/05/2017.
 */

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        // TODO Auto-generated method stub
        Log.w("boot_broadcast_poc", "starting service...");
        context.startService(new Intent(context, NotifyingDailyService.class));
    }

}
