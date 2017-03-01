package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy1 implements MatchStrategy{

	/* (non-Javadoc)
	 * 上述事实...等证据证实，足以认定/所证实...
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para){
        String regex = "[^。]*(以上|上述)[^。]*事实.*(足以认定|所证实)[^。]*。";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match3:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            return true;
        }
		
		return false;
	}
//	public boolean match(EvPara para) {
//
//		String content1 = para.getContent();
//		if (content1 == null || content1.isEmpty()) {
//			return false;
//		}
//
//		String[] stringList = content1.split("，|。| ,");
//
//		boolean isLocated1 = false; // 上述事实是否被定位
//		boolean isLocated2 = false; // 且有是否被定位
//		boolean isLocated3 = false; // 结尾是否被定位
//		int beginNumber = 0; // 所需要的句子的开头位置
//		int endNumber = 0;
//		for (int i = 0; i < stringList.length; i++) {
//			// 找到上述事实的位置
//			if (!isLocated1 && !(stringList[i].contains("上述事实") || stringList[i].contains("以上事实"))) {
//				continue;
//			} else if (!isLocated1) {
//				isLocated1 = true;
//				beginNumber = i;
//			}
//
//			if (stringList[i].contains("且有") || stringList[i].contains("并有")) {
//				isLocated2 = true;
//			}
//
//			if (stringList[i].contains("足以认定") || isMatchEnd(stringList[i])) {
//				endNumber = i;
//				isLocated3 = true;
//			}
//		}
//
//		// 判断是否为此种模式，如果是继续计算输出最终结果否则直接退出
//		// 删除 且有/并有 的判断 ， 增加命中率
//		// if(!isLocated1 || !isLocated2 || !isLocated3){
//		// return null;
//		// }
//		if (!isLocated1 || !isLocated3) {
//			return false;
//		}
//
//		int index1 = 0;
//		int index2 = 0;
//		isLocated1 = false;
//		int splitOccurNumber = 0;
//		for (int i = 0; i < content1.length(); i++) {
//			if (splitOccurNumber == beginNumber && !isLocated1) {
//				index1 = i;
//				isLocated1 = true;
//			}
//			if (splitOccurNumber - 1 == endNumber) {
//				index2 = i;
//				break;
//			}
//			if (content1.charAt(i) == ',' || content1.charAt(i) == '，' || content1.charAt(i) == '。') {
//				splitOccurNumber++;
//			}
//		}
//
//		para.setKeyContent(content1.substring(index1, index2));
//		return true;
//		//return content1.substring(index1, index2);
//	}
	

	/**
	 * 由于刑事一审的第一类情况结尾为足以认定或者等证据证实字样，故需要对后一种类型进行匹配
	 * 
	 * @return
	 */
	private boolean isMatchEnd(String input) {

		// 等
		// 证据/材料
		// 予以
		// 证实/证明/
		int TOTAL_POINT = 4;
		int matchPoint = 0;
		if (input.contains("等")) {
			matchPoint++;
		}
		if (input.contains("证据") || input.contains("材料")) {
			matchPoint++;
		}
		if (input.contains("予以")) {
			matchPoint++;
		}
		if (input.contains("证实") || input.contains("证明")) {
			matchPoint++;
		}

		if (matchPoint >= 3) {
			return true;
		} else {
			return false;
		}
	}

}
