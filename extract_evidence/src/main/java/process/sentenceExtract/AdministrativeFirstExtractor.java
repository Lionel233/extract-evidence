package process.sentenceExtract;

import model.QwModel;

public class AdministrativeFirstExtractor implements EvidenceSentenceExtract{
	
	private static AdministrativeFirstExtractor extractor = new AdministrativeFirstExtractor();
	private AdministrativeFirstExtractor(){}
	public static AdministrativeFirstExtractor getInstance(){
		return extractor;
	}

	@Override
	public String extractSentences(QwModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
