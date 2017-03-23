package process.evDetailExtract;

import model.EvPara;
import model.PreEv;

public interface EvidenceDetailExtract {
	
	/**
	 * 从所得到得证据段中提取出证据明细
	 * 
	 * @return
	 */
	public boolean extractDetails(PreEv preEv,EvPara evpara);

}
