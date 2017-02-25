package process.sentenceExtract;

import model.QwModel;

public class CriminalSecondExtractor implements EvidenceSentenceExtract {
	private static CriminalSecondExtractor extractor = new CriminalSecondExtractor();
	private CriminalSecondExtractor(){}
	public static CriminalSecondExtractor getInstance(){
		return extractor;
	}
	@Override
	public String extractSentences(QwModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
