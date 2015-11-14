package com.CenterFragment.TabFragment.Goods;

import com.LeftFragement.BaseItemData;

import model.GoodsItemData;

/**
 * Created by Jerome on 2015/10/18.
 */
public class GoodsItemRecordsData {
    private static int serialIndex = 0;
    private static GoodsItemData mGoodsItemData = new GoodsItemData("顯示全商品", ++serialIndex);
    public static GoodsItemData getChild(int childIndex)
    {
        return (GoodsItemData)mGoodsItemData.getChild(childIndex);
    }
    public static void addChild(GoodsItemData item)
    {
        mGoodsItemData.addChild(item);
    }
    public static int getChildSize()
    {
        return mGoodsItemData.getChildSize();
    }
    public static BaseItemData getGoodsItemRecordsData()
    {
        return mGoodsItemData;
    }
}
