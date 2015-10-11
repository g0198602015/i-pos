package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.CenterFragment.TabFragment.BaseFragment;

import jerome.i_pos.R;

public class GoodsCartDetailActivity extends Activity {

    private GoodsItemData mItem = null;
    private Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.goods_cart_detail, null);
        Bundle bundle = getIntent().getExtras();
        mItem = (GoodsItemData)bundle.getSerializable("ListViewData");
        mContext = this;
        TextView toolbarTitle = (TextView)view.findViewById(R.id.goods_record_detail_toolbar_title);
        toolbarTitle.setText(mItem.getTitle());

        TextView servicePersionEditText = (TextView)view.findViewById(R.id.goods_record_detail_service_persion_editText);
        servicePersionEditText.setText(mItem.getServeicePersionName());

        TextView sumpriceTextView = (TextView)view.findViewById(R.id.goods_record_detail_sumprice_textView);
        sumpriceTextView.setText(String.valueOf(mItem.getCount() * mItem.getDiscount()));

        TextView discountTextView = (TextView)view.findViewById(R.id.goods_record_detail_discount_TextView);
        discountTextView.setText(String.valueOf(mItem.getDiscount()));

        TextView commentTextView = (TextView)view.findViewById(R.id.goods_record_detail_commit_textView);
        commentTextView.setText(mItem.getCommit());

        TextView countTextView = (TextView)view.findViewById(R.id.goods_record_detail_count_TextView);
        countTextView.setText(String.valueOf(mItem.getCount()));

        ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_record_detail_goods_icon);
        goodsIconImageView.setBackgroundResource(mItem.getIconResourceID());

        setContentView(view);
    }
}
