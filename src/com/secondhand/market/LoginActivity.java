package com.secondhand.market;

import com.secondhand.fragment.FragmentLogin;
import com.secondhand.fragment.FragmentRegister;
import com.secondhand.fragment.FragmentInterfaceChoice.ChoiceFragmentInterface;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class LoginActivity extends FragmentActivity implements ChoiceFragmentInterface {

	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;

	private FragmentLogin mLogin;
	private FragmentRegister mRegister;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.activity_settings);

		initData();
	}

	private void initData() {
		mFragmentManager = getSupportFragmentManager();

		setFragmentChioce(0);
	}

	private void setFragmentChioce(int flag) {
		mFragmentTransaction = mFragmentManager.beginTransaction();

		switch (flag) {
		case 1:
			if (mRegister == null) {
				mRegister = new FragmentRegister(this);
			}
			mFragmentTransaction.replace(R.id.content_main, mRegister);
			break;
		case 0:
			if (mLogin == null) {
				mLogin = new FragmentLogin(this);
			}
			mFragmentTransaction.replace(R.id.content_main, mLogin);
			break;
		default:
			break;
		}

		mFragmentTransaction.commit();
	}

	@Override
	public void setChoice(int flag) {
		setFragmentChioce(flag);
	}
}
