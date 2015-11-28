package com.CenterFragment.TabFragment.Goods;

/**
 * Created by Jerome on 2015/8/9.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeftFragement.BaseItemData;

import java.util.ArrayList;
import java.util.List;

import i_so.pos.ActivityRequestConstants;
import i_so.pos.R;
import model.BundleConstant;
import model.GoodsCartRecordData;
import model.GoodsItemData;

public class GoodsCartListRecyclerViewAdapter extends RecyclerView.Adapter<GoodsCartListRecyclerViewAdapter.NormalTextViewHolder> {
//    private static ArrayList<BaseItemData> _DataItems;
    private final LayoutInflater mLayoutInflater;
    private static Context mContext;

    public GoodsCartListRecyclerViewAdapter(Context context, BaseItemData dataItems)
    {
//        _DataItems = new ArrayList<BaseItemData>();
//        parseDataItems(dataItems, _DataItems);
        mContext = context;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        //return null;
//        return GoodsCardRecordData.getGoodsItem(position);
//    }
    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.goods_cart_listview_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        GoodsItemData appInfo = (GoodsItemData) GoodsCartRecordData.getGoodsItem(position);
        if (appInfo != null)
        {
            holder.goodsItemData = appInfo;
            holder.itemName.setText(appInfo.getTitle());
//            holder.itemInfo.setText(appInfo.getInfo());
            holder.priceTextView.setText("$" + appInfo.getPrice());
            holder.subTotalTextView.setText("$" + appInfo.getCount() * appInfo.getPrice());
            holder.countTextView.setText("" + appInfo.getCount());
//            GoodsCartListActivity.addSubtotalValue(appInfo.getCount() * appInfo.getPrice());
            if (appInfo.getIconResourceID() != 0)
                holder.itemImage.setImageDrawable(holder.itemImage.getResources().getDrawable(appInfo.getIconResourceID()));
        }
    }

    @Override
    public int getItemCount()
    {
        return GoodsCartRecordData.getGoodsItemSize();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
//        TextView itemInfo;
        TextView plusTextView;
        TextView minusTextView;
        TextView countTextView;
        TextView subTotalTextView;
        TextView priceTextView;
        GoodsItemData goodsItemData;
        List<String> mClickItems;
        NormalTextViewHolder(View view) {
            super(view);
            mClickItems = new ArrayList<>();
            mClickItems.add("修改數量");
            mClickItems.add("拆單");
            mClickItems.add("贈送一個");
            itemImage = (ImageView)view.findViewById(R.id.ItemImage);
            itemName = (TextView)view.findViewById(R.id.ItemName);
//            itemInfo = (TextView)view.findViewById(R.id.ItemInfo);
            minusTextView = (TextView)view.findViewById(R.id.goods_cart_listview_minus_textView);
            plusTextView =  (TextView)view.findViewById(R.id.goods_cart_listview_plus_textView);
            countTextView = (TextView)view.findViewById(R.id.goods_cart_listview_count_textView);
            subTotalTextView =  (TextView)view.findViewById(R.id.goods_cart_listview_subtotal_textView);
            priceTextView = (TextView)view.findViewById(R.id.goods_cart_listview_price_textView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(((Activity) mContext), GoodsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(BundleConstant.TYPE, 1);
                    bundle.putSerializable("ListViewData", goodsItemData);
                    intent.putExtras(bundle);
                    ((Activity) mContext).startActivityForResult(intent, ActivityRequestConstants.GOODS_CART_LIST_RECYCLER_VIEW_ADAPTER);
                }
            });
            LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.goods_cart_listview_root_linearLayout);
            linearLayout.setOnTouchListener(new View.OnTouchListener() {

                private int padding = 0;
                private int initialx = 0;
                private int currentx = 0;
                private View viewHolder;
                private int width;
                public boolean onTouch(View v, MotionEvent event) {
                    if (GoodsCartListActivity.mBSubmit)
                        return true;
                    width = v.getWidth();
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        padding = 0;
                        initialx = (int) event.getX();
                        currentx = (int) event.getX();
                        viewHolder = v;
                    }
                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        currentx = (int) event.getX();
                        padding = currentx - initialx;
                    }

                    if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                        if (padding < 3) //click
                        {

                            if (GoodsCartRecordData.contains(goodsItemData.getSerialIndex()) != null)
                            {
                                new AlertDialog.Builder(mContext).setItems(mClickItems.toArray(new String[mClickItems.size()]), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        if (which == 0) //修改數量
                                        {
                                            Intent intent = new Intent();
                                            intent.setClass(((Activity) mContext), GoodsDetailActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt(BundleConstant.TYPE, 1);
                                            bundle.putSerializable("ListViewData", goodsItemData);
                                            intent.putExtras(bundle);
                                            ((Activity) mContext).startActivityForResult(intent, ActivityRequestConstants.GOODS_CART_LIST_RECYCLER_VIEW_ADAPTER);
                                        }
                                        else if (which == 1) //拆單
                                        {
                                            int count = Integer.parseInt(countTextView.getText().toString());
                                            if (count > 1) // 數量大於兩個才可以拆單
                                            {
                                                //Org
                                                GoodsItemData newGoodsItemData = (GoodsItemData)goodsItemData.clone();
                                                goodsItemData.setCount( goodsItemData.getCount() - 1);
                                                newGoodsItemData.setCount(1);
                                                GoodsCartListActivity.addItem(newGoodsItemData, true);
                                            }

                                        }
                                        else if (which == 2) //贈送一個
                                        {
                                            GoodsItemData newGoodsItemData = (GoodsItemData)goodsItemData.clone();
                                            newGoodsItemData.setCount(1);
                                            newGoodsItemData.setPrice(0);
                                            GoodsCartListActivity.addItem(newGoodsItemData, true);
                                        }

                                    }
                                })
                                .show();

                            }
                        }
                        padding = 0;
                        initialx = 0;
                        currentx = 0;
                    }

                    if (viewHolder != null) {
                        if (padding > (width*0.30))
                        {
                            GoodsCartListActivity.removeItem((GoodsItemData) goodsItemData);
                            GoodsCartListActivity.updateSubTotalValue();
                        }
//                        if (padding > 75) {
//                            viewHolder.setRunning(true);
//                        }
//                        if (padding < -75) {
//                            viewHolder.setRunning(false);
//                        }
                        v.setPadding(padding, 0, 0, 0);
                    }
                    return true;
                }
            });
            minusTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int count = Integer.parseInt(countTextView.getText().toString());
                        count = count - 1;
                        if (count <= 0) {
                            GoodsCartListActivity.removeItem((GoodsItemData) goodsItemData);
                        } else {
                            goodsItemData.setCount(count);
                            countTextView.setText("" + count);
                            double price = Double.parseDouble(priceTextView.getText().toString().replace("$", ""));
                            subTotalTextView.setText("$" + count * price);
                        }
                        GoodsCartListActivity.updateSubTotalValue();

                    } catch (Exception ex) {

                    }
                }
            });
            plusTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int count = Integer.parseInt(countTextView.getText().toString());
                        count = count + 1;
                        goodsItemData.setCount(count);
                        countTextView.setText("" + count);
                        double price = Double.parseDouble(priceTextView.getText().toString().replace("$", ""));
                        subTotalTextView.setText("$" + count * price);
//                        GoodsCartListActivity.addSubtotalValue(price);
                        GoodsCartListActivity.updateSubTotalValue();
                    } catch (Exception ex) {

                    }
                }
            });
        }
    }

//    private  ArrayList<BaseItemData> parseDataItems(BaseItemData currentDataItem,ArrayList<BaseItemData> arrayListItems)
//    {
//        if (currentDataItem.getVisible())
//        {
//            int childSize = currentDataItem.getChildSize();
//            for (int childIndex = 0; childIndex < childSize; childIndex++)
//            {
//                BaseItemData childDataItem = currentDataItem.getChild(childIndex);
//                if (!childDataItem.getClassification()) // 不為類別身分
//                {
//                    arrayListItems.add(childDataItem);
//                }
//                else
//                {
//                    parseDataItems(childDataItem, arrayListItems); //類別的話, 繼續往下挖
//                }
//            }
//        }
//        return arrayListItems;
//    }
}