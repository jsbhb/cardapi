package com.fireCloud.tradCity.util.lucene;

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
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.util.DateUtil;

/**
 * @fun 单例模式，并且操作会员和商品的IndexWriter和IndexSearcher也都是单例
 * @author wqy
 *
 * @date 2017年7月11日
 */
public class LuceneUtil {
	private Analyzer analyzer = new IKAnalyzer();

	private Directory directory;

	private String member_filePath = LuceneUtil.class.getClassLoader().getResource("").getPath() + "member";

	private String product_filePath = LuceneUtil.class.getClassLoader().getResource("").getPath() + "product";

	private IndexWriter memberIndexWriter = getIndexWriter(member_filePath);

	private IndexWriter productIndexWriter = getIndexWriter(product_filePath);

	private IndexSearcher memberIndexSearch;

	private IndexSearcher productIndexSearch;

	private int textmaxlength = 2000;

	private static String prefixHTML = "<font color='red'>";

	private static String suffixHTML = "</font>";

	private static LuceneUtil instancs = null;

	private static String dateFormat = "yyyy-MM-dd hh:mm:ss";

	private DirectoryReader reader;

	private static final int MEMBER_TYPE = 0;

	private static final int PRODUCT_TYPE = 1;

	private LuceneUtil() {
	}

	public static synchronized LuceneUtil getInstance() {
		if (instancs == null) {
			instancs = new LuceneUtil();
		}
		return instancs;
	}

	/**
	 * @fun 创建索引
	 * @param objList
	 */
	public <T> void writerIndex(List<T> objList) {
		
		if(objList.get(0) instanceof SimpleMemberInfoModel){
			buildMemberIndex(objList);
		}
		

	}


	/**
	 * lucene 搜索
	 * 
	 * @param obj
	 *            查询参数
	 * @param pagination
	 *            分页参数
	 * @param sortList
	 *            排序参数
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> search(Object obj, Pagination pagination, SortModelList sortList) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> keyWordsList = new ArrayList<String>();
		List<String> filedsList = new ArrayList<String>();
		List<BooleanClause.Occur> occurList = new ArrayList<BooleanClause.Occur>();
		Map<String, String> accuratePara = new HashMap<String, String>();

		if (obj instanceof SimpleMemberInfoModel) {
			searchMember(obj, pagination, sortList, result, keyWordsList, filedsList, occurList, accuratePara);
		}

		return result;
	}

	public void deleteIndex(Integer id, int type) {
		if (type == MEMBER_TYPE) {
			try {
				memberIndexWriter.deleteDocuments(new Term("id", id + ""));
				memberIndexWriter.commit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void updateIndex(Map<String,String> param){
		Document doc = new Document();
		
		for(Map.Entry<String, String> entry : param.entrySet()){
			if("memberName".equals(entry.getKey()) || "product".equals(entry.getKey())){
				doc.add(new StringField(entry.getKey(), entry.getValue(), Store.NO));
			} else {
				doc.add(new StringField(entry.getKey(), entry.getValue(), Store.YES));
			}
		}
		try {
			memberIndexWriter.updateDocument(new Term("id",param.get("id")), doc);
			memberIndexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private <T> void buildMemberIndex(List<T> objList) {
		Document doc;
		long time = 0;
		for (Object obj : objList) {
			
			SimpleMemberInfoModel model = (SimpleMemberInfoModel)obj;

			doc = new Document();
			doc.add(new StringField("id", model.getMemberId() + "", Store.YES));
			
			//企业名称设置权重
			TextField memberName = new TextField("memberName", model.getMemberName(), Store.YES);
			doc.add(memberName);
			memberName.setBoost(5.0f);
			
			//商品名称设置权重
			TextField product = new TextField("product", model.getProduct() == null ? "" : model.getProduct(), Store.YES);
			doc.add(product);
			product.setBoost(1.0f);
			
			
			doc.add(new StringField("reputation", model.getReputation() == null ? "0" : model.getReputation() + "",
					Store.NO));
			doc.add(new StringField("guarantee", model.getGuarantee() + "", Store.NO));
			doc.add(new StringField("highQuality", model.getHighQuality() + "", Store.NO));
			doc.add(new StringField("isRel", model.getIsRel() == null ? "0" : model.getIsRel() + "", Store.NO));
			try {
				time = DateUtil.stringToLong(model.getEnterTime(), dateFormat);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			doc.add(new LongField("enterTime", time, Store.NO));
			doc.add(new StringField("sincerity", model.getSincerity() + "", Store.NO));
			doc.add(new StringField("returnGoods", model.getReturnGoods() + "", Store.NO));
			doc.add(new StringField("province", model.getProvince() == null ? "" : model.getProvince(), Store.NO));
			doc.add(new StringField("city", model.getCity() == null ? "" : model.getCity(), Store.NO));
			doc.add(new StringField("area", model.getArea() == null ? "" : model.getArea(), Store.NO));
			doc.add(new StringField("address", model.getAddress() == null ? "" : model.getAddress(), Store.NO));
			doc.add(new StringField("popularize", model.getPopularize() == null ? "0" : model.getPopularize() + "",
					Store.NO));
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
	

	private void searchMember(Object obj, Pagination pagination, SortModelList sortList, Map<String, Object> result,
			List<String> keyWordsList, List<String> filedsList, List<BooleanClause.Occur> occurList,
			Map<String, String> accuratePara)
					throws IntrospectionException, IllegalAccessException, InvocationTargetException,
					org.apache.lucene.queryparser.classic.ParseException, IOException, InvalidTokenOffsetsException {
		SimpleMemberInfoModel memberInfo = (SimpleMemberInfoModel) obj;

		memberIndexSearch = getIndexSearch(member_filePath);

		// 封装查询参数
		renderQueryParameter(keyWordsList, filedsList, occurList, accuratePara, memberInfo);

		Query query = MultiFieldQueryParser.parse(keyWordsList.toArray(new String[keyWordsList.size()]),
				filedsList.toArray(new String[filedsList.size()]),
				occurList.toArray(new BooleanClause.Occur[occurList.size()]), analyzer);

		System.out.println("Searching for: " + query.toString());
		// 封装排序参数
		Sort sort = renderSortParameter(sortList);

		// 是否需要精确查找
		if (accuratePara.size() > 0) {
			LuceneFilter luceneFilter = new LuceneFilter();
			for (Map.Entry<String, String> entry : accuratePara.entrySet()) {
				luceneFilter.addFilter(entry.getKey(), entry.getValue());
			}
			query = luceneFilter.getFilterQuery(query);// 结果过滤
		}

		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(prefixHTML, suffixHTML);
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
		highlighter.setTextFragmenter(new SimpleFragmenter(this.textmaxlength));

		ScoreDoc scoreDoc = getLastScoreDoc(pagination.getCurrentPage(), pagination.getNumPerPage(), query,
				memberIndexSearch, sort);
		TopDocs results = memberIndexSearch.searchAfter(scoreDoc, query, pagination.getNumPerPage(), sort);
		System.out.println("Total match：" + results.totalHits);
		ScoreDoc[] hits = results.scoreDocs;
		List<Integer> memberIdList = new ArrayList<Integer>();
		SimpleMemberInfoModel info = null;
		Map<Integer, SimpleMemberInfoModel> highlighterModel = new HashMap<Integer, SimpleMemberInfoModel>();
		for (ScoreDoc hit : hits) {
			Document doc1 = memberIndexSearch.doc(hit.doc);
			String res = doc1.get("id");
			if (res != null) {
				info = new SimpleMemberInfoModel();
				info.setMemberName(highlighter.getBestFragment(analyzer, "memberName", doc1.get("memberName")));
				info.setProduct(highlighter.getBestFragment(analyzer, "product", doc1.get("product")));
				highlighterModel.put(Integer.parseInt(res), info);
				memberIdList.add(Integer.parseInt(res));
			}
		}
		result.put(Constants.TOTAL, results.totalHits);
		result.put(Constants.ID_LIST, memberIdList);
		result.put(Constants.HIGHLIGHTER_MODEL, highlighterModel);
	}

	private Sort renderSortParameter(SortModelList sortList) {
		List<SortField> sortFieldList = new ArrayList<SortField>();
		SortField s3 = new SortField("isRel", Type.STRING, true);
		SortField s1 = new SortField("popularize", Type.STRING, true);
		sortFieldList.add(s3);
		sortFieldList.add(s1);
		if (sortList != null && sortList.getSortList() != null && sortList.getSortList().size() > 0) {
			List<SortModel> list = sortList.getSortList();

			SortField sortField = null;
			for (SortModel model : list) {
				if ("enterTime".equals(model.getSortField())) {
					sortField = new SortField(model.getSortField(), Type.LONG,
							"desc".equals(model.getSortRule()) ? true : false);
				} else {
					sortField = new SortField(model.getSortField(), Type.STRING,
							"desc".equals(model.getSortRule()) ? true : false);
				}
				sortFieldList.add(sortField);
			}
		}
		sortFieldList.add(SortField.FIELD_SCORE);
		Sort sort = new Sort(sortFieldList.toArray(new SortField[sortFieldList.size()]));
		return sort;
	}

	private void renderQueryParameter(List<String> keyWordsList, List<String> filedsList,
			List<BooleanClause.Occur> occurList, Map<String, String> accuratePara, SimpleMemberInfoModel memberInfo)
					throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Object o = null;

		Class clazz = memberInfo.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			o = getMethod.invoke(memberInfo, null);
			if (o != null) {
				// 如果是memberName,需要memberName和product一起模糊查询
				if ("memberName".equals(field.getName())) {
					keyWordsList.add(o + "");
					filedsList.add(field.getName());
					keyWordsList.add(o + "");
					filedsList.add("product");
					occurList.add(BooleanClause.Occur.SHOULD);
					occurList.add(BooleanClause.Occur.SHOULD);
				} else {
					accuratePara.put(field.getName(), o + "");
				}
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
