package com.brunnerit.gofastdfs.conf;

import java.nio.charset.Charset;

import javax.crypto.spec.SecretKeySpec;

import com.brunnerit.gofastdfs.token.IGenToken;

/**
 * 
 * @author zhenhua wang
 *
 */
public class GoFastdfsConfig {
	private GoFastdfsConfig() {
	}

	private static GoFastdfsConfig instance = new GoFastdfsConfig();

	private Charset charset = Charset.forName("UTF-8");
	private IGenToken genToken;
	private boolean enableAuth = false;
	
	/** 内置的ak/sk算法 */
	private String accessKey;
	private SecretKeySpec secretKey;

	public static GoFastdfsConfig instance() {
		return instance;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public IGenToken getGenToken() {
		return genToken;
	}

	public void setGenToken(IGenToken genToken) {
		this.genToken = genToken;
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public void setEnableAuth(boolean enableAuth) {
		this.enableAuth = enableAuth;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public SecretKeySpec getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKeySpec secretKey) {
		this.secretKey = secretKey;
	}
	
	
}
