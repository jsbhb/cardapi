package com.fireCloud.tradCity.popularize.mapper;

import java.util.List;

import com.fireCloud.tradCity.popularize.model.IndexBannerModel;
import com.fireCloud.tradCity.popularize.model.MemberModel;
import com.fireCloud.tradCity.popularize.model.MemberProductModel;
import com.fireCloud.tradCity.popularize.model.ProductInfoModel;
/**
 * 
 * @author wqy
 * @fun 获取推广服务的数据
 */
public interface PopularizeMapper {
    //获取会员和商品联合推广的model
	List<MemberProductModel> queryMemberProduct();
	//获取首页轮播model
	List<IndexBannerModel> queryIndexBanner();
	//获取商品推广
	List<ProductInfoModel> queryProductInfo();
	//获取推广企业
	List<MemberModel> queryMember();
}