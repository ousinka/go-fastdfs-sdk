package com.brunnerit.gofastdfs.token;

/**
 * 	验证Token是否有效，服务器端实现，不需要SDK实现
 * @author zhenhua wang
 *
 */
public interface IValidToken {

	public String validToken(String auth_token);
}
