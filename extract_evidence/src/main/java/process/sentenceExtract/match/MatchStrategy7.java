package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy7 implements MatchStrategy{

	/* (non-Javadoc)
	 * 为主张其...提交...。
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para) {
		String regex = "[^。]*(支持|证明)[^。]*(主张|请求)[^。]*(提交|提供)[^。]*。";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match5:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            return true;
        }
		
		return false;
	}

}
