package process.sentenceExtract;

public class AdministrativeFirstExtractor extends KeyContentExtractor {
	
	private static AdministrativeFirstExtractor extractor = new AdministrativeFirstExtractor();
	private AdministrativeFirstExtractor(){}
	public static AdministrativeFirstExtractor getInstance(){
		return extractor;
	}

}
