package com.brunnerit.gofastdfs.http;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 
 * @author zhenhua wang
 *
 */
public interface HttpDelegate {

	String get(String url);

	String get(String url, Map<String, String> queryParas);

	String post(String url, String data);

	InputStream download(String url, String params);

	String upload(String url, File file, Map<String, String> params);

}
