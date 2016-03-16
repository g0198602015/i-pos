package com.BroadCastReceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.WhosIncomingCaller.WhosIncomingCallerActivity;
import com.model.Employee;
import com.model.UserConnectionData;

import i_so.pos.R;

/**
 * Created by Jerome on 2016/3/3.
 */
public class IncomingCellServices extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingCellServices", "onReceive", "onReceive");

        String state =  intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingCellServices", "onReceive", "state:"+state);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            if (UserConnectionData.getInstance() == null) {
                UserConnectionData.CreateInstance(context.getCacheDir() + com.Util.Constants.FILE_USER_DATA);
                if (com.i_so.MainViewActivity.m_debug) {
                    UserConnectionData.CreateInstance("http://zoom-world.tw/WuchDemo/Login.aspx", "http://zoom-world.tw/WuchDemo/CloudService.asmx", "64860217");
                }
            }
            if (Employee.getInstance() == null || Employee.getInstance().getID() == 0) {
                Employee.CreateInstance(context.getCacheDir() + com.Util.Constants.FILE_EMPLOYEE);
                if (com.i_so.MainViewActivity.m_debug) {
                    Employee.CreateInstance("2", "zonghan");
                }
            }
            if (UserConnectionData.getInstance() != null && Employee.getInstance() != null && Employee.getInstance().getID() != 0) {
                //Trigger Activity
                ComponentName comp = new ComponentName("jerome.i_pos", "com.WhosIncomingCaller.WhosIncomingCallerActivity");
                Intent newItent = new Intent();
                newItent.setComponent(comp);
                newItent.addCategory(Intent.CATEGORY_DEFAULT);
                newItent.putExtras(intent.getExtras());
                newItent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newItent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(newItent);
            } else
                com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingCellServices", "onReceive", "U0ser Connection Data doesn't exit");
        }
        else
        {
            WhosIncomingCallerActivity.closeActivity();
        }
    }

}