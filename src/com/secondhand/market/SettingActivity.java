package com.secondhand.market;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.secondhand.market.view.FragmentSettingMessageRemind;
import com.secondhand.market.view.FragmentSettingMian;

public class SettingActivity extends FragmentActivity {

	private FragmentManager mFragmentManager;
	private FragmentTransaction mTransaction;

	private FragmentSettingMian mSettingMain;
	private FragmentSettingMessageRemind mMessageRemind;

	private int mCurrentFragment;

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

	public void setFragmentChoice(int flag) {
		mCurrentFragment = flag;
		mTransaction = mFragmentManager.beginTransaction();

		switch (flag) {
		case 1:
			if (mMessageRemind == null) {
				mMessageRemind = new FragmentSettingMessageRemind();
			}
			mTransaction.replace(R.id.content_main, mMessageRemind);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 0:
		default:
			if (mSettingMain == null) {
				mSettingMain = new FragmentSettingMian(this);
			}
			mTransaction.replace(R.id.content_main, mSettingMain);
			break;
		}
		mTransaction.commit();
	}

	@Override
	public void onBackPressed() {
		if (mCurrentFragment == 0) {
			super.onBackPressed();
		} else {
			setFragmentChoice(0);
		}
	}

}
