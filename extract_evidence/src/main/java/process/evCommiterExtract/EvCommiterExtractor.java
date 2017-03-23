package process.evCommiterExtract;


import java.util.ArrayList;

import model.EvPara;
import process.evCommiterExtract.match.MatchStrategy;
import process.evCommiterExtract.match.MatchStrategy1;
import process.evCommiterExtract.match.MatchStrategy2;


public class EvCommiterExtractor {
	protected ArrayList<MatchStrategy> strategyList = new ArrayList<MatchStrategy>();
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy1());
		this.strategyList.add(new MatchStrategy2());
	}
	public boolean getCommiter(EvPara evPara){
		boolean flag = false;
		for(MatchStrategy strategy :strategyList){
			if(strategy.match(evPara)){
				flag = true;
			}
		}
		return flag;
			 
	}
}
