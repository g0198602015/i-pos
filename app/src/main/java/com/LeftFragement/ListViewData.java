package com.LeftFragement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jerome on 2015/9/3.
 */
public class ListViewData implements Serializable
{

    private boolean mClassification; //判斷有無子項目
    private String mTitle;
    private String mInfo;
    private ListViewData mParent;
    private ArrayList<ListViewData> mChilds;
    private boolean mVisible;
    private int mIconId=0;
    public ListViewData(String title)
    {
        mTitle = title;
        mInfo = "";
        mParent = null;
        mChilds = new ArrayList<ListViewData>();
        mVisible = true;
        mClassification = false;
    }
    public ListViewData setIconResourceID(int id)
    {
        mIconId = id;
        return this;
    }
    public int getIconResourceID()
    {
        return mIconId;
    }
    public String getTitle()
    {
        return mTitle;
    }
    public ListViewData setParent(ListViewData parent)
    {
        mParent = parent;
        return this;
    }
    public ListViewData getParent()
    {
        return mParent;
    }
    public ListViewData addChild(ListViewData child)
    {
        setClassification(true);
        mChilds.add(child);
        return this;
    }
    public int getChildSize()
    {
        return mChilds.size();
    }
    public ListViewData getChild(int index)
    {
        return mChilds.get(index);
    }
    public ListViewData setInfo(String info)
    {
        mInfo = info;
        return this;
    }
    public String getInfo()
    {
        return mInfo;
    }
    public ListViewData setVisible(boolean visible)
    {

        mVisible = visible;
        return this;
    }
    public boolean getVisible()
    {
        return mVisible;
    }

    public boolean getClassification()
    {
        return mClassification;
    }
    public ListViewData setClassification(boolean isClassification)
    {
        mClassification = isClassification;
        return this;
    }
    public ListViewData setTitle(String title)
    {
        mTitle = title;
        return this;
    }
}
