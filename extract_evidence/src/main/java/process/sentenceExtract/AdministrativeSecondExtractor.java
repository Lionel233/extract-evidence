package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy2;
import process.sentenceExtract.match.MatchStrategy3;
import process.sentenceExtract.match.MatchStrategyNoNew;

public class AdministrativeSecondExtractor extends KeyContentExtractor {
	private static AdministrativeSecondExtractor extractor = new AdministrativeSecondExtractor();
	private AdministrativeSecondExtractor(){}
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy2());
		this.strategyList.add(new MatchStrategy3());
		this.strategyList.add(new MatchStrategyNoNew());
	}
	
	public static AdministrativeSecondExtractor getInstance(){
		return extractor;
	}

}
