package extract_evidence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.QwModel;
import util.XMLUtil;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	public void start() {
		// load
		ArrayList<QwModel> list = load("刑事一审");

		// process
		process(list);
		// output
	}

	/**
	 * @param type
	 *            : 刑事一审/刑事二审/民事一审...
	 * @return
	 */
	private ArrayList<QwModel> load(String type) {
		ArrayList<QwModel> list = new ArrayList<QwModel>();
		File file = new File(XMLUtil.readPath + type);
		String filenames[];
		filenames = file.list();
		for (String filename : filenames) {
			ArrayList<String> res = null;
			switch (type) {
			case "刑事一审":
			case "刑事二审":
				try {
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSSLD/@value");
					if(!res.isEmpty()){
						list.add(new QwModel(res.get(0),"BSSLD"));
					}
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
				break;
			case "民事一审":
			case "行政一审":
				try {
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//ZJD/@value");
					if(!res.isEmpty()){
						list.add(new QwModel(res.get(0),"ZJD"));
					}
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
				break;
			case "民事二审":
			case "行政二审":
				try {
					QwModel model = new QwModel();
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//QSZJD/@value");
					if(!res.isEmpty()){
						model.setContent1(res.get(0));
						model.setSource1("QSZJD");
					}
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSZJD/@value");
					if(!res.isEmpty()){
						model.setContent2(res.get(0));
						model.setSource2("BSZJD");
					}
					list.add(model);
				} catch (XPathExpressionException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		return list;
	}

	private void process(ArrayList<QwModel> list){
		
	}
	
	
}
