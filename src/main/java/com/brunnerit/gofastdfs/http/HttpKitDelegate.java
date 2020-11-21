package com.brunnerit.gofastdfs.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.brunnerit.gofastdfs.conf.GoFastdfsConfig;
import com.brunnerit.gofastdfs.util.IoUtils;
import com.brunnerit.gofastdfs.util.StrUtils;

/**
 * 
 * @author zhenhua wang
 *
 */
public class HttpKitDelegate implements HttpDelegate {
	private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";

	/**
	 * https 域名校验
	 */
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private static final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new HttpKitDelegate.TrustAnyHostnameVerifier();

	private static final String GET = "GET";
	private static final String POST = "POST";

	private static int connectTimeout = 19000; // 连接超时，单位毫秒
	private static int readTimeout = 19000; // 读取超时，单位毫秒

	private static final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();

	private static SSLSocketFactory initSSLSocketFactory() {
		try {
			TrustManager[] tm = { new HttpUtils.TrustAnyTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("TLS"); // ("TLS", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			return sslContext.getSocketFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static HttpURLConnection getHttpConnection(String url, String method, Map<String, String> headers)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		URL _url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		if (conn instanceof HttpsURLConnection) {
			((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
			((HttpsURLConnection) conn).setHostnameVerifier(trustAnyHostnameVerifier);
		}

		conn.setRequestMethod(method);
		conn.setDoOutput(true);
		conn.setDoInput(true);

		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);

		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);

		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		return conn;
	}

	/**
	 * Send GET request
	 */
	public String get(String url, Map<String, String> queryParas, Map<String, String> headers) {
		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), GET, headers);
			conn.connect();
			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public String get(String url, Map<String, String> queryParas) {
		return get(url, queryParas, null);
	}

	public String get(String url) {
		return get(url, null, null);
	}

	/**
	 * Send POST request
	 */
	public String post(String url, Map<String, String> queryParas, String data, Map<String, String> headers) {
		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), POST, headers);
			conn.connect();

			if (data != null) {
				OutputStream out = conn.getOutputStream();
				out.write(data.getBytes(GoFastdfsConfig.instance().getCharset()));
				out.flush();
				out.close();
			}

			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public String post(String url, Map<String, String> queryParas, String data) {
		return post(url, queryParas, data, null);
	}

	public String post(String url, String data, Map<String, String> headers) {
		return post(url, null, data, headers);
	}

	public String post(String url, String data) {
		return post(url, null, data, null);
	}

	private String readResponseString(HttpURLConnection conn) {
		BufferedReader reader = null;
		try {
			StringBuilder ret;
			reader = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), GoFastdfsConfig.instance().getCharset()));
			String line = reader.readLine();
			if (line != null) {
				ret = new StringBuilder();
				ret.append(line);
			} else {
				return "";
			}

			while ((line = reader.readLine()) != null) {
				ret.append('\n').append(line);
			}
			return ret.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Build queryString of the url
	 */
	private String buildUrlWithQueryString(String url, Map<String, String> queryParas) {
		if (queryParas == null || queryParas.isEmpty()) {
			return url;
		}

		StringBuilder sb = new StringBuilder(url);
		boolean isFirst;
		if (url.indexOf('?') == -1) {
			isFirst = true;
			sb.append('?');
		} else {
			isFirst = false;
		}

		for (Entry<String, String> entry : queryParas.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append('&');
			}

			String key = entry.getKey();
			String value = entry.getValue();
			if (StrUtils.notBlank(value)) {
				try {
					value = URLEncoder.encode(value, GoFastdfsConfig.instance().getCharset().name());
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
			sb.append(key).append('=').append(value);
		}
		return sb.toString();
	}

	@Override
	public InputStream download(String url, String params) {
		try {
			URL _url = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			// 连接超时
			conn.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢，增大时间
			conn.setReadTimeout(25000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "Keep-Alive");
			conn.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			if (StrUtils.notBlank(params)) {
				OutputStream out = conn.getOutputStream();
				out.write(params.getBytes(GoFastdfsConfig.instance().getCharset()));
				out.flush();
				IoUtils.close(out);
			}
			return conn.getInputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String upload(String url, File file, Map<String, String> params) {
		try {
			URL urlGet = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", DEFAULT_USER_AGENT);
			conn.setRequestProperty("Charsert", "UTF-8");
			// 定义数据分隔线
			String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL";
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			
			// 写文件
			StringBuilder mediaData = new StringBuilder();
			mediaData.append("--").append(BOUNDARY).append("\r\n");
			mediaData.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
			mediaData.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] mediaDatas = mediaData.toString().getBytes();
			out.write(mediaDatas);
			
			DataInputStream fs = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = fs.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			IoUtils.close(fs);
			// 多个文件时，二个文件之间加入这个
			out.write("\r\n".getBytes());
			
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String,String> entry : params.entrySet()) {
					StringBuilder paramData = new StringBuilder();
					paramData.append("--").append(BOUNDARY).append("\r\n");
					paramData.append("Content-Disposition: form-data;name=\"" +  entry.getKey()+ "\"\r\n");
					paramData.append("\r\n");
					paramData.append(entry.getValue());
					paramData.append("\r\n");
					byte[] paramDatas = paramData.toString().getBytes();
					out.write(paramDatas);
				}
			}
			byte[] end_data = ("--" + BOUNDARY + "--\r\n\r\n").getBytes();
			out.write(end_data);
			out.flush();
			IoUtils.close(out);

			// 定义BufferedReader输入流来读取URL的响应
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(
					new InputStreamReader(in, GoFastdfsConfig.instance().getCharset()));
			String valueString = null;
			StringBuffer bufferRes = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			IoUtils.close(in);
			// 关闭连接
			if (conn != null) {
				conn.disconnect();
			}
			return bufferRes.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
