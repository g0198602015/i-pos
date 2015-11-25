package model;

import org.w3c.dom.Document;
/**
 * Created by Jerome on 2015/11/17.
 */
public class UserConnectionData
{
    private static int mBranchID = 1;
    private static String mTokenID = "";
    private static int mEmployeeID = 0;
    private static String mCloudService = "";
    private static UserConnectionData data = null;
    private UserConnectionData(String cloudService,  String tokenID)
    {
        mCloudService = cloudService;
        mTokenID = tokenID;
    }
    public static UserConnectionData CreateInstance(String cloudService,  String tokenID)
    {
        if (data == null)
        {
            data = new UserConnectionData(cloudService, tokenID);
        }
        return data;
    }
    public static int getBranchID()
    {
        return mBranchID;
    }
    public static String getTokenID()
    {
        return mTokenID;
    }
    public static int getEmployeeID()
    {
        return mEmployeeID;
    }
    public static String getCloudService()
    {
        return mCloudService;
    }

}
