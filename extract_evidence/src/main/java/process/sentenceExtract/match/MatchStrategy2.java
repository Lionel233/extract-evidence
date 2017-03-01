package process.sentenceExtract.match;

import model.EvPara;

public class MatchStrategy2  implements MatchStrategy{

	@Override
	public boolean match(EvPara para) {
		String content1 = para.getContent();

		// 标号目前最高有25，不使用集合暂时没想到什么好方法，
		String[] sequenceNumber1 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25" };
		String[] sequenceNumber2 = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五",
				"十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五" };
		String[] sequenceNumber3 = { "（一）", "（二）", "（三）", "（四）", "（五）", "（六）", "（七）", "（八）", "（九）", "（十）", "（十一）",
				"（十二）", "（十三）", "（十四）", "（十五）", "（十六）", "（十七）", "（十八）", "（十九）", "（二十）", "（二十一）", "（二十二）", "（二十三）",
				"（二十四）", "（二十五）" };

		char[] puntuations = { '．','、', '，', ',','.'};

		String combine1 = null; // 标号为1组合
		String combine2 = null; // 标号为2组合
		String[] sequenceMatch = null; // 所匹配的标号集合
		int indexOfPuntuations = 0; // 所匹配的标点集合
		boolean isMatch = false; // 目前是否匹配

		// 先定位至标号前的那句话
		String[] stringList = content1.split("。");
		int matchIndex = -1;
		for (int i = 0; i < stringList.length; i++) {
			if (isMatchSequenceAhead(stringList[i])) {
				matchIndex = i;
				break;
			}
		}
		if (matchIndex < 0) {
			return false;
		}

		for (int i = 0; i < puntuations.length; i++) {
			combine1 = sequenceNumber1[0] + puntuations[i];
			combine2 = sequenceNumber1[1] + puntuations[i];
			if (content1.contains(combine1) && content1.contains(combine2)) {
				sequenceMatch = sequenceNumber1;
				indexOfPuntuations = i;
				isMatch = true;
				break;
			}
		}
		if (!isMatch) {
			for (int i = 0; i < puntuations.length; i++) {
				combine1 = sequenceNumber2[0] + puntuations[i];
				combine2 = sequenceNumber2[1] + puntuations[i];
				if (content1.contains(combine1) && content1.contains(combine2)) {
					sequenceMatch = sequenceNumber2;
					indexOfPuntuations = i;
					isMatch = true;
					break;
				}
			}
		}
		if (!isMatch) {
			for (int i = 0; i < puntuations.length; i++) {
				combine1 = sequenceNumber3[0] + puntuations[i];
				combine2 = sequenceNumber3[1] + puntuations[i];
				if (content1.contains(combine1) && content1.contains(combine2)) {
					sequenceMatch = sequenceNumber3;
					indexOfPuntuations = i;
					isMatch = true;
					break;
				}
			}
		}

		if (!isMatch) {
			return false;
		}

		// 提取从第一个标号开始到最后一个标号结束(以句号为标志)的所有文字
		StringBuilder result = new StringBuilder("");
		int currentIndex = 0;
		for (int i = matchIndex; i < stringList.length; i++) {
			String token = stringList[i];
			try {
				if (token.contains(sequenceMatch[currentIndex] + puntuations[indexOfPuntuations])) {
					result.append(token).append("。");
					currentIndex++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		para.setKeyContent(result.toString());
		return true;
		//return result.toString();
	}


	/**
	 * 判断是否与第一个标号前的文字相匹配
	 * 
	 * @param input
	 * @return
	 */
	private boolean isMatchSequenceAhead(String input) {

//		if (!(input.contains("上述") && input.contains("事实"))) {
//			return false;
//		}

		// 质证/认证
		// 提供/提交
		// 证据/材料
		// 予以
		// 证实/证明
		// ：
		double TOTAL_POINT = 4;
		double matchPoint = 0;
		if (input.contains("质证") || input.contains("认证")) {
			matchPoint += 0.5;
		}
		if(input.contains("提供") || input.contains("提交")){
			matchPoint++;
		}
		if (input.contains("证据") || input.contains("材料")) {
			matchPoint++;
		}
		if (input.contains("予以")) {
			matchPoint += 0.5;
		}
		if (input.contains("证实") || input.contains("证明")) {
			matchPoint++;
		}
		if (input.contains("：")) {
			matchPoint++;
		}
		if (matchPoint >= 1.9999) {
			return true;
		}
		return false;
	}
}
