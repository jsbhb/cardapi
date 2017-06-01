package com.fireCloud.tradCity.popularize.service;

import java.util.Map;

/**
 * 
 * @author wqy
 * @fun 获取推广服务的service（包括首页banner轮播，企业推广，商品推广，企业+商品联合推广）
 * @time 2017/05/31
 */
public interface PopularizeService {

	Map<String,Map<String,Object>> getIndexPopularize();
}
