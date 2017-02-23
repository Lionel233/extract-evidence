package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by raychen on 2016/12/7.
 */
public class XMLUtil {

    public static String readPath = "src/main/resources/in/";
    public static String outPath = "src/main/resources/out/";

    //获取xml中节点
    public static ArrayList<String> getNodes(String path, String x) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        ArrayList<String> result = new ArrayList<String>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        File f = new File(path);
        //编译xml
        Document doc = builder.parse(f);
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        //x是xml中的节点，例如“YGSCD”, 格式://YGSCD/@value
        XPathExpression expr = xpath.compile(x);
        //doc中读取expr节点内容
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() > 0) {
            for (int i = 0; i < nodes.getLength(); i++) {
                result.add(nodes.item(i).getNodeValue());// 满足正则的XML中的那段文字复制到result中
            }
        }
        return result;
    }

    //生成xml文件, 这里主要是生成划分好的事实段
//    public static void buildNewSSD(ArrayList<SSDModel> ssdModels, String fileName){
//        Element root = new Element("SYSSD").setAttribute("nameCN","所有事实段");
//        root.setAttribute("value", "事实段出现的各个节点");
//        // 将根节点添加到文档中；
//        org.jdom.Document doc = new org.jdom.Document(root);
//
//        //将拆解好的事实插入文档
//        for (SSDModel ssdModel: ssdModels) {
//            Element e = new Element("SSD").setAttribute("nameCN", ssdModel.getName());
//            e.setAttribute("value", ssdModel.getValue());
//            root.addContent(e);
//            for (SSModel s: ssdModel.getSsModels()) {
//                Element e2 = new Element("SS").setAttribute("nameCN", "事实");
//                e2.setAttribute("value", s.getValue());
//                e.addContent(e2);
//                //增加事实关键词提取
//                Element e31 = new Element("SSWhere").setAttribute("nameCN", "地点");
//                e31.setAttribute("value", s.getWhere());
//                e2.addContent(e31);
//                Element e32 = new Element("SSWhen").setAttribute("nameCN", "时间");
//                e32.setAttribute("value", s.getWhen());
//                e2.addContent(e32);
//                Element e33 = new Element("SSWhat").setAttribute("nameCN", "事物");
//                e33.setAttribute("value", s.getWhat());
//                e2.addContent(e33);
//                Element e34 = new Element("SSWho").setAttribute("nameCN", "人名");
//                e34.setAttribute("value", s.getWho());
//                e2.addContent(e34);
//                Element e35 = new Element("SSHowMuch").setAttribute("nameCN", "数量");
//                e35.setAttribute("value", s.getHow_much());
//                e2.addContent(e35);
//            }
//        }
//
//        // 使xml文件 缩进效果
//        Format format = Format.getPrettyFormat();
//        XMLOutputter XMLOut = new XMLOutputter(format);
//        try {
//            XMLOut.output(doc, new FileOutputStream(outPath+fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
    	XMLUtil.readPath = "src/main/resources/in/";
    	File file=new File(XMLUtil.readPath + "刑事一审");
        String filenames[];
        filenames = file.list();
        for (String filename: filenames) {
            ArrayList<String> res = XMLUtil.getNodes(XMLUtil.readPath+"刑事一审/"+filename, "//ZKZJ/@value");
            for (String s: res) {
                System.out.println(s);
            }
        }
    }

}
