package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy5  implements MatchStrategy{

	/* (non-Javadoc)
	 * 提供...等证据材料
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 * 行政一审中使用到
	 */
	@Override
	public boolean match(EvPara para) {
		String regex = "[^。]*提供(.*)等(证据|材料)[\u4e00-\u9fa5]*。";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match5:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            para.setEvContent(mMatcher.group(1));
            return true;
        }
		
		return false;
	}

	
	
}
