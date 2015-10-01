package model;

/**
 * Created by Jerome on 2015/8/22.
 * 進貨
 */
public class PurchaseDataClass extends BaseDataClass
{
    int mPurchaseCount = 0; //進貨數量
    double mUnitCost = 0;   //單位成本
    double mSubtotal = 0;   //小計
    String mId = "";        //編號
    public int getPurchaseCount() {
        return mPurchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.mPurchaseCount = purchaseCount;
    }

    public double getUnitCost() {
        return mUnitCost;
    }

    public void setmUnitCost(double unitCost) {
        this.mUnitCost = unitCost;
    }

    public double getSubtotal() {
        return mSubtotal;
    }

    public void setmSubtotal(double subtotal) {
        this.mSubtotal = subtotal;
    }

    public String getId() {
        return mId;
    }

    public void setmId(String id) {
        this.mId = id;
    }

}
