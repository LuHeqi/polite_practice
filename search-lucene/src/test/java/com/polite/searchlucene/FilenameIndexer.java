package com.polite.searchlucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author polite
 * @date 2016-08-04 .
 */
public class FilenameIndexer {
    private IndexWriter iw;

    public FilenameIndexer(String indexPath) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iw = new IndexWriter(dir, iwc);
    }

    public void close() throws IOException {
        iw.close();
    }

    public int index(String dataDir) throws IOException {
        File file = new File(dataDir);
        if (file.exists()) {
                indexFile(file);
        }

        return iw.numDocs();
    }

    public void indexFile(File file) throws IOException {
        if(file.isFile()){
            Document doc = getDocument(file);
            iw.addDocument(doc);
            return;
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                indexFile(f);//recursive call
            }
        }

    }

    private Document getDocument(File file) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("filename",file.getName(), Field.Store.YES));
        doc.add(new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES));
        return doc;
    }
}
