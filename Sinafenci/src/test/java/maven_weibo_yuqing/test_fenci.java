package maven_weibo_yuqing;

import java.util.List;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class test_fenci {
	public static void main(String[] args) {
		Logger logger=Logger.getLogger(test_fenci.class);
		 logger.setLevel(Level.ERROR);
		logger.info("this is infor");
		// 指定要进行分词的句子
		String str = "马蓉王宝强事件";
		// 采用nlp分词，具备(用户自定义词典/数字识别/人名识别/机构名识别/新词发现)功能
		Result result = NlpAnalysis.parse(str);
		// 将分词结果集合返回给变量itemList
		List<Term> termList = result.getTerms();
		//存储每次分词完成后的词序列集合，词之间以'\001'分隔
		StringBuilder stringBuilder = new StringBuilder();
		//循环记数器，当counter>0的时候，每次添加元素前先添加分隔符
		int counter = 0;
		//遍历集合,加入结果集中
		for (Term term : termList) {
		if (counter > 0) {
		stringBuilder.append('\001');
		}
		//只要分词的名字结果，不要词性部分
		stringBuilder.append(term.getName());
		counter++;
		}
		//将最后的汇总结果输出
		System.out.println(stringBuilder.toString());
		}
	}


