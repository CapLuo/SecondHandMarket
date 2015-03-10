package com.secondhand.util;

import java.io.File;

import android.os.Environment;

public class ToolsUtil {

	public static String getAppFilePath() {
		String sdcardPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String appPath = sdcardPath + "/" + FieldUtil.AppFile;
		File file = new File(appPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return appPath;
	}
}
