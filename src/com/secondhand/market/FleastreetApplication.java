package com.secondhand.market;

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
