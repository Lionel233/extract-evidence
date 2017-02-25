package process.sentenceExtract;

import model.QwModel;

public class AdministrativeSecondExtractor implements EvidenceSentenceExtract {
	private static AdministrativeSecondExtractor extractor = new AdministrativeSecondExtractor();
	private AdministrativeSecondExtractor(){}
	public static AdministrativeSecondExtractor getInstance(){
		return extractor;
	}
	@Override
	public String extractSentences(QwModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
