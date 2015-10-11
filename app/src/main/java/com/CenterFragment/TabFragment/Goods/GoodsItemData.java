package com.CenterFragment.TabFragment.Goods;

import com.LeftFragement.BaseItemData;

/**
 * Created by Jerome on 2015/10/5.
 */
public class GoodsItemData extends BaseItemData {

    //名稱, 服務人員, 價格, 折數, 備註
    private int mCount = 0; //購買數量
    private String mName = "";
    private String mServicePersionName = "";
    private double mPrice = 0.0;
    private double mDiscount ;
    private String mCommit = "";
    public GoodsItemData(String title)
    {
        super(title);
    }
    public void setName(String name)
    {
        mName = name;
    }
    public String getName()
    {
        return mName;
    }
    public void setServicePersionName(String persionName)
    {
        mServicePersionName = persionName;
    }
    public String getServeicePersionName()
    {
        return mServicePersionName;
    }
    public void setPrice(double price)
    {
        mPrice = price;
    }
    public double getPrice()
    {
        return mPrice;
    }
    public void setDiscount(double discount)
    {
        mDiscount = discount;
    }
    public double getDiscount()
    {
        return mDiscount;
    }
    public void getCommit(String commit)
    {
        mCommit = commit;
    }
    public String getCommit()
    {
        return mCommit;
    }
    public void setCount(int count)
    {
        mCount = count;
    }
    public int getCount()
    {
        return mCount;
    }
}
