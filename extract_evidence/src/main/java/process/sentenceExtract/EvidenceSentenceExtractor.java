package process.sentenceExtract;

import model.QwModel;

/**
 * 基于模式的判断
 *
 * 当前正在基于刑事一审编写测试代码
 */
public abstract class EvidenceSentenceExtractor implements EvidenceSentenceExtract{
	
	private EvidenceSentenceExtractor(){}
	
	public static EvidenceSentenceExtract getInstance(String type){
		switch(type){
		case "刑事一审":
			return CriminalFirstExtractor.getInstance();
		case "刑事二审":
			return CriminalSecondExtractor.getInstance();
		case "民事一审":
			return CivilFirstExtractor.getInstance();
		case "民事二审":
			return CivilSecondExtractor.getInstance();
		case "行政一审":
			return AdministrativeFirstExtractor.getInstance();
		case "行政二审":
			return AdministrativeSecondExtractor.getInstance();
		default:
			return null;
		}
	}
	
}
