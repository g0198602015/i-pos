package com.CenterFragment.TabFragment.Goods;

import com.LeftFragement.BaseItemData;

import model.GoodsItemData;

/**
 * Created by Jerome on 2015/10/17.
 */
public class GoodsCardRecordData
{
    private static BaseItemData mListViewItems = new BaseItemData("全部",0);
    public static BaseItemData getAllGoodsItem() {
        return mListViewItems;
    }
    public static BaseItemData getGoodsItem(int index)
    {
        if (index < 0 || index > mListViewItems.getChildSize() -1)
            return null;
        return mListViewItems.getChild(index);
    }
    public static void addGoodsItem(BaseItemData item)
    {
        GoodsItemData itemData = (GoodsItemData)contains(item.getSerialIndex());
        if (itemData != null)
        {
           GoodsItemData goodsItemData = (GoodsItemData)itemData;
           int count = goodsItemData.getCount() + ((GoodsItemData)item).getCount();
            itemData.setCount(count);
        }
        else
            mListViewItems.addChild(item);
    }
    public static void removeGoodsItem(BaseItemData item)
    {
        mListViewItems.removeChild(item);
    }
    public static int getGoodsItemSize()
    {
        return mListViewItems.getChildSize();
    }
    public static BaseItemData contains(int serialIndex)
    {
        int size = mListViewItems.getChildSize();
        for (int index = 0 ; index < size ; index++)
        {
            if (mListViewItems.getChild(index).getSerialIndex() == serialIndex)
                return (GoodsItemData)mListViewItems.getChild(index);
        }
        return null;
    }
    public static double getSubTotal()
    {
        double sum = 0;
        for (int index = 0; index < GoodsCardRecordData.getGoodsItemSize(); index++) {
            GoodsItemData itemData = (GoodsItemData) GoodsCardRecordData.getGoodsItem(index);
            double subtotal = itemData.getCount() * itemData.getPrice();
            sum = sum + subtotal;
        }
        return sum;
    }
}
