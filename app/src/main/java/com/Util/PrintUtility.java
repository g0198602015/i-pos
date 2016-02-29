package com.Util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.LeftFragement.BaseItemData;
import com.android.print.sdk.Barcode;
import com.android.print.sdk.PrinterConstants;
import com.android.print.sdk.PrinterInstance;
import com.model.GoodsItemData;

import java.text.SimpleDateFormat;

import i_so.pos.R;
import com.model.GoodsCartRecordData;

/**
 * Created by Jerome on 2015/11/28.
 */
public class PrintUtility
{

    public static void printImage(Resources resources,
                           PrinterInstance mPrinter, String barcodeString) {
        mPrinter.init();

        mPrinter.setFont(0, 0, 0, 0);
        mPrinter.setEncoding("BIG5");
        //LOGO
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_CENTER);
        Bitmap logobitmap = BitmapFactory.decodeResource(resources, R.drawable.logo);
        mPrinter.printImage(logobitmap);
        logobitmap.recycle();
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        //Date
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_RIGHT);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String date = sDateFormat.format(new java.util.Date());
        mPrinter.printText(date);
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        // ======
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_CENTER);
        mPrinter.printText("=============================================");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        //Goods Item
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_LEFT);
        BaseItemData itemData = GoodsCartRecordData.getAllGoodsItem();
        String strBlank = "                    ";
        String strBlank2 = "    ";
        mPrinter.printText("商品名稱" + strBlank + "數量" + strBlank2 + "單價" + strBlank2 + "金額");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        double total = 0;
        strBlank  = strBlank + "        ";

        int blankPixel = calStringPixel(strBlank);
        strBlank2 = strBlank2 + strBlank2;
        int blank2Pixel = calStringPixel(strBlank2);
        for (int index = 0 ; index < itemData.getChildSize() ; index++)
        {
            GoodsItemData childItemData = (GoodsItemData)itemData.getChild(index);
            String info;
            String nameStrng = childItemData.getName();
            int namePixel = calStringPixel(nameStrng);
            String countStrng = ""+childItemData.getCount();
            int countPixel = calStringPixel(countStrng);
            String priceStrng = ""+childItemData.getPrice();
            int pricePixel = calStringPixel(priceStrng);
            info = nameStrng
                    + strBlank.subSequence(0, blankPixel - namePixel)
                    + countStrng
                    + strBlank2.subSequence(0, blank2Pixel - countPixel)
                    + childItemData.getPrice()
                    + strBlank2.subSequence(0, blank2Pixel - pricePixel)
                    + childItemData.getPrice()*childItemData.getCount();
            mPrinter.printText(info);
            mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
            total = total + childItemData.getPrice()*childItemData.getCount();
        }
//        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_LEFT);
//        mPrinter.printText("Item 1");
//        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
//        mPrinter.printText("Item 2");
//        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        //Total money
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_RIGHT);
        mPrinter.printText("總價:$"+total);
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        // ======
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_CENTER);
        mPrinter.printText("=============================================");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        //Barcode Bitmap
        Barcode barcode4 = new Barcode(PrinterConstants.BarcodeType.CODE128, 2, 150, 2, barcodeString);
        mPrinter.printBarCode(barcode4);
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 3);
    }
    private static int calStringPixel(String message)
    {
        int pixel = 0;
        for (int index=0 ; index < message.length() ; index++)
        {
            String str = message.substring(index, index+1);
            if (str.getBytes().length == 3)
                pixel = pixel +2;
            else
                pixel = pixel + 1;
        }
        return pixel;
    }
}
