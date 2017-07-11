package com.fireCloud.tradCity.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
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
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;

/**
 * @fun 单例模式，并且操作会员和商品的IndexWriter和IndexSearcher也都是单例
 * @author wqy
 *
 * @date 2017年7月11日
 */
public class LuceneUtil {
	private Analyzer analyzer = new IKAnalyzer();

	private Directory directory;

	private String member_filePath = System.getProperty("user.dir") + File.separator + "luence" + File.separator
			+ "member";

	private String product_filePath = System.getProperty("user.dir") + File.separator + "luence" + File.separator
			+ "product";

	private IndexWriter memberIndexWriter = getIndexWriter(member_filePath);

	private IndexWriter productIndexWriter = getIndexWriter(product_filePath);

	private IndexSearcher memberIndexSearch;

	private IndexSearcher productIndexSearch;

	private static LuceneUtil instancs = null;

	private static String dateFormat = "yyyy-MM-dd hh:mm:ss";

	private DirectoryReader reader;

	private LuceneUtil() {
	}

	public static synchronized LuceneUtil getInstance() {
		if (instancs == null) {
			instancs = new LuceneUtil();
		}
		return instancs;
	}

	public void writerMemberIndx(List<SimpleMemberInfoModel> memberInfoList) {
		Document doc = null;
		long time = 0;
		for (SimpleMemberInfoModel model : memberInfoList) {

			doc = new Document();
			doc.add(new StringField("id", model.getMemberId()+"", Store.YES));
			doc.add(new TextField("memberName", model.getMemberName(), Store.NO));
			doc.add(new TextField("product", model.getProduct(), Store.NO));
			doc.add(new IntField("reputation", model.getReputation(), Store.NO));
			doc.add(new StringField("guarantee", model.getGuarantee() + "", Store.NO));
			doc.add(new StringField("highQuality", model.getHighQuality() + "", Store.NO));
			doc.add(new IntField("isRel", model.getIsRel(), Store.NO));
			try {
				time = DateUtil.stringToLong(model.getEnterTime(), dateFormat);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			doc.add(new LongField("enterTime", time, Store.NO));
			doc.add(new StringField("sincerity", model.getSincerity() + "", Store.NO));
			doc.add(new StringField("returnGoods", model.getReturnGoods() + "", Store.NO));
			doc.add(new StringField("province", model.getProvince(), Store.NO));
			doc.add(new StringField("city", model.getCity(), Store.NO));
			doc.add(new StringField("area", model.getArea(), Store.NO));
			doc.add(new StringField("address", model.getAddress(), Store.NO));
			doc.add(new IntField("popularize", model.getPopularize(), Store.NO));
			doc.add(new StringField("isDel", model.getIsDel() + "", Store.NO));
			try {
				memberIndexWriter.addDocument(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			memberIndexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String,Object> search(Object obj, Pagination pagination, SortModelList sortList) throws Exception {

		Map<String,Object> result = new HashMap<String,Object>();
		
		List<String> keyWordsList = new ArrayList<String>();
		List<String> filedsList = new ArrayList<String>();
		List<BooleanClause.Occur> occurList = new ArrayList<BooleanClause.Occur>();

		if (obj instanceof SimpleMemberInfoModel) {
			SimpleMemberInfoModel memberInfo = (SimpleMemberInfoModel) obj;

			memberIndexSearch = getIndexSearch(member_filePath);

			//封装查询参数
			renderQueryParameter(keyWordsList, filedsList, occurList, memberInfo);

			Query query = MultiFieldQueryParser.parse(keyWordsList.toArray(new String[keyWordsList.size()]),
					filedsList.toArray(new String[filedsList.size()]),
					occurList.toArray(new BooleanClause.Occur[occurList.size()]), analyzer);

			System.out.println("Searching for: " + query.toString());
			//封装排序参数
			Sort sort = renderSortParameter(sortList);
			
			ScoreDoc scoreDoc = getLastScoreDoc(pagination.getCurrentPage(), pagination.getNumPerPage(), query, memberIndexSearch, sort);
			TopDocs results = memberIndexSearch.searchAfter(scoreDoc, query, pagination.getNumPerPage(), sort);
			System.out.println("Total match：" + results.totalHits);
			ScoreDoc[] hits = results.scoreDocs;
			List<String> memberIdList = new ArrayList<String>();
			for (ScoreDoc hit : hits) {
				Document doc1 = memberIndexSearch.doc(hit.doc);
				String res = doc1.get("id");
				memberIdList.add(res);
			}
			result.put(Constants.TOTAL, results.totalHits);
			result.put(Constants.ID_LIST, memberIdList);
		}
		
		return result;
	}

	private Sort renderSortParameter(SortModelList sortList) {
		List<SortField> sortFieldList = new ArrayList<SortField>();
		SortField s3 = new SortField("isRel", Type.INT, false);
		SortField s1 = new SortField("popularize", Type.INT, false);
		sortFieldList.add(s3);
		sortFieldList.add(s1);
		if(sortList != null && sortList.getSortList() != null && sortList.getSortList().size() > 0){
			List<SortModel> list = sortList.getSortList();
			
			SortField sortField = null;
			for(SortModel model : list){
				if("enterTime".equals(model.getSortField())){
					sortField = new SortField(model.getSortField(), Type.LONG,"desc".equals(model.getSortRule()) ? false : true);
				} else {
					sortField = new SortField(model.getSortField(), Type.INT,"desc".equals(model.getSortRule()) ? false : true);
				}
				sortFieldList.add(sortField);
			}
		}
		sortFieldList.add(SortField.FIELD_SCORE);
		Sort sort = new Sort(sortFieldList.toArray(new SortField[sortFieldList.size()]));
		return sort;
	}

	private void renderQueryParameter(List<String> keyWordsList, List<String> filedsList,
			List<BooleanClause.Occur> occurList, SimpleMemberInfoModel memberInfo)
					throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Object o = null;

		Class clazz = memberInfo.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			o = getMethod.invoke(memberInfo, null);
			if (o != null) {
				keyWordsList.add(o + "");
				filedsList.add(field.getName());
				// 如果是memberName,需要memberName和product一起模糊查询
				if ("memberName".equals(field.getName())) {
					keyWordsList.add(o + "");
					filedsList.add("product");
					occurList.add(BooleanClause.Occur.SHOULD);
					occurList.add(BooleanClause.Occur.SHOULD);
				} else {
					occurList.add(BooleanClause.Occur.MUST);
				}
			}
			if("isDel".equals(field.getName())){
				keyWordsList.add("0");
				filedsList.add("isDel");
				occurList.add(BooleanClause.Occur.MUST);
			}
		}
	}

	private ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, IndexSearcher searcher, Sort sort)
			throws IOException {
		if (pageIndex == 1)
			return null;// 如果是第一页就返回空
		int num = pageSize * (pageIndex - 1);// 获取上一页的最后数量
		TopDocs tds = searcher.search(query, num, sort);
		return tds.scoreDocs[num - 1];
	}

	private IndexWriter getIndexWriter(String filePath) {
		// 3.创建IndexWriterConfig
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		// 4.创建IndexWriter
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			directory = FSDirectory.open(file);
			// 创建writer
			return new IndexWriter(directory, iwc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private IndexSearcher getIndexSearch(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (member_filePath.equals(filePath)) {

			if (memberIndexSearch == null) {
				try {
					directory = FSDirectory.open(file);
					reader = DirectoryReader.open(directory);
					return new IndexSearcher(reader);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				if (reader != null) {
					try {
						directory = FSDirectory.open(file);
						DirectoryReader newReader = DirectoryReader.openIfChanged(reader);
						if (newReader != null) {
							reader.close();
							reader = newReader;
							return new IndexSearcher(reader);
						} else {
							return memberIndexSearch;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return memberIndexSearch;
			}
		}

		return null;

	}

}
