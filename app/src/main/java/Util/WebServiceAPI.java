package Util;

import com.CenterFragment.TabFragment.Goods.GoodsItemData;
import com.LeftFragement.BaseItemData;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import jerome.i_pos.R;

/**
 * Created by Jerome on 2015/10/12.
 */
public class WebServiceAPI
{
//    private static final String SOAP_ACTION1 = "http://tempuri.org/HelloWorld"; //Web Services命名空間+函數名稱

//    private static final String HelloWorldmethod = "HelloWorld";　//要呼叫的函數名稱

    private static final String NAMESPACE = "http://tempuri.org/"; //Web Services命名空間

    private static final String URL = "http://zoom-world.tw/CloudDemo/CloudService.asmx"; //Web Services的網址



    public WebServiceAPI()
    {

    }


    public static void GetStoresName()
    {
        String methodName = "GetStoreName";
        String soapAction = NAMESPACE+methodName;
        try{


            SoapObject request1 = new SoapObject(NAMESPACE, methodName);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request1);

            //Web method call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
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

    public static BaseItemData GetProducts()
    {
        BaseItemData _RootItemData = new GoodsItemData("全部");
        _RootItemData.setParent(null);
        BaseItemData goodsItemData = new GoodsItemData("商品");
        goodsItemData.setParent(_RootItemData);
        _RootItemData.addChild(goodsItemData);

        String methodName = "GetProducts";
//        String Get_HelloWorld="";
        String soapAction = NAMESPACE+methodName;
        try{

            // add paramaters and values

            SoapObject request1 = new SoapObject(NAMESPACE, methodName);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;
            request1.addProperty("BranchID",0);
            request1.addProperty("token","24832031");
            envelope.setOutputSoapObject(request1);



            //Web method call

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.debug = true;



            androidHttpTransport.call(soapAction, envelope);
            SoapObject result= (SoapObject)envelope.getResponse();
            int productCount = result.getPropertyCount();

            for(int productIndex = 0; productIndex<productCount; productIndex++)
            {
                GoodsItemData newGoodsItemData = new GoodsItemData();
                SoapObject prodcut = (SoapObject)result.getProperty(productIndex);

                    String value;
                    try {
//                        if (prodcut.getProperty("ID") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("ID");
//                            value = property.getValue().toString();
//                        }
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
//                        if (prodcut.getProperty("編號") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("編號");
//                            value = property.getValue().toString();
//                        }
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
//                        if (prodcut.getProperty("條碼") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("條碼");
//                            value = property.getValue().toString();
//                        }
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
//                        if (prodcut.getProperty("OwnerPackage") != null) {
//                            SoapPrimitive property = (SoapPrimitive) prodcut.getProperty("OwnerPackage");
//                            value = property.getValue().toString();
//                        }
                    }
                    catch (Exception e){

                        String message= e.toString();
                        message = message;

                    }
                boolean bExistMainClassName = false;
                String newItemMainClassName = newGoodsItemData.getMainClassName();
                newGoodsItemData.setIconResourceID(R.drawable.hairdresser);
                int childSize = goodsItemData.getChildSize();
                for (int childIndex = 0 ; childIndex < childSize ; childIndex++)
                {
                    GoodsItemData childItem = (GoodsItemData)goodsItemData.getChild(childIndex);
                    if (childItem.getClassification() && childItem.getmClassificationName().compareToIgnoreCase(newItemMainClassName) == 0)
                    {
                        childItem.addChild(newGoodsItemData);
                        newGoodsItemData.setParent(childItem);
                        bExistMainClassName = true;
                    }
                }
                if (!bExistMainClassName)
                {
                    BaseItemData item = new GoodsItemData(newItemMainClassName);
                    item.setParent(goodsItemData);
                    goodsItemData.addChild(item);

                    newGoodsItemData.setParent(item);
                    item.addChild(newGoodsItemData);
                }
            }




            String results = result.toString();

//            Get_HelloWorld=results;

        }catch (Exception e){

            String message= e.toString();
            message = message;
//            Get_HelloWorld=e.getMessage(); //將錯誤訊息傳回

        }
        return _RootItemData;
//        return Get_HelloWorld; //傳回字串

    }
}
