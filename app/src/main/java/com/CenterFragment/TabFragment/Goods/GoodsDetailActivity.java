package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

import jerome.i_pos.R;

public class GoodsDetailActivity extends Activity {

    private GoodsItemData mItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Bundle bundle = getIntent().getExtras();
        mItem = (GoodsItemData)bundle.getSerializable("ListViewData");
        View view = inflater.inflate(R.layout.goods_detail, null);
        TextView toolbarTitle = (TextView)view.findViewById(R.id.goods_detail_toolbar_title);
        toolbarTitle.setText(mItem.getTitle());
        EditText servicePersionEditText = (EditText)view.findViewById(R.id.goods_detail_service_persion_editText);
        servicePersionEditText.setText(mItem.getServeicePersionName());
        EditText priceEditText = (EditText)view.findViewById(R.id.goods_detail_price_editText);
        priceEditText.setText(String.valueOf(mItem.getPrice()));
        EditText discountEditText = (EditText)view.findViewById(R.id.goods_detail_discount_editText);
        discountEditText.setText(String.valueOf(mItem.getDiscount()));
        TextView commitTextView = (TextView)view.findViewById(R.id.goods_detail_commit_textView);
        commitTextView.setText(mItem.getCommit());
        ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_detail_goods_icon);
        goodsIconImageView.setBackgroundResource(mItem.getIconResourceID());
        setContentView(view);

    }

}
