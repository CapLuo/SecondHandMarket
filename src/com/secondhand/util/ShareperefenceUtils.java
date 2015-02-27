package com.secondhand.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ShareperefenceUtils {

	public static void writeSharedPreferences(Context mContext, String key,
			String value) {
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
				FieldUtil.SHARE_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getSharePreferences(Context mContext, String key,
			String default_value) {
		String value;
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
				FieldUtil.SHARE_NAME, Context.MODE_PRIVATE);
		value = sharedPreferences.getString(key, default_value);
		return value;
	}
}