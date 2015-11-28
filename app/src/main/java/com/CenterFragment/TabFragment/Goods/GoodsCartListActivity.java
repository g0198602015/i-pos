package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LeftFragement.BaseItemData;
import com.android.print.sdk.PrinterConstants;
import com.android.print.sdk.PrinterInstance;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import Util.PrintUtility;
import i_so.pos.ActivityRequestConstants;
import i_so.pos.BluetoothDeviceList;
import i_so.pos.BluetoothOperation;
import Util.IPrinterOpertion;
import Util.WebServiceAPI;
import i_so.pos.R;
import model.GoodsCartRecordData;
import model.UserConnectionData;

/**
 * Created by Jerome on 2015/10/5.
 */
public class GoodsCartListActivity extends Activity {
    private static boolean isConnected;
    private static GoodsCartListRecyclerViewAdapter mRecyclerViewerAdapter = null;
    private View mMainView = null;
    private static TextView mSubToalTextView = null;
    private Context mContext = null;
    public static boolean mBSubmit = false;
    protected static IPrinterOpertion myOpertion;
    private PrinterInstance mPrinter;
    //private Bitmap barcodeBitmap = null;
    private String barcodeString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainView = inflater.inflate(R.layout.goods_cart, null);
        final ImageView barcodeImageView = (ImageView) mMainView.findViewById(R.id.goods_cart_barcode_imageView);
        final TextView barcodeTextView = (TextView) mMainView.findViewById(R.id.goods_cart_barcode_textView);
        mSubToalTextView = (TextView) mMainView.findViewById(R.id.goods_cart_subtotal_textView);
        mContext = this;
        final Button printButton = (Button) mMainView.findViewById(R.id.goods_cart_print_button);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOpertion != null && myOpertion.getPrinter() != null) {
                    Print();
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.str_message)
                            .setMessage(R.string.str_connlast)
                            .setPositiveButton(R.string.yesconn, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    myOpertion = new BluetoothOperation(mContext, mHandler);
                                    Context context = mContext;
                                    myOpertion.btAutoConn(context, mHandler);

                                }
                            })
                            .setNegativeButton(R.string.str_resel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    myOpertion = new BluetoothOperation(mContext, mHandler);
                                    myOpertion.chooseDevice();
                                }

                            })
                            .show();


                }
            }
        });
        final Button submitButton = (Button) mMainView.findViewById(R.id.goods_cart_submit_button);
        submitButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(mContext, "傳送中..請稍後...", Toast.LENGTH_SHORT).show();
//                    barcodeTextView.setText("傳送中..請稍後...");
                }
                return false;
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GoodsCartRecordData.getGoodsItemSize() > 0) {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                String res = WebServiceAPI.SaveConsumeSetting2(UserConnectionData.getCloudService(), UserConnectionData.getBranchID(), UserConnectionData.getEmployeeID(), UserConnectionData.getTokenID(), GoodsCartRecordData.getAllGoodsItem());
                                String[] res2 = res.split(",");
                                String barcode = "";
                                if (res.length() > 1)
                                    barcode = res2[1];
                                mBSubmit = true;
                                submitButton.setVisibility(View.GONE);
                                printButton.setVisibility(View.VISIBLE);
                                com.google.zxing.MultiFormatWriter writer = new MultiFormatWriter();
                                barcodeString = barcode;
                                BitMatrix bitMatrix = null;

                                int width = (int) barcodeImageView.getWidth();
                                int height = (int) barcodeImageView.getWidth() / 4;
                                bitMatrix = writer.encode(barcodeString, BarcodeFormat.CODE_128, width, height);
                                Bitmap barcodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                                if (barcodeBitmap != null) {
                                    for (int i = 0; i < width; i++) {// width
                                        for (int j = 0; j < height; j++) {// height
                                            barcodeBitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                                        }
                                    }
                                    barcodeImageView.setImageBitmap(barcodeBitmap);
                                    barcodeTextView.setText(barcodeString);
                                }
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


            }
        });

        setContentView(mMainView);
    }

    @Override
    public void onResume() {
        try {
            super.onResume();
            queryGoodsRecord();
        } catch (Exception ex) {

        }
    }

    public static void updateSubTotalValue() {
        mSubToalTextView.setText("$" + GoodsCartRecordData.getSubTotal());
    }

    //    public static void addSubtotalValue(double value)
//    {
//        double total = Double.parseDouble(mSubToalTextView.getText().toString().replace("$", ""));
//        mSubToalTextView.setText("$"+(total+value));
//    }
//    public static void subSubtotalValue(double value)
//    {
//        double total = Double.parseDouble(mSubToalTextView.getText().toString().replace("$", ""));
//        mSubToalTextView.setText("$"+(total-value));
//    }
    public static void addItem(BaseItemData item, boolean bAllowRepeat) {
        GoodsCartRecordData.addGoodsItem(item, bAllowRepeat);
        mRecyclerViewerAdapter.notifyDataSetChanged();
    }

    public static void removeItem(BaseItemData item) {
        GoodsCartRecordData.removeGoodsItem(item);
        mRecyclerViewerAdapter.notifyDataSetChanged();
    }

    public void queryGoodsRecord() {
        GoodsCartListActivity.updateSubTotalValue();
        mRecyclerViewerAdapter = new GoodsCartListRecyclerViewAdapter(
                this,
                GoodsCartRecordData.getAllGoodsItem()
        );
        mRecyclerViewerAdapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        try {

            if (mMainView != null) {
                RecyclerView recyclerView = (RecyclerView) mMainView.findViewById(R.id.goods_record_recyclerView);
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(mRecyclerViewerAdapter);

                }
            }
        } catch (Exception ex) {
            String str = ex.toString();
            str = str;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == ActivityRequestConstants.GOODS_CART_LIST_RECYCLER_VIEW_ADAPTER) {
            finish();
        } else if (requestCode == ActivityRequestConstants.CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK) {
                new Thread(new Runnable() {
                    public void run() {
                        myOpertion.open(data);
                    }
                }).start();

            }
        } else if (requestCode == ActivityRequestConstants.ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                myOpertion.chooseDevice();
            } else {
                Toast.makeText(this, R.string.bt_not_enabled,
                        Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (mBSubmit) {
            GoodsCartRecordData.clearGoodsItem();
            mBSubmit = false;
        }
        super.onBackPressed();
    }

    //用於接受連接狀態消息的 Handler
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PrinterConstants.Connect.SUCCESS:
                    isConnected = true;
                    mPrinter = myOpertion.getPrinter();
                    Print();
                    break;
                case PrinterConstants.Connect.FAILED:
                    isConnected = false;
                    Toast.makeText(mContext, R.string.conn_failed,
                            Toast.LENGTH_SHORT).show();
                    break;
                case PrinterConstants.Connect.CLOSED:
                    isConnected = false;
                    Toast.makeText(mContext, R.string.conn_closed, Toast.LENGTH_SHORT)
                            .show();
                    break;
                case PrinterConstants.Connect.NODEVICE:
                    isConnected = false;
                    Toast.makeText(mContext, R.string.conn_no, Toast.LENGTH_SHORT)
                            .show();
                    break;

                default:
                    break;
            }

            //updateButtonState();

//            if (dialog != null && dialog.isShowing()) {
//                dialog.dismiss();
//            }
        }

    };

    private void Print()
    {
        if(myOpertion!=null&&myOpertion.getPrinter()!=null)
        {
            PrintUtility.printImage(getResources(), myOpertion.getPrinter(), barcodeString);
        }
    }
}
