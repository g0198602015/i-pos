package com.LeftFragement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jerome on 2015/9/3.
 */
public class BaseItemData implements Serializable, Cloneable
{

    private boolean mClassification; //判斷有無子項目
    private String mClassificationName = "";
    private String mTitle = "";
    private String mInfo = "";
    private String mMainClassName = "";
    private String mSecondClassName = "";
    private BaseItemData mParent = null;
    private ArrayList<BaseItemData> mChilds;
    private boolean mVisible;
    private int mIconId=0;
    private int mSerialIndex=0;
    public BaseItemData(int serialIndex)
    {
        mSerialIndex = serialIndex;
    }
    public BaseItemData(String title, int serialIndex)
    {
        mSerialIndex = serialIndex;
        mTitle = title;
        mInfo = "";
        mParent = null;
        mChilds = new ArrayList<BaseItemData>();
        mVisible = true;
        mClassification = false;
    }
    public BaseItemData setMainClassName(String mainClassName)
    {
        mMainClassName = mainClassName;
        return this;
    }
    public String getMainClassName()
    {
        return mMainClassName;
    }
    public BaseItemData setSecondClassName(String secondClassName)
    {
        mSecondClassName = secondClassName;
        return this;
    }
    public String getSecondClassName()
    {
        return mSecondClassName;
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
        mClassificationName = child.getMainClassName();
        mChilds.add(child);
        return this;
    }
    public String getmClassificationName()
    {
        return mClassificationName;
    }
    public BaseItemData removeChild(BaseItemData child)
    {
        mChilds.remove(child);
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
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch( CloneNotSupportedException e )
        {
            return null;
        }
    }
    public int getSerialIndex()
    {
        return mSerialIndex;
    }

}
