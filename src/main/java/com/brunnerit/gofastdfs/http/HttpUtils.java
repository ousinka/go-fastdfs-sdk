package com.brunnerit.gofastdfs.http;

import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.brunnerit.gofastdfs.util.ClassUtils;

/**
 * Http请求工具类，整理借鉴于 JFinal-weixin
 * 
 * @author L.cm
 */
public final class HttpUtils {

	private HttpUtils() {
	}

	public static String get(String url) {
		return delegate.get(url);
	}

	public static String get(String url, Map<String, String> queryParas) {
		return delegate.get(url, queryParas);
	}

	public static String post(String url, String data) {
		return delegate.post(url, data);
	}

	public static InputStream download(String url, String params) {
		return delegate.download(url, params);
	}

	public static String upload(String url, File file, Map<String, String> params) {
		return delegate.upload(url, file, params);
	}

	// http请求工具代理对象
	private static HttpDelegate delegate;

	public static void setHttpDelegate(HttpDelegate httpDelegate) {
		delegate = httpDelegate;
	}

	static {
		HttpDelegate delegateToUse = null;
		// okhttp3.OkHttpClient?
		if (ClassUtils.isPresent("okhttp3.OkHttpClient1", HttpUtils.class.getClassLoader())) {
			delegateToUse = new OkHttp3Delegate();
		}
		// com.squareup.okhttp.OkHttpClient?
		else if (ClassUtils.isPresent("com.squareup.okhttp.OkHttpClient", HttpUtils.class.getClassLoader())) {
			delegateToUse = new OkHttpDelegate();
		}
		// com.jfinal.kit.HttpKit
		else if (ClassUtils.isPresent("com.brunnerit.gofastdfs.http.HttpKitDelegate", HttpUtils.class.getClassLoader())) {
			delegateToUse = new HttpKitDelegate();
		}
		delegate = delegateToUse;
	}

	public static X509TrustManager getTrustManager() {
		try {
			TrustManagerFactory trustManagerFactory = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init((KeyStore) null);
			TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
			if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
				// throw new IllegalStateException("Unexpected default trust managers:" +
				// java.util.Arrays.toString(trustManagers));
				System.err.println("Unexpected default trust managers:" + java.util.Arrays.toString(trustManagers));
				return trustAnyTrustManager;
			}
			return (X509TrustManager) trustManagers[0];
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return trustAnyTrustManager;
		}
	}

	static final TrustAnyTrustManager trustAnyTrustManager = new TrustAnyTrustManager();

	static class TrustAnyTrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}
	
	/**
	 * Build queryString of the url
	 */
	public static String buildMapTodata(Map<String, String> queryParas) {
		if (queryParas.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (Entry<String, String> entry : queryParas.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append('&');
			}

			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key).append('=').append(value);
		}
		return sb.toString();
	}
}
