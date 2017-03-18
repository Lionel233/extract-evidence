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
		List<Term> termList = NLPTokenizer.segment("上述事实，被告人王敏超在开庭审理过程未提出异议，且有经庭审举证、质证的下列证据证实：刑事判决书等书证；证人侯某、董某、李某的证言；被害人宋某的陈述；被告人王敏超的供述和辩解；唐山市人民检察院出具的对被害人宋某的人体损伤程度鉴定意见书；河北省监狱管理局唐山监狱所作的现场方位图及照片等证据材料证实");
		System.out.println(termList);
	}
	
	public void start2(){
        CoNLLSentence sentence = HanLP.parseDependency("被害人王某甲、王某乙的陈述");
        System.out.println(sentence);
        // 可以方便地遍历它
        for (CoNLLWord word : sentence)
        {
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
	}
	
}
