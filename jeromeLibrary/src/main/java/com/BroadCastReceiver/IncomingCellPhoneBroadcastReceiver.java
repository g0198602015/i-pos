package com.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by Jerome on 2016/2/25.
 */
public class IncomingCellPhoneBroadcastReceiver extends BroadcastReceiver
{
    private final String mClassName = "IncomingCellPhoneBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        final String mMethodName = "onReceive";
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)
                || state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

            ComponentName comp = new ComponentName("com.example.jeromelibrary.WhosIncomingCaller", "com.example.jeromelibrary.WhosIncomingCallerActivity");
            Intent i = new Intent();
            i.putExtras(intent);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            i.setComponent(comp);
            context.startActivity(i);
        }
    }
}