package process.sentenceExtract;

public class CivilSecondExtractor extends KeyContentExtractor  {
	private static CivilSecondExtractor extractor = new CivilSecondExtractor();
	private CivilSecondExtractor(){}
	
	{}
	
	public static CivilSecondExtractor getInstance(){
		return extractor;
	}

}
