package com.brunnerit.gofastdfs;

import java.io.File;

import com.brunnerit.gofastdfs.api.GoFastdfsApi;
import com.brunnerit.gofastdfs.msg.UploadInfo;
import com.brunnerit.gofastdfs.util.JsonUtils;

/**
 * 
 * @author zhenhua wang
 *
 */
public class TestGoFastdfs {
	private static String url = "http://127.0.0.1:8080/group1";

	public static void main(String[] args) {
		//GoFastdfsResult<List<FileStats>> result = GoFastdfsApi.stat(url);
		//GoFastdfsResult<FileStats> result = GoFastdfsApi.repairStat(url, "20201121");
		//GoFastdfsResult<String> result = GoFastdfsApi.repair(url, true);
		//GoFastdfsResult<List<ListDirInfo>> result = GoFastdfsApi.listdir(url, "");
		//GoFastdfsResult<FileInfo> result = GoFastdfsApi.fileInfo(url, "a4f80e8f25c4e5069b6739bfeba7f888");
		UploadInfo result = GoFastdfsApi.upload(url, "","", new File("d:/go-fastdfs/test.txt"));
		
		GoFastdfsApi.removeEmptyDir(url);
		GoFastdfsApi.backup(url, "20201121");
		GoFastdfsApi.repair(url, true);
		GoFastdfsApi.delete(url, "a4f80e8f25c4e5069b6739bfeba7f888");
		
		GoFastdfsApi.download(url);
		System.out.println(JsonUtils.toStr(result));
	}
}
