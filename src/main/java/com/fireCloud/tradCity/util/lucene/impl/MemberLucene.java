package com.fireCloud.tradCity.util.lucene.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.member.model.submodel.SimpleMemberInfoModel;
import com.fireCloud.tradCity.util.DateUtil;
import com.fireCloud.tradCity.util.lucene.AbstractLucene;

/**
 * ClassName: MemberLucene <br/>
 * Function: 会员lucene工具类. <br/>
 * date: Aug 8, 2017 8:16:38 PM <br/>
 * 
 * @author wqy
 * @version
 * @since JDK 1.7
 */
public class MemberLucene extends AbstractLucene {

	private static String dateFormat = "yyyy-MM-dd hh:mm:ss";

	private volatile static MemberLucene instance;

	public static MemberLucene getInstance() {
		if (instance == null) {
			synchronized (MemberLucene.class) {
				if (instance == null) {
					instance = new MemberLucene();
				}
			}
		}
		return instance;
	}

	@Override
	public <T> void writerIndex(List<T> objList) {
		Document doc;
		long time = 0;
		for (Object obj : objList) {

			SimpleMemberInfoModel model = (SimpleMemberInfoModel) obj;

			doc = new Document();
			doc.add(new StringField("id", model.getMemberId() + "", Store.YES));

			// 企业名称设置权重
			TextField memberName = new TextField("memberName", model.getMemberName(), Store.YES);
			doc.add(memberName);
			memberName.setBoost(1.5f);

			// 商品名称设置权重
			TextField product = new TextField("product", model.getProduct() == null ? "" : model.getProduct(),
					Store.YES);
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
				indexWriter.addDocument(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			indexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateIndex(Map<String, String> param) {
		Document doc = new Document();

		for (Map.Entry<String, String> entry : param.entrySet()) {
			if ("memberName".equals(entry.getKey()) || "product".equals(entry.getKey())) {
				doc.add(new TextField(entry.getKey(), entry.getValue(), Store.YES));
			} else {
				doc.add(new StringField(entry.getKey(), entry.getValue(), Store.NO));
			}
		}
		try {
			indexWriter.updateDocument(new Term("id", param.get("id")), doc);
			indexWriter.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Map<String, Object> packageData(Highlighter highlighter, TopDocs results, ScoreDoc[] hits)
			throws IOException, InvalidTokenOffsetsException {

		Document doc1;
		SimpleMemberInfoModel info = null;
		Map<String, Object> result = new HashMap<String, Object>(16);
		List<Integer> memberIdList = new ArrayList<Integer>();

		Map<Integer, SimpleMemberInfoModel> highlighterModel = new HashMap<Integer, SimpleMemberInfoModel>();
		for (ScoreDoc hit : hits) {
			doc1 = indexSearch.doc(hit.doc);
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

		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void renderParameter(List<String> keyWordsList, List<String> filedsList, Map<String, String> accuratePara,
			Object obj) {
		Object o = null;

		SimpleMemberInfoModel memberInfo = (SimpleMemberInfoModel) obj;
		Class clazz = memberInfo.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			PropertyDescriptor pd = null;
			try {
				pd = new PropertyDescriptor(field.getName(), clazz);
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Method getMethod = pd.getReadMethod();// 获得get方法
			try {
				o = getMethod.invoke(memberInfo);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (o != null) {
				// 如果是memberName,需要memberName和product一起模糊查询
				if ("memberName".equals(field.getName())) {
					keyWordsList.add(o + "");
					filedsList.add(field.getName());
					keyWordsList.add(o + "");
					filedsList.add("product");
				} else {
					accuratePara.put(field.getName(), o + "");
				}
			}
		}

	}

	@Override
	public Sort renderSortParameter(SortModelList sortList) {
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

	@Override
	public BooleanQuery packageQuery(List<String> keyWordsList, List<String> filedsList) throws IOException {
		BooleanQuery query = new BooleanQuery();
		TokenStream stream = null;
		for (int i = 0; i < keyWordsList.size(); i++) {
			BooleanQuery query1 = new BooleanQuery();

			stream = analyzer.tokenStream(filedsList.get(i), new StringReader(keyWordsList.get(i)));

			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			stream.reset();
			while (stream.incrementToken()) {
				Term tm = new Term(filedsList.get(i), cta.toString());

				TermQuery parser = new TermQuery(tm);
				query1.add(parser, BooleanClause.Occur.MUST);
			}
			stream.end();
			stream.close();

			query.add(query1, BooleanClause.Occur.SHOULD);
		}
		return query;
	}

	private MemberLucene() {
		init();
	}

	private void init() {
		filePath = MemberLucene.class.getClassLoader().getResource("").getPath() + "member";
		getIndexWriter();
	}

	@Override
	public BooleanFilter accurateQuery(Map<String, String> accuratePara, BooleanQuery query) {
		if (accuratePara != null && accuratePara.size() > 0) {
			BooleanFilter booleanFilter = new BooleanFilter();
			for (Map.Entry<String, String> entry : accuratePara.entrySet()) {
				Term term = new Term(entry.getKey(), entry.getValue());// 添加term
				QueryWrapperFilter filter = new QueryWrapperFilter(new TermQuery(term));// 添加过滤器
				booleanFilter.add(filter, Occur.MUST);
			}
			return booleanFilter;
		}
		return null;
	}

}
