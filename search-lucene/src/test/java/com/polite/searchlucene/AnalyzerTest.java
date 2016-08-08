package com.polite.searchlucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;

/**
 * @author polite
 * @date 2016-08-08 .
 */
public class AnalyzerTest {
    public static void main(String[] args) throws IOException {
        String text = "ADFRET043F worlds 我们是中国人";
      //  Analyzer standardAnalyzer = new StandardAnalyzer();
        display(new StandardAnalyzer(),text);
        display(new CJKAnalyzer(),text);
        display(new IndonesianAnalyzer(),text);
        display(new ClassicAnalyzer(),text);
        display(new SimpleAnalyzer(),text);

    }

    private static void display(Analyzer analyzer, String text) throws IOException {
        System.out.printf(" current  analyzer is %s \n ",analyzer.getClass().getSimpleName());
        TokenStream tokenStream = analyzer.tokenStream("content", text);

        OffsetAttribute offsetAttribute =  tokenStream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);

        tokenStream.reset();

        int position = 0;
        while (tokenStream.incrementToken()) {
            int positionIncrement = positionIncrementAttribute.getPositionIncrement();
            if (positionIncrement > 0) {
                position += positionIncrement;
            }
            int startOffset = offsetAttribute.startOffset();
            int endOffset = offsetAttribute.endOffset();
            System.out.printf("第%d个分词， 分词内容是 ：%s , 开始位置：%d ==>结束位置：%d ,分词类型是：%s \n ",
                    position,charTermAttribute.toString(),startOffset,endOffset,typeAttribute.type());
        }
        tokenStream.close();
        System.out.println("========================================================");

    }
}
