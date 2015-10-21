package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainView = inflater.inflate(R.layout.goods_cart, null);
        mSubToalTextView = (TextView)mMainView.findViewById(R.id.goods_cart_subtotal_textView);
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
        mSubToalTextView.setText("$"+GoodsCardRecordData.getSubTotal());
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
    public static void removeItem(BaseItemData item)
    {
        GoodsCardRecordData.removeGoodsItem(item);
        mRecyclerViewerAdapter.notifyDataSetChanged();
    }
    public void queryGoodsRecord()
    {
        GoodsCartListActivity.updateSubTotalValue();
        mRecyclerViewerAdapter = new GoodsCartListRecyclerViewAdapter(
                this,
                GoodsCardRecordData.getAllGoodsItem()
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
}
