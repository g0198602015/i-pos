package model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Jerome on 2015/8/20.
 */
public class BaseDataClass implements Serializable
{
    //：圖示, 名稱, 廠商, 庫存,
    private Bitmap mItemIcon = null; //商品圖示
    private String mTitle = "";      //商品名稱
    private String mSupplier = "";      //廠商
    private int mStock = 0;          //庫存
    public void setItemIcon(Bitmap bitmap)
    {
        mItemIcon = bitmap;
    }
    public Bitmap getItemIcon()
    {
        return mItemIcon;
    }
    public void setTitle(String title)
    {
        mTitle = title;
    }
    public String getTitle()
    {
        return mTitle;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public void setSupplier(String supplier) {
        mSupplier = supplier;
    }

    public int getStock() {
        return mStock;
    }

    public void setStock(int count) {
        mStock = count;
    }
}
