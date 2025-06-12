package com.example.sleepit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 알람이 울리면 AlarmRingActivity 실행
        Intent i = new Intent(context, AlarmRingActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 외부에서 Activity 실행 시 필요
        context.startActivity(i);
    }
}
