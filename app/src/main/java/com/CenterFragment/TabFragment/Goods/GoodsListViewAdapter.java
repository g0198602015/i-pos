package com.CenterFragment.TabFragment.Goods;

/**
 * Created by Jerome on 2015/8/9.
 */

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

import jerome.i_pos.R;

public class GoodsListViewAdapter extends BaseAdapter
{
    private ArrayList<GoodsItemData> _DataItems;
    private LayoutInflater mInflater;
    private Context mContext;
    private ItemView mItemView;
    private String mSearchText="";
    private class ItemView {
        ImageView ItemImage;
        TextView ItemName;
        TextView ItemInfo;
    }

    public GoodsListViewAdapter(Context context, BaseItemData dataItems, String searchText)
    {
        _DataItems = new ArrayList<GoodsItemData>();
        mSearchText = searchText;
        parseDataItems(dataItems, _DataItems);
        mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //return 0;
        return _DataItems.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return null;
        return _DataItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        //return 0;
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        //return null;

        if (convertView != null) {
            mItemView = (ItemView) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.goods_fragment_listview_adapter, null);
            mItemView = new ItemView();
            mItemView.ItemImage = (ImageView)convertView.findViewById(R.id.ItemImage);
            mItemView.ItemName = (TextView)convertView.findViewById(R.id.ItemName);
            mItemView.ItemInfo = (TextView)convertView.findViewById(R.id.ItemInfo);
            convertView.setTag(mItemView);
        }

        GoodsItemData appInfo = _DataItems.get(position);
        if (appInfo != null)
        {
            mItemView.ItemName.setText(appInfo.getTitle());
            mItemView.ItemInfo.setText(appInfo.getInfo());
            if (appInfo.getIconResourceID() != 0)
                mItemView.ItemImage.setImageDrawable(mItemView.ItemImage.getResources().getDrawable(appInfo.getIconResourceID()));
        }

        return convertView;
    }
    private  ArrayList<GoodsItemData> parseDataItems(BaseItemData currentDataItem,ArrayList<GoodsItemData> arrayListItems)
    {
        if (currentDataItem.getVisible())
        {
            int childSize = currentDataItem.getChildSize();
            for (int childIndex = 0; childIndex < childSize; childIndex++)
            {
                BaseItemData childDataItem = currentDataItem.getChild(childIndex);
                if (!childDataItem.getClassification()) // 不為類別身分
                {
                    if (mSearchText.length() != 0)
                    {
                        if (childDataItem.getTitle().contains(mSearchText) || childDataItem.getInfo().contains(mSearchText))
                            arrayListItems.add((GoodsItemData)childDataItem);
                    }
                    else
                    {
                        arrayListItems.add((GoodsItemData)childDataItem);
                    }
                }
                else
                {
                    parseDataItems((GoodsItemData)childDataItem, arrayListItems); //類別的話, 繼續往下挖
                }
            }
        }
        return arrayListItems;
    }

}