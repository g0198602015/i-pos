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

public class GoodsRecordsRecyclerViewAdapter extends RecyclerView.Adapter<GoodsRecordsRecyclerViewAdapter.NormalTextViewHolder> {
    private static ArrayList<BaseItemData> _DataItems;
    private final LayoutInflater mLayoutInflater;
    private static Context mContext;


    public GoodsRecordsRecyclerViewAdapter(Context context, BaseItemData dataItems)
    {
        _DataItems = new ArrayList<BaseItemData>();
        parseDataItems(dataItems, _DataItems);
        mContext = context;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return null;
        return _DataItems.get(position);
    }
    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.shippment_fragment_listview_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        BaseItemData appInfo = _DataItems.get(position);
        if (appInfo != null)
        {
            holder.ItemName.setText(appInfo.getTitle());
            holder.ItemInfo.setText(appInfo.getInfo());
            if (appInfo.getIconResourceID() != 0)
                holder.ItemImage.setImageDrawable(holder.ItemImage.getResources().getDrawable(appInfo.getIconResourceID()));
        }
    }

    @Override
    public int getItemCount()
    {
        return _DataItems.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        ImageView ItemImage;
        TextView ItemName;
        TextView ItemInfo;
        NormalTextViewHolder(View view) {
            super(view);
            ItemImage = (ImageView)view.findViewById(R.id.ItemImage);
            ItemName = (TextView)view.findViewById(R.id.ItemName);
            ItemInfo = (TextView)view.findViewById(R.id.ItemInfo);
//            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass((Activity) mContext, GoodsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("Type", 0);
                    bundle.putSerializable("ListViewData", (BaseItemData) _DataItems.get(getPosition()));
                    intent.putExtras(bundle);
                    intent.putExtras(bundle);
                    ((Activity)mContext).startActivityForResult(intent, 0);
                }
            });
        }
    }

    private  ArrayList<BaseItemData> parseDataItems(BaseItemData currentDataItem,ArrayList<BaseItemData> arrayListItems)
    {
        if (currentDataItem.getVisible())
        {
            int childSize = currentDataItem.getChildSize();
            for (int childIndex = 0; childIndex < childSize; childIndex++)
            {
                BaseItemData childDataItem = currentDataItem.getChild(childIndex);
                if (!childDataItem.getClassification()) // 不為類別身分
                {
                    arrayListItems.add(childDataItem);
                }
                else
                {
                    parseDataItems(childDataItem, arrayListItems); //類別的話, 繼續往下挖
                }
            }
        }
        return arrayListItems;
    }
}