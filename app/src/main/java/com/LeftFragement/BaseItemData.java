package com.LeftFragement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jerome on 2015/9/3.
 */
public class BaseItemData implements Serializable
{

    private boolean mClassification; //判斷有無子項目
    private String mTitle;
    private String mInfo;
    private BaseItemData mParent;
    private ArrayList<BaseItemData> mChilds;
    private boolean mVisible;
    private int mIconId=0;
    public BaseItemData(String title)
    {
        mTitle = title;
        mInfo = "";
        mParent = null;
        mChilds = new ArrayList<BaseItemData>();
        mVisible = true;
        mClassification = false;
    }
    public BaseItemData setIconResourceID(int id)
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
    public BaseItemData setParent(BaseItemData parent)
    {
        mParent = parent;
        return this;
    }
    public BaseItemData getParent()
    {
        return mParent;
    }
    public BaseItemData addChild(BaseItemData child)
    {
        setClassification(true);
        mChilds.add(child);
        return this;
    }
    public int getChildSize()
    {
        return mChilds.size();
    }
    public BaseItemData getChild(int index)
    {
        return mChilds.get(index);
    }
    public BaseItemData setInfo(String info)
    {
        mInfo = info;
        return this;
    }
    public String getInfo()
    {
        return mInfo;
    }
    public BaseItemData setVisible(boolean visible)
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
    public BaseItemData setClassification(boolean isClassification)
    {
        mClassification = isClassification;
        return this;
    }
    public BaseItemData setTitle(String title)
    {
        mTitle = title;
        return this;
    }
}
