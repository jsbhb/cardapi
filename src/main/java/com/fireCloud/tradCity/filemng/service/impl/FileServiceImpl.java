package com.fireCloud.tradCity.filemng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fireCloud.tradCity.filemng.mapper.FileMapper;
import com.fireCloud.tradCity.filemng.model.FileModel;
import com.fireCloud.tradCity.filemng.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Resource
	FileMapper fileMapper;
	
	@Override
	public List<FileModel> queryFileById(List<String> FileIdList) {
		
		return fileMapper.queryFileById(FileIdList);
	}

}
