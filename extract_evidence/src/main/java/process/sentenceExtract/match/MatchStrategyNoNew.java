package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategyNoNew implements MatchStrategy{

	/* (non-Javadoc)
	 * 本审未提供新证据，本审证据与原审一样
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para) {
		String regex = "([^。]*未[^。]*(提交|提供)[^。]*新[^。]*(证据|材料|证明)[^。]*。)|([^。]*(证据|材料|证明)[^。]*与(原审|一审)相同)";
        
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
