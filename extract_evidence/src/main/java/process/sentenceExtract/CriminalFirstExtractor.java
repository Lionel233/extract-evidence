package process.sentenceExtract;

import model.QwModel;

public class CriminalFirstExtractor implements EvidenceSentenceExtract {
	private static CriminalFirstExtractor extractor = new CriminalFirstExtractor();

	private CriminalFirstExtractor() {
	}

	public static CriminalFirstExtractor getInstance() {
		return extractor;
	}

	@Override
	public String extractSentences(QwModel model) {

		String result = null;

		if (result == null) {
			result = match1(model);
			if (result != null) {
				System.out.println("match1");
			}
		}
		if (result == null) {
			result = match2(model);
			if (result != null) {
				System.out.println("match2");
			}
		}
		if (result == null) {
			System.out.println("null:         " + model.getPath());
		}

		return result;
	}

	/**
	 * 上述事实...且有/并有...等证据予以证实，足以认定
	 * 
	 * @return null if not match
	 */
	private String match1(QwModel model) {

		String content1 = model.getContent1();
		if (content1 == null || content1.isEmpty()) {
			return null;
		}

		String[] stringList = content1.split("，|。| ,");

		boolean isLocated1 = false; // 上述事实是否被定位
		boolean isLocated2 = false; // 且有是否被定位
		boolean isLocated3 = false; // 结尾是否被定位
		int beginNumber = 0; // 所需要的句子的开头位置
		int endNumber = 0;
		for (int i = 0; i < stringList.length; i++) {
			// 找到上述事实的位置
			if (!isLocated1 && !(stringList[i].contains("上述事实") || stringList[i].contains("以上事实"))) {
				continue;
			} else if (!isLocated1) {
				isLocated1 = true;
				beginNumber = i;
			}

			if (stringList[i].contains("且有") || stringList[i].contains("并有")) {
				isLocated2 = true;
			}

			if (stringList[i].contains("足以认定") || isMatchEnd(stringList[i])) {
				endNumber = i;
				isLocated3 = true;
			}
		}

		// 判断是否为此种模式，如果是继续计算输出最终结果否则直接退出
		// 删除 且有/并有 的判断 ， 增加命中率
		// if(!isLocated1 || !isLocated2 || !isLocated3){
		// return null;
		// }
		if (!isLocated1 || !isLocated3) {
			return null;
		}

		int index1 = 0;
		int index2 = 0;
		isLocated1 = false;
		int splitOccurNumber = 0;
		for (int i = 0; i < content1.length(); i++) {
			if (splitOccurNumber == beginNumber && !isLocated1) {
				index1 = i;
				isLocated1 = true;
			}
			if (splitOccurNumber - 1 == endNumber) {
				index2 = i;
				break;
			}
			if (content1.charAt(i) == ',' || content1.charAt(i) == '，' || content1.charAt(i) == '。') {
				splitOccurNumber++;
			}
		}

		return content1.substring(index1, index2);

	}

	/**
	 * 上述事实...下列证据予以证实...(数字标号)
	 * 
	 * 划掉 ！！！ 此方法目前仅识别各种标号并提取包含标号的内容 由于有事实段标号信息存在，仅通过标号提取证据段并不可行，需要判断冒号前的文字
	 * 
	 * @param model
	 * @return
	 */
	private String match2(QwModel model) {

		String content1 = model.getContent1();
		String content2 = model.getContent2();

		// 标号目前最高有25，不使用集合暂时没想到什么好方法，
		String[] sequenceNumber1 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25" };
		String[] sequenceNumber2 = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五",
				"十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五" };
		String[] sequenceNumber3 = { "（一）", "（二）", "（三）", "（四）", "（五）", "（六）", "（七）", "（八）", "（九）", "（十）", "（十一）",
				"（十二）", "（十三）", "（十四）", "（十五）", "（十六）", "（十七）", "（十八）", "（十九）", "（二十）", "（二十一）", "（二十二）", "（二十三）",
				"（二十四）", "（二十五）" };

		char[] puntuations = { '.', '、', '，', ',' };

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
			}
		}
		if (matchIndex < 0) {
			return null;
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
			return null;
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

			}
		}

		return result.toString();
	}

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

	/**
	 * 判断是否与第一个标号前的文字相匹配
	 * 
	 * @param input
	 * @return
	 */
	private boolean isMatchSequenceAhead(String input) {

		if (!(input.contains("上述") && input.contains("事实"))) {
			return false;
		}

		// 质证/认证
		// 证据
		// 予以
		// 证实/证明
		// ：
		double TOTAL_POINT = 4;
		double matchPoint = 0;
		if (input.contains("质证") || input.contains("认证")) {
			matchPoint += 0.5;
		}
		if (input.contains("证据")) {
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
		if (matchPoint >= 2.9999) {
			return true;
		}
		return false;
	}

}
