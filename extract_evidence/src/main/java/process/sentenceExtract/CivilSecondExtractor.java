package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy2;
import process.sentenceExtract.match.MatchStrategy3;
import process.sentenceExtract.match.MatchStrategy4;
import process.sentenceExtract.match.MatchStrategy8;
import process.sentenceExtract.match.MatchStrategyNoNew;

public class CivilSecondExtractor extends KeyContentExtractor  {
	private static CivilSecondExtractor extractor = new CivilSecondExtractor();
	private CivilSecondExtractor(){}
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy2());
		this.strategyList.add(new MatchStrategy3());
		this.strategyList.add(new MatchStrategy4());
		this.strategyList.add(new MatchStrategy8());
		this.strategyList.add(new MatchStrategyNoNew());
	}
	
	public static CivilSecondExtractor getInstance(){
		return extractor;
	}

}
