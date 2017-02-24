package process;

import java.util.ArrayList;

import model.EvidenceRecord;

public interface EvidenceDetailExtract {
	
	/**
	 * 从所得到得包含证据的句子中提取出证据信息
	 * 
	 * @return
	 */
	public ArrayList<EvidenceRecord> extractDetails(String sentences);

}
