package com.secondhand.util;

import java.io.File;
import java.io.IOException;

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
		String noMediaPath = appPath + "/" + ".nomedia";
		File noMedia = new File(noMediaPath);
		try {
			if (!noMedia.exists()) {
				noMedia.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appPath;
	}
}
