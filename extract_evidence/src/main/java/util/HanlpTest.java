package util;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

public class HanlpTest {
	
	public static void main(String[] args){
		HanlpTest t = new HanlpTest();
		t.start2();
	}
	
	public void start(){
//		Segment segment = new CRFSegment();
//		segment.enablePartOfSpeechTagging(true);
		List<Term> termList = NLPTokenizer.segment("你好屌呀");
		System.out.println(termList);
	}
	
	public void start2(){
        CoNLLSentence sentence = HanLP.parseDependency("抓获经过");
        System.out.println(sentence);
        // 可以方便地遍历它
        for (CoNLLWord word : sentence)
        {
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
	}
	
}
