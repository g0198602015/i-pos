package com.CenterFragment.TabFragment.Goods;

/**
 * Created by Jerome on 2015/8/9.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

import java.util.ArrayList;

import jerome.i_pos.R;

public class GoodsCartListRecyclerViewAdapter extends RecyclerView.Adapter<GoodsCartListRecyclerViewAdapter.NormalTextViewHolder> {
//    private static ArrayList<BaseItemData> _DataItems;
    private final LayoutInflater mLayoutInflater;
    private static Context mContext;

    public GoodsCartListRecyclerViewAdapter(Context context, BaseItemData dataItems)
    {
//        _DataItems = new ArrayList<BaseItemData>();
//        parseDataItems(dataItems, _DataItems);
        mContext = context;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return null;
        return GoodsCartListActivity.getGoodsItem(position);
    }
    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.goods_cart_listview_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        GoodsItemData appInfo = (GoodsItemData)GoodsCartListActivity.getGoodsItem(position);
        if (appInfo != null)
        {
            holder.mItemData = appInfo;
            holder.ItemName.setText(appInfo.getTitle());
            holder.ItemInfo.setText(appInfo.getInfo());
            holder.mPriceTextView.setText("$"+appInfo.getPrice());
            holder.mSubTotalTextView.setText("$"+appInfo.getCount() * appInfo.getPrice());
            GoodsCartListActivity.addSubtotalValue(appInfo.getCount() * appInfo.getPrice());
            if (appInfo.getIconResourceID() != 0)
                holder.ItemImage.setImageDrawable(holder.ItemImage.getResources().getDrawable(appInfo.getIconResourceID()));
        }
    }

    @Override
    public int getItemCount()
    {
        return GoodsCartListActivity.getGoodsItemSize();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        ImageView ItemImage;
        TextView ItemName;
        TextView ItemInfo;
        TextView mPlusTextView;
        TextView mMinusTextView;
        TextView mCountTextView;
        TextView mSubTotalTextView;
        TextView mPriceTextView;
        GoodsItemData mItemData;
        NormalTextViewHolder(View view) {
            super(view);
            ItemImage = (ImageView)view.findViewById(R.id.ItemImage);
            ItemName = (TextView)view.findViewById(R.id.ItemName);
            ItemInfo = (TextView)view.findViewById(R.id.ItemInfo);
            mMinusTextView = (TextView)view.findViewById(R.id.goods_cart_listview_minus_textView);
            mPlusTextView =  (TextView)view.findViewById(R.id.goods_cart_listview_plus_textView);
            mCountTextView = (TextView)view.findViewById(R.id.goods_cart_listview_count_textView);
            mSubTotalTextView =  (TextView)view.findViewById(R.id.goods_cart_listview_subtotal_textView);
            mPriceTextView = (TextView)view.findViewById(R.id.goods_cart_listview_price_textView);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass((Activity) mContext, GoodsCartDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("Type", 0);
//                    bundle.putSerializable("ListViewData", (BaseItemData) _DataItems.get(getPosition()));
//                    intent.putExtras(bundle);
//                    ((Activity)mContext).startActivity(intent);
//                }
//            });
            mMinusTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try
                    {
                        int count = Integer.parseInt(mCountTextView.getText().toString());
                        count = count - 1;
                        if (count <= 0)
                        {
                            GoodsCartListActivity.removeGoodsRecord(mItemData);
                        }
                        else {
                            mItemData.setCount(count);
                            mCountTextView.setText("" + count);
                            double price = Double.parseDouble(mPriceTextView.getText().toString().replace("$", ""));
                            mSubTotalTextView.setText("$" + count * price);
                            GoodsCartListActivity.subSubtotalValue(price);
                        }

                    } catch(Exception ex)
                    {

                    }
                }
            });
            mPlusTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try
                    {
                        int count = Integer.parseInt(mCountTextView.getText().toString());
                        count = count + 1;
                        mItemData.setCount(count);
                        mCountTextView.setText("" + count);
                        double price = Double.parseDouble(mPriceTextView.getText().toString().replace("$",""));
                        mSubTotalTextView.setText("$" + count * price);
                        GoodsCartListActivity.addSubtotalValue(price);
                    }
                    catch(Exception ex)
                    {

                    }
                }
            });
        }
    }

//    private  ArrayList<BaseItemData> parseDataItems(BaseItemData currentDataItem,ArrayList<BaseItemData> arrayListItems)
//    {
//        if (currentDataItem.getVisible())
//        {
//            int childSize = currentDataItem.getChildSize();
//            for (int childIndex = 0; childIndex < childSize; childIndex++)
//            {
//                BaseItemData childDataItem = currentDataItem.getChild(childIndex);
//                if (!childDataItem.getClassification()) // 不為類別身分
//                {
//                    arrayListItems.add(childDataItem);
//                }
//                else
//                {
//                    parseDataItems(childDataItem, arrayListItems); //類別的話, 繼續往下挖
//                }
//            }
//        }
//        return arrayListItems;
//    }
}