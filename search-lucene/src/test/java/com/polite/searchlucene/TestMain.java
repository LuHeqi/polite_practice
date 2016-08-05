package com.polite.searchlucene;

import org.junit.Test;

import java.util.List;

/**
 * @author polite
 * @date 2016-08-05 .
 */
public class TestMain {

    @Test
    public void indexTest() throws Exception {
        String indexPath = "/home/lucene_index";
        String dataDir = "/home/polite";

        long start = System.currentTimeMillis();
        FilenameIndexer filenameIndexer = new FilenameIndexer(indexPath);

        int count = filenameIndexer.index(dataDir);
        filenameIndexer.close();
        System.out.println("  index count "+count +" total time "+(System.currentTimeMillis() - start));
    }

    @Test
    public void searchTest() throws Exception{
        String indexPath = "/home/lucene_index";
        String queryString = "idea";
        long start = System.currentTimeMillis();
        List<String> list = FilenameSearcher.search(indexPath, queryString,20);
        System.out.println("total time "+(System.currentTimeMillis() - start)+" match count : "+list.size());
        for (String s : list) {
            System.out.println(s);
        }
    }
}
