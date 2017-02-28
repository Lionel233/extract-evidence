package process.sentenceExtract;

public class CivilFirstExtractor extends KeyContentExtractor {
	private static CivilFirstExtractor extractor = new CivilFirstExtractor();
	private CivilFirstExtractor(){}
	
	{}
	
	public static CivilFirstExtractor getInstance(){
		return extractor;
	}
}
