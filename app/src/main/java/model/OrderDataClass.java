package model;

/**
 * Created by Jerome on 2015/8/22.
 * 打單
 */


public class OrderDataClass extends BaseDataClass
{

    //原價, 特價, 購買數量, 說明, 備註, 「購買」
    double mOriginalCost = 0;   //原價
    double mBargainCost = 0;    //特價
    int mBuyCount = 0;          //購買數量
    String mIllustrateto = "";  //說明
    String mRemarkNote = "";    //備註


    public double getOriginalCost() {
        return mOriginalCost;
    }

    public void setOriginalCost(double originalCost) {
        this.mOriginalCost = originalCost;
    }

    public double getBargainCost() {
        return mBargainCost;
    }

    public void setBargainCost(double bargainCost) {
        this.mBargainCost = bargainCost;
    }

    public int getBuyCount() {
        return mBuyCount;
    }

    public void setBuyCount(int buyCount) {
        this.mBuyCount = buyCount;
    }

    public String getIllustrateto() {
        return mIllustrateto;
    }

    public void setIllustrateto(String mIllustrateto) {
        this.mIllustrateto = mIllustrateto;
    }

    public String getRemarkNote() {
        return mRemarkNote;
    }

    public void setmRemarkNotr(String remarkNote) {
        this.mRemarkNote = remarkNote;
    }
}
