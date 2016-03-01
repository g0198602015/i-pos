package com.WhosIncomingCaller;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jeromelibrary.R;


public class WhosIncomingCallerActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            LogUtility.Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", "onCreate: ");
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

            LogUtility.Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", "flagy ");

            setContentView(R.layout.activity_incoming_caller);

            LogUtility.Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", "flagz");

            String number = getIntent().getStringExtra(
                    TelephonyManager.EXTRA_INCOMING_NUMBER);
            TextView text = (TextView) findViewById(R.id.textView);
            text.setText("Incoming call from " + number);
        }
        catch (Exception e)
        {
            LogUtility.Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", e.toString());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}