package jerome.i_pos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.CenterFragment.TabFragment.Goods.GoodsCartListActivity;

import Util.WebServiceAPI;

public class MainViewActivity extends Activity implements WebServiceAPI.OnProductDataReceivedListener {

    private ProgressBar mProgressBar;
    private Context mContext;
    private int myProgress = 0;
    private TextView mProgressTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableNetwork();
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_view, null);

        mContext = this;
        Util.WebServiceAPI.addProductDataReceivedListener(this);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
//        mProgressBar.setVisibility(View.GONE);
        mProgressTextView = (TextView)view.findViewById(R.id.textView3);
//        mProgressTextView.setVisibility(View.GONE);
        ImageButton orderImageButton = (ImageButton)view.findViewById(R.id.orderImageButton);
        ImageButton shippmentImageButton = (ImageButton)view.findViewById(R.id.shippmentImageButton);
        ImageButton inventoryImageButton = (ImageButton)view.findViewById(R.id.inventoryImageButton);
        ImageButton recordImageButton = (ImageButton)view.findViewById(R.id.recordImageButton);
//        orderImageButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent motionEvent) {
//
//                new Thread()
//                {
//                    @Override
//                    public void run() {
//                        try {
////                    Util.WebServiceAPI.SaveConsumeSetting2();
////                            Util.WebServiceAPI.GetProducts(WebServiceAPI.mBranchID, WebServiceAPI.mTokenID);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        } finally {
//                        }
//                    }
//                }.start(); //開始執行執行緒
////                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
////                    Toast.makeText(mContext, "讀取產品資料中...", Toast.LENGTH_LONG).show();
////                }
//                return false;
//            }
//        });
        orderImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//
                if (Util.WebServiceAPI.mBGettingProductsFinish == false) {
                    new Thread() {
                        @Override
                        public void run() {
                            Util.WebServiceAPI.GetProducts(WebServiceAPI.mBranchID, WebServiceAPI.mTokenID);
                        }
                    }.start(); //開始執行執行緒
                }
                else
                    startMainActivity();
//                Intent intent = new Intent();
//                intent.setClass(mContext, MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("Type", 0);
//                intent.putExtras(bundle);
//                startActivityForResult(intent, ActivityRequestConstants.MAIN_ACTVITIY);
            }
        });
        shippmentImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Type", 1);
                intent.putExtras(bundle);
                startActivityForResult(intent, ActivityRequestConstants.MAIN_ACTVITIY);
            }
        });
        inventoryImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //do something

            }
        });
        recordImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, GoodsCartListActivity.class);
                startActivity(intent);
            }
        });

        setContentView(view);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void enableNetwork()
    {
        android.os.StrictMode.setThreadPolicy(new android.os.StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        android.os.StrictMode.setVmPolicy(new android.os.StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    @Override
    public void onBackPressed()
    {
        //Theme.AppCompat.Light.Dialog.Alert
        new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert).setTitle(R.string.app_name).setMessage(R.string.message_exit)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {

                    }
                }).show();
    }

    @Override
    public void onDataReceive(long total, long current)
    {

        final int max = (int)total;
        mProgressBar.setMax(max);
        final int progress = (int)current +1;

        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              mProgressTextView.setVisibility(View.VISIBLE);
                              mProgressTextView.setText("讀取產品資料中..." + progress + "/" + max);
                              mProgressBar.setVisibility(View.VISIBLE);
                              mProgressBar.setProgress(progress);
                              if (max == progress) {
                                  mProgressBar.setVisibility(View.GONE);
                                  mProgressTextView.setVisibility(View.GONE);
                                  startMainActivity();
                              }
                          }
                      }
        );
    }
    private void startMainActivity()
    {
        Intent intent = new Intent();
        intent.setClass(mContext, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Type", 0);
        intent.putExtras(bundle);
        startActivityForResult(intent, ActivityRequestConstants.MAIN_ACTVITIY);
    }
}
