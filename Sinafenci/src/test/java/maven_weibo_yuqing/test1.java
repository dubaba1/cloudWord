package maven_weibo_yuqing;

import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class test1 {
	public static void main(String[] args) {
		String aString="你好啊我是赛利亚！";
		Result result=NlpAnalysis.parse(aString);
		List<Term> list=result.getTerms();
	}

}
