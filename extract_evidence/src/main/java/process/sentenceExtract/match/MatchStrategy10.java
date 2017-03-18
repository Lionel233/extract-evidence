package process.sentenceExtract.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EvPara;

public class MatchStrategy10 implements MatchStrategy{

	@Override
	public boolean match(EvPara para) {
        //String regex = "[^。]*(有|由)(((?!(有|由)).)*)(等|证据|材料|予以|在卷|在案)[^。]*(佐证|证明|证实|为凭|为证)[^。]*。";
        String regex = "[^。]*(以上|上述)[^。]*事实[^。]*?(且有|有|由)([^。]*)(等|证据|材料|予以|在卷|在案)[^。]*(佐证|证明|证实|为凭|为证)[^。]*。";
        
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(para.getContent());
        while (mMatcher.find()) {
            //System.out.println("match3:\t" + mMatcher.group(0));
            para.setKeyContent(mMatcher.group(0));
            para.setEvContent(mMatcher.group(3));
            return true;
        }
		
		return false;
	}

}
