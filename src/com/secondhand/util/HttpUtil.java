package com.secondhand.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public class HttpUtil {

	private static AsyncHttpClient client = new AsyncHttpClient();
	static {
		client.setTimeout(10000);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}

	// 用一个完整url获取一个string对象
	public static void get(String urlString, AsyncHttpResponseHandler res) {
		client.get(urlString, res);
	}

	// url里面带参数
	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.get(urlString, params, res);
	}

	// 不带参数，获取json对象或者数组
	public static void get(String urlString, JsonHttpResponseHandler res) {
		client.get(urlString, res);
	}

	/* 带参数，获取json对象或者数组
	 * params.put("name", name);
	 */
	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) {
		client.get(urlString, params, res);
	}

	// 下载数据使用，会返回byte数据
	public static void get(String uString, BinaryHttpResponseHandler bHandler) {
		client.get(uString, bHandler);
	}

	/* 上传文件
	 * params.put("fileName", file);
	 */
	public static void post(String uString, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(uString, params, responseHandler);
	}

	public static void post(Context context, String url, JSONObject object, String contentType, ResponseHandlerInterface responseHandler) throws UnsupportedEncodingException {
		StringEntity entity = new StringEntity(object.toString());
		entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		client.post(context, url, entity, contentType, responseHandler);
	}

	private static HttpEntity paramsToEntity(RequestParams params, ResponseHandlerInterface responseHandler) {
        HttpEntity entity = null;

        try {
            if (params != null) {
                entity = params.getEntity(responseHandler);
            }
        } catch (IOException e) {
            if (responseHandler != null) {
                responseHandler.sendFailureMessage(0, null, null, e);
            } else {
                e.printStackTrace();
            }
        }

        return entity;
    }
}
