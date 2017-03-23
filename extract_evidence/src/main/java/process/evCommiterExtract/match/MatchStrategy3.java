package process.evCommiterExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy3 implements MatchStrategy{
	/* 
	 * 为主张其...提交...。
	 */
	@Override
	public boolean match(EvPara evPara) {
		String regex = "[^。]*(支持|证明)[^。]*(主张|请求)[^。]*(提交|提供)[^。]*。";
		
		Pattern mPattern = Pattern.compile(regex);
		Matcher mMatcher = mPattern.matcher(evPara.getContent());
		while(mMatcher.find()){
			evPara.setCommiter(mMatcher.group(1));
			return true;
		}
		return false;
	}

}
