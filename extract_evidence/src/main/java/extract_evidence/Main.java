package extract_evidence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.EvPara;
import model.PreEv;
import process.sentenceExtract.KeyContentExtract;
import process.sentenceExtract.KeyContentExtractor;
import util.XMLUtil;

public class Main {
	
	public String currentType;	//表明当前处理的是哪个文件夹当中的文件

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	public void start() {
		// load
		ArrayList<PreEv> list = load("刑事一审");

		// process
		process(list);
		
		// output
	}
 
	/**
	 * @param type
	 *            : 刑事一审/刑事二审/民事一审...
	 * @return
	 */
	private ArrayList<PreEv> load(String type) {
		ArrayList<PreEv> list = new ArrayList<PreEv>();
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
					PreEv preEV = new PreEv();
					preEV.setPath(filename);
					ArrayList<EvPara> evParaList = new ArrayList<EvPara>();
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSSLD/@value");
					for(String token:res){
						evParaList.add(new EvPara(token,"BSSLD"));
					}
					preEV.setEvParaList(evParaList);
					list.add(preEV);
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
					PreEv preEV = new PreEv();
					preEV.setPath(filename);
					ArrayList<EvPara> evParaList = new ArrayList<EvPara>();
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//ZJD/@value");
					for(String token:res){
						evParaList.add(new EvPara(token,"ZJD"));
					}
					preEV.setEvParaList(evParaList);
					list.add(preEV);
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
					PreEv preEV = new PreEv();
					preEV.setPath(filename);
					ArrayList<EvPara> evParaList = new ArrayList<EvPara>();
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//QSZJD/@value");
					for(String token:res){
						evParaList.add(new EvPara(token,"QSZJD"));
					}
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSZJD/@value");
					for(String token:res){
						evParaList.add(new EvPara(token,"BSZJD"));
					}
					list.add(preEV);
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

	private void process(ArrayList<PreEv> list){
		
		int total = 0;
		int hit = 0;
		KeyContentExtract extractor = KeyContentExtractor.getInstance(currentType);
		for(PreEv model:list){
			boolean result = extractor.extractSentences(model);
			//System.out.println(result);
			total ++;
			if(result){
				hit ++;
			}
		}
		System.out.println("hit: " + hit);
		System.out.println("total: " + total);
		System.out.println("hit rate: " + 1.0 * hit/total);
	}
	
	
}
