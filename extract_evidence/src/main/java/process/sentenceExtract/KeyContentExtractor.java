package process.sentenceExtract;

import java.util.ArrayList;

import model.EvPara;
import model.PreEv;
import process.sentenceExtract.match.MatchStrategy;

/**
 * 基于模式的判断
 *
 * 当前正在基于刑事一审编写测试代码
 */
public abstract class KeyContentExtractor implements KeyContentExtract{
	
	/**
	 * 策略列表
	 */
	protected ArrayList<MatchStrategy> strategyList = new ArrayList<MatchStrategy>();
	
	public static KeyContentExtract getInstance(String type){
		switch(type){
		case "刑事一审":
			return CriminalFirstExtractor.getInstance();
		case "刑事二审":
			return CriminalSecondExtractor.getInstance();
		case "民事一审":
			return CivilFirstExtractor.getInstance();
		case "民事二审":
			return CivilSecondExtractor.getInstance();
		case "行政一审":
			return AdministrativeFirstExtractor.getInstance();
		case "行政二审":
			return AdministrativeSecondExtractor.getInstance();
		default:
			return null;
		}
	}
	
	/**
	 * 所有子类在定义策略列表之后均可调用此方法以顺序执行各个策略
	 * @param model
	 * @return empty list if none match
	 */
	protected boolean match(EvPara para){
		
		boolean flag = false; 
		for(MatchStrategy strategy : strategyList){
			if(strategy.match(para)){
				flag = true;
				para.setResolveType(strategy.getClass().getSimpleName());
				break;
			}
		}
		
		return flag;
	}
	
	public boolean extractSentences(PreEv model){
		boolean flag = false;
		for(EvPara para:model.getEvParaList()){
			flag = (flag || this.match(para));
			if(flag){
				break;
			}
		}
		//for debug
//		if(!flag){
//			System.out.println(model.getPath());
//		}
		System.out.print(model.getPath());
		for(EvPara para:model.getEvParaList()){
			System.out.print("\t" + para.getResolveType());;
		}
		System.out.println();
		return flag;
	}
	
}
