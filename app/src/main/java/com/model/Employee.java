package com.model;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Jerome on 2016/3/8.
 */
public class Employee {
    private int mID = 0; // <ID>int</ID>
    private String mCodeNumber; //<編號>string</編號>
    private String mName; //<姓名>string</姓名>
    private String mNickName; //<名稱>string</名稱>
    private String mUID; //<身分證>string</身分證>
    private static Employee mEmployee = null;
    private Employee(int id, String nickname)
    {
        mID = id;
        mNickName = nickname;
    }
    public static Employee CreateInstance(String id, String nickname)
    {
        mEmployee = new Employee(Integer.parseInt(id), nickname);
        return mEmployee;
    }
    public static Employee CreateInstance(String filePath)
    {
        mEmployee = readEmpolyeeData(filePath);
        return mEmployee;
    }
    public static Employee getInstance()
    {
        return mEmployee;
    }
    private static Employee readEmpolyeeData(String path)
    {
        try
        {
            File file = new File(path);
            if (file.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String id = br.readLine();
                String nickname = br.readLine();
                br.close();
                return  Employee.CreateInstance(id, nickname);
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return null;
    }
    public void setID(int id) {
        mID = id;
    }

    public int getID()
    {
        return mID;
    }
    public String getCodeNumber()
    {
        return mCodeNumber;
    }
    public String getName()
    {
        return mName;
    }
    public String getNickName()
    {
        return mNickName;
    }
    public String getUID()
    {
        return mUID;
    }

}
