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

import jerome.i_pos.R;

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
//        rootLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 int position = (int)v.getTag();
//                 for (int index = 0 ; index < mDatas.getChildSize() ; index++) {
//                     if (position == 0)
//                         mDatas.getChild(index).setVisible(true);
//                     else if (index == (position - 1))
//                         mDatas.getChild(index).setVisible(true);
//                     else
//                         mDatas.getChild(index).setVisible(false);
//                 }
//                 mCallback.onListViewClickChanged();
//            }
//        });
//        rootLinearLayout.setTag(position);
//        ImageView imageView = (ImageView) containView.findViewById(R.id.leftFragmentListViewItemImageView);
//        final CheckBox checkBox = (CheckBox) containView.findViewById(R.id.leftFragmentListViewItemCheckBox);
        TextView textView = (TextView) containView.findViewById(R.id.left_fragment_classifcationName);
//      Version 1 <可以有多個分類>
//        if (mDatas.getParent() != null )
//        {
//            if (position == 0 )
//            {
//                imageView.setImageResource(R.drawable.go_top);
//                imageView.setVisibility(View.VISIBLE);
//                checkBox.setVisibility(View.GONE);
//                textView.setText(mDatas.getTitle());
//            }
//            else {
//                int index = position -1;
//                imageView.setImageResource(R.drawable.go_bottom);
//                imageView.setVisibility(View.GONE);
//                checkBox.setVisibility(View.VISIBLE);
//                checkBox.setChecked( mDatas.getChild(index).getVisible());
//                checkBox.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int position = (int)v.getTag();
//                        mDatas.getChild(position).setVisible(checkBox.isChecked());
//                        mCallback.onListViewClickChanged();
//                    }
//                });
//                checkBox.setTag(index);
//                textView.setText(mDatas.getChild(index).getTitle());
//
//            }
//        }
//        else if (mDatas.getParent() == null)
//        {
//            imageView.setImageResource(R.drawable.go_bottom);
//            imageView.setVisibility(View.VISIBLE);
//            checkBox.setVisibility(View.GONE);
//            textView.setText(mDatas.getChild(position).getTitle());
//
//        }


        if (mDatas.getParent() == null)
        {
//            imageView.setVisibility(View.VISIBLE);
//            checkBox.setVisibility(View.GONE);

            if (position == 0 )
            {
//                imageView.setImageResource(R.drawable.go_top);
                textView.setText(mDatas.getTitle());
            }
            else {
//                int index = position -1;
//                imageView.setImageResource(R.drawable.go_bottom);
                textView.setText(" "+mDatas.getChild(position - 1).getTitle());
//                imageView.setVisibility(View.GONE);
//                checkBox.setVisibility(View.VISIBLE);
//                checkBox.setChecked( mDatas.getChild(index).getVisible());
//                checkBox.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int position = (int)v.getTag();
//                        mDatas.getChild(position).setVisible(checkBox.isChecked());
//                        mCallback.onListViewClickChanged();
//                    }
//                });
//                checkBox.setTag(index);



            }

        }
        textView.setTypeface(mTypeface);
        return containView;
    }
}
