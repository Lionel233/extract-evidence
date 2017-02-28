package process.sentenceExtract;

import model.PreEv;

public interface KeyContentExtract {
	
	/**
	 * 读取model 中的content1,content2以及位置信息
	 * 给出包含证据的句子
	 * 
	 * 如果同时有content1和content2，所得结果合并
	 * @param model
	 * @return
	 */
	public boolean extractSentences(PreEv model);

}
