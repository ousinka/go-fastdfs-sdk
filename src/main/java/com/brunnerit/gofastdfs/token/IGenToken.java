package com.brunnerit.gofastdfs.token;

/**
 * 	用户自定义Token生成接口
 * @author zhenhua wang
 *
 */
public interface IGenToken {
	
	public String uploadToken(String scene, long expires);
	
	public String urlToken(String url, long deadline);
}
