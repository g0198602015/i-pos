package com.WhosIncomingCaller;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Util.WebServiceAPI;
import com.jeromelibrary.LogUtility.Log;
import com.jeromelibrary.Utility.IntentFactory;
import com.model.CustomerInfo;
import com.model.UserConnectionData;

import i_so.pos.R;


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
            Bundle phoneBundle = getIntent().getExtras();
            final String number = phoneBundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TextView text = (TextView) findViewById(R.id.phoneNumberTextView);
            text.setText("" + number);
            Button exitButton = (Button)findViewById(R.id.exitButton);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    mActivity.finish();
                }
            });
            if (UserConnectionData.getInstance() != null)
            {
                new Thread() {
                    @Override
                    public void run()
                    {
                        final CustomerInfo customerInfo = WebServiceAPI.getCustomerInfo(UserConnectionData.getInstance().getCloudService(), number, UserConnectionData.getInstance().getTokenID());
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                TextView text = (TextView) findViewById(R.id.whoTextView);
                                text.setText("" + customerInfo.getName());
                                text = (TextView) findViewById(R.id.infoTextView);
                                text.setText("" + customerInfo.getDescription());
                                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = IntentFactory.getIntent(IntentFactory.IntentType.WEB, UserConnectionData.getInstance().getLoginAspx()+"CustomerInfo?"+customerInfo.getID());
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                }.start(); //開始執行執行緒
            }

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