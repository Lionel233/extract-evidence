package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy1;
import process.sentenceExtract.match.MatchStrategy2;
import process.sentenceExtract.match.MatchStrategy3;
import process.sentenceExtract.match.MatchStrategy6;
import process.sentenceExtract.match.MatchStrategy7;

public class CivilFirstExtractor extends KeyContentExtractor {
	private static CivilFirstExtractor extractor = new CivilFirstExtractor();
	private CivilFirstExtractor(){}
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy1());
		this.strategyList.add(new MatchStrategy2());
		this.strategyList.add(new MatchStrategy3());
		this.strategyList.add(new MatchStrategy6());
		this.strategyList.add(new MatchStrategy7());
	}
	
	public static CivilFirstExtractor getInstance(){
		return extractor;
	}
}
