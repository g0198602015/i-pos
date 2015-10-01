package model;

/**
 * Created by Jerome on 2015/8/22.
 * 盤點
 */

public class InventoryDataClass extends BaseDataClass
{
    //庫存數量, 盤點數量, 編號
    int mInventoryCount = 0;//盤點數量
    String mId; //編號

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public int getInventoryCount() {
        return mInventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.mInventoryCount = inventoryCount;
    }

}
