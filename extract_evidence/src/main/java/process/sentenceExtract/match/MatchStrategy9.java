package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy9  implements MatchStrategy{
	/* (non-Javadoc)
	 * xxx(为证明其主张)提供如下证据：...。
	 * @see process.sentenceExtract.match.MatchStrategy#match(model.EvPara)
	 */
	@Override
	public boolean match(EvPara para) {
		
        String regex = "(以上|上述)[^。]*事实[^。]*(举证|质证)[^。]*(证据|材料)[^。]*：([^。]*)(等|证据|材料|予以|在卷|在案)[^。]*(佐证|证明|证实|为凭|为证|。)";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match4:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            para.setEvContent(mMatcher.group(4));
            return true;
        }
		
		return false;
	}
}
