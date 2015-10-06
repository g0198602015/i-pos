package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.CenterFragment.TabFragment.Shippment.NormalRecyclerViewAdapter;
import com.LeftFragement.BaseItemData;

import jerome.i_pos.R;

/**
 * Created by Jerome on 2015/10/5.
 */
public class GoodsRecordActivity extends Activity
{
    private BaseItemData mListViewItems;
    private GoodsRecordsRecyclerViewAdapter mRecyclerViewerAdapter = null;
    private View mainView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(R.layout.goods_record, null);
        setContentView(mainView);
    }
    @Override
    public void onResume()
    {
        try
        {
            super.onResume();
            queryGoodsRecord();
        }
        catch(Exception ex)
        {

        }
    }
    public void queryGoodsRecord()
    {
        if (mListViewItems == null)
            mListViewItems = parseData();
        mRecyclerViewerAdapter = new GoodsRecordsRecyclerViewAdapter(
                this,
                mListViewItems
        );
        mRecyclerViewerAdapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        try {

            if (mainView != null)
            {
                RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.goods_record_recyclerView);
                if (recyclerView != null)
                {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(mRecyclerViewerAdapter);
                }
            }
        }
        catch (Exception ex)
        {
            String str = ex.toString();
            str = str;
        }
    }
    private BaseItemData parseData()
    {
        BaseItemData _RootItemData = new BaseItemData("全部");
        _RootItemData.setParent(null);
        BaseItemData goodItem = createViewData("商品", _RootItemData);
        BaseItemData goodsChildItem = createViewData("商品的子類別", goodItem);
        createViewData("商品 1", goodsChildItem).setIconResourceID(R.drawable.hair_salon_goods).setInfo("商品描述");
        createViewData("商品 2", goodsChildItem).setIconResourceID(R.drawable.hair_salon_goods).setInfo("商品描述");
        BaseItemData operationItem = createViewData("技術操作", _RootItemData);
        BaseItemData operationChildItem = createViewData("促銷折扣", operationItem);
        createViewData("促銷折扣 1", operationChildItem).setIconResourceID(R.drawable.discount).setInfo("折扣描述");
        //睫毛
        operationChildItem = createViewData("睫毛", operationItem);
        createViewData("睫毛 1", operationChildItem).setIconResourceID(R.drawable.mascara2).setInfo("商品描述");
        createViewData("睫毛 2", operationChildItem).setIconResourceID(R.drawable.mascara1).setInfo("商品描述");
        createViewData("睫毛 3", operationChildItem).setIconResourceID(R.drawable.mascara2).setInfo("商品描述");
        createViewData("睫毛 4", operationChildItem).setIconResourceID(R.drawable.mascara1).setInfo("商品描述");

        //剪髮
        operationChildItem = createViewData("剪髮", operationItem);
        createViewData("剪髮 1", operationChildItem).setIconResourceID(R.drawable.scissors).setInfo("商品描述");
        createViewData("剪髮 2", operationChildItem).setIconResourceID(R.drawable.scissors2).setInfo("商品描述");
        createViewData("剪髮 3", operationChildItem).setIconResourceID(R.drawable.scissors).setInfo("商品描述");
        createViewData("剪髮 4", operationChildItem).setIconResourceID(R.drawable.scissors2).setInfo("商品描述");

        //燙髮
        operationChildItem = createViewData("燙髮", operationItem);
        createViewData("燙髮 1", operationChildItem).setIconResourceID(R.drawable.hairdresser).setInfo("商品描述");
        createViewData("燙髮 2", operationChildItem).setIconResourceID(R.drawable.hairdresser2).setInfo("商品描述");
        createViewData("燙髮 3", operationChildItem).setIconResourceID(R.drawable.hairdresser).setInfo("商品描述");
        createViewData("燙髮 4", operationChildItem).setIconResourceID(R.drawable.hairdresser2).setInfo("商品描述");

        //洗髮
        operationChildItem = createViewData("洗髮", operationItem);
        createViewData("洗髮 1", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");
        createViewData("洗髮 2", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");
        createViewData("洗髮 3", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");
        createViewData("洗髮 4", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");

        //染髮
        operationChildItem = createViewData("染髮", operationItem);
        createViewData("染髮 1", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        createViewData("染髮 2", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        createViewData("染髮 3", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        createViewData("染髮 4", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        return _RootItemData;
    }
    public BaseItemData createViewData(String title,BaseItemData parent)
    {
        BaseItemData item = new BaseItemData(title);
        if (parent != null) {
            item.setParent(parent);
            parent.addChild(item);
        }
        return item;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }
}
