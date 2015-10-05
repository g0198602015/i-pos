package com.CenterFragment.TabFragment;

import android.support.v4.app.Fragment;

import com.LeftFragement.BaseItemData;
/**
 * Created by Jerome on 2015/8/8.
 */
public abstract class BaseFragment extends Fragment {

    protected BaseFragmentEventListener mCallback;
    // Container Activity must implement this interface
    public interface BaseFragmentEventListener {
        public void onListViewDataChanged(BaseItemData items);
    }
//    private ArrayList<BaseDataClass> mData;
    private String mTitle = "";
    private String mSearchText = "";
//    private int mIndicatorColor = Color.BLUE;
//    private int mDividerColor = Color.GRAY;
//    private Bitmap mBitmap = null;
    public String getTitle()
    {
        return mTitle;
    }
    public void setTitle(String title)
    {
        this.mTitle = title;
    }
    public void setSearchText(String searchText)
    {
        mSearchText = searchText;
    }
    public String getSearchText()
    {
        return mSearchText;
    }
    public abstract void refreshListViewData();
//    public int getmIndicatorColor()
//    {
//
//        return mIndicatorColor;
//    }
//    public void setmIndicatorColor(int mIndicatorColor)
//    {
//        this.mIndicatorColor = mIndicatorColor;
//    }
//    public int getmDividerColor()
//    {
//        return mDividerColor;
//    }
//    public void setmDividerColor(int mDividerColor)
//    {
//        this.mDividerColor = mDividerColor;
//    }
//    public void setIcon(Bitmap bitmap)
//    {
//        mBitmap = bitmap;
//    }
//    public Bitmap getIcon()
//    {
//        return mBitmap;
//    }
//    public ArrayList<BaseDataClass> getData() {
//        return mData;
//    }

//    public void setData(ArrayList<BaseDataClass> data) {
//        this.mData = data;
//    }
}