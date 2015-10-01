package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeftFragement.ListViewData;

import jerome.i_pos.R;

public class GoodsDetailActivity extends Activity {

    private ListViewData mItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Bundle bundle = getIntent().getExtras();
        mItem = (ListViewData)bundle.getSerializable("ListViewData");
        View view = inflater.inflate(R.layout.goods_detail, null);
        TextView toolbarTitle = (TextView)view.findViewById(R.id.goods_detail_toolbar_title);
        toolbarTitle.setText(mItem.getTitle());
        TextView textView1 = (TextView)view.findViewById(R.id.goods_detail_textview1);
        TextView textView2 = (TextView)view.findViewById(R.id.goods_detail_textview2);
        int type = bundle.getInt("Type");
        if (type == 0)
        {
            textView1.setText("庫存數量");
            textView2.setText("盤點數量");
        }
        TextView infoTextView = (TextView)view.findViewById(R.id.goods_detail_info_textview);
        infoTextView.setText(mItem.getInfo());
        ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_detail_goods_icon);
        goodsIconImageView.setBackgroundResource(mItem.getIconResourceID());
        setContentView(view);

    }

}
