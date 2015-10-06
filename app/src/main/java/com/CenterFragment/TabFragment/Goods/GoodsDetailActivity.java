package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

import jerome.i_pos.R;

public class GoodsDetailActivity extends Activity {

    private GoodsItemData mItem = null;
    private Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.goods_detail, null);
        Bundle bundle = getIntent().getExtras();
        mItem = (GoodsItemData)bundle.getSerializable("ListViewData");
        mContext = this;
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

        ImageButton shoppingCartButton = (ImageButton)view.findViewById(R.id.goods_detail_shppoing_cart);
        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(mContext, GoodsRecordActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("Type", 0);
//                bundle.putSerializable("ListViewData", (BaseItemData) mGoodsListViewAdapter.getItem(position));
//                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });
        setContentView(view);
    }

}
