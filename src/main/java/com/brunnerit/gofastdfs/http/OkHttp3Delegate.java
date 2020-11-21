package com.brunnerit.gofastdfs.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.brunnerit.gofastdfs.util.StrUtils;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 
 * @author zhenhua wang
 *
 */
public class OkHttp3Delegate implements HttpDelegate {

	private okhttp3.OkHttpClient httpClient;

	public OkHttp3Delegate() {
		// 分别设置Http的连接,写入,读取的超时时间
		httpClient = new okhttp3.OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
	}

	private static final okhttp3.MediaType CONTENT_TYPE_FORM = okhttp3.MediaType
			.parse("application/x-www-form-urlencoded");

	private String exec(okhttp3.Request request) {
		try (okhttp3.Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);

			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String get(String url) {
		okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
		return exec(request);
	}

	@Override
	public String get(String url, Map<String, String> queryParas) {
		okhttp3.HttpUrl.Builder urlBuilder = okhttp3.HttpUrl.parse(url).newBuilder();
		for (Entry<String, String> entry : queryParas.entrySet()) {
			urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
		}
		okhttp3.HttpUrl httpUrl = urlBuilder.build();
		okhttp3.Request request = new okhttp3.Request.Builder().url(httpUrl).get().build();
		return exec(request);
	}

	@Override
	public String post(String url, String params) {
		okhttp3.RequestBody body = okhttp3.RequestBody.create(CONTENT_TYPE_FORM, params);
		okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(body).build();
		return exec(request);
	}

	@Override
	public InputStream download(String url, String params) {
		okhttp3.Request request;
		if (StrUtils.notBlank(params)) {
			okhttp3.RequestBody body = okhttp3.RequestBody.create(CONTENT_TYPE_FORM, params);
			request = new okhttp3.Request.Builder().url(url).post(body).build();
		} else {
			request = new okhttp3.Request.Builder().url(url).get().build();
		}

		try (okhttp3.Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);

			return response.body().byteStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String upload(String url, File file, Map<String, String> params) {
		Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file",
				file.getName(), RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"), file));

		if (params != null && !params.isEmpty()) {
			params.forEach((key, value) -> {
				builder.addFormDataPart(key, value);
			});
		}

		MultipartBody multipartBody = builder.build();

		Request request = new Request.Builder().url(url).post(multipartBody).build();
		return exec(request);
	}


}
