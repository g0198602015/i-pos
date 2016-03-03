package com.jeromelibrary.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

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
//            jerome.i_pos/com.WhosIncomingCaller.WhosIncomingCallerActivity
//            ComponentName comp = new ComponentName("jerome.i_pos", "com.WhosIncomingCaller.WhosIncomingCallerActivity");
//            Intent i = new Intent();
//            i.setComponent(comp);
//            i.addCategory(Intent.CATEGORY_DEFAULT);
//            i.putExtras(intent);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            context.startActivity(i);
            Intent i = new Intent("com.jeromeLibary.PHONE_STATE");
            i.putExtras(intent);
            context.sendBroadcast(i);
        }
    }
}