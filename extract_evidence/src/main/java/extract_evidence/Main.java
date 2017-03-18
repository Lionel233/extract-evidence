package extract_evidence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import model.EvPara;
import model.Litigant;
import model.PreEv;
import process.sentenceExtract.KeyContentExtract;
import process.sentenceExtract.KeyContentExtractor;
import util.XMLUtil;

public class Main {

	public String currentType; // 表明当前处理的是哪个文件夹当中的文件

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	public void start() {
		ArrayList<PreEv> list = load("刑事一审");
		process(list);

		// ArrayList<PreEv> list = load("行政一审");
		// process(list);

		// ArrayList<PreEv> list = load("行政二审");
		// process(list);

		// ArrayList<PreEv> list = load("民事一审");
		// process(list);

		// ArrayList<PreEv> list = load("民事二审");
		// process(list);

		// ArrayList<PreEv> list = load("刑事二审");
		// process(list);
		// output
		output(list);
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
			if (filename.contains(".DS_Store")) {
				continue;
			}
			try {
				ArrayList<String> res = null;
				currentType = type;
				PreEv preEV = new PreEv();
				preEV.setPath(filename);
				preEV.setLitigantList(Litigant.getLitigants(XMLUtil.readPath + type + "/" + filename));
				ArrayList<EvPara> evParaList = new ArrayList<EvPara>();
				switch (type) {
				case "刑事一审":
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSSLD/@value");
					for (String token : res) {
						evParaList.add(new EvPara(token, "BSSLD"));
					}
					preEV.setEvParaList(evParaList);
					list.add(preEV);
					break;
				case "刑事二审":
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//QSSLD/@value");
					for (String token : res) {
						evParaList.add(new EvPara(token, "QSSLD"));
					}
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSSLD/@value");
					for (String token : res) {
						evParaList.add(new EvPara(token, "BSSLD"));
					}
					preEV.setEvParaList(evParaList);
					list.add(preEV);
					break;
				case "民事一审":
				case "行政一审":
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//ZJD/@value");
					for (String token : res) {
						evParaList.add(new EvPara(token, "ZJD"));
					}
					preEV.setEvParaList(evParaList);
					list.add(preEV);
					break;
				case "民事二审":
				case "行政二审":
					preEV.setPath(filename);
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//QSZJD/@value");
					for (String token : res) {
						evParaList.add(new EvPara(token, "QSZJD"));
					}
					res = XMLUtil.getNodes(XMLUtil.readPath + type + "/" + filename, "//BSZJD/@value");
					for (String token : res) {
						evParaList.add(new EvPara(token, "BSZJD"));
					}
					preEV.setEvParaList(evParaList);
					list.add(preEV);
					break;
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
		}

		return list;
	}

	private void process(ArrayList<PreEv> list) {

		int total = 0;
		int hit = 0;
		KeyContentExtract extractor = KeyContentExtractor.getInstance(currentType);
		for (PreEv model : list) {
			// 有些文书并不包含证据段，故直接忽略
			if (model.getEvParaList().isEmpty()) {
				continue;
			}
			boolean result = extractor.extractSentences(model);
			// System.out.println(result);
			total++;
			if (result) {
				hit++;
			}
		}
		System.out.println("hit: " + hit);
		System.out.println("total: " + total);
		System.out.println("hit rate: " + 1.0 * hit / total);
	}

	private void output(ArrayList<PreEv> list) {

		for (PreEv preEv : list) {
			if (preEv == null || preEv.getEvParaList().isEmpty()) {
				continue;
			}
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(
						new File(XMLUtil.outPath + currentType + "/" + preEv.getPath().replace("xml", "txt"))));
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (EvPara para : preEv.getEvParaList()) {
				if (para.getKeyContent() == null) {
					continue;
				}
				try {
					writer.write(para.getKeyContent() + System.lineSeparator());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
