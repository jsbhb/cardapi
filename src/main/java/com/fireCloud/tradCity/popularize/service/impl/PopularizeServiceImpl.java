package com.fireCloud.tradCity.popularize.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.constants.LoggerConstants;
import com.fireCloud.tradCity.log.SysLogger;
import com.fireCloud.tradCity.popularize.mapper.PopularizeMapper;
import com.fireCloud.tradCity.popularize.model.IndexBannerModel;
import com.fireCloud.tradCity.popularize.model.MemberModel;
import com.fireCloud.tradCity.popularize.model.MemberProductModel;
import com.fireCloud.tradCity.popularize.model.ProductInfoModel;
import com.fireCloud.tradCity.popularize.service.PopularizeService;

/**
 * @author wqy
 * @fun PopularizeService接口实现类
 * @date 2017年6月5日
 */
@Service
public class PopularizeServiceImpl implements PopularizeService {

	private final String POPULARIZE_NAME = "popularizeName";
	private final String MODEL_LIST = "modelList";

	@Resource
	PopularizeMapper popularizeMapper;

	@Resource
	SysLogger sysLogger;

	@Override
	public Map<String, Map<String, Object>> getIndexPopularize() {
		Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>(16);
		// 企业商品联合推广
		List<MemberProductModel> memberProductList = popularizeMapper.queryMemberProduct();
		sysLogger.info(LoggerConstants.MEMBER_PRODUCT_POPULARIZE_SIZE,
				memberProductList == null ? "=====0" : "=====" + memberProductList.size());
		if (memberProductList != null && memberProductList.size() > 0) {
			// 封装会员商品联合推广数据(区分相同的推广形式出现多个模块)
			packageModel(resultMap, memberProductList, MemberProductModel.class);
		}

		// 首页轮播模块
		List<IndexBannerModel> indexBannerList = popularizeMapper.queryIndexBanner();
		sysLogger.info(LoggerConstants.INDXE_BANNER_SIZE,
				indexBannerList == null ? "=====0" : "=====" + indexBannerList.size());
		if (indexBannerList != null && indexBannerList.size() > 0) {
			// 封装首页轮播数据
			packageModel(resultMap, indexBannerList, IndexBannerModel.class);
		}

		// 商品推广
		List<ProductInfoModel> productInfoList = popularizeMapper.queryProductInfo();
		sysLogger.info(LoggerConstants.PRODUCT_POPULARIZE_SIZE,
				productInfoList == null ? "=====0" : "=====" + productInfoList.size());
		if (productInfoList != null && productInfoList.size() > 0) {
			// 封装商品推广数据
			packageModel(resultMap, productInfoList, ProductInfoModel.class);
		}

		// 推广企业
		List<MemberModel> memberList = popularizeMapper.queryMember();
		sysLogger.info(LoggerConstants.MEMBER_POPULARIZE_SIZE,
				memberList == null ? "=====0" : "=====" + memberList.size());
		if (memberList != null && memberList.size() > 0) {
			// 封装会员推广数据
			packageModel(resultMap, memberList, MemberModel.class);
		}
		return resultMap;
	}

	private <T> void packageModel(Map<String, Map<String, Object>> resultMap, List<T> objList, Class clazz) {
		try {
			Method method = null;
			for (Object model : objList) {
				method = clazz.getDeclaredMethod("getPopularizeCode", null);
				String popularizeCode = (String) method.invoke(model, null);
				if (resultMap.get(popularizeCode) == null) {
					Map<String, Object> tempMap = new HashMap<String, Object>();
					method = clazz.getDeclaredMethod("getName", null);
					tempMap.put(POPULARIZE_NAME, method.invoke(model, null));
					List<Object> tempList = new ArrayList<Object>();
					tempList.add(model);
					tempMap.put(MODEL_LIST, tempList);
					resultMap.put(popularizeCode, tempMap);
				} else {
					Map<String, Object> tempMap = resultMap.get(popularizeCode);
					if (tempMap.get(MODEL_LIST) == null) {
						List<Object> tempList = new ArrayList<Object>();
						tempList.add(model);
						tempMap.put(MODEL_LIST, tempList);
					} else {
						List<Object> tempList = (List<Object>) tempMap.get(MODEL_LIST);
						tempList.add(model);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
