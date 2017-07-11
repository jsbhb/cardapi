package com.fireCloud.tradCity.test.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LucenePoJo extends Thread{

	private static Analyzer analyzer = new IKAnalyzer();

	private Directory directory;

//	private static String filePath = System.getProperty("user.dir") + File.separator + "luence" + File.separator + "test";

	private String filePath;
	public LucenePoJo(String filePath){
		this.filePath = filePath;
	}
	
	public static void main(String[] args) throws Exception {
		LucenePoJo l = new LucenePoJo(System.getProperty("user.dir") + File.separator + "luence" + File.separator + "test");
		LucenePoJo ll = new LucenePoJo(System.getProperty("user.dir") + File.separator + "luence" + File.separator + "test");
		try {
			l.run();
			ll.run();
//			long time = System.currentTimeMillis();
//			l.search();
//			System.out.println("搜索时间：" + (System.currentTimeMillis() - time));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void run() {
		try {
			writerIndx(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long stringToLong(String strTime, String formatType) throws ParseException {
		Date date = stringToDate(strTime, formatType); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}

	public long dateToLong(Date date) {
		return date.getTime();
	}

	public Date stringToDate(String strTime, String formatType) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		try {
			date = formatter.parse(strTime);
		} catch (ParseException e) {
			try {
				date = formatter.parse("2017-06-29 14:37:36");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return date;
	}

	private void writerIndx(String filePath) throws IOException, ParseException {
		long time = System.currentTimeMillis();
		// 3.创建IndexWriterConfig
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		// 4.创建IndexWriter
		IndexWriter iw = null;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		directory = FSDirectory.open(file);
		String temp = null;
		String[] tem = null;
		Document doc = null;
		String entertime = null;
		InputStreamReader isr = new InputStreamReader(
				new FileInputStream(new File("C:\\Users\\user\\Desktop\\212.csv")));
		BufferedReader br = new BufferedReader(isr);
		try {
			// 创建writer
			iw = new IndexWriter(directory, iwc);
			int i = 1;
			while ((temp = br.readLine()) != null) {
				System.out.println(i);
				tem = temp.split(",");
				doc = new Document();
				doc.add(new StringField("id", tem[0], Store.YES));
				doc.add(new TextField("memberName", tem[1], Store.NO));
				doc.add(new TextField("product", tem[2], Store.NO));
				doc.add(new StringField("reputation", tem[3], Store.NO));
				doc.add(new StringField("guarantee", tem[4], Store.NO));
				doc.add(new StringField("highQuality", tem[5], Store.NO));
				doc.add(new StringField("frontPicPath", tem[6], Store.NO));
				doc.add(new StringField("isRel", tem[7], Store.NO));
				entertime = tem[8];
				if (entertime == null || "NULL".equals(entertime)) {
					entertime = "2017-06-29 14:37:36";
				}
				doc.add(new LongField("enterTime", stringToLong(entertime.replace("\"", ""), "yyyy-MM-dd hh:mm:ss"),
						Store.NO));
				doc.add(new StringField("sincerity", tem[9], Store.NO));
				doc.add(new StringField("returnGoods", tem[10], Store.NO));
				doc.add(new StringField("province", tem[11], Store.NO));
				doc.add(new StringField("city", tem[12], Store.NO));
				doc.add(new StringField("area", tem[13], Store.NO));
				doc.add(new StringField("address", tem[14], Store.NO));
				doc.add(new StringField("popularize", tem[15], Store.NO));
				iw.addDocument(doc);
				i++;
			}
			System.out.println("创建索引：" + (System.currentTimeMillis() - time));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (iw != null) {
				iw.commit();
			}

		}
	}

	private ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, IndexSearcher searcher, Sort sort)
			throws IOException {
		if (pageIndex == 1)
			return null;// 如果是第一页就返回空
		int num = pageSize * (pageIndex - 1);// 获取上一页的最后数量
		TopDocs tds = searcher.search(query, num,sort);
		return tds.scoreDocs[num - 1];
	}

	private void search() throws Exception {

		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		IndexReader reader = DirectoryReader.open(FSDirectory.open(file));
		IndexSearcher searcher = new IndexSearcher(reader);
		String[] keyWords = {"涂料","涂料"};
		String[] fileds = {"memberName","product"};
		BooleanClause.Occur[] flags=new BooleanClause.Occur[]{BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};  

		Query query = MultiFieldQueryParser.parse(keyWords,fileds,flags,analyzer);

		
		System.out.println("Searching for: " + query.toString());
		SortField s1 = new SortField("popularize", Type.STRING, true);
		SortField s3 = new SortField("isRel", Type.STRING, true);
		SortField s2 = new SortField("enterTime", Type.LONG, false);
		Sort sort = new Sort(new SortField[] { s1, s3, s2, SortField.FIELD_SCORE, });
		ScoreDoc scoreDoc = getLastScoreDoc(2, 20, query, searcher,sort);
		TopDocs results = searcher.searchAfter(scoreDoc, query, 20,sort);
		System.out.println("Total match：" + results.totalHits);
		ScoreDoc[] hits = results.scoreDocs;
		int count = 1;
		for (ScoreDoc hit : hits) {
			Document doc1 = searcher.doc(hit.doc);
			String res = doc1.get("memberName");
			System.err.println(count + "  " + res + ", " + doc1.get("id") + "  " + doc1.get("product") +"  "+ doc1.get("enterTime"));
			count++;
		}
	}
}
