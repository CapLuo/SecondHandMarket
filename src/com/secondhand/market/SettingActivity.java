package com.secondhand.market;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.secondhand.market.view.FragmentSettingMian;

public class SettingActivity extends FragmentActivity {

	private FragmentManager mFragmentManager;
	private FragmentTransaction mTransaction;

	private FragmentSettingMian mSettingMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_settings);

		initView();
	}

	private void initView() {
		mFragmentManager = getSupportFragmentManager();

		setFragmentChoice(0);
	}

	private void setFragmentChoice(int flag) {
		mTransaction = mFragmentManager.beginTransaction();

		switch (flag) {
		case 0:
		default:
			if (mSettingMain == null) {
				mSettingMain = new FragmentSettingMian();
			}
			mTransaction.replace(R.id.content_main, mSettingMain);
			break;
		}
		mTransaction.commit();
	}
}
