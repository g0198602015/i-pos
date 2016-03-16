package com.BroadCastReceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Util.Constants;

/**
 * Created by Jerome on 2016/3/15.
 */
public class IncomingNotifcationServices extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent)
    {
        com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingNotifcationServices", "onReceive", "onReceive");
        try {
            final int notifyID = intent.getIntExtra(Constants.BUNDLE_INCOMING_NOTIFACTION_ID, 0);
            Intent activityIntent = intent.getParcelableExtra(Constants.BUNDLE_INCOMING_NOTIFACTION_INTENT);
            final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); // 取得系統的通知服務
            notificationManager.cancel(notifyID);
            context.startActivity(activityIntent);
        }
        catch(Exception ex)
        {
            com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingNotifcationServices", "onReceive", "Error:"+ex.toString());

        }
    }

}