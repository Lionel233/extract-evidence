package process.sentenceExtract;

import model.QwModel;

public class CivilFirstExtractor implements EvidenceSentenceExtract {
	private static CivilFirstExtractor extractor = new CivilFirstExtractor();
	private CivilFirstExtractor(){}
	public static CivilFirstExtractor getInstance(){
		return extractor;
	}
	@Override
	public String extractSentences(QwModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
