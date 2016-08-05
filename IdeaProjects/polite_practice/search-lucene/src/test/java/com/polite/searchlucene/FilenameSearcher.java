package com.polite.searchlucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author polite
 * @date 2016-08-05 .
 */
public class FilenameSearcher {
    public static  List<String> search(String indexPath,String queryString,int topNum) throws IOException, ParseException {
        List<String> list = new ArrayList<>();
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        IndexReader ir = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(ir);
        Analyzer analyzer = new StandardAnalyzer();
        QueryParser filenameParser = new QueryParser("filename",analyzer);

        Query query = filenameParser.parse(queryString);

        TopDocs topDocs = is.search(query, topNum);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            IndexableField fullPath = doc.getField("fullPath");
            list.add(fullPath.stringValue());
        }
        ir.close();
        return list;
    }
}
