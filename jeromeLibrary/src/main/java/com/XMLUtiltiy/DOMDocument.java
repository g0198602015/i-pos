package com.XMLUtiltiy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import LogUtility.Log;

/**
 * Created by Jerome on 2015/11/17.
 */
public class DOMDocument
{
    public static Document createDocument(String filepath, String decoder)
    {
        String _strXML = "";

        try
        {
            File _File = new File(filepath);
            if(_File.exists())
            {
                FileInputStream fis = new FileInputStream(filepath);
                InputStreamReader reader= new InputStreamReader(fis, decoder);	// utf8? unicode?

                int size=(int)_File.length();
                char buffer[];
                buffer=new char[size];
                reader.read(buffer);
                _strXML = String.valueOf(buffer);
                reader.close();
                fis.close();
            }

            if(_strXML.length() > 0)
            {
                Document doc = createDocument(_strXML);
                return doc;
            }
            else
                return null;
        }
        catch(Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString() + "[" + _strXML + "]", Log.LogLevel.ERROR);

            return null;
        }
    }

    public static Document createDocument(InputStream in)
    {
        try
        {
            String string = ConvertInputStreamToString(in, "UTF-8");
            return createDocument(string);
        }
        catch(Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return null;
        }
    }
    public static Document createDocument(String xmlString) throws Exception
    {
        try
        {
            xmlString = xmlString.trim();
            xmlString = xmlString.substring(0, xmlString.lastIndexOf(">") + 1);

            //2011.07.11_Zonghan_ �Ҧp <?xml version="1.0" encoding="UTF-8" standalone="no"?> �o�@�q�|�����D
            if(xmlString.indexOf("?") >= 0 && xmlString.indexOf("?") < 5)
                xmlString = xmlString.substring(xmlString.indexOf(">")+1);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader reader = new StringReader( xmlString );
            org.xml.sax.InputSource inputSource = new org.xml.sax.InputSource( reader );
            Document doc = builder.parse( inputSource );
            reader.close();
            return doc;

        }
        catch (Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return null;
        }
    }

    public static Node getNodeFromNodeList(NodeList nodelist, String name)
    {
        for(int i=0 ; i < nodelist.getLength() ; i++)
        {
            if(nodelist.item(i).getNodeName().equalsIgnoreCase(name))
                return nodelist.item(i);
    }
        return null;
    }

    public static String getAttributeTextFromNode(Node node, String name)
    {
        try
        {
            return node.getAttributes().getNamedItem(name).getTextContent();
        }
        catch(Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return "";
        }
    }

    public static Document createDocument()
    {
        try
        {
            Document document;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            return document;
        }
        catch (Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return null;
        }
    }

    public static Boolean saveDocumentToFile(Document doc, String filePath)
    {
        try
        {
            Source xmlSource = new DOMSource(doc);
            Result result = new StreamResult(new FileOutputStream(filePath));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(xmlSource, result);
            return true;
        }
        catch (Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return false;
        }
    }

    public static String getStringFromDocument(Document doc)
    {
        try
        {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch (Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return "";
        }
    }

    public static String getStringFromNode(Node node)
    {
        try
        {
            StringWriter writer = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(node), new StreamResult(writer));
            String xml = writer.toString();
            return xml;
        }
        catch (Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return "";
        }
    }

    public static Node getNodeFromString(String str)
    {
        try
        {
            Element node =  DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(str.getBytes()))
                    .getDocumentElement();
            return node;
        } catch (Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString(), Log.LogLevel.ERROR);
            return null;
        }
    }
    //
    // <ParentNode>
    //	 <ChildeNode> 1 </ChildeNode>
    //   <ChildeNode> 1 </ChildeNode>
    //   <ChildeNode> 1 </ChildeNode>
    //   <ChildeNode> 1 </ChildeNode>
    // </ParentNode>
    public static List<String> ParseNodeList(Node parentNode, String childNodeName)
    {
        List<String> results = new ArrayList<String>();
        for(int i=0 ; i<parentNode.getChildNodes().getLength() ; i++)
        {
            Node childNode = parentNode.getChildNodes().item(i);
            String nodeName = childNode.getNodeName();
            String textContent = childNode.getTextContent();
            if(nodeName.equals(childNodeName))
            {
                results.add(textContent);
            }
        }
        return results;
    }
    // <parentNode attriName = "1" />

    public static List<String> ParseAttributeValue(Node parentNode, String attriName)
    {
        List<String> results = new ArrayList<String>();
        for (int i = 0 ; i < parentNode.getChildNodes().getLength() ; i ++)
        {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                for (int attIndex = 0 ; attIndex < node.getAttributes().getLength() ; attIndex ++)
                {
                    Node attriNode = node.getAttributes().item(attIndex);
                    if (attriNode.getNodeType() == Node.ATTRIBUTE_NODE)
                    {
                        if (attriNode.getNodeName().equalsIgnoreCase("GID"))
                            results.add((String) attriNode.getNodeValue());
                    }
                }

            }
        }
        return results;
    }

    private static String ConvertInputStreamToString(InputStream in, String charset)
    {
        try
        {
            int BUFFER_SIZE = 1204;
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[BUFFER_SIZE];
            int count = -1;
            while((count = in.read(data,0,BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);

            data = null;
            return new String(outStream.toByteArray(),charset);
        }
        catch(Exception ex)
        {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            LogUtility.Log.WriteLog(fullClassName, methodName, ex.toString());
            return "";
        }
    }
}