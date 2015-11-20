package com.LeftFragement;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import i_so.pos.R;

/**
 * Created by Jerome on 2015/9/3.
 */

public class LeftListViewAdapter extends BaseAdapter
{
    LeftFragment.OnLeftFragmentEventListener mCallback = null;
    private LayoutInflater myInflater;
    private BaseItemData mDatas;
    private Context mContext;
    private Typeface mTypeface;
    public LeftListViewAdapter(Context context, BaseItemData datas, LeftFragment.OnLeftFragmentEventListener callback)
    {
        mContext = context;
        myInflater = LayoutInflater.from(mContext);
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/list_view_item_text.TTF");
        mDatas = datas;
        mCallback = callback;
    }
    public void setListViewData(BaseItemData datas)
    {
        mDatas = datas;
    }
    @Override
    public int getCount() {
        return mDatas.getChildSize()+1;
//      Version 1 <可以有多個分類>
//        if (mDatas.getParent() == null)
//            return mDatas.getChildSize();
//        return mDatas.getChildSize()+1;
    }

    @Override
    public Object getItem(int position)
    {
        return mDatas.getChild(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View containView, ViewGroup parent)
    {

        if(containView == null)
        {
            containView = myInflater.inflate(R.layout.left_fragment_list_view_item, null);
        }
        LinearLayout rootLinearLayout = (LinearLayout)containView.findViewById(R.id.left_fragment_listview_root_linearlayout);
        if (position == 0)
            rootLinearLayout.setBackgroundResource(R.color.leftfragment_item_top);
        else
        {
            rootLinearLayout.setBackgroundResource(R.color.leftfragment_item_others);
        }
        TextView textView = (TextView) containView.findViewById(R.id.left_fragment_classifcationName);
        if (mDatas.getParent() == null)
        {

            if (position == 0 )
            {
                textView.setText(mDatas.getTitle());
            }
            else {
                textView.setText(" "+mDatas.getChild(position - 1).getTitle());

            }

        }
        textView.setTypeface(mTypeface);
        return containView;
    }
}
