package com.brunnerit.gofastdfs.token;

import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.brunnerit.gofastdfs.conf.GoFastdfsConfig;
import com.brunnerit.gofastdfs.util.JsonUtils;
import com.brunnerit.gofastdfs.util.StrUtils;

/**
 * token:ak+sign+data(scene,path,expires)
 * 
 * @author zhenhua wang
 *
 */
public class Auth implements IGenToken{
	private final String accessKey;
	private final SecretKeySpec secretKey;

	private Auth(String accessKey, SecretKeySpec secretKeySpec) {
		this.accessKey = accessKey;
		this.secretKey = secretKeySpec;
	}

	public static Auth use(String accessKey, String secretKey) {
		if (StrUtils.notBlank(accessKey) || StrUtils.notBlank(secretKey)) {
			throw new IllegalArgumentException("accessKey or secretKey is null");
		}
		byte[] sk = secretKey.getBytes(GoFastdfsConfig.instance().getCharset());
		SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
		return new Auth(accessKey, secretKeySpec);
	}

	private Mac createMac() {
		Mac mac;
		try {
			mac = javax.crypto.Mac.getInstance("HmacSHA1");
			mac.init(secretKey);
		} catch (GeneralSecurityException e) {
			throw new IllegalArgumentException(e);
		}
		return mac;
	}

	private String sign(byte[] data) {
		Mac mac = createMac();
		String encodedSign = Base64.getUrlEncoder().encodeToString(mac.doFinal(data));
		return this.accessKey + ":" + encodedSign;
	}

	public String signWithData(byte[] data) {
		String s = Base64.getUrlEncoder().encodeToString(data);
		return sign(s.getBytes(GoFastdfsConfig.instance().getCharset())) + ":" + s;
	}

	public String signWithData(String data) {
		return signWithData(data.getBytes(GoFastdfsConfig.instance().getCharset()));
	}

	/**
	 * @param scene
	 * @param expires
	 * @return
	 */
	@Override
	public String uploadToken(String scene, long expires) {
		long deadline = System.currentTimeMillis() / 1000 + expires;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scene", scene);
		map.put("deadline", deadline);
		String signStr = JsonUtils.toStr(map);

		return "U" + signWithData(signStr);
	}
	
	public String uploadToken(String scene) {
		return uploadToken(scene, 600);
	}
	
	@Override
	public String urlToken(String baseUrl, long deadline) {
		StringBuilder b = new StringBuilder();
		b.append(baseUrl);
		int pos = baseUrl.indexOf("?");
		if (pos > 0) {
			b.append("&e=");
		} else {
			b.append("?e=");
		}
		b.append(deadline);
		String token = signWithData(b.toString().getBytes(GoFastdfsConfig.instance().getCharset()));
		b.append("&auth_token=L");
		b.append(token);
		return b.toString();
	}
}
