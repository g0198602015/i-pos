package com.model;

/**
 * Created by Jerome on 2015/11/17.
 */
public class UserConnectionData
{
    private String mLoginAspx ="";
    private int mBranchID = 1;
    private String mTokenID = "";
    private int mEmployeeID = 0;
    private String mCloudService = "";
    private static UserConnectionData mUserConnectionData = null;
    private UserConnectionData(String loginAspx, String cloudService,  String tokenID)
    {
        mLoginAspx = loginAspx;
        mCloudService = cloudService;
        mTokenID = tokenID;
    }
    public static UserConnectionData getInstance()
    {
        return mUserConnectionData;
    }
    public static UserConnectionData CreateInstance(String loginAspx, String cloudService,  String tokenID)
    {
        mUserConnectionData = new UserConnectionData(loginAspx, cloudService, tokenID);
        return mUserConnectionData;
    }
    public int getBranchID()
    {
        return mBranchID;
    }
    public String getTokenID()
    {
        return mTokenID;
    }
    public int getEmployeeID()
    {
        return mEmployeeID;
    }
    public String getCloudService()
    {
        return mCloudService;
    }
    public String getLoginAspx()
    {
        return mLoginAspx;
    }

}
