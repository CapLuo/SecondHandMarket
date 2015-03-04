package com.secondhand.util;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ControllerFromNet {

	public static void loginCreate(Context context, String user, String password)
			throws JSONException, UnsupportedEncodingException {
		JSONObject object = new JSONObject();
		object.put("Email", user);
		object.put("PassWord", password);
		HttpUtil.post(context, FieldUtil.Link_API + "/Login/LoginCreate",
				object, "application/json", new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login success."
								+ (arg2 == null ? "" : new String(
										arg2)));
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login failure."
								+ (new String(arg2) == null ? "" : new String(
										arg2)));
					}
				});
	}

	public static void test() {
		RequestParams params = new RequestParams();
		// params.put("id", 2);
		HttpUtil.get(FieldUtil.Link_API + "/Test/Get",
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login success.");
						test("");
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login failure.");
					}
				});
	}

	public static void test(String two) {
		RequestParams params = new RequestParams();
		params.put("id", 2);
		HttpUtil.get(FieldUtil.Link_API + "/Test/Get", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login success.");
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login failure.");
					}
				});
	}

	public static void appLoginOut() {
		RequestParams params = new RequestParams();
		params.put("a", "a");
		HttpUtil.get(FieldUtil.Link_API + "/Test/Logout", params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login success.");
						test("");
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.i(FieldUtil.TAG, arg0
								+ "---------> Http request login failure.");
					}
				});
	}
}
