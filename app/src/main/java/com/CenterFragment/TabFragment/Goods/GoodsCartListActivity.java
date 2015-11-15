package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import Util.WebServiceAPI;
import jerome.i_pos.R;
import model.ActivityRequestCodeConstant;

/**
 * Created by Jerome on 2015/10/5.
 */
public class GoodsCartListActivity extends Activity
{

    private static GoodsCartListRecyclerViewAdapter mRecyclerViewerAdapter = null;
    private View mMainView = null;
    private static TextView mSubToalTextView = null;
    private Context mContext = null;
    private boolean mBSubmit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainView = inflater.inflate(R.layout.goods_cart, null);
        final ImageView imageView = (ImageView)mMainView.findViewById(R.id.goods_cart_barcode_imageView);
        final TextView barcodeTextView= (TextView)mMainView.findViewById(R.id.goods_cart_barcode_textView);
        mSubToalTextView = (TextView)mMainView.findViewById(R.id.goods_cart_subtotal_textView);
        mContext = this;
        final Button submitButton = (Button)mMainView.findViewById(R.id.goods_cart_submit_button);
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
                                String res = WebServiceAPI.SaveConsumeSetting2(WebServiceAPI.mBranchID, WebServiceAPI.mEmployeeID, WebServiceAPI.mTokenID, GoodsCartRecordData.getAllGoodsItem());
                                String[] res2 = res.split(",");
                                String barcode = "";
                                if (res.length() > 1)
                                    barcode = res2[1];
                                mBSubmit = true;
                                submitButton.setVisibility(View.GONE);
                                com.google.zxing.MultiFormatWriter writer = new MultiFormatWriter();
                                String finaldata = barcode;
                                BitMatrix bitMatrix = null;

                                int width = (int) imageView.getWidth();
                                int height = (int) imageView.getWidth() / 4;
                                bitMatrix = writer.encode(finaldata, BarcodeFormat.CODE_128, width, height);
                                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                                if (bitmap != null) {
                                    for (int i = 0; i < width; i++) {// width
                                        for (int j = 0; j < height; j++) {// height
                                            bitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                                        }
                                    }
                                    imageView.setImageBitmap(bitmap);
                                    barcodeTextView.setText(finaldata);
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
    public void onResume()
    {
        try
        {
            super.onResume();
            queryGoodsRecord();
        }
        catch(Exception ex)
        {

        }
    }
    public static void updateSubTotalValue()
    {
        mSubToalTextView.setText("$"+GoodsCartRecordData.getSubTotal());
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
    public static void addItem(BaseItemData item, boolean bAllowRepeat)
    {
        GoodsCartRecordData.addGoodsItem(item, bAllowRepeat);
        mRecyclerViewerAdapter.notifyDataSetChanged();
    }

    public static void removeItem(BaseItemData item)
    {
        GoodsCartRecordData.removeGoodsItem(item);
        mRecyclerViewerAdapter.notifyDataSetChanged();
    }
    public void queryGoodsRecord()
    {
        GoodsCartListActivity.updateSubTotalValue();
        mRecyclerViewerAdapter = new GoodsCartListRecyclerViewAdapter(
                this,
                GoodsCartRecordData.getAllGoodsItem()
        );
        mRecyclerViewerAdapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        try {

            if (mMainView != null)
            {
                RecyclerView recyclerView = (RecyclerView) mMainView.findViewById(R.id.goods_record_recyclerView);
                if (recyclerView != null)
                {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(mRecyclerViewerAdapter);

                }
            }
        }
        catch (Exception ex)
        {
            String str = ex.toString();
            str = str;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityRequestCodeConstant.GOODS_CART_LIST_RECYCLER_VIEW_ADAPTER)
            finish();
    }

    @Override
    public void onBackPressed()
    {
        if (mBSubmit)
            GoodsCartRecordData.clearGoodsItem();
        super.onBackPressed();
    }
}
