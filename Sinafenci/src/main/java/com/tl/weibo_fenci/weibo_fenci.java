package com.tl.weibo_fenci;

import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.log4j.Logger;
public class weibo_fenci extends UDF {
	public static String evaluate (String a) {
		Logger logger=Logger.getLogger(weibo_fenci.class);
		logger.info("this is infor");
		// 指定要进行分词的句子
		String str = a;
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
		return stringBuilder.toString();
		}
	public static void main(String[] args) {
		String aString=evaluate("今天天气好 你说呢");
		System.out.println(aString);
	}
	}
