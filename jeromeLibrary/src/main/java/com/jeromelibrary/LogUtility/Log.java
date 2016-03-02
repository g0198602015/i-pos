package com.jeromelibrary.LogUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;


public class Log 
{
	public enum LogLevel 
	{
		ERROR(0),
		DEBUG(1),
		INFO(2);
		
		public int type;
		LogLevel(int p) 
		{
			type = p;
		}
	}
	//private String filePath;
	private File mLogFile;
	private Writer mWriter;
	private boolean mIsRecord = false;
	private String mFileName = "log";
	private String mFolderPath = "JeromeLibrary";
	private Date mDate = new Date();
	private SimpleDateFormat mSimpleDateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private LogLevel mLogLevel = LogLevel.DEBUG;
	private static Log mLog = null;
	private boolean mIsShowLogcat = true;
	private Log()
	{

	}
	public static Log getInstance()
	{
		if (mLog == null)
			mLog = new Log();
		return mLog;
	}
	public void setShowLogCat(boolean isShow)
	{
		mIsShowLogcat = isShow;
	}
	public boolean getIsShowLogcat()
	{
		return mIsShowLogcat;
	}
	public void SetLogFolderName(String folderName)
	{
		mFolderPath = folderName;
	}
	public void SetLogLevel(LogLevel level)
	{
		mLogLevel = level;
	}
	public boolean WriteLog(String className,String methodName, String msg)
	{
		return write(mIsRecord, mIsShowLogcat, className, methodName, msg + "\n", mLogLevel);
	}

	public boolean WriteLog(String className,String methodName, String msg, boolean enforceRecord, LogLevel logLevel)
	{
		return write(enforceRecord, mIsShowLogcat, className, methodName,  msg, logLevel);
	}
	public boolean WriteLog(String className,String methodName, byte[] msg, LogLevel logLevel)
	{
		return write(mIsRecord, mIsShowLogcat, className, methodName,  msg, logLevel);
	}
	public boolean WriteLog(String className,String methodName, String msg, LogLevel logLevel)
	{
		return write(mIsRecord, mIsShowLogcat, className, methodName,  msg, logLevel);
	}
	public void IsRecord(boolean flag)
	{
		mIsRecord = flag;
	}
	private boolean write(boolean bRecord, boolean isShowLogcat, String className,String methodName, byte[] msg, LogLevel logLevel)
	{
		if (bRecord)
		{
		    File root = Environment.getExternalStorageDirectory();
		    File outDir = new File(root.getAbsolutePath() + File.separator + mFolderPath);
		    if (!outDir.isDirectory())
		    {
		      outDir.mkdir();
		    }
			String fileName = mFileName;
			if (logLevel == LogLevel.ERROR)
				fileName = fileName +".error";
			if (logLevel == LogLevel.DEBUG)
				fileName = fileName +".debug";
			if (logLevel == LogLevel.INFO)
				fileName = fileName +".info";
		    mLogFile = new File(outDir, fileName);

			try
			{
				mWriter = new BufferedWriter(new FileWriter(mLogFile, true));
				mDate = new Date();
				String retStrFormatNowDate = mSimpleDateFormatter.format(mDate);
				StringBuilder strMsg = new StringBuilder();
				for(int i = 0; i < msg.length - 1;i++)
				{
					strMsg.append(String.format("%d,", msg[i]));
				}
				mWriter.write("[" + retStrFormatNowDate + "-> " + className + "->" + methodName + "]: " + strMsg + "\n");
				mWriter.flush();
				mWriter.close();
				return true;
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}
	
	private boolean write(boolean bRecord, boolean isShowLogcat, String className,String methodName, String msg, LogLevel logLevel)
	{
		if (isShowLogcat)
			android.util.Log.i("LogUtility",  className + "->" + methodName + "]: " + msg + "\n");
		if (bRecord)
		{
		    File root = Environment.getExternalStorageDirectory();
		    File outDir = new File(root.getAbsolutePath() + File.separator + mFolderPath);
		    if (!outDir.isDirectory()) 
		    {
		      outDir.mkdir();
		    }
			String fileName = mFileName;
			if (logLevel == LogLevel.ERROR)
				fileName = fileName +".error";
			if (logLevel == LogLevel.DEBUG)
				fileName = fileName +".debug";
			if (logLevel == LogLevel.INFO)
				fileName = fileName +".info";
		    mLogFile = new File(outDir, fileName);

			try 
			{
				mWriter = new BufferedWriter(new FileWriter(mLogFile, true));
				mDate = new Date();
				String retStrFormatNowDate = mSimpleDateFormatter.format(mDate);
					
				mWriter.write("[" + retStrFormatNowDate + "-> " + className + "->" + methodName + "]: " + msg + "\n");
				mWriter.flush();
				mWriter.close();
				return true;
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;		
		
	}
}
