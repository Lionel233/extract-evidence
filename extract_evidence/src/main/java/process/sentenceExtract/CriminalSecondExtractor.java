package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy1;
import process.sentenceExtract.match.MatchStrategy2;
import process.sentenceExtract.match.MatchStrategy3;
import process.sentenceExtract.match.MatchStrategy4;
import process.sentenceExtract.match.MatchStrategyNoNew;

public class CriminalSecondExtractor extends KeyContentExtractor {
	private static CriminalSecondExtractor extractor = new CriminalSecondExtractor();
	private CriminalSecondExtractor(){}
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy1());
		this.strategyList.add(new MatchStrategy2());
		this.strategyList.add(new MatchStrategy3());
		this.strategyList.add(new MatchStrategy4());
		this.strategyList.add(new MatchStrategyNoNew());
	}
	
	
	public static CriminalSecondExtractor getInstance(){
		return extractor;
	}

}
