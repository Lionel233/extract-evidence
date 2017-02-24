package process;

import model.QwModel;

public interface EvidenceSentenceExtract {
	
	/**
	 * 读取model 中的content1,content2以及位置信息
	 * 给出包含证据的句子
	 * 
	 * 如果同时有content1和content2，所得结果合并
	 * @param model
	 * @return
	 */
	public String extractSentences(QwModel model);

}
