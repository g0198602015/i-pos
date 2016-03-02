package com.jeromelibrary.WhosIncomingCaller;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jeromelibrary.LogUtility.Log;
import com.jeromelibrary.R;


public class WhosIncomingCallerActivity extends Activity
{
    private Activity mActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            mActivity = this;
            Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", "onCreate: ");
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            setContentView(R.layout.activity_incoming_caller);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            String number = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TextView text = (TextView) findViewById(R.id.phoneNumberTextView);
            text.setText(""+number);
            Button exitButton = (Button)findViewById(R.id.exitButton);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    mActivity.finish();
                }
            });
        }
        catch (Exception e)
        {
            Log.getInstance().WriteLog("WhosIncomingCallerActivity", "savedInstanceState", e.toString());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed()
    {

    }
}