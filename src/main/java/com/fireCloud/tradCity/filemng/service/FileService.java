package com.fireCloud.tradCity.filemng.service;

import java.util.List;

import com.fireCloud.tradCity.filemng.model.FileModel;

/**
 * @author wqy
 * @fun 文件类服务service
 * 		1、根据fileId获取FileModel
 * @date 2017年6月7日
 */
public interface FileService {

	/**
	 * @fun 根据fileID获取filemodel
	 * @param FileIdList
	 * @return
	 */
	List<FileModel> queryFileById(List<String> FileIdList);
}
