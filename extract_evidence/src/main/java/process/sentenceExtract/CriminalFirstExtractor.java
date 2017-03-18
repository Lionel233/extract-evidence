package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy1;
import process.sentenceExtract.match.MatchStrategy10;
import process.sentenceExtract.match.MatchStrategy2;
import process.sentenceExtract.match.MatchStrategy3;
import process.sentenceExtract.match.MatchStrategy9;

public class CriminalFirstExtractor extends KeyContentExtractor {
	private static CriminalFirstExtractor extractor = new CriminalFirstExtractor();

	private CriminalFirstExtractor(){};
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy2());
		
		this.strategyList.add(new MatchStrategy9());
		this.strategyList.add(new MatchStrategy10());
		this.strategyList.add(new MatchStrategy3());
		this.strategyList.add(new MatchStrategy1());
	}

	public static CriminalFirstExtractor getInstance() {
		return extractor;
	}



}
