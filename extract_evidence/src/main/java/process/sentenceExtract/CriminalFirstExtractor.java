package process.sentenceExtract;

import process.sentenceExtract.match.MatchStrategy1;
import process.sentenceExtract.match.MatchStrategy2;

public class CriminalFirstExtractor extends KeyContentExtractor {
	private static CriminalFirstExtractor extractor = new CriminalFirstExtractor();

	private CriminalFirstExtractor(){};
	
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy1());
		this.strategyList.add(new MatchStrategy2());
	}

	public static CriminalFirstExtractor getInstance() {
		return extractor;
	}



}
