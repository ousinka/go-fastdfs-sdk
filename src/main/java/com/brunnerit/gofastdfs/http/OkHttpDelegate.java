package com.brunnerit.gofastdfs.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.brunnerit.gofastdfs.util.StrUtils;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * 
 * @author zhenhua wang
 *
 */
public class OkHttpDelegate implements HttpDelegate{

	private final OkHttpClient httpClient;

	Lock lock = new ReentrantLock();

	public OkHttpDelegate() {
		httpClient = new OkHttpClient();
		// 分别设置Http的连接,写入,读取的超时时间
		httpClient.setConnectTimeout(10, TimeUnit.SECONDS);
		httpClient.setWriteTimeout(10, TimeUnit.SECONDS);
		httpClient.setReadTimeout(30, TimeUnit.SECONDS);
	}

	private static final MediaType CONTENT_TYPE_FORM = MediaType
			.parse("application/x-www-form-urlencoded");

	private String exec(Request request) {
		try {
			Response response = httpClient.newCall(request).execute();

			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);

			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String get(String url) {
		Request request = new Request.Builder().url(url).get().build();
		return exec(request);
	}

	@Override
	public String get(String url, Map<String, String> queryParas) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		for (Entry<String, String> entry : queryParas.entrySet()) {
			urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
		}
		HttpUrl httpUrl = urlBuilder.build();
		Request request = new Request.Builder().url(httpUrl).get().build();
		return exec(request);
	}

	@Override
	public String post(String url, String params) {
		RequestBody body = RequestBody.create(CONTENT_TYPE_FORM, params);
		Request request = new Request.Builder().url(url).post(body).build();
		return exec(request);
	}

	@Override
	public InputStream download(String url, String params) {
		Request request;
		if (StrUtils.notBlank(params)) {
			RequestBody body = RequestBody.create(CONTENT_TYPE_FORM, params);
			request = new Request.Builder().url(url).post(body).build();
		} else {
			request = new Request.Builder().url(url).get().build();
		}
		try {
			Response response = httpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new RuntimeException("Unexpected code " + response);
			}
				
			return response.body().byteStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String upload(String url, File file, Map<String, String> params) {
		RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
		MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM)
				.addFormDataPart("file", file.getName(), fileBody);
		if (params != null && !params.isEmpty()) {
			params.forEach((key, value) -> {
				builder.addFormDataPart(key, value);
			});
		}
		RequestBody requestBody = builder.build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		return exec(request);
	}


}
