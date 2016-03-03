package com.BroadCastReceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.Util.WebServiceAPI;
import com.model.UserConnectionData;

/**
 * Created by Jerome on 2016/3/3.
 */
public class IncomingCellServices extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {

        com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingCellServices", "onReceive", "onReceive");
        if (UserConnectionData.getInstance()==null) {
            UserConnectionData.CreateInstance(context.getCacheDir() + com.Util.Constants.mUserConnectionDataXMLFileName);
            if (com.i_so.MainViewActivity.m_debug)
            {
                UserConnectionData.CreateInstance("http://192.168.1.1/customer.aspx","http://zoom-world.tw/WuchDemo/CloudService.asmx", "64860217");
            }
        }
        if (UserConnectionData.getInstance() != null)
        {
            WebServiceAPI.getCustomerInfo(UserConnectionData.getInstance().getCloudService(), "0910954445", UserConnectionData.getInstance().getTokenID());
            ComponentName comp = new ComponentName("jerome.i_pos", "com.WhosIncomingCaller.WhosIncomingCallerActivity");
            Intent i = new Intent();
            i.setComponent(comp);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.putExtras(intent.getExtras());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(i);
        }
        else
            com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("IncomingCellServices", "onReceive", "U0ser Connection Data doesn't exit");

    }

}