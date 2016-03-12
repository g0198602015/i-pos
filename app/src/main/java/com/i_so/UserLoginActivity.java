package com.i_so;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CenterFragment.TabFragment.Goods.GoodsCartListActivity;
import com.Util.Constants;
import com.Util.WebServiceAPI;
import com.model.Employee;
import com.model.UserConnectionData;

import i_so.pos.R;

/**
 * Created by Jerome on 2016/3/7.
 */
public class UserLoginActivity extends Activity
{
    private Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (UserConnectionData.getInstance() != null)
            Toast.makeText(this,getResources().getText(R.string.USERLOGIN_QECODE_VERIFICATION_SUCCESFUL), Toast.LENGTH_LONG).show();
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.activity_user_login, null);

        Button loginButton = (Button)view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                final String userName = ((EditText)view.findViewById(R.id.userNameEditText)).getText().toString();
                final String password = ((EditText)view.findViewById(R.id.passwordEditText)).getText().toString();
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            final Employee employee = WebServiceAPI.VerifyEmployeeApp(UserConnectionData.getInstance().getCloudService(), userName, password, UserConnectionData.getInstance().getTokenID());
                            runOnUiThread(new Runnable()
                            {
                                  @Override
                                  public void run()
                                  {
                                      if (employee != null && employee.getID() > 0)
                                      {
                                          AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                          builder.setMessage(getResources().getText(R.string.USERLOGIN_VERIFICATION_SUCCESFUL))
                                                  .setCancelable(false)
                                                  .setPositiveButton(getResources().getText(R.string.USERLOGIN_ENJOY_ISO_POS), new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int id) {
                                                          Toast.makeText(mContext, getResources().getText(R.string.USERLOGIN_STARTUP_INCOMING_SERVICE), Toast.LENGTH_LONG).show();
                                                          Intent intent = new Intent();
                                                          Bundle bundle = new Bundle();
                                                          bundle.putInt(Constants.BUNDLE_EMPLOYEE_ID, employee.getID());
                                                          bundle.putString(Constants.BUNDLE_EMPLOYEE_NICK_NAME, employee.getNickName());
                                                          intent.putExtras(bundle);
                                                          ((Activity) mContext).setResult(RESULT_OK, intent);
                                                          ((Activity) mContext).finish();
                                                      }
                                                  });
                                          AlertDialog alert = builder.create();
                                          alert.show();
                                      } else
                                          Toast.makeText(mContext, getResources().getText(R.string.USERLOGIN_VERIFICATION_FAILED), Toast.LENGTH_LONG).show();
                                  }
                            });
                        }
                        catch(Exception ex)
                        {
                            com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("UserLoginActivity", "onCreate", "error:"+ex.toString());

                        }
                    }
                }.start();
            }
        });
        setContentView(view);
    }
    @Override
    public void onBackPressed()
    {
        try
        {
           //setResult(RESULT_CANCELED);
           finish();
        }
        catch(Exception ex)
        {
            com.jeromelibrary.LogUtility.Log.getInstance().WriteLog("UserLoginActivity", "onBackPressed", "error:"+ex.toString());

        }
    }
}
