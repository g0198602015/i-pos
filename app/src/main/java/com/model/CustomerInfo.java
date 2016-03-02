package com.model;

/**
 * Created by Jerome on 2016/3/2.
 */
public class CustomerInfo
{
    public int mID = 0;
    public String mName = "";
    public String mDescription = "";
    public String mLastConsumeDay = "";
    public String mBirthday = "";
    public int mColor = 0;
    public void setID(int id)
    {
        mID = id;
    }
    public int getID()
    {
        return mID;
    }
    public void setName(String name)
    {
        mName = name;
    }
    public String getName()
    {
        return mName;
    }
    public void setDescription(String description)
    {
        mDescription = description;
    }
    public String getDescription()
    {
        return mDescription;
    }

    public void setLastConsumeDay(String lastConsumeDay)
    {
        mLastConsumeDay = lastConsumeDay;
    }

    public String getmLastConsumeDay()
    {
        return mLastConsumeDay;
    }

    public void setBirthday(String birthday)
    {
        mBirthday = birthday;
    }
    public String getmBirthday()
    {
        return mBirthday;
    }
    public void setColor(int color)
    {
        mColor = color;
    }
    public int getColor()
    {
        return mColor;
    }



}
