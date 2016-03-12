package com.i_so;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.CenterFragment.BaseFragment;
import com.CenterFragment.TabFragment.Goods.GoodsDetailActivity;

import com.model.GoodsItemAllData;
import com.LeftFragement.LeftFragment;
import com.LeftFragement.BaseItemData;
import com.RightFragment.RightFragment;
import com.CenterFragment.SlidingMenu;
import com.CenterFragment.CenterFragmenet;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.model.BundleConstant;
import com.model.GoodsItemData;

import i_so.pos.R;

public class MainActivity extends AppCompatActivity implements LeftFragment.OnLeftFragmentEventListener, BaseFragment.BaseFragmentEventListener
{
    SlidingMenu mSlidingMenu;
    LeftFragment mLeftFragment;
    RightFragment mRightFragment;
    CenterFragmenet mCenterFragmenet;
//    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
    }

    private void initListener()
    {
        mSlidingMenu.setCanSliding(false,false);
    }
    private void init()
    {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
        mSlidingMenu.setLeftView(getLayoutInflater().inflate(
                R.layout.left_fragment, null));
        mSlidingMenu.setRightView(getLayoutInflater().inflate(
                R.layout.right_fragment, null));
        mSlidingMenu.setCenterView(getLayoutInflater().inflate(
                R.layout.center_frame, null));

        FragmentTransaction t = this.getSupportFragmentManager()
                .beginTransaction();

        mLeftFragment = new LeftFragment();
        t.replace(R.id.left_frame, mLeftFragment);

        mRightFragment = new RightFragment();
        t.replace(R.id.right_frame, mRightFragment);

        Bundle extras = getIntent().getExtras();
        //int type = extras.getInt("Type", -1);
        mCenterFragmenet = new CenterFragmenet();
        mCenterFragmenet.setArguments(extras);
        t.replace(R.id.center_frame, mCenterFragmenet);
        t.commit();
    }

    public void showLeft() {
        mSlidingMenu.showLeftView();
    }

    public void showRight() {
        mSlidingMenu.showRightView();
    }

    public void onListViewClickChanged() {
        CenterFragmenet articleFrag = (CenterFragmenet)
                getSupportFragmentManager().findFragmentById(R.id.center_frame);

        if (articleFrag != null) {
            articleFrag.updateFragmentData("", "");
        }
    }
    private boolean bInitial = true;
    public void onListViewDataChanged(BaseItemData items) {
        if (bInitial) {
            LeftFragment articleFrag = (LeftFragment)
                    getSupportFragmentManager().findFragmentById(R.id.left_frame);

            if (articleFrag != null) {
                articleFrag.setListViewData(items);
            }
            bInitial = false;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE && data != null)
        {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult != null)
            {
                String result = scanResult.getContents();
                GoodsItemData goodsItemData = (GoodsItemData) GoodsItemAllData.getGoodsItemRecordsData();
                GoodsItemData resultItemData = IsContainsBarcode(goodsItemData, result);
                if (resultItemData != null)
                {
                    ShowGoodsDetailActivity(resultItemData);
                }
            }
        }
    }
    private GoodsItemData IsContainsBarcode(BaseItemData currentDataItem,String barcode)
    {
        if (currentDataItem.getVisible())
        {
            int childSize = currentDataItem.getChildSize();
            for (int childIndex = 0; childIndex < childSize; childIndex++)
            {
                GoodsItemData childDataItem = (GoodsItemData)currentDataItem.getChild(childIndex);
                if (!childDataItem.getClassification()) // 不為類別身分廠商
                {
                    if (barcode.length() != 0 )
                    {
                        if (childDataItem.getBarcode().length() != 0 && childDataItem.getBarcode().equalsIgnoreCase(barcode))
                        {
                            return childDataItem;
                        }
                    }
                }
                else
                {
                    GoodsItemData data =  IsContainsBarcode((GoodsItemData) childDataItem, barcode);
                    if (data != null)
                        return data; //類別的話, 繼續往下挖
                }
            }
        }
        return null;
    }
    private void ShowGoodsDetailActivity(GoodsItemData goodsItemData) {
        Intent intent = new Intent();
        intent.setClass(this, GoodsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleConstant.TYPE, 0);
        bundle.putSerializable("ListViewData", goodsItemData);
        intent.putExtras(bundle);
        startActivityForResult(intent, ActivityRequestConstants.GOODS_FRAGMENT);
    }
}
