package com.i_so;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.CenterFragment.TabFragment.Goods.GoodsCartListActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.Util.WebServiceAPI;
import com.model.UserConnectionData;

import i_so.pos.R;

public class MainViewActivity extends Activity implements WebServiceAPI.OnProductDataReceivedListener {

    private ProgressBar mProgressBar;
    private Context mContext;
    private int myProgress = 0;
    private TextView mProgressTextView;
    private String mUserConnectionDataXMLPath = "";
    private boolean m_debug = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableNetwork();
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_view, null);

        mContext = this;
        mUserConnectionDataXMLPath = getCacheDir() + "userConnectionData.txt";
        WebServiceAPI.addProductDataReceivedListener(this);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        mProgressTextView = (TextView)view.findViewById(R.id.textView3);
        Button orderImageButton = (Button)view.findViewById(R.id.orderImageButton);
        Button inventoryImageButton = (Button)view.findViewById(R.id.inventoryImageButton);
        Button recordImageButton = (Button)view.findViewById(R.id.recordImageButton);
        orderImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                mProgressTextView.setVisibility(View.VISIBLE);
                if (UserConnectionData.getInstance() == null)
                    mProgressTextView.setText("請先確認連線資訊");
                else
                    mProgressTextView.setText("讀取產品資料中...");
//
                if (WebServiceAPI.mBGettingProductsFinish == false) {
                    new Thread() {
                        @Override
                        public void run()
                        {
                            WebServiceAPI.GetProducts(UserConnectionData.getInstance().getCloudService(), UserConnectionData.getInstance().getBranchID(), UserConnectionData.getInstance().getTokenID());
                            WebServiceAPI.getCustomerInfo(UserConnectionData.getInstance().getCloudService(), "0910954445", UserConnectionData.getInstance().getTokenID());

                        }
                    }.start(); //開始執行執行緒
                }
                else
                    startMainActivity();
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
        if (m_debug)
        {
            UserConnectionData.CreateInstance("","http://zoom-world.tw/WuchDemo/CloudService.asmx", "64860217");
        }
        else
        {
            readUserConnectionData();
            if (UserConnectionData.getInstance() == null)
                startScanQRCodeActivity();
        }
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startScanQRCodeActivity();
                return false;
            }
        });
        setContentView(view);

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        mProgressTextView.setVisibility(View.GONE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE && data != null)
        {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            String text = "";
            if (scanResult != null)
            {
                String base64 = scanResult.getContents();
                byte[] decodeBytes = Base64.decode(base64, Base64.DEFAULT);
                int length = decodeBytes.length / 2;
                byte[] decodeBytes2 = new byte[length];
                for (int index = 0 ;index < length ; index++ )
                {
                    decodeBytes2[index] = decodeBytes[index*2];
                }
                try {
                    text = new String(decodeBytes2, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            text  = text + "";
            String[] token = text.split("\n");
            if (token.length == 3)
            {
                String loginAspx = token[0];
                String cloudService = token[1];
                String tokenID = token[2];
                saveUserConnectionData(loginAspx, cloudService, tokenID);
            }
            text = text;
        }
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
        final int progress = (int)current+1;

        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {

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

    private boolean readUserConnectionData()
    {
        try
        {
            File file = new File(mUserConnectionDataXMLPath);
            if (file.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String loginAspx = br.readLine();
                String cloudService = br.readLine();
                String tokenID = br.readLine();
                br.close();
                UserConnectionData.CreateInstance(loginAspx, cloudService,  tokenID);
                return true;
            }
        }
        catch (IOException e) {
        }
        return false;
    }
    private void saveUserConnectionData(String loginAspx, String cloudService,  String tokenID)
    {
        try
        {
            File file;
            FileOutputStream outputStream;
            file = new File(mUserConnectionDataXMLPath);
            Writer writer =  new BufferedWriter(new FileWriter(file));
            writer.write(loginAspx+"\r\n");
            writer.write(cloudService+"\r\n");
            writer.write(tokenID+"\r\n");
            writer.close();
            readUserConnectionData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startScanQRCodeActivity()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

}
