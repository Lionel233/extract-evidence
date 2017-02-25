package process.sentenceExtract;

import model.QwModel;

public class CivilSecondExtractor implements EvidenceSentenceExtract {
	private static CivilSecondExtractor extractor = new CivilSecondExtractor();
	private CivilSecondExtractor(){}
	public static CivilSecondExtractor getInstance(){
		return extractor;
	}
	@Override
	public String extractSentences(QwModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
