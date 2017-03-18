package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 当事人
 *
 */
public class Litigant {

	private String name;
	private LitigantType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LitigantType getType() {
		return type;
	}

	public void setType(LitigantType type) {
		this.type = type;
	}

	public static ArrayList<Litigant> getLitigants(String filename) {
		System.out.println(filename);
		ArrayList<Litigant> litigantList = new ArrayList<Litigant>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();
			File f = new File(filename);
			Document doc;
			doc = builder.parse(f);
			NodeList dsrNode = doc.getElementsByTagName("DSR");
			if (dsrNode.getLength()!=0) {
				NodeList dsrList = dsrNode.item(0).getChildNodes();
				Element element;
				for (int i = 0; i < dsrList.getLength() - 1; i++) {
					Litigant litigant = new Litigant();
					Node dsr = (Node) dsrList.item(i);
					
					//防止在win上报错
					if(dsr.getNodeName().equals("null") || dsr.getNodeName().equals("#text")) continue;
					//System.out.println(dsr.getNodeName());
					NodeList dsrChild = dsr.getChildNodes();
					for (int j = 0; j < dsrChild.getLength(); j++) {
						//System.out.println(dsrChild.item(j).getNodeName());
						//防止在win上报错
						if(dsrChild.item(j).getNodeName().equals("null") || dsrChild.item(j).getNodeName().equals("#text")) continue;
						element = (Element) dsrChild.item(j);
						if (element.getTagName() == "SSCYR"/* 诉讼参与人 */) {
							litigant.setName(element.getAttribute("value"));
						}
						if (element.getTagName() == "DSRLB"/* 当事人类别 */) {
							if (element.getAttribute("value").equals("被告人")) {
								litigant.setType(LitigantType.getLitigantType("被告"));
							} else {
								litigant.setType(LitigantType.getLitigantType((element.getAttribute("value"))));
							}
							break;
						}
					}
					//防止在win上报错
					if(litigant.getName()!=null && litigant.getType()!=null){
						litigantList.add(litigant);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return litigantList;
	}

}
