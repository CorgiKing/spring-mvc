package com.corgiking.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;

public class FileUtil {
	private Logger log = Logger.getLogger(FileUtil.class);

	public final String FILE_SEPARATOR = File.separator;

	private String basePath = ConfigUtil.getProperty("file.base_path");// 上传文件保存地址
	private static List<String> allowedSuffixs = getAllowedSuffixs();

	public List<File> saveFile(String file_type, HttpServletRequest request) throws FileUploadException, IOException {
		ServletFileUpload servletFileUpload = new ServletFileUpload();
		FileItemIterator iterator = servletFileUpload.getItemIterator(request);
		List<File> list = new ArrayList<File>();
		try {
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();

				if (item.isFormField()) {// 如果不是文件继续下一个
					continue;
				}

				// 校验上传文件是否符合要求
				String itemFileName = checkValidity(item);

				String targetFileName = genUploadFileName(basePath, genDateFolder(), file_type, itemFileName);

				File file = writeToFile(item.openStream(), targetFileName);

				// 判断上传的文件是否保存完毕,并且是否可读
				checkUploaded(targetFileName);

				list.add(file);
			}
		} catch (Exception e) {
			//清除已上传的文件
			for(File f:list){
				if (f.exists()) {
					f.delete();
				}
			}
			throw e;
		}

		return list;
	}

	private void checkUploaded(String targetFileName) {
		File file = new File(targetFileName);
		if (file.exists() && !file.canRead()) {
			if (!file.delete()) {
				log.warn("Failed to delete image file: " + file.getName());
			}
			log.error("upload file can not read.");
			throw new CustomException(ErrorCode.BAD_REQUEST, "上传文件不可读");
		}
	}

	private String checkValidity(FileItemStream item) {
		String itemFileName = item.getName();
		if (StringUtils.isBlank(itemFileName)) {
			log.error("没有上传文件名");
			throw new CustomException(ErrorCode.BAD_REQUEST, "没有上传文件名");
		}

		// 后缀
		if (itemFileName.lastIndexOf(".") != -1) {
			String suffix = itemFileName.substring(itemFileName.lastIndexOf(".") + 1);
			if (!allowedSuffixs.contains(suffix.trim())) {
				log.error("上传文件类型错误");
				throw new CustomException(ErrorCode.BAD_REQUEST, "上传文件类型错误");
			}
		} else {
			log.error("上传错误文件");
			throw new CustomException(ErrorCode.BAD_REQUEST, "上传错误文件");
		}
		return itemFileName;
	}

	private static List<String> getAllowedSuffixs() {
		String str = ConfigUtil.getProperty("file.allowed");
		String[] suffixs = str.split(";");
		List<String> list = Arrays.asList(suffixs);
		return list;
	}

	private File writeToFile(InputStream inputStream, String targetFileName) throws IOException {
		File targetFile = new File(targetFileName);
		FileUtils.copyInputStreamToFile(inputStream, targetFile);
		return targetFile;
	}

	/**
	 * 生成文件名
	 * 
	 * @return
	 */
	private String genUploadFileName(String basePath, String dateFolder, String file_type, String fileName) {

		StringBuffer buffer = new StringBuffer();
		// 获取存储根目录
		buffer.append(getPath()).append(FILE_SEPARATOR);
		// 基础路径
		buffer.append(basePath).append(FILE_SEPARATOR);

		// 日期
		buffer.append(dateFolder).append(FILE_SEPARATOR);
		generateUploadFolder(buffer.toString());

		// 文件类型路径
		buffer.append(file_type).append(FILE_SEPARATOR);
		generateUploadFolder(buffer.toString());

		buffer.append(UUID.randomUUID().toString());
		buffer.append(ConfigUtil.getProperty("file.flag"));
		if (fileName.lastIndexOf(".") != -1) {
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			buffer.append(suffix);
		}
		return buffer.toString();
	}

	private Object getPath() {
		String path = "d:";
		return path;
	}

	private String getTomcatPath() {
		String path = System.getProperty("java.class.path");
		String[] paths = path.split(";");
		path = paths[0];
		log.info(path);
		int i = path.indexOf("bin");
		log.info(i);
		path = path.substring(0, i);
		return path;
	}

	private void generateUploadFolder(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists() || !file.isDirectory()) {
			if (!file.mkdirs()) {
				log.error("Failed to create upload directory: " + folderPath);
				throw new RuntimeException();
			}
		}
	}

	public static String genDateFolder() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

}
