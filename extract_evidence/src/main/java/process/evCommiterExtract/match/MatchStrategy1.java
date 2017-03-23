package process.evCommiterExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy1 implements MatchStrategy{
	/* 
	 * xx向法院提交
	 */
	@Override
	public boolean match(EvPara evPara) {
		String regex = "([^。]*)*(提交|提供)";
		
		Pattern mPattern = Pattern.compile(regex);
		Matcher mMatcher = mPattern.matcher(evPara.getContent());
		while(mMatcher.find()){
			evPara.setCommiter(mMatcher.group(1));
			return true;
		}
		return false;
	}
}
