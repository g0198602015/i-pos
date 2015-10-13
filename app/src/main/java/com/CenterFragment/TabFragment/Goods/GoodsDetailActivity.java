package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        final EditText servicePersionEditText = (EditText)view.findViewById(R.id.goods_detail_service_persion_editText);
        servicePersionEditText.setText(mItem.getServeicePersionName());
        final EditText priceEditText = (EditText)view.findViewById(R.id.goods_detail_price_editText);
        priceEditText.setText(String.valueOf(mItem.getPrice()));
        final EditText discountEditText = (EditText)view.findViewById(R.id.goods_detail_discount_editText);
        discountEditText.setText(String.valueOf(mItem.getDiscount()));
        TextView commitTextView = (TextView)view.findViewById(R.id.goods_detail_commit_textView);
        commitTextView.setText(mItem.getCommit());
        final ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_detail_goods_icon);
        goodsIconImageView.setBackgroundResource(mItem.getIconResourceID());

        final GoodsDetailCartImageView shoppingCartImageView = (GoodsDetailCartImageView)view.findViewById(R.id.goods_detail_cart_imageview);
        shoppingCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, GoodsCartListActivity.class);
                startActivity(intent);
            }
        });

        //製作購物車的動畫
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int timeDuraction = 1000;
        final Animation am = new TranslateAnimation(0,size.x/2,0,-100);
        am.setDuration(timeDuraction);
        am.setRepeatCount(0);
        final AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(am);
        AlphaAnimation alpha=new AlphaAnimation(1,0);
        alpha.setDuration(timeDuraction);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                shoppingCartImageView.invalidate();
            }
        });
        animationSet.addAnimation(alpha);
        LinearLayout shoppingCartButton = (LinearLayout)view.findViewById(R.id.goods_detail_shppoing_cart);
        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsIconImageView.startAnimation(animationSet);
                mItem.setCount(1);
                mItem.setServicePersionName(servicePersionEditText.getText().toString());
                mItem.setPrice(Double.parseDouble(priceEditText.getText().toString()));
                GoodsCartListActivity.addGoodsItem(mItem.clone());


            }
        });
        setContentView(view);
    }

}
