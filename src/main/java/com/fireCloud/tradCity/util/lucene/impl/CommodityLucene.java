package com.fireCloud.tradCity.util.lucene.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
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
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.BytesRef;

import com.fireCloud.tradCity.basic.model.SortModel;
import com.fireCloud.tradCity.basic.model.SortModelList;
import com.fireCloud.tradCity.commodity.model.CommodityModel;
import com.fireCloud.tradCity.constants.Constants;
import com.fireCloud.tradCity.util.DateUtil;
import com.fireCloud.tradCity.util.lucene.AbstractLucene;

/**
 * ClassName: CommodityLucene <br/>
 * Function: 商品lucene工具类. <br/>
 * date: Aug 8, 2017 8:16:15 PM <br/>
 * 
 * @author wqy
 * @version
 * @since JDK 1.7
 */
public class CommodityLucene extends AbstractLucene {

	private volatile static CommodityLucene instance;

	private static String dateFormat = "yyyy-MM-dd hh:mm:ss";
	
	private static String decimalFormat = "000000000";

	public static CommodityLucene getInstance() {
		if (instance == null) {
			synchronized (CommodityLucene.class) {
				if (instance == null) {
					instance = new CommodityLucene();
				}
			}
		}
		return instance;
	}

	@Override
	public <T> void writerIndex(List<T> objList) {
		Document doc;
		long time = 0;
		DecimalFormat df2=(DecimalFormat) DecimalFormat.getInstance();
		df2.applyPattern(decimalFormat);
		for (Object obj : objList) {

			CommodityModel model = (CommodityModel) obj;

			doc = new Document();
			doc.add(new StringField("id", model.getId() + "", Store.YES));

			doc.add(new StringField("memberId", model.getMemberId()+"", Store.NO));

			// 商品名称设置权重
			TextField commodityName = new TextField("commodityName",
					model.getCommodityName() == null ? "" : model.getCommodityName(), Store.YES);
			doc.add(commodityName);

			doc.add(new StringField("commodityCategory1",
					model.getCommodityCategory1() == null ? "" : model.getCommodityCategory1(), Store.NO));

			doc.add(new StringField("commodityCategory2",
					model.getCommodityCategory2() == null ? "" : model.getCommodityCategory2(), Store.NO));

			doc.add(new StringField("commodityCategory3",
					model.getCommodityCategory3() == null ? "" : model.getCommodityCategory3(), Store.NO));

			doc.add(new StringField("brand", model.getBrand() == null ? "" : model.getBrand() + "", Store.NO));
			doc.add(new StringField("uom", model.getUom() == null ? "" : model.getUom(), Store.NO));
			doc.add(new StringField("color", model.getColor() == null ? "" : model.getColor(), Store.NO));
			doc.add(new StringField("size", model.getSize() == null ? "" : model.getSize() + "", Store.NO));
			doc.add(new StringField("hotFlg", model.getHotFlg() == null ? "" : model.getHotFlg() + "", Store.NO));
			doc.add(new StringField("price", model.getPrice() == null ? "0" : df2.format((model.getPrice() * 100))+"" , Store.NO));
			try {
				time = DateUtil.stringToLong(model.getCreateTime(), dateFormat);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			doc.add(new LongField("createTime", time, Store.NO));
			doc.add(new StringField("isRel", model.getIsRel() == null ? "" : model.getIsRel() + "", Store.NO));
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
			if ("commodityName".equals(entry.getKey())) {
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
		CommodityModel info = null;
		Map<String, Object> result = new HashMap<String, Object>(16);
		List<String> commodityIdList = new ArrayList<String>();

		Map<String, CommodityModel> highlighterModel = new HashMap<String, CommodityModel>();
		for (ScoreDoc hit : hits) {
			doc1 = indexSearch.doc(hit.doc);
			String res = doc1.get("id");
			if (res != null) {
				info = new CommodityModel();
				info.setCommodityName(
						highlighter.getBestFragment(analyzer, "commodityName", doc1.get("commodityName")));
				highlighterModel.put(res, info);
				commodityIdList.add(res);
			}
		}
		result.put(Constants.TOTAL, results.totalHits);
		result.put(Constants.ID_LIST, commodityIdList);
		result.put(Constants.HIGHLIGHTER_MODEL, highlighterModel);

		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void renderParameter(List<String> keyWordsList, List<String> filedsList, Map<String, String> accuratePara,
			Object obj) {

		Object o = null;

		CommodityModel commodityInfo = (CommodityModel) obj;
		Class clazz = commodityInfo.getClass();
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
				o = getMethod.invoke(commodityInfo);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (o != null) {

				if ("commodityName".equals(field.getName()) || "memberId".equals(field.getName())) {
					keyWordsList.add(o + "");
					filedsList.add(field.getName());
				} else {
					if (!"hotDown".equals(field.getName()) && !"hotUp".equals(field.getName())
							&& !"priceUp".equals(field.getName()) && !"priceDown".equals(field.getName())
							&& !"createTimeUp".equals(field.getName()) && !"createTimeDown".equals(field.getName()))
						
						accuratePara.put(field.getName(), o + "");
				}
			}
		}

	}

	@Override
	public Sort renderSortParameter(SortModelList sortList) {
		List<SortField> sortFieldList = new ArrayList<SortField>();
		SortField s3 = new SortField("isRel", Type.STRING, true);
		sortFieldList.add(s3);
		if (sortList != null && sortList.getSortList() != null && sortList.getSortList().size() > 0) {
			List<SortModel> list = sortList.getSortList();

			SortField sortField = null;
			for (SortModel model : list) {
				if ("createTime".equals(model.getSortField()) || "price".equals(model.getSortField())) {
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

			if ("memberId".equals(filedsList.get(i))) {

				Term tm = new Term(filedsList.get(i), keyWordsList.get(i));

				TermQuery parser = new TermQuery(tm);
				query1.add(parser, BooleanClause.Occur.MUST);
				query.add(query1, BooleanClause.Occur.MUST);
				continue;
			}

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

			query.add(query1, BooleanClause.Occur.MUST);
		}
		return query;
	}

	private CommodityLucene() {
		init();
	}

	private void init() {
		filePath = CommodityLucene.class.getClassLoader().getResource("").getPath() + "product";
		getIndexWriter();
	}

	@Override
	public BooleanFilter accurateQuery(Map<String, String> accuratePara, BooleanQuery query) {
		if(accuratePara != null && accuratePara.size() > 0){
			BooleanFilter booleanFilter = new BooleanFilter();
			if(accuratePara.get("priceMin") != null && accuratePara.get("priceMax") != null){
				DecimalFormat df2=(DecimalFormat) DecimalFormat.getInstance();
				df2.applyPattern(decimalFormat);
				String priceMin = df2.format((Double.valueOf(accuratePara.get("priceMin"))*100))+"";
				String priceMax = df2.format((Double.valueOf(accuratePara.get("priceMax"))*100))+"";
				Filter filter=new TermRangeFilter("price", new BytesRef(priceMin), new BytesRef(priceMax), true, true);
				booleanFilter.add(filter, Occur.MUST);
			}
			for(Map.Entry<String, String> entry : accuratePara.entrySet()){
				if(!"priceMin".equals(entry.getKey()) && !"priceMax".equals(entry.getKey())){
					Term term=new Term(entry.getKey(),entry.getValue());//添加term 
					QueryWrapperFilter filter=new QueryWrapperFilter(new TermQuery(term));//添加过滤器 
					booleanFilter.add(filter,Occur.MUST);
				}
			}
			return booleanFilter;
		}
		return null;
	}

	
}
