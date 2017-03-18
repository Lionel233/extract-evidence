package process.evDetailExtract;

import java.util.ArrayList;

import model.EvPara;
import model.EvRecord;

public class EvidenceDetailExtractor implements EvidenceDetailExtract{
	
	private static EvidenceDetailExtract evidenceDetailExtractor = new EvidenceDetailExtractor();
	
	private EvidenceDetailExtractor(){};
	
	public static EvidenceDetailExtract getInstance(){
		return evidenceDetailExtractor;
	}
	
	@Override
	public boolean extractDetails(EvPara evpara) {
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
			
			String[] contents = evContent.split("，|,|。|\\.|；|;|：|、");
			
			//目前尚未判断是否 真的为证据，未对证言做特殊处理
			for(String token:contents){
				EvRecord record = new EvRecord();
				record.setName(token);
				record.setContent(token);
				record.setType(EvTypeJudge.judge(record.getName()));
				recordList.add(record);
			}
		}
		evpara.setRecordList(recordList);
		return true;
	}

}
