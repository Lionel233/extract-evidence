package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy4 implements MatchStrategy{

	/* (non-Javadoc)
	 * xxx(为证明其主张)提供如下证据：...。
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para) {
		
        String regex = "[^。]*(提供|提交|认定)[^。]*(证据|材料)[^。]*：.*。";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match4:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            return true;
        }
		
		return false;
	}

}
