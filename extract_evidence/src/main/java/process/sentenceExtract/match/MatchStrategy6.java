package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy6  implements MatchStrategy{

	/* (non-Javadoc)
	 * 提交...证明...予以采信
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para) {
		String regex = "[^。]*(提供|提交)[^。]*(证明|证实).*(采信|认定)[^。]*。";
        
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
