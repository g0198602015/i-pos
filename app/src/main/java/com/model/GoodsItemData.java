package com.model;

import com.LeftFragement.BaseItemData;

/**
 * Created by Jerome on 2015/10/5.
 */
public class GoodsItemData extends BaseItemData {

    //名稱, 服務人員, 價格, 折數, 備註
    private int mProductID = -1;
    private int mCount = 0; //購買數量
    private String mName = "";
    private String mServicePersionName = "";
    private double mPrice = 0.0;
    private double mDiscount ;
    private String mComment = "";
    private String mFirm = "";
    private String mBarcode = "";
    private String mGoodsSerialNumber = ""; //編號
    public void setGoodsSerialNumber(String serialNumber)
    {
        mGoodsSerialNumber = serialNumber;
    }
    public String getGoodsSerialNumber()
    {
        return mGoodsSerialNumber;
    }
    public GoodsItemData(String title, int serialIndex)
    {
        super(title, serialIndex);
    }
    public GoodsItemData(int serialIndex)
    {
        super(serialIndex);
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
    public void setComment(String commit)
    {
        mComment = commit;
    }
    public String getComment()
    {
        return mComment;
    }
    public void setCount(int count)
    {
        mCount = count;
    }
    public int getCount()
    {
        return mCount;
    }
    public void setFirm(String firm)
    {
        mFirm = firm;
    }
    public String getFirm()
    {
        return mFirm;
    }
    public void setBarcode(String barcode)
    {
        mBarcode = barcode;
    }
    public String getBarcode()
    {
        return mBarcode;
    }
    public void setProductId(int productID)
    {
        mProductID = productID;
    }
    public int getProductID()
    {
        return mProductID;
    }
}
