package com.jeromelibrary.Utility;

import java.io.File;


import android.content.Intent;
import android.net.Uri;

public class IntentFactory
{
	public static Intent getIntent(IntentType intentType,String param)
	{
		if (intentType == IntentType.AUDIO)
			return getAudioFileIntent(param);
		else if (intentType == IntentType.CHM)
			return getChmFileIntent(param);
		else if (intentType == IntentType.EXCEL)
			return getExcelFileIntent(param);
		else if (intentType == IntentType.PDF)
			return getPdfFileIntent(param);
		else if (intentType == IntentType.PHOTO)
			return getImageFileIntent(param);
		else if (intentType == IntentType.PPT)
			return getPptFileIntent(param);
		else if (intentType == IntentType.TEXT)
			return getTextFileIntent(param,false);
		else if (intentType == IntentType.VIDEO)
			return getVideoFileIntent(param);
		else if (intentType == IntentType.WEB)
			return getWebIntent(param);
		else if (intentType == IntentType.WORD)
			return getWordFileIntent(param);
		else if (intentType == IntentType.MARKET)
			return getMarketIntent(param);
		else 
			return null;
			
	}
	// Android obtain the intent to open the HTML file 
	public static Intent getHtmlFileIntent (String param) 
	{ 
		Uri uri = Uri.parse (param)
					 .buildUpon()
					 .encodedAuthority("com.android.htmlfileprovider")
					 .scheme("content")
					 .encodedPath(param)
					 .build(); 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.setDataAndType (uri, "text/html"); 
		return intent;
	}
	
	//Android obtain the one used to open the picture file intent 
	public static Intent getImageFileIntent (String param) 
	{
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "image/*"); 
		return intent; 
	}
	
	//Android obtain the one used to open the PDF file intent 
	public static Intent getPdfFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "application/pdf"); 
		return intent; 
	}
	
	//Android obtain the intent to open a text file 
	public static Intent getTextFileIntent (String param, boolean paramBoolean) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		if (paramBoolean) 
		{ 
			Uri uri1 = Uri.parse (param); 
			intent.setDataAndType (uri1, "text/plain"); 
		} 
		else
		{ 
			Uri uri2 = Uri.fromFile (new File (param)); 
			intent.setDataAndType (uri2, "text/plain"); 
		} 
		return intent; 
	}
	//Android obtain the intent to open the audio file 
	public static Intent getAudioFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		intent.putExtra ("oneshot", 0); 
		intent.putExtra ("configchange", 0); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "audio/*"); 
		return intent; 
	}
	
	// Android obtain the intent to open a video file 
	public static Intent getVideoFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		intent.putExtra ("oneshot", 0); 
		intent.putExtra ("configchange", 0); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "video/*"); 
		return intent; 
	}
	
	//Android obtain the intent for opening a CHM file 
	public static Intent getChmFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "application/x-chm"); 
		return intent; 
	}
	
	//Android obtain the intent used to open a Word document 
	public static Intent getWordFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "application/msword"); 
		return intent; 
	}
	
	//Android obtain the intet to open app google play stor
	public static Intent getMarketIntent(String param) 
	{
		Intent intent = new Intent(Intent.ACTION_VIEW); 
		intent.setData(Uri.parse(param));
		return intent; 
	}
	//Android obtain the intent to open an Excel file 
	public static Intent getExcelFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "application/vnd.ms-excel"); 
		return intent; 
	}
	//Android obtain the intent to open the PPT file
	public static Intent getPptFileIntent (String param) 
	{ 
		Intent intent = new Intent ("android.intent.action.VIEW"); 
		intent.addCategory ("android.intent.category.DEFAULT"); 
		intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); 
		Uri uri = Uri.fromFile (new File (param)); 
		intent.setDataAndType (uri, "application/vnd.ms-powerpoint"); 
		return intent; 
	}
	//Web
	public static Intent getWebIntent(String param)
	{
		if (!param.startsWith("http://") && param.toLowerCase().startsWith("www"))
			param = "http://"+param;
		Uri webUri = Uri.parse(param);
		Intent intent = new Intent(Intent.ACTION_VIEW, webUri);
		return intent;
	}
	
	public static Intent getEmailEntent(String To, String title, String content)
	{
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
	            "mailto",To, null));
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
//		startActivity(Intent.createChooser(emailIntent, "Send email..."));
		return emailIntent;
	}
	public static enum IntentType 
	{
		NONE(0),
		VIDEO(1),  
		PHOTO(2), 
		WEB(3),
		PDF(4),
		TEXT(5),
		AUDIO(6),
		CHM(7),
		WORD(8),
		EXCEL(9),
		PPT(10),
		MARKET(11);
	    private final int id;
	    private static IntentType[] allValues = values();
	    IntentType(int id) { this.id = id; }
	    public int getValue() { return id; }
	    public static IntentType fromInteger(int n) 
	    {
	    	return allValues[n];
	    }
	    public static String[] names() {
	    	IntentType[] states = values();
	        String[] names = new String[states.length];

	        for (int i = 0; i < states.length; i++) {
	            names[i] = states[i].name();
	        }

	        return names;
	    }
	}
}
