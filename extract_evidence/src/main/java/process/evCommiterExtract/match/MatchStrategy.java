package process.evCommiterExtract.match;

import model.EvPara;

/**
 * @author gmq13
 *在证据段中提取证据提交人
 */
public interface MatchStrategy {

	public boolean match(EvPara evPara);
}
