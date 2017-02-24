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
		
		String result = null;
		
		if(result == null){
			result = match1(model);
			if(result!=null){
				System.out.println("match1");
			}
		}
		if(result == null){
			result = match2(model);
			if(result!=null){
				System.out.println("match2");
			}
		}
		
		return result;
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
		boolean isLocated2 = false; //且有是否被定位
		boolean isLocated3 = false; //结尾是否被定位
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
			
			if(stringList[i].contains("足以认定") || isMatchEnd(stringList[i])){
				endNumber = i;
				isLocated3 = true;
			}
		}
		
		//判断是否为此种模式，如果是继续计算输出最终结果否则直接退出
		if(!isLocated1 || !isLocated2 || !isLocated3){
			return null;
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

	/**
	 * 上述事实...下列证据予以证实...(数字标号)
	 * 
	 * 此方法目前仅识别各种标号并提取包含标号的内容
	 * @param model
	 * @return
	 */
	private String match2(QwModel model){
		
		String content1 = model.getContent1();
		String content2 = model.getContent2();
		
		String[] sequenceNumber1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
		String[] sequenceNumber2 = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五"};
		String[] sequenceNumber3 = {"（一）","（二）","（三）","（四）","（五）","（六）","（七）","（八）","（九）","（十）","（十一）","（十二）","（十三）","（十四）","（十五）"};
		
		char[] puntuations = {'.','、','，',','};
		
		
		String combine1 = null;			//标号为1组合
		String combine2 = null;			//标号为2组合
		String[] sequenceMatch = null;	//所匹配的标号集合
		int indexOfPuntuations = 0;		//所匹配的标点集合
		boolean isMatch = false;		//目前是否匹配
		
		for(int i = 0;i < puntuations.length;i ++){
			combine1 = sequenceNumber1[0] + puntuations[i];
			combine2 = sequenceNumber1[1] + puntuations[i];
			if(content1.contains(combine1) && content1.contains(combine2)){
				sequenceMatch = sequenceNumber1;
				indexOfPuntuations = i;
				isMatch = true;
				break;
			}
		}
		if(!isMatch){
			for(int i = 0;i < puntuations.length;i ++){
				combine1 = sequenceNumber2[0] + puntuations[i];
				combine2 = sequenceNumber2[1] + puntuations[i];
				if(content1.contains(combine1) && content1.contains(combine2)){
					sequenceMatch = sequenceNumber2;
					indexOfPuntuations = i;
					isMatch = true;
					break;
				}
			}
		}
		if(!isMatch){
			for(int i = 0;i < puntuations.length;i ++){
				combine1 = sequenceNumber3[0] + puntuations[i];
				combine2 = sequenceNumber3[1] + puntuations[i];
				if(content1.contains(combine1) && content1.contains(combine2)){
					sequenceMatch = sequenceNumber3;
					indexOfPuntuations = i;
					isMatch = true;
					break;
				}
			}
		}
		
		if(!isMatch){
			return null;
		}
		
		//提取从第一个标号开始到最后一个标号结束(以句号为标志)的所有文字
		String[] stringList = content1.split("。");
		StringBuilder result = new StringBuilder("");
		int currentIndex = 0;
		for(String token:stringList){
			if(token.contains(sequenceMatch[currentIndex] + puntuations[indexOfPuntuations])){
				result.append(token).append("。");
				currentIndex++;
			}
		}
		
		return result.toString();
	}
	
	/**
	 * 由于刑事一审的第一类情况结尾为足以认定或者等证据证实字样，故需要对后一种类型进行匹配
	 * 
	 * @return
	 */
	private boolean isMatchEnd(String input){
		
		//等
		//证据/材料
		//予以
		//证实/证明/
		int TOTAL_POINT = 4;
		int matchPoint = 0;
		if(input.contains("等")){
			matchPoint++;
		}
		if(input.contains("证据") || input.contains("材料")){
			matchPoint++;
		}
		if(input.contains("予以")){
			matchPoint++;
		}
		if(input.contains("证实")||input.contains("证明")){
			matchPoint++;
		}
		
		if(matchPoint >= 3){
			return true;
		}
		else{
			return false;
		}
	}
}
