package process.sentenceExtract;

public class CriminalSecondExtractor extends KeyContentExtractor {
	private static CriminalSecondExtractor extractor = new CriminalSecondExtractor();
	private CriminalSecondExtractor(){}
	
	{}
	
	
	public static CriminalSecondExtractor getInstance(){
		return extractor;
	}

}
