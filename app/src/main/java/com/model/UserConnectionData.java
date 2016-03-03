package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
    private static boolean readUserConnectionData(String path)
    {
        try
        {
            File file = new File(path);
            if (file.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String loginAspx = br.readLine();
                String cloudService = br.readLine();
                String tokenID = br.readLine();
                br.close();
                UserConnectionData.CreateInstance(loginAspx, cloudService,  tokenID);
                return true;
            }
        }
        catch (IOException e) {
        }
        return false;
    }
    public static UserConnectionData CreateInstance(String filePath)
    {
        readUserConnectionData(filePath);
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
