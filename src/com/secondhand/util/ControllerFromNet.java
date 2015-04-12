package com.secondhand.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public class ControllerFromNet {

	public static void loginCreate(Context context, String user,
			String password, ResponseHandlerInterface responseHandler)
			throws JSONException, UnsupportedEncodingException {
		JSONObject object = new JSONObject();
		object.put("Email", user);
		object.put("PassWord", password);
		HttpUtil.post(context, FieldUtil.Link_API + "/Login/LoginCreate",
				object, "application/json", responseHandler);
	}

	public static void loginSign(Context context, String user, String password,
			ResponseHandlerInterface responseHandler) throws JSONException,
			UnsupportedEncodingException {
		JSONObject object = new JSONObject();
		object.put("Email", user);
		object.put("PassWord", password);
		HttpUtil.post(context, FieldUtil.Link_API + "/Login/LoginCreate",
				object, "application/json", responseHandler);
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

	public static void UpdatePics(Context context, List<String> list,
			String schoolid, String userid,
			ResponseHandlerInterface responseHandler) throws JSONException,
			UnsupportedEncodingException {
		try {
			for (String filePath : list) {
				File file = new File(filePath);
				UpdatePic(context, file, schoolid, userid, responseHandler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void UpdatePic(Context context, File pic, String schoolid,
			String userid, ResponseHandlerInterface responseHandler)
			throws JSONException, UnsupportedEncodingException {
		try {
			JSONObject object = new JSONObject();
			FileInputStream fileInput = new FileInputStream(pic);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fileInput.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			String file = new String(Base64.encode(bos.toByteArray(), 0));
			object.put("File", file);
			object.put("SchoolId", schoolid);
			object.put("UserId", userid);
			object.put("Extensions", "jpg"); // 文件扩展名

			fileInput.close();
			bos.close();

			HttpUtil.post(context, FieldUtil.Link_API + "/GoodsPicture/Save",
					object, "application/json", responseHandler);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void dealCreate(Context context, JSONObject object,
			ResponseHandlerInterface responseHandler) {
		try {
			Log.e("@@@@", object.toString());
			HttpUtil.post(context, FieldUtil.Link_API + "/DealInfo/Create",
					object, "application/json", responseHandler);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	// deal type 货物的分类信息
	public static void getDealType(JsonHttpResponseHandler responseHandler) {
		HttpUtil.get(FieldUtil.Link_API + "/ConstData/GetDealTypeConstData",
				responseHandler);
	}

	public static void getAllDealInfo(JsonHttpResponseHandler responseHandler) {
		HttpUtil.get(FieldUtil.Link_API + "/DealInfo/GetAll", responseHandler);
	}
}
