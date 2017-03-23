package process.evDetailExtract;

import java.util.ArrayList;

import model.EvPara;
import model.EvRecord;
import model.PreEv;

public class EvidenceDetailExtractor implements EvidenceDetailExtract{
	
	private int currentIndex;
	
	private static EvidenceDetailExtract evidenceDetailExtractor = new EvidenceDetailExtractor();
	
	private EvidenceDetailExtractor(){};
	
	public static EvidenceDetailExtract getInstance(){
		return evidenceDetailExtractor;
	}
	
	@Override
	public boolean extractDetails(PreEv preEv,EvPara evpara) {
		ArrayList<EvRecord> recordList = new ArrayList<EvRecord>();
		
		if(evpara.getResolveType()==null){
			return false;
		}
		//只取第一小节为证据名称，每个标号为一个明细
		else if(evpara.getResolveType().equals("MatchStrategy2")){
			for(String token:evpara.getEvContentList()){
				if(token==null || token.isEmpty()) return false;
				EvRecord record = new EvRecord();
				record.setName(token.split("，|,|。|\\.|；|;|：")[0]);
				record.setContent(token);
				record.setType(EvTypeJudge.judge(record.getName()));
				recordList.add(record);
			}
		}
		else{
			String evContent = evpara.getEvContent();
			if(evContent == null) return false;
			evContent = evContent.replaceAll("(等|等证据)$", "").trim();
			if(evContent.isEmpty()) return false;
			
//			String[] contents = evContent.split("，|,|。|\\.|；|;|：|、");
//			
//			for(String token:contents){
//				EvRecord record = new EvRecord();
//				record.setName(token);
//				record.setContent(token);
//				record.setType(EvTypeJudge.judge(record.getName()));
//				recordList.add(record);
//			}
			
			//目前尚未判断是否 真的为证据，未对证言做特殊处理
			//根据关键词和标点进行分句
			currentIndex = 0;
			String step = null;
			String cache = "";
			while((step = next(evContent)) != null){
				if(EvTypeJudge.containsKeyword(step)){
					cache += step;
					
					EvRecord record = new EvRecord();
					record.setName(cache);
					record.setContent(cache);
					record.setType(EvTypeJudge.judge(record.getName()));
					recordList.add(record);
					cache = "";
				}
				else{
					cache += step;
				}
			}
		}
		evpara.setRecordList(recordList);
		return true;
	}
	
	/**
	 * 根据标点返回下一小句，同时改变指针
	 * @param currentIndex
	 * @param step
	 * @param content
	 * @return
	 */
	private String next(String content){
		if(currentIndex >= content.length() || content.isEmpty()) return null;
		String step = "";
		
		char[] punctuations = {'，',',','。','.','；',';','：','、'};
		
		boolean isPunc = false;

		for(char c:punctuations){
			if(content.charAt(currentIndex) == c){
				isPunc = true;
				step += c;
				currentIndex++;
				break;
			}
		}
		while(currentIndex < content.length() &&  !isPunc){
			isPunc = false;
			
			step += content.charAt(currentIndex);
			currentIndex++;
			
			for(char c:punctuations){
				if(currentIndex < content.length() && content.charAt(currentIndex) == c){
					isPunc = true;
					break;
				}
			}
		}
		
		return step;
	}

}
