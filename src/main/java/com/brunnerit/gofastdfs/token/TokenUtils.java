package com.brunnerit.gofastdfs.token;

import com.brunnerit.gofastdfs.conf.GoFastdfsConfig;

/**
 * 
 * @author zhenhua wang
 *
 */
public class TokenUtils {

	private static Auth auth = null;

	public static String uploadToken(String scene, long expires) {
		if (GoFastdfsConfig.instance().getGenToken() == null) {
			getAuth();
			return auth.uploadToken(scene, expires);
		} else {
			return GoFastdfsConfig.instance().getGenToken().uploadToken(scene, expires);
		}
	}

	public static String urlToken(String url, long deadline) {
		if (GoFastdfsConfig.instance().getGenToken() == null) {
			getAuth();
			return auth.urlToken(url, deadline);
		} else {
			return GoFastdfsConfig.instance().getGenToken().urlToken(url, deadline);
		}
	}

	private synchronized static Auth getAuth() {
		if (auth == null) {
			auth = Auth.use(GoFastdfsConfig.instance().getAccessKey(), GoFastdfsConfig.instance().getAccessKey());
		}
		return auth;
	}
}
