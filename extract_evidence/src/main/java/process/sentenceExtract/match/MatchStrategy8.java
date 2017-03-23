package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy8 implements MatchStrategy{

	/* (non-Javadoc)
	 * (原审认定以上事实的)证据有...等
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para) {
		String regex = "[^。]*(证据|材料)[^。]*有([^。]*)等[^。]*。";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match5:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            para.setEvContent(mMatcher.group(2));
            return true;
        }
		
		return false;
	}

}
