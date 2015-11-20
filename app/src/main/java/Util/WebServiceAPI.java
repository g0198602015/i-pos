package Util;

import model.GoodsItemData;

import model.GoodsItemAllData;
import com.LeftFragement.BaseItemData;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import i_so.pos.R;

/**
 * Created by Jerome on 2015/10/12.
 */
public class WebServiceAPI
{
    private static List<OnProductDataReceivedListener> _OnProductDataReceivedListener = new ArrayList<OnProductDataReceivedListener>();
    public interface OnProductDataReceivedListener
    {
        abstract void onDataReceive(long total, long current);
    }
    public static void addProductDataReceivedListener(OnProductDataReceivedListener listener)
    {
        _OnProductDataReceivedListener.add(listener);
    }
    public static void removeProductDataReceivedListener(OnProductDataReceivedListener listener)
    {
        _OnProductDataReceivedListener.remove(listener);
    }
    public static void clearProductDataReceivedListener()
    {
        _OnProductDataReceivedListener.clear();
    }
//    private static final String SOAP_ACTION1 = "http://tempuri.org/HelloWorld"; //Web Services命名空間+函數名稱

//    private static final String HelloWorldmethod = "HelloWorld";　//要呼叫的函數名稱

    private static final String NAMESPACE = "http://tempuri.org/"; //Web Services命名空間

    //private static final String URL = "http://zoom-world.tw/WuchDemo/CloudService.asmx"; //Web Services的網址
    
    public WebServiceAPI()
    {

    }


    public static String SaveConsumeSetting2(String url, int branchID, int employeeID, String token, BaseItemData goodsCartData)
    {
        String methodName = "SaveConsumeSetting2";
        String soapAction = NAMESPACE+methodName;
        try{


            SoapObject request1 = new SoapObject(NAMESPACE, methodName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request1);
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            //Web method call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(url, 30000);
            envelope.dotNet = true;
            request1.addProperty("BranchID", branchID);
            request1.addProperty("EmployeeID", employeeID);
            request1.addProperty("token", token);
            SoapObject products = new SoapObject(NAMESPACE, "Products");

    //            SoapObject serviceItems = new SoapObject();
    //            SoapObject serviceItem = new SoapObject();
    //            serviceItem.addProperty("部門","Jerome");
    //            serviceItem.addProperty("統計類別","Jerome");
    //            serviceItem.addProperty("保留工時",123);
    //            serviceItem.addProperty("標準工時",123);
    //            serviceItem.addProperty("是否折扣",true);
    //            serviceItem.addProperty("是否折操作點",true);
    //            serviceItem.addProperty("是否折設計點",true);
    //            serviceItem.addProperty("是否於組合中顯示",true);
    //            serviceItem.addProperty("是否應稅",true);
    //            serviceItem.addProperty("單價1",123.0);
    //            serviceItem.addProperty("單價2",123.0);
    //            serviceItem.addProperty("單價3",123.0);
    //            serviceItem.addProperty("單價4",123.0);
    //            serviceItem.addProperty("單價5",123.0);
    //            serviceItem.addProperty("單價6",123.0);
    //            serviceItem.addProperty("單價7",123.0);
    //            serviceItem.addProperty("單價8",123.0);
    //            serviceItem.addProperty("單價9",123.0);
    //            serviceItem.addProperty("單價10",123.0);
    //            serviceItem.addProperty("操作點數1",10.2);
    //            serviceItem.addProperty("操作點數2",10.2);
    //            serviceItem.addProperty("操作點數3",10.2);
    //            serviceItem.addProperty("操作點數4",10.2);
    //            serviceItem.addProperty("操作點數5",10.2);
    //            serviceItem.addProperty("操作點數6",10.2);
    //            serviceItem.addProperty("操作點數7",10.2);
    //            serviceItem.addProperty("操作點數8",10.2);
    //            serviceItem.addProperty("操作點數9",10.2);
    //            serviceItem.addProperty("操作點數10",10.2);
    //            serviceItem.addProperty("操作點數抽成百分比",10.2);
    //            serviceItem.addProperty("設計點數1",10.2);
    //            serviceItem.addProperty("設計點數2",10.2);
    //            serviceItem.addProperty("設計點數3",10.2);
    //            serviceItem.addProperty("設計點數4",10.2);
    //            serviceItem.addProperty("設計點數5",10.2);
    //            serviceItem.addProperty("設計點數6",10.2);
    //            serviceItem.addProperty("設計點數7",10.2);
    //            serviceItem.addProperty("設計點數8",10.2);
    //            serviceItem.addProperty("設計點數9",10.2);
    //            serviceItem.addProperty("設計點數10",10.2);
    //            serviceItem.addProperty("設計點數抽成百分比",10.2);
    //            serviceItem.addProperty("設計點數公式","10.2");
    //            serviceItem.addProperty("服務人員ID",12);
    //            serviceItem.addProperty("手動自訂價格",12.5);
    //            serviceItem.addProperty("折扣",12.5);
    //            serviceItem.addProperty("指定服務",true);
    //            serviceItem.addProperty("是否完成",true);
    //            serviceItem.addProperty("銷售備註","111true");
    //            serviceItem.addProperty("TempConsumeDetailRecordID",123);
    //            serviceItem.addProperty("OwnerPackage",123);
    //            serviceItems.addProperty("ServiceItem",serviceItem);
    //            request1.addProperty("ServiceItems", serviceItems);

                //
            int size = goodsCartData.getChildSize();
            for (int index = 0 ; index < size ; index++)
            {
                GoodsItemData goodsItemData = (GoodsItemData)goodsCartData.getChild(index);
                SoapObject product = new SoapObject(NAMESPACE, "Product");
                product.addProperty("名稱", goodsItemData.getName());
                product.addProperty("單價1", goodsItemData.getPrice());
                product.addProperty("使用數量", goodsItemData.getCount());
                product.addProperty("ID", goodsItemData.getProductID());
//                product.addProperty("折扣", );
                products.addSoapObject(product);
            }
//            if (prodcut.getProperty("名稱") != null) {
//                SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("名稱");
//                value = property.getValue().toString();

            request1.addSoapObject(products);

            //
            envelope.setOutputSoapObject(request1);


            androidHttpTransport.call(soapAction, envelope);
            SoapPrimitive result= (SoapPrimitive)envelope.getResponse();
            String res2 = result.toString();
            return res2;
//            String productCount = result.getAttribute("SaveConsumeSetting2Result").toString();
        }catch (Exception e)
        {
            String message= e.toString();
            return "";
        }
    }
    public static void GetEmployee(String url)
    {
        String methodName = "GetEmployee";
        String soapAction = NAMESPACE+methodName;
        try{


            SoapObject request1 = new SoapObject(NAMESPACE, methodName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request1);

            //Web method call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(url, 30000);
            envelope.dotNet = true;
            request1.addProperty("EmID",0);
            request1.addProperty("token","64860217");
            envelope.setOutputSoapObject(request1);


            androidHttpTransport.call(soapAction, envelope);
            SoapObject result= (SoapObject)envelope.getResponse();
            int productCount = result.getPropertyCount();
        }catch (Exception e){
            String message= e.toString();
        }
    }
    public static void GetStoresName(String url)
    {
        String methodName = "GetStoreName";
        String soapAction = NAMESPACE+methodName;
        try{


            SoapObject request1 = new SoapObject(NAMESPACE, methodName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request1);

            //Web method call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
            androidHttpTransport.debug = true;
            androidHttpTransport.call(soapAction, envelope);

            //get the response
            SoapPrimitive response2 = (SoapPrimitive) envelope.getResponse();
//            SoapObject result= (SoapObject)envelope.getResponse();

            String results = response2.toString();

        }catch (Exception e){
            String message= e.toString();
        }
    }
    public static boolean mBGettingProductsFinish = false;
    public static boolean mBGettingProducts = false;
    public static boolean mBTest= false;
    public static int mRecallTime = 0;
    public static void GetProducts(String url, int branchID, String tokenID)
    {
        if (mBGettingProducts && mRecallTime >= 2 )
            return;

        mBGettingProducts = true;
        int serialIndex = 0;
        String methodName = "GetProducts"; //GetBranch, GetProducts
        String soapAction = NAMESPACE+methodName;
        try{

            // add paramaters and values

            SoapObject request1 = new SoapObject(NAMESPACE, methodName);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;
            request1.addProperty("BranchID",branchID);
            request1.addProperty("token", tokenID);
            envelope.setOutputSoapObject(request1);



            //Web method call

            HttpTransportSE androidHttpTransport = new HttpTransportSE(url, 30000);

            androidHttpTransport.debug = true;



            androidHttpTransport.call(soapAction, envelope);
            SoapObject result= (SoapObject)envelope.getResponse();
            int productCount = result.getPropertyCount();
            if (productCount == 0 && mRecallTime<=2)
            {
                GetBranch(url, branchID, tokenID);
                mRecallTime++;
                GetProducts(url, branchID, tokenID);
            }
            for(int productIndex = 0; productIndex<productCount; productIndex++)
            {
                GoodsItemData newGoodsItemData = new GoodsItemData(++serialIndex);
                newGoodsItemData.setCount(1);
                SoapObject prodcut = (SoapObject)result.getProperty(productIndex);

                    String value;
                    try {
                        if (prodcut.getProperty("ID") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("ID");
                            value = property.getValue().toString();
                            newGoodsItemData.setProductId(Integer.parseInt(value));
                        }
                        if (prodcut.getProperty("名稱") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("名稱");
                            value = property.getValue().toString();
                            newGoodsItemData.setName(value);
                            newGoodsItemData.setTitle(value);
                        }
//                        if (prodcut.getProperty("說明") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("說明");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("編號") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("編號");
                            value = property.getValue().toString();
                            newGoodsItemData.setGoodsSerialNumber(value);
                        }
//                        if (prodcut.getProperty("條碼") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("條碼");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("主類別") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("主類別");
                            value = property.getValue().toString();
                            newGoodsItemData.setMainClassName(value);

                        }
//                        if (prodcut.getProperty("副類別") != null) {
//                            SoapObject property = (SoapObject) prodcut.getProperty("副類別");
//                            value = property.getValue().toString();
//                            newGoodsItemData.setSecondClassName(value);
//                        }
                        if (prodcut.getProperty("條碼") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("條碼");
                            value = property.getValue().toString();
//                            if (mBTest == false)
//                            {
//                                value = "0123456789012";
//                                mBTest = true;
//                            }
                            if (value != null && value.length() > 0)
                                newGoodsItemData.setBarcode(value);
                        }
                        if (prodcut.getProperty("備註") != null) {
                            SoapObject property = (SoapObject) prodcut.getProperty("備註");
//                            value = property.().toString();
                            value = "comment";
                            newGoodsItemData.setComment(value);
                        }
//                        if (prodcut.getProperty("是否下架") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否下架");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("UpdateTime") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("UpdateTime");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("UpdateEmployee") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("UpdateEmployee");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("Folder") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("Folder");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("Picture") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("Picture");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單位") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單位");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("包裝單位") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("包裝單位");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("包裝容量") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("包裝容量");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("廠商") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("廠商");
                            value = property.getValue().toString();
                            newGoodsItemData.setFirm(value);
                        }
//                        if (prodcut.getProperty("包裝容量") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("包裝容量");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("櫃位") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("櫃位");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("部門") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("部門");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("進貨價格") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("進貨價格");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("自購價格") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("自購價格");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("安全存量") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("安全存量");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("常備量") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("常備量");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("最小訂購量") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("最小訂購量");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否折扣") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否折扣");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否折店販點") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否折店販點");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否銷貨時扣除存量") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否銷貨時扣除存量");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否為暢銷商品") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否為暢銷商品");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否為滯銷商品") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否為滯銷商品");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否應稅") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否應稅");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否寄賣") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否寄賣");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否虛擬") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否虛擬");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("是否結帳時檢查庫存") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否結帳時檢查庫存");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("單價1") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價1");
                            value = property.getValue().toString();
                            newGoodsItemData.setPrice(Double.parseDouble(value));
                        }
//                        if (prodcut.getProperty("單價2") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價2");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價3") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價3");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價4") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價4");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價5") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價5");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價6") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價6");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價7") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價7");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價8") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價8");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("單價9") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價9");
//                            value = property.getValue().toString();
//                        }
//
//                        if (prodcut.getProperty("單價10") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("單價10");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數1") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數1");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數2") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數2");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數3") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數3");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數4") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數4");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數5") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數5");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數6") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數6");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數7") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數7");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數8") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數8");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數9") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數9");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數10") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數10");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數抽成百分比") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數抽成百分比");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("店販點數公式") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("店販點數公式");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("服務人員ID") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("服務人員ID");
//                            value = property.getValue().toString();
//                        }
//                        if (prodcut.getProperty("手動自訂價格") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("手動自訂價格");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("折扣") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("折扣");
                            value = property.getValue().toString();
                            newGoodsItemData.setDiscount(Double.parseDouble(value));
                        }
//                        if (prodcut.getProperty("是否完成") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("是否完成");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("銷售備註") != null) {
                            SoapObject property = (SoapObject) prodcut.getProperty("銷售備註");
                            value = "Sales Comment";
                            newGoodsItemData.setComment(value);

                        }
//                        if (prodcut.getProperty("TempProductDetailRecordID") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("TempProductDetailRecordID");
//                            value = property.getValue().toString();
//                        }
                        if (prodcut.getProperty("OwnerPackage") != null) {
                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("OwnerPackage");
                            value = property.getValue().toString();
                        }
                    }
                    catch (Exception e){

                        String message= e.toString();
                        message = message;

                    }
                boolean bExistMainClassName = false;
                String newItemMainClassName = newGoodsItemData.getMainClassName();
                newGoodsItemData.setIconResourceID(R.drawable.hairdresser);
                int childSize = GoodsItemAllData.getChildSize();
                for (int childIndex = 0 ; childIndex < childSize ; childIndex++)
                {
                    GoodsItemData childItem = (GoodsItemData)GoodsItemAllData.getChild(childIndex);
                    if (childItem.getClassification() && childItem.getmClassificationName().compareToIgnoreCase(newItemMainClassName) == 0)
                    {

                        childItem.addChild(newGoodsItemData);
                        newGoodsItemData.setParent(childItem);
                        bExistMainClassName = true;
                    }
                }
                if (!bExistMainClassName)
                {
                    BaseItemData item = new GoodsItemData(newItemMainClassName, ++serialIndex);
                    item.setParent(GoodsItemAllData.getGoodsItemRecordsData());
                    GoodsItemAllData.addChild((GoodsItemData)item);

                    newGoodsItemData.setParent(item);
                    item.addChild(newGoodsItemData);
                }
                try {
                    for (int index = 0; index < _OnProductDataReceivedListener.size(); index++) {
                        _OnProductDataReceivedListener.get(index).onDataReceive(productCount, productIndex);
                    }
                    Thread.sleep(100);
                }catch (Exception e){

                    String message= e.toString();
                    message = message;

                }

            }
            mBGettingProductsFinish = true;
            String results = result.toString();

        }catch (Exception e){

            String message= e.toString();
            message = message;

        }
        mBGettingProducts = false;

    }
    public static void GetBranch(String url, int branchID, String tokenID)
    {

        int serialIndex = 0;
        String methodName = "GetBranch"; //GetBranch, GetProducts
        String soapAction = NAMESPACE+methodName;
        try{

            // add paramaters and values

            SoapObject request1 = new SoapObject(NAMESPACE, methodName);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            request1.addProperty("BranchID",branchID);
            request1.addProperty("token", tokenID);
            envelope.setOutputSoapObject(request1);
            //Web method call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(url, 30000);
            androidHttpTransport.debug = true;
            androidHttpTransport.call(soapAction, envelope);
            SoapObject result= (SoapObject)envelope.getResponse();
            String results = result.toString();

        }catch (Exception e){

            String message= e.toString();
            message = message;
        }

    }
}
