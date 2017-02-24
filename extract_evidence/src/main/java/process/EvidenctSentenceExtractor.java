package process;

import model.QwModel;

/**
 * 基于模式的判断
 *
 * 当前正在基于刑事一审编写测试代码
 */
public class EvidenctSentenceExtractor implements EvidenceSentenceExtract{

	private static EvidenctSentenceExtractor extractor = new EvidenctSentenceExtractor();
	
	private EvidenctSentenceExtractor(){};
	
	public static EvidenctSentenceExtractor getInstance(){
		return extractor;
	}
	
	@Override
	public String extractSentences(QwModel model) {
		
		return match1(model);
	}
	
	/**
	 * 上述事实...且有...等证据予以证实，足以认定
	 * @return null if not match
	 */
	private String match1(QwModel model){
		
		String content1 = model.getContent1();
		if(content1==null || content1.isEmpty()){
			return null;
		}
		
		String[] stringList = content1.split("，|。| ,");
		
		boolean isLocated1 = false;	//上述事实是否被定位
		boolean isLocated2 = false; //且有是否被定为
		int beginNumber = 0;	//所需要的句子的开头位置
		int endNumber = 0;
		for(int i = 0;i < stringList.length;i ++){
			//找到上述事实的位置
			if(!isLocated1 && !stringList[i].contains("上述事实")){
				continue;
			}
			else if(!isLocated1){
				isLocated1 = true;
				beginNumber = i;
			}
			
			if(stringList[i].contains("且有")){
				isLocated2 = true;
			}
			
			if(stringList[i].contains("足以认定")){
				endNumber = i;
				break;
			}
		}
		
		int index1 = 0;
		int index2 = 0;
		isLocated1 = false;
		int splitOccurNumber = 0;
		for(int i = 0;i < content1.length();i ++){
			if(splitOccurNumber == beginNumber && !isLocated1){
				index1 = i;
				isLocated1 = true;
			}
			if(splitOccurNumber-1 == endNumber){
				index2 = i;
				break;
			}
			if(content1.charAt(i) == ','
					|| content1.charAt(i) == '，'
					|| content1.charAt(i) == '。'){
				splitOccurNumber ++;
			}
		}
		
		return content1.substring(index1, index2);
	}

}
