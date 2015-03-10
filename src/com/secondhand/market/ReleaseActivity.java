package com.secondhand.market;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.secondhand.fragment.FragmentInterfaceChoice.ChoiceFragmentInterface;
import com.secondhand.fragment.FragmentReleaseStepOne;

public class ReleaseActivity extends FragmentActivity implements
		OnClickListener {

	private int mCurrentFragment;

	private FragmentManager mManager;
	private FragmentTransaction mTransactioin;

	private FragmentReleaseStepOne mReleaseOne;

	private View mBack;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.activity_release);

		initView();
		initData();
	}

	private void initView() {
		mManager = getSupportFragmentManager();

		mBack = findViewById(R.id.release_back);
		mBack.setOnClickListener(this);
	}

	private void initData() {
		setChoiceFragment(0);
	}

	private void setChoiceFragment(int flag) {

		mCurrentFragment = flag;

		mTransactioin = mManager.beginTransaction();

		switch (flag) {
		case 1:
			break;
		case 0:
			if (mReleaseOne == null) {
				mReleaseOne = new FragmentReleaseStepOne(
						new ChoiceFragmentInterface() {
							@Override
							public void setChoice(int flag) {
								setChoiceFragment(1);
							}
						});
			}
			mTransactioin.replace(R.id.content_main, mReleaseOne);
			break;
		default:
			break;
		}
		mTransactioin.commit();
	}

	@Override
	public void onBackPressed() {
		if (mCurrentFragment == 0) {
			super.onBackPressed();
		} else {
			setChoiceFragment(mCurrentFragment - 1);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.release_back:
			onBackPressed();
			break;
		default:
			break;
		}
	}
}
