package com.fireCloud.tradCity.filemng.mapper;

import java.util.List;

import com.fireCloud.tradCity.filemng.model.FileModel;

/**
 * @author wqy
 * @fun 获取文件数据
 * @date 2017年6月5日
 */
public interface FileMapper {
	
	List<FileModel> queryFileById(List<String> list);
}