package com.brunnerit.gofastdfs.api;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brunnerit.gofastdfs.conf.GoFastdfsConfig;
import com.brunnerit.gofastdfs.http.HttpUtils;
import com.brunnerit.gofastdfs.msg.FileInfo;
import com.brunnerit.gofastdfs.msg.FileStats;
import com.brunnerit.gofastdfs.msg.GoFastdfsResult;
import com.brunnerit.gofastdfs.msg.ListDirInfo;
import com.brunnerit.gofastdfs.msg.Status;
import com.brunnerit.gofastdfs.msg.UploadInfo;
import com.brunnerit.gofastdfs.token.TokenUtils;
import com.brunnerit.gofastdfs.util.JsonUtils;
import com.brunnerit.gofastdfs.util.StrUtils;

/**
 * go-fastdfs接口封装
 * @author zhenhua wang
 *
 */
public class GoFastdfsApi {

	private static Logger logger = LoggerFactory.getLogger(GoFastdfsApi.class);

	/** 系统信息api */
	private static final String API_STATUS = "/status";
	/** 统计信息api */
	private static final String API_STAT = "/stat";
	/** 上传文件api */
	private static final String API_UPLOAD = "/upload";
	/** 删除文件api */
	private static final String API_DELETE = "/delete";
	/** 修复统计信息api */
	private static final String API_REPAIR_STAT = "/repair_stat";
	/** 删除空目录api */
	private static final String API_REMOVE_EMPTY_DIR = "/remove_empty_dir";
	/** 备份元数据api */
	private static final String API_BACKUP = "/backup";
	/** 同步失败修复api */
	private static final String API_REPAIR = "/repair";
	/** 文件列表api */
	private static final String API_LIST_DIR = "/list_dir";
	/** 文件信息api */
	private static final String API_GET_FILE_INFO = "/get_file_info";

	private static String tokenKey = "auth_token";
	private static int expires = 600;

	/**
	 * 系统信息
	 * 
	 * @param url
	 * @return
	 */
	public static GoFastdfsResult<Status> status(String url) {
		String res = HttpUtils.get(url + API_STATUS);
		GoFastdfsResult<Status> result = JsonUtils.readGoFastDfsResult(res, Status.class);
		logger.info("status is {}.", result.isSucc());
		return result;
	}

	/**
	 * 统计信息
	 * 
	 * @param url
	 * @return
	 */
	public static GoFastdfsResult<List<FileStats>> stat(String url) {
		String res = HttpUtils.get(url + API_STAT);
		GoFastdfsResult<List<FileStats>> result = JsonUtils.readreadGoFastDfsResultList(res, FileStats.class);
		logger.info("status is {}.", result.isSucc());
		return result;
	}

	/**
	 * 修复统计信息
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static GoFastdfsResult<FileStats> repairStat(String url, String dateStr) {
		Map<String, String> map = new HashMap<>(16);
		map.put("date", dateStr);
		String res = HttpUtils.get(url + API_REPAIR_STAT, map);
		GoFastdfsResult<FileStats> result = JsonUtils.readGoFastDfsResult(res, FileStats.class);
		logger.info("repair_stat is {}.", result.isSucc());
		return result;
	}

	/**
	 * 删除空目录
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static GoFastdfsResult<String> removeEmptyDir(String url) {
		String res = HttpUtils.get(url + API_REMOVE_EMPTY_DIR);
		GoFastdfsResult<String> result = JsonUtils.readGoFastDfsResult(res, String.class);
		logger.info("remove_empty_dir is {}.", result.isSucc());
		return result;
	}

	/**
	 * 备份元数据
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static GoFastdfsResult<String> backup(String url, String dateStr) {
		Map<String, String> map = new HashMap<>(16);
		map.put("date", dateStr);
		String res = HttpUtils.get(url + API_BACKUP, map);
		GoFastdfsResult<String> result = JsonUtils.readGoFastDfsResult(res, String.class);
		logger.info("backup is {}.", result.isSucc());
		return result;
	}

	/**
	 * 同步失败修复
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static GoFastdfsResult<String> repair(String url, boolean force) {
		String tempUrl = null;
		if (force) {
			tempUrl = url + API_REPAIR + "?force=1";
		} else {
			tempUrl = url + API_REPAIR + "?force=0";
		}

		String res = HttpUtils.get(tempUrl);
		GoFastdfsResult<String> result = JsonUtils.readGoFastDfsResult(res, String.class);
		logger.info("repair is {}.", result.isSucc());
		return result;
	}

	/**
	 * 删除文件
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static GoFastdfsResult<String> delete(String url, String md5) {
		Map<String, String> map = new HashMap<>(16);
		if (!StrUtils.isBlank(md5)) {
			map.put("md5", md5);
		}
		if (GoFastdfsConfig.instance().isEnableAuth()) {
			map.put(tokenKey, TokenUtils.urlToken(url, expires));
		}
		String res = HttpUtils.get(url + API_DELETE, map);
		GoFastdfsResult<String> result = JsonUtils.readGoFastDfsResult(res, String.class);
		logger.info("delete:{}. result:{}.", md5, result.isSucc());
		return result;
	}

	public static GoFastdfsResult<List<ListDirInfo>> listdir(String url, String dir) {
		Map<String, String> map = new HashMap<>(16);
		if (!StrUtils.isBlank(dir)) {
			map.put("dir", dir);
		}
		if (GoFastdfsConfig.instance().isEnableAuth()) {
			map.put(tokenKey, TokenUtils.urlToken(url, expires));
		}
		String res = HttpUtils.get(url + API_LIST_DIR, map);
		GoFastdfsResult<List<ListDirInfo>> result = JsonUtils.readreadGoFastDfsResultList(res, ListDirInfo.class);
		logger.info("list_dir:{}. result:{}.", dir, result.isSucc());
		return result;
	}

	public static GoFastdfsResult<FileInfo> fileInfo(String url, String md5) {
		Map<String, String> map = new HashMap<>(16);
		if (!StrUtils.isBlank(md5)) {
			map.put("md5", md5);
		}
		if (GoFastdfsConfig.instance().isEnableAuth()) {
			map.put(tokenKey, TokenUtils.urlToken(url, expires));
		}
		String res = HttpUtils.get(url + API_GET_FILE_INFO, map);
		GoFastdfsResult<FileInfo> result = JsonUtils.readGoFastDfsResult(res, FileInfo.class);
		logger.info("get_file_info:{}. result:{}.", md5, result.isSucc());
		return result;
	}

	public static UploadInfo upload(String url, String scene, String path, File file) {
		Map<String, String> params = new HashMap<>();
		params.put("path", path);
		params.put("output", "json");
		params.put("scene", scene);
		if (GoFastdfsConfig.instance().isEnableAuth()) {
			params.put(tokenKey, TokenUtils.uploadToken(scene, expires));
		}
		String resp = HttpUtils.upload(url + API_UPLOAD, file, params);
		UploadInfo result = JsonUtils.toBean(resp, UploadInfo.class);
		return result;
	}

	public static InputStream download(String url) {
		Map<String, String> params = new HashMap<>();
		if (GoFastdfsConfig.instance().isEnableAuth()) {
			params.put(tokenKey, TokenUtils.urlToken(url, expires));
		}
		return HttpUtils.download(url, "");
	}
}
