package process.evCommiterExtract;


import java.util.ArrayList;

import model.EvPara;
import model.Litigant;
import model.PreEv;
import process.evCommiterExtract.match.MatchStrategy;
import process.evCommiterExtract.match.MatchStrategy1;
import process.evCommiterExtract.match.MatchStrategy2;


public class EvCommiterExtractor implements EvCommiterExtract{
	protected ArrayList<MatchStrategy> strategyList = new ArrayList<MatchStrategy>();
	{
		this.strategyList.clear();
		this.strategyList.add(new MatchStrategy1());
		this.strategyList.add(new MatchStrategy2());
	}
	@Override
	public boolean getCommiter(PreEv preEv,EvPara evPara){
		boolean flag = false;
		for(MatchStrategy strategy :strategyList){
			if(strategy.match(evPara)){
				String commiter = evPara.getCommiter();
				ArrayList<Litigant> litigants = preEv.getLitigantList();
				for(Litigant litigant :litigants){
					if(commiter.contains(litigant.getName())||commiter.contains(litigant.getType().getType())){
						evPara.setCommiter(litigant.getType().getType());
						break;
					}
				}
				flag = true;
			}
		}
		return flag;
			 
	}
}
