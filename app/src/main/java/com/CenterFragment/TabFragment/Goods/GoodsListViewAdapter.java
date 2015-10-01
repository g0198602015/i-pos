package com.CenterFragment.TabFragment.Goods;

/**
 * Created by Jerome on 2015/8/9.
 */

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeftFragement.ListViewData;

import jerome.i_pos.R;

public class GoodsListViewAdapter extends BaseAdapter
{

    private ArrayList<ListViewData> _DataItems;
//    private ArrayList<HashMap<String, Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private ItemView itemView;
    private String mSearchText="";
    private class ItemView {
        ImageView ItemImage;
        TextView ItemName;
        TextView ItemInfo;
//        Button ItemButton;
    }

    public GoodsListViewAdapter(Context c, ListViewData dataItems, String searchText)
    {
        _DataItems = new ArrayList<ListViewData>();
        mSearchText = searchText;
        //mAppList = new ArrayList<HashMap<String, Object>>();
        parseDataItems(dataItems, _DataItems);
        mContext = c;
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
            itemView = (ItemView) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.goods_fragment_listview_adapter, null);
            itemView = new ItemView();
            itemView.ItemImage = (ImageView)convertView.findViewById(R.id.ItemImage);
            itemView.ItemName = (TextView)convertView.findViewById(R.id.ItemName);
            itemView.ItemInfo = (TextView)convertView.findViewById(R.id.ItemInfo);
//            itemView.ItemButton = (Button)convertView.findViewById(R.id.ItemButton);
            convertView.setTag(itemView);
        }

        ListViewData appInfo = _DataItems.get(position);
        if (appInfo != null) {

//            int mid = (Integer)appInfo.get(keyString[0]);
//            String name = (String) appInfo.get(keyString[1]);
//            String info = (String) appInfo.get(keyString[2]);
//            int bid = (Integer)appInfo.get(keyString[3]);
            itemView.ItemName.setText(appInfo.getTitle());
            itemView.ItemInfo.setText(appInfo.getInfo());
            if (appInfo.getIconResourceID() != 0)
                itemView.ItemImage.setImageDrawable(itemView.ItemImage.getResources().getDrawable(appInfo.getIconResourceID()));
//            itemView.ItemButton.setBackgroundDrawable(itemView.ItemButton.getResources().getDrawable((int) appInfo.get("ItemButtonImage")));
//            itemView.ItemButton.setOnClickListener(new ItemButton_Click(position));

        }

        return convertView;
    }

    private class ItemButton_Click implements OnClickListener {
        private int position;

        ItemButton_Click(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View v) {
            int vid=v.getId();
//            if (vid == itemView.ItemButton.getId())
//                Log.v("ola_log",String.valueOf(position) );
        }
    }
    private  ArrayList<ListViewData> parseDataItems(ListViewData currentDataItem,ArrayList<ListViewData> arrayListItems)
    {
        if (currentDataItem.getVisible())
        {
            int childSize = currentDataItem.getChildSize();
            for (int childIndex = 0; childIndex < childSize; childIndex++)
            {
                ListViewData childDataItem = currentDataItem.getChild(childIndex);
                if (!childDataItem.getClassification()) // 不為類別身分
                {
//                        HashMap<String, Object> item = new HashMap<String, Object>();
//                        item.put("ItemImage", childDataItem.getIconResourceID());
//                        item.put("ItemName", childDataItem.getTitle());
//                        item.put("ItemInfo", childDataItem.getInfo());
//                        item.put("ItemButtonImage", R.drawable.shopping_cart);
                    if (mSearchText.length() != 0)
                    {
                        if (childDataItem.getTitle().contains(mSearchText) || childDataItem.getInfo().contains(mSearchText))
                            arrayListItems.add(childDataItem);
                    }
                    else
                    {
                        arrayListItems.add(childDataItem);
                    }
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