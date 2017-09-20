package com.corgiking.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;
import com.corgiking.utils.ConfigUtil;
import com.corgiking.utils.FileUtil;

@Service
public class FileService {

	private static final String FILE_SERVER = ConfigUtil.getProperty("file.server");

	public List<String> saveFile(String file_type, HttpServletRequest request) {
		try {
			FileUtil fileUtil = new FileUtil();

			List<File> fileList = fileUtil.saveFile(file_type, request);

			if (CollectionUtils.isEmpty(fileList)) {
				throw new CustomException(ErrorCode.SYSTEM_ERROR, "没有上传文件!");
			}
			
			//生成返回的文件名
			List<String> list = genReturnFileName(fileUtil, fileList);
			//保存数据库
			saveDataBase(list);
			return list;
			// String server = buildFileServer(file_type);

		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new CustomException(ErrorCode.SYSTEM_ERROR, "");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException(ErrorCode.SYSTEM_ERROR, "");
		}
	}

	private void saveDataBase(List<String> list) {
		
	}

	private List<String> genReturnFileName(FileUtil fileUtil, List<File> fileList) {
		List<String> list = new ArrayList<String>();
		for(File file:fileList){
			String name = file.getAbsolutePath();
			int i = name.indexOf(fileUtil.FILE_SEPARATOR);
			String str = name.substring(i);
			list.add(FILE_SERVER + str);
		}
		return list;
	}
	
}
