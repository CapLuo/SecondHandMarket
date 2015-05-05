package com.secondhand.market;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.secondhand.data.PersionInfo;

import android.app.Application;

public class FleastreetApplication extends Application {

	private boolean isLogin;
	private PersionInfo mInfo;

	@Override
	public void onCreate() {
		super.onCreate();

		isLogin = false;
		mInfo = new PersionInfo();

		// 创建默认的ImageLoader配置参数
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				this).threadPoolSize(5).memoryCache(new WeakMemoryCache())
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);
	}

	public boolean getLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLoginSucess) {
		isLogin = isLoginSucess;
	}

	public PersionInfo getInfo() {
		return mInfo;
	}

	public void setInfo(PersionInfo mInfo) {
		this.mInfo = mInfo;
	}
}
