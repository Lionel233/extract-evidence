package extract_evidence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.QwModel;
import process.sentenceExtract.EvidenceSentenceExtract;
import process.sentenceExtract.EvidenceSentenceExtractor;
import util.XMLUtil;

public class Main {
	
	public String currentType;	//表明当前处理的是哪个文件夹当中的文件

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
			if(filename.contains(".DS_Store")){
				continue;
			}
			ArrayList<String> res = null;
			currentType = type;
			switch (type) {
			case "刑事一审":
			case "刑事二审":
				try {
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSSLD/@value");
					if(!res.isEmpty()){
						list.add(new QwModel(res.get(0),"BSSLD",filename));
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
						list.add(new QwModel(res.get(0),"ZJD",filename));
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
						model.setPath(filename);
					}
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSZJD/@value");
					if(!res.isEmpty()){
						model.setContent2(res.get(0));
						model.setSource2("BSZJD");
						model.setPath(filename);
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
		
		int total = 0;
		int hit = 0;
		EvidenceSentenceExtract extractor = EvidenceSentenceExtractor.getInstance(currentType);
		for(QwModel model:list){
			String result = extractor.extractSentences(model);
			//System.out.println(result);
			total ++;
			if(result != null){
				hit ++;
			}
		}
		System.out.println("hit: " + hit);
		System.out.println("total: " + total);
		System.out.println("hit rate: " + 1.0 * hit/total);
	}
	
	
}
