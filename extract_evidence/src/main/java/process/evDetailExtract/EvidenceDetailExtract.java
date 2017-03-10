package process.evDetailExtract;

import java.util.ArrayList;

import model.EvPara;
import model.EvRecord;

public interface EvidenceDetailExtract {
	
	/**
	 * 从所得到得证据段中提取出证据明细
	 * 
	 * @return
	 */
	public ArrayList<EvRecord> extractDetails(EvPara evpara);

}
