package process.sentenceExtract.match;

import model.QwModel;

/**
 * @author gmq13
 *在证据段中提取证据分析的语句
 */
public interface MatchStrategy {
	public String match(QwModel model);
}
