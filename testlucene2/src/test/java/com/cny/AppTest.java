package com.cny;

import static org.junit.Assert.assertTrue;

import com.cny.mode.Article;
import com.cny.utils.LuceneUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * 普通写
     */
    @Test
    public void testWriteDoc() throws IOException {
        Article article = new Article(1, "马云", "阿里巴巴总裁");
        Document document = new Document();
        document.add(new Field("id", article.getId().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("titile", article.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content", article.getContent(), Field.Store.YES, Field.Index.ANALYZED));
        Directory directory = FSDirectory.open(new File("E:/LuceneDB"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter.MaxFieldLength maxFieldLength = new IndexWriter.MaxFieldLength(100);
        IndexWriter indexWriter = new IndexWriter(directory, analyzer, maxFieldLength);
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    /**
     * 普通读
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testReadDoc() throws IOException, ParseException {
        FSDirectory directory = FSDirectory.open(new File("E:/LuceneDB"));
        IndexSearcher indexSearcher = new IndexSearcher(directory);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        QueryParser queryParser = new QueryParser(Version.LUCENE_30, "content", analyzer);
        Query query = queryParser.parse("巴巴总裁");
        TopDocs topDocs = indexSearcher.search(query, 10);

        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            int no = scoreDoc.doc;
            Document document = indexSearcher.doc(no);
            Integer id = Integer.parseInt(document.get("id"));
            String title = document.get("titile");
            String content = document.get("content");
//            Article article = new Article(id, title, content);
            System.out.println("id:" + id);
            System.out.println("title:" + title);
            System.out.println("content:" + content);
        }
        indexSearcher.close();
    }

    /**
     * 写
     */
    @Test
    public void testUtilsWrite() {

        Article article = new Article(2, "马化腾", "腾讯公司总裁，一把手");
        try {
            LuceneUtil.writeObject(article);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("IllegalAccessException异常出现了");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException异常出现了");
        }
    }

    /**
     * 读
     */
    @Test
    public void testUtilsRead() {
        try {
            List<Object> objs = LuceneUtil.readObject("一把手", Article.class);
            List<Article> articles = new ArrayList<>();
            for (Object obj : objs) {
                Article article = (Article) obj;
                articles.add(article);
                System.out.println(article.getId());
                System.out.println(article.getTitle());
                System.out.println(article.getContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("见鬼了");
        }
    }

    /**
     * 改
     */
    @Test
    public void testUtilsUpdate() {
        Article mazai = new Article(2, "马化腾", "小马仔，江湖不可多得的人才");
        try {
            LuceneUtil.updateObject(mazai);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("见鬼了");
        }

    }

    /**
     * 删
     */
    @Test
    public void testUtilsDelete() {
        Article mazai = new Article(2, "马化腾", "小马仔，江湖不可多得的人才");
        try {
            LuceneUtil.deleteObject(mazai);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除马仔出错啦");
        }
    }

    /**
     * 索引库文件合并
     */
    @Test
    public void testMergeFile() throws Exception{
        Article article = new Article(1, "马云", "阿里巴巴总裁");
        Document document = new Document();
        document.add(new Field("id", article.getId().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("titile", article.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content", article.getContent(), Field.Store.YES, Field.Index.ANALYZED));
        Directory directory = FSDirectory.open(new File("E:/LuceneDB"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter.MaxFieldLength maxFieldLength = new IndexWriter.MaxFieldLength(100);
        IndexWriter indexWriter = new IndexWriter(directory, analyzer, maxFieldLength);
        indexWriter.addDocument(document);
//        indexWriter.optimize(5);
        indexWriter.setMergeFactor(3);
        indexWriter.close();
    }

    /**
     * 分词器测试
     */
    @Test
    public  void testAnalyzer() throws IOException {
//        analyzerMethod(new StandardAnalyzer(Version.LUCENE_30), "传智播客说我们的首都是北京呀I AM zhaojun");
//        analyzerMethod(new CJKAnalyzer(Version.LUCENE_30), "你妈妈的~ 你真的就是个混蛋");
//        analyzerMethod(new IKAnalyzer(), "你妈妈的~ 你真的就是个混蛋");
        analyzerMethod(new IKAnalyzer(), "传智播客说我们的首都是北京呀I AM zhaojun");
    }
    private static void analyzerMethod(Analyzer analyzer,String text) throws IOException {
        TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(text));
        tokenStream.addAttribute(TermAttribute.class);
        while (tokenStream.incrementToken()) {
            TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
            System.out.println(termAttribute.term());
        }
    }
}


















