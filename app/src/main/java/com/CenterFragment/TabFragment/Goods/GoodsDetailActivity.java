package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import jerome.i_pos.R;
import model.ActivityRequestCodeConstant;
import model.BundleConstant;
import model.GoodsItemData;

public class GoodsDetailActivity extends Activity {

    private GoodsItemData mItem = null;
    private View view = null;
    private AnimationSet animationSet;
    private TextView mGoodsCartCountTextView = null;
    private TextView mGoodsCartSubtotalTextView = null;
    private int mType = 0;
    private Context mContext = null;
    final DecimalFormat mFormatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.goods_detail, null);
        mFormatter.applyPattern("#,###");
        Bundle bundle = getIntent().getExtras();
        mItem = (GoodsItemData)bundle.getSerializable("ListViewData");
        mType = bundle.getInt(BundleConstant.TYPE);
        mContext = this;
        final TextView toolbarTitle = (TextView)view.findViewById(R.id.goods_detail_toolbar_title);
        toolbarTitle.setText(mItem.getTitle());

        final TextView priceTextView = (TextView)view.findViewById(R.id.goods_detail_price_TextView);
        String priceStr = mFormatter.format(mItem.getPrice());
        priceTextView.setText(getResources().getText(R.string.goods_detail_price).toString() + " $" + priceStr);

        final TextView subtotalTextView = (TextView)view.findViewById(R.id.goods_detail_subtotal_TextView);
        String subtotalStr = mFormatter.format(mItem.getCount() * mItem.getPrice());
        subtotalTextView.setText(getResources().getText(R.string.goods_detail_subtotal).toString() + " $" + subtotalStr);

        final ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_detail_goods_icon);
        goodsIconImageView.setBackgroundResource(mItem.getIconResourceID());

        final GoodsDetailCartImageView shoppingCartImageView = (GoodsDetailCartImageView)view.findViewById(R.id.goods_detail_cart_imageview);
        shoppingCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, GoodsCartListActivity.class);

                startActivityForResult(intent, ActivityRequestCodeConstant.GOODS_DETAIL_ACTIVITY);
            }
        });
        final EditText countEditText = (EditText)view.findViewById(R.id.goods_detail_count_editText);
        countEditText.setText(String.valueOf(mItem.getCount()));
        countEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.number_calculator);
                final TextView numberView = (TextView)dialog.findViewById(R.id.number);
                numberView.setPaintFlags(numberView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                int value = Integer.parseInt(countEditText.getText().toString());
                numberView.setText(mFormatter.format(value));


                View.OnClickListener listener = new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        String orgValueStr = numberView.getText().toString();

                        String valueStr = v.getTag().toString();
                        if (valueStr.equalsIgnoreCase("+1"))
                        {
                            try
                            {
                                int intValue = mFormatter.parse(numberView.getText().toString()).intValue();

                                intValue = intValue +1;
                                String str = mFormatter.format(intValue);
                                numberView.setText(str);
                            } catch (ParseException e)
                            {
                                numberView.setText(orgValueStr);
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            try
                            {
                                int intValue = mFormatter.parse(numberView.getText().toString()).intValue();
                                String str = mFormatter.format(Integer.parseInt(""+intValue+valueStr));
                                numberView.setText(str);
                            } catch (ParseException e) {
                                numberView.setText(orgValueStr);
                                e.printStackTrace();
                            }
                        }
                    }
                };
                int buttonValue = 0;
                Button button = (Button)dialog.findViewById(R.id.number_0);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_1);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_2);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_3);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_4);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_5);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_6);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_7);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_8);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_9);
                button.setTag(buttonValue++);
                button.setOnClickListener(listener);
                button = (Button)dialog.findViewById(R.id.number_plus);
                button.setTag("+1");
                button.setOnClickListener(listener);
                Button clearButton = (Button)dialog.findViewById(R.id.number_clear);
                clearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        numberView.setText("0");
                    }
                });
                Button backButton = (Button)dialog.findViewById(R.id.number_back);
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String orgValueStr = numberView.getText().toString();
                        int length = numberView.getText().toString().length();
                        if (length > 1)
                        {
                            try
                            {
                                String strValue = numberView.getText().toString();
                                strValue = strValue.replace(",","");
                                length = strValue.length();
                                int intValue = mFormatter.parse(strValue.substring(0, length - 1)).intValue();
                                String str = mFormatter.format(intValue);
                                numberView.setText(str);
                            } catch (ParseException e) {
                                numberView.setText(orgValueStr);
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            numberView.setText("0");
                        }
                    }
                });
                Button okButton = (Button)dialog.findViewById(R.id.number_ok);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        String orgValueStr = numberView.getText().toString();
                        try
                        {
                            int intValue = mFormatter.parse(numberView.getText().toString()).intValue();
                            String str =""+intValue;
                            countEditText.setText(str);
                            double price = mItem.getPrice();
                            double subtotal = intValue * price;
                            String subtotalStr = mFormatter.format(subtotal);
                            subtotalTextView.setText(getResources().getText(R.string.goods_detail_subtotal).toString() + " $" + subtotalStr);
                            dialog.dismiss();
                        } catch (ParseException e) {
                            numberView.setText(orgValueStr);
                            e.printStackTrace();
                        }
                    }
                });
                dialog.show();
            }
        });

        mGoodsCartCountTextView = (TextView)view.findViewById(R.id.goods_detail_goodsCartCount_textivew);
        mGoodsCartCountTextView.setText("(" + GoodsCartRecordData.getGoodsItemSize() + ")");

        mGoodsCartSubtotalTextView = (TextView)view.findViewById(R.id.goods_detail_goodssubtotal_textivew);
        mGoodsCartSubtotalTextView.setText("$" + GoodsCartRecordData.getSubTotal());

        //製作購物車的動畫
        TextView addGoodsTextView =  (TextView)view.findViewById(R.id.goods_detial_addgoods_textview);
        if (mType == 0)
            addGoodsTextView.setText(getResources().getText(R.string.goods_record_detail_addGoods));
        else if (mType == 1)
            addGoodsTextView.setText(getResources().getText(R.string.goods_record_detail_modifyGoods));

        LinearLayout shoppingCartButton = (LinearLayout)view.findViewById(R.id.goods_detail_shppoing_cart);
        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mType == 0) {
                        GoodsItemData goodItemData = (GoodsItemData) mItem.clone();
                        goodItemData.setCount(Integer.parseInt(countEditText.getText().toString()));
                        //goodItemData.setPrice(Double.parseDouble(priceTextView.getText().toString()));
                        goodsIconImageView.startAnimation(animationSet);
                        GoodsCartRecordData.addGoodsItem(goodItemData, false);
                    } else if (mType == 1) {
                        GoodsItemData goodsItem = (GoodsItemData) GoodsCartRecordData.contains(mItem.getSerialIndex());
                        goodsItem.setCount(Integer.parseInt(countEditText.getText().toString()));
                        //goodsItem.setPrice(Double.parseDouble(priceTextView.getText().toString()));
                        goodsIconImageView.startAnimation(animationSet);

                    }
                    mGoodsCartCountTextView.setTextColor(getResources().getColor(R.color.red));
                    mGoodsCartCountTextView.setText("(" + GoodsCartRecordData.getGoodsItemSize() + ")" );
                    mGoodsCartSubtotalTextView.setTextColor(getResources().getColor(R.color.red));
                    mGoodsCartSubtotalTextView.setText("$" + GoodsCartRecordData.getSubTotal());
                } catch (Exception ex) {

                }

            }
        });
        setContentView(view);
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        final ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_detail_goods_icon);
        int[] goodsImageViewlocation = new int[2];
        goodsImageViewlocation[0] = 0 ;
        goodsImageViewlocation[1] = 0;
        goodsIconImageView.getLocationOnScreen(goodsImageViewlocation);

        final GoodsDetailCartImageView shoppingCartImageView = (GoodsDetailCartImageView)view.findViewById(R.id.goods_detail_cart_imageview);
        int[] cartImageViewlocation = new int[2];
        cartImageViewlocation[0] = 0 ;
        cartImageViewlocation[1] = 0;
        shoppingCartImageView.getLocationOnScreen(cartImageViewlocation);
        int delX = cartImageViewlocation[0] - goodsImageViewlocation[0];
        int delY = cartImageViewlocation[1] - goodsImageViewlocation[1];
        int timeDuraction = 1000;
        final Animation am = new TranslateAnimation(0, delX, 0, delY);
        am.setDuration(timeDuraction);
        am.setRepeatCount(0);
        animationSet = new AnimationSet(true);
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
            public void onAnimationEnd(Animation animation)
            {
//                GoodsItemData itemData = (GoodsItemData)GoodsCardRecordData.contains(mItem.getSerialIndex());
//                String countStr = "";
//                if (itemData != null)
//                    countStr = ""+itemData.getCount();
//                mGoodsCartCountTextView.setTextColor(getResources().getColor(R.color.red));
//                mGoodsCartCountTextView.setText("(" + GoodsCardRecordData.getGoodsItemSize() + ")" + countStr);
//                mGoodsCartSubtotalTextView.setTextColor(getResources().getColor(R.color.red));
//                mGoodsCartSubtotalTextView.setText("$" + GoodsCardRecordData.getSubTotal());
//                shoppingCartImageView.invalidate();
                finish();
            }
        });
        animationSet.addAnimation(alpha);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == ActivityRequestCodeConstant.GOODS_DETAIL_ACTIVITY)
        {
            finish();
        }
    }

}
