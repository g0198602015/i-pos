package com.WhosIncomingCaller;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.BroadCastReceiver.IncomingCellServices;
import com.BroadCastReceiver.IncomingNotifcationServices;
import com.Util.Constants;
import com.Util.WebServiceAPI;
import com.jeromelibrary.LogUtility.Log;
import com.jeromelibrary.Utility.IntentFactory;
import com.model.CustomerInfo;
import com.model.Employee;
import com.model.UserConnectionData;

import i_so.pos.R;


public class WhosIncomingCallerActivity extends Activity {
    private static Activity mActivity = null;
    public static final String ACTION_CLOSE = "com.WhosIncomingCaller.ACTION_CLOSE";
    private CustomerInfo mCustomerInfo = null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        try {
            mActivity = this;
            Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", "onCreate: ");
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            setContentView(R.layout.activity_incoming_caller);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Bundle phoneBundle = getIntent().getExtras();
            final String number = phoneBundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TextView text = (TextView) findViewById(R.id.phoneNumberTextView);
            text.setText("" + number);
            Button exitButton = (Button) findViewById(R.id.exitButton);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
            final LinearLayout imageParent = (LinearLayout) findViewById(R.id.LinearLayout_Incoming_ImageParent);
            imageParent.setVisibility(View.GONE);
           // ((Button) findViewById(R.id.Button_Incoming_NewReservation)).setOnClickListener(newReservationClickListener);
            View.OnTouchListener newReservationTouchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            v.setBackgroundColor(Color.parseColor("#D3D3D3"));
                            break;
                        case MotionEvent.ACTION_UP:
                            if (mCustomerInfo != null && UserConnectionData.getInstance() != null) {
                                Intent intent = IntentFactory.getIntent(IntentFactory.IntentType.WEB, UserConnectionData.getInstance().getAddingTempConsumeRecordURL() + "?CusID=" + mCustomerInfo.getID());
                                ((Context)mActivity).startActivity(intent);
                            }
                            v.setBackgroundColor(Color.WHITE);
                            break;
                    }
                    return true;
                }
            };
            View.OnTouchListener bookingTouchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            v.setBackgroundColor(Color.parseColor("#D3D3D3"));
                            break;
                        case MotionEvent.ACTION_UP:
                            if (mCustomerInfo != null && UserConnectionData.getInstance() != null) {
                                Intent intent = IntentFactory.getIntent(IntentFactory.IntentType.WEB, UserConnectionData.getInstance().getCustomerURL() + "?CusID=" + mCustomerInfo.getID());
                                ((Context)mActivity).startActivity(intent);
                            }
                            v.setBackgroundColor(Color.WHITE);
                            break;
                    }
                    return true;
                }
            };
            ((LinearLayout) findViewById(R.id.LinearLayout_Incoming_NewReservation)).setOnTouchListener(newReservationTouchListener);
            ((LinearLayout) findViewById(R.id.LinearLayout_Incoming_Booking)).setOnTouchListener(bookingTouchListener);

            if (UserConnectionData.getInstance() != null)
            {
                new Thread() {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Log.getInstance().WriteLog("WhosIncomingCallerActivity", "onCreate", "getCustomerInfo");
                            mCustomerInfo = WebServiceAPI.getCustomerInfo(UserConnectionData.getInstance().getCloudService(), number, UserConnectionData.getInstance().getTokenID(), Employee.getInstance().getID());
                            if (mCustomerInfo != null)
                            {
                                final int notifyID = 123456789; // 通知的識別號碼
                                int flags = PendingIntent.FLAG_ONE_SHOT; // ONE_SHOT：PendingIntent只使用一次；CANCEL_CURRENT：PendingIntent執行前會先結束掉之前的；NO_CREATE：沿用先前的PendingIntent，不建立新的PendingIntent；UPDATE_CURRENT：更新先前PendingIntent所帶的額外資料，並繼續沿用

                                // Resveration Intent
                                final Intent newReservationIntent = IntentFactory.getIntent(IntentFactory.IntentType.WEB, UserConnectionData.getInstance().getAddingTempConsumeRecordURL() + "?CusID=" + mCustomerInfo.getID());
                                newReservationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Intent tempIntent = new Intent(getApplicationContext(), IncomingNotifcationServices.class);
                                tempIntent.putExtra(Constants.BUNDLE_INCOMING_NOTIFACTION_ID, notifyID);
                                tempIntent.putExtra(Constants.BUNDLE_INCOMING_NOTIFACTION_INTENT, newReservationIntent);
                                final PendingIntent newReservationPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, tempIntent, flags);

                                final Intent customerInfoIntent = IntentFactory.getIntent(IntentFactory.IntentType.WEB, UserConnectionData.getInstance().getCustomerURL() + "?CusID=" + mCustomerInfo.getID());
                                customerInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Intent tempIntent2 = new Intent(getApplicationContext(), IncomingNotifcationServices.class);
                                tempIntent2.putExtra(Constants.BUNDLE_INCOMING_NOTIFACTION_ID, notifyID);
                                tempIntent2.putExtra(Constants.BUNDLE_INCOMING_NOTIFACTION_INTENT, customerInfoIntent);
                                final PendingIntent bookingPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, tempIntent2, flags); // 取得PendingIntent
//                                final Notification notification = new Notification.Builder(getApplicationContext())
//                                                                                 .setSmallIcon(R.drawable.incoming_icon)
//                                                                                 .setContentTitle("i-so pos")
//                                                                                 .setContentText(number)
//                                                                                 .addAction(R.mipmap.booking, getResources().getString(R.string.button_customer_info), customerInfoPendingIntent)
//                                                                                 .addAction(R.mipmap.customersingle, getResources().getString(R.string.button_new_reservation), newReservationPendingIntent)
//                                                                                 .setAutoCancel(true)
//                                                                                 .build(); // 建立通知
//
//                                notificationManager.notify(notifyID, notification); // 發送通知

                                RemoteViews remoteViews = new RemoteViews(
                                        ((Context)mActivity).getPackageName(),
                                        R.layout.activity_incoming_caller2
                                );
                                remoteViews.setTextViewText(R.id.whoTextView, mCustomerInfo.getName()+", "+number);
                                remoteViews.setTextViewText(R.id.infoTextView, mCustomerInfo.getDescription());
                                remoteViews.setOnClickPendingIntent(R.id.LinearLayout_Incoming_NewReservation, newReservationPendingIntent);
                                remoteViews.setOnClickPendingIntent(R.id.LinearLayout_Incoming_Booking,  bookingPendingIntent);
// build notification
                                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(((Context)mActivity))
                                        .setSmallIcon(R.drawable.incoming_icon)
                                    //    .setContentTitle("Content Title")
                                    //    .setContentText("Content Text")
                                    //    .setContentIntent(pendingIntent)
                                        .setContent(remoteViews)
                                        .setPriority(NotificationCompat.PRIORITY_MIN);

                                final Notification notification = mBuilder.build();
                                final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // 取得系統的通知服務
                                notificationManager.notify(notifyID, notification); // 發送通知
                                mActivity.runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        imageParent.setVisibility(View.VISIBLE);
                                        Log.getInstance().WriteLog("WhosIncomingCallerActivity", "onCreate", "Update UI:" + mCustomerInfo.getName() + "," + mCustomerInfo.getDescription());
                                        TextView text = (TextView) findViewById(R.id.whoTextView);
                                        text.setText("" + mCustomerInfo.getName());
                                        text = (TextView) findViewById(R.id.infoTextView);
                                        text.setText("" + mCustomerInfo.getDescription());
                                    }
                                });
                            }
                        }
                        catch (Exception ex)
                        {
                            Log.getInstance().WriteLog("WhosIncomingCallerActivity", "onCreate", "error:" + ex.toString());
                        }
                    }
                }.start();
            }

        } catch (Exception e)
        {
            Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

    }

    public static void closeActivity() {
        if (mActivity != null)
            mActivity.finish();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}