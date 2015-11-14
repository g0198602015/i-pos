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
import model.GoodsItemData;

public class GoodsListViewAdapter extends BaseAdapter
{
    private ArrayList<GoodsItemData> _DataItems;
    private LayoutInflater mInflater;
    private Context mContext;
    private ItemView mItemView;
    private String mSearchText="";
    private String mSearchBarcode = "";
    private class ItemView {
        ImageView ItemImage;
        TextView ItemName;
        TextView ItemInfo;
        TextView ItemPrice;
    }

    public GoodsListViewAdapter(Context context, BaseItemData dataItems, String searchText, String searchBarcode)
    {
        _DataItems = new ArrayList<GoodsItemData>();
        mSearchText = searchText;
        mSearchBarcode = searchBarcode;
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
            mItemView.ItemPrice = (TextView)convertView.findViewById(R.id.ItemPrice);
            convertView.setTag(mItemView);
        }

        GoodsItemData appInfo = _DataItems.get(position);
        if (appInfo != null)
        {
            mItemView.ItemName.setText(appInfo.getTitle());
            StringBuilder info = new StringBuilder();
            if (appInfo.getMainClassName().length() >= 0)
                info.append("["+appInfo.getMainClassName()+"]");
            if (appInfo.getGoodsSerialNumber().length() >= 0)
            {
                info.append(appInfo.getGoodsSerialNumber());
            }
//            if (appInfo.getFirm().length() >= 0)
//                info.append("廠商:"+appInfo.getFirm());
//            if (appInfo.getInfo().length() >= 0)
//            {
//                if (info.length() > 0)
//                    info.append(", ");
//                info.append(appInfo.getInfo());
//            }
            mItemView.ItemInfo.setText(info.toString());
            if (appInfo.getIconResourceID() != 0)
                mItemView.ItemImage.setImageDrawable(mItemView.ItemImage.getResources().getDrawable(appInfo.getIconResourceID()));
            mItemView.ItemPrice.setText("$"+ appInfo.getPrice());
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
                GoodsItemData childDataItem = (GoodsItemData)currentDataItem.getChild(childIndex);
                if (!childDataItem.getClassification()) // 不為類別身分廠商
                {

                    if (mSearchBarcode.length() != 0 )
                    {
                        if (childDataItem.getBarcode().length() != 0 && childDataItem.getBarcode().equalsIgnoreCase(mSearchBarcode))
                        {
                            arrayListItems.add((GoodsItemData)childDataItem);
                            //return arrayListItems;
                        }
                    }
                    else if (mSearchText.length() != 0)
                    {
                        String searchText = mSearchText.toLowerCase();
                        if (childDataItem.getTitle().toLowerCase().contains(searchText) ||
                                childDataItem.getInfo().toLowerCase().contains(searchText) ||
                                childDataItem.getFirm().toLowerCase().contains(searchText))
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