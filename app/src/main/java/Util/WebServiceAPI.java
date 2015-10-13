package Util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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

    public static void GetProducts()
    {
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



            //get the response
//            SoapPrimitive response2 = (SoapPrimitive) envelope.getResponse();
            SoapObject result= (SoapObject)envelope.getResponse();
            int count = result.getPropertyCount();


            String results = result.toString();

//            Get_HelloWorld=results;

        }catch (Exception e){

            String message= e.toString();
            message = message;
//            Get_HelloWorld=e.getMessage(); //將錯誤訊息傳回

        }

//        return Get_HelloWorld; //傳回字串

    }
}
