package process.evDetailExtract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.XMLUtil;

/**
 * 证据种类判断
 */
public class EvTypeJudge {

	public final static String EVTYPE_TESTIMONY = "证人证言";
	public final static String EVTYPE_DOCUMENTARY = "书证";
	public final static String EVTYPE_MATERIAL = "物证";
	public final static String EVTYPE_AUDIO_VISUAL = "视听资料";
	public final static String EVTYPE_STATEMENT = "当事人陈述";
	public final static String EVTYPE_CONCLUSION = "鉴定结论";
	public final static String EVTYPE_NOTES = "笔录";
	public final static String EVTYPE_OTHER = "其它";
	
	public static ArrayList<Pair> keywords = new ArrayList<Pair>();
	
	static{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(XMLUtil.resourcesPath + "evidence.txt")));
			String line = "";
			while((line = reader.readLine())!=null){
				if(line.trim().isEmpty()) continue;
				String[] pairs = line.split("；");
				System.out.println(line);
				keywords.add(new Pair(pairs[0],pairs[1]));
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入一小段证据，返回此证据的种类
	 * 例如 随案移交物品 ：物证
	 * @param ev
	 * @return
	 */
	public static String judge(String ev){
		for(Pair pair:keywords){
			if(ev.contains(pair.keyword)){
				return pair.type;
			}
		}
		return EVTYPE_OTHER;
	}
	
	public static boolean containsKeyword(String ev){
		for(int i = 0;i < keywords.size() - 1;i ++){
			if(ev.contains(keywords.get(i).keyword )){
				return true;
			}
		}
		return false;
	}
	

	
}
