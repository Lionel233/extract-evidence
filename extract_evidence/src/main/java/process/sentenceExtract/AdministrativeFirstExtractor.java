package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy2;
import process.sentenceExtract.match.MatchStrategy3;
import process.sentenceExtract.match.MatchStrategy4;
import process.sentenceExtract.match.MatchStrategy5;

public class AdministrativeFirstExtractor extends KeyContentExtractor {
	
	private static AdministrativeFirstExtractor extractor = new AdministrativeFirstExtractor();
	private AdministrativeFirstExtractor(){}
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy2());
		this.strategyList.add(new MatchStrategy3());
		this.strategyList.add(new MatchStrategy4());
		this.strategyList.add(new MatchStrategy5());
	}
	
	public static AdministrativeFirstExtractor getInstance(){
		return extractor;
	}

}
