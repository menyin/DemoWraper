package com.cny.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Field;

public class LuceneUtil {
    private static Directory directory;
    private static Analyzer analyzer;
    private static Version version;
    private static IndexWriter.MaxFieldLength maxFieldLength;
    public static Directory getDirectory() {
        return directory;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    public static Version getVersion() {
        return version;
    }

    public static IndexWriter.MaxFieldLength getMaxFieldLength() {
        return maxFieldLength;
    }


    static {
        try {
            directory = FSDirectory.open(new File("E:/LuceneDB"));
            analyzer = new StandardAnalyzer(Version.LUCENE_30);
            maxFieldLength = new IndexWriter.MaxFieldLength(100);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeObject(Object object) throws IllegalAccessException, IOException {
        Document document = object2document(object);
        IndexWriter indexWriter = new IndexWriter(directory,analyzer,maxFieldLength);
        indexWriter.addDocument(document);
        indexWriter.close();
    }
    public static List<Object> readObject(String keyWord,Class<?> clazz) throws Exception {
        QueryParser queryParser = new QueryParser(Version.LUCENE_30,"content",analyzer);
        Query query = queryParser.parse(keyWord);
        IndexSearcher indexSearcher = new IndexSearcher(directory);
        TopDocs topDocs = indexSearcher.search(query, 10);
        List<Object> objs = new ArrayList<>();
        for (int i=0;i<topDocs.scoreDocs.length;i++){
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            int no = scoreDoc.doc;
            Document document = indexSearcher.doc(no);
            Object obj = clazz.newInstance();
            java.lang.reflect.Field[] declaredFields = clazz.getDeclaredFields();
            for (int j = 0; j < declaredFields.length; j++) {
                java.lang.reflect.Field declaredField = declaredFields[j];
                declaredField.setAccessible(true);
                Type genericType = declaredField.getGenericType();
                /*if("class java.lang.Integer".equals(genericType.toString())){
                    Integer newVal=Integer.parseInt(document.get(declaredField.getName()));
                    declaredField.set(obj,newVal);
                }else{
                    declaredField.set(obj,document.get(declaredField.getName()));
                }*/
                BeanUtils.setProperty(obj,declaredField.getName(),document.get(declaredField.getName()));
            }
            objs.add(obj);
        }
        indexSearcher.close();
        return objs;
    }

    public static void updateObject(Object object) throws Exception {
        Document document = object2document(object);
        Term term = new Term("id", document.get("id"));
        IndexWriter indexWriter = new IndexWriter(directory,analyzer,maxFieldLength);
        indexWriter.updateDocument(term,document);
        indexWriter.close();
    }
    public static void deleteObject(Object object)throws Exception {
        Document document = object2document(object);
        IndexWriter indexWriter = new IndexWriter(directory,analyzer,maxFieldLength);
        Term term = new Term("id", document.get("id"));
        indexWriter.deleteDocuments(term);
        indexWriter.close();
    }

    private static Document object2document(Object object) throws IllegalAccessException {
        Document document = new Document();
        Class<?> aClass = object.getClass();
        java.lang.reflect.Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            java.lang.reflect.Field field = fields[i];
            field.setAccessible(true);
            String name = field.getName();
            String value = field.get(object).toString();
            document.add(new Field(name,value, Field.Store.YES,Field.Index.ANALYZED));
        }
        return document;
    }

}
