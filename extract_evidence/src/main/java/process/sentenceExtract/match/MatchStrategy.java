package process.sentenceExtract.match;

import model.EvPara;

/**
 * @author gmq13
 *在证据段中提取证据分析的语句
 */
public interface MatchStrategy {
	/**
	 * 
	 * 如果有多段，则每一段分别调用此方法
	 * @param para 待提取的自然段
	 * @return 黄字 keySentence
	 */
	public boolean match(EvPara para);
}
