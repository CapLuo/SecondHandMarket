package com.secondhand.market;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import com.secondhand.fragment.FragmentInterfaceChoice.ChoiceFragmentInterface;
import com.secondhand.fragment.FragmentReleaseStepOne;
import com.secondhand.fragment.FragmentReleaseStepThree;
import com.secondhand.fragment.FragmentReleaseStepTwo;

public class ReleaseActivity extends FragmentActivity implements
		OnClickListener {

	private int mCurrentFragment;

	private FragmentManager mManager;
	private FragmentTransaction mTransactioin;

	private FragmentReleaseStepOne mReleaseOne;
	private FragmentReleaseStepTwo mReleaseTwo;
	private FragmentReleaseStepThree mReleaseThree;

	private View mBack;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.activity_release);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
		hideAllFragment(mTransactioin);

		switch (flag) {
		case 2:
			if (mReleaseThree == null) {
				mReleaseThree = new FragmentReleaseStepThree(
						new ChoiceFragmentInterface() {

							@Override
							public void setChoice(int flag) {

							}
						});
				mTransactioin.add(R.id.content_main, mReleaseThree);
			} else {
				mTransactioin.show(mReleaseThree);
			}
			break;
		case 1:
			if (mReleaseTwo == null) {
				mReleaseTwo = new FragmentReleaseStepTwo(
						new ChoiceFragmentInterface() {

							@Override
							public void setChoice(int flag) {
								setChoiceFragment(2);
							}
						});
				mTransactioin.add(R.id.content_main, mReleaseTwo);
			} else {
				mTransactioin.show(mReleaseTwo);
			}
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
				mTransactioin.add(R.id.content_main, mReleaseOne);
			} else {
				mTransactioin.show(mReleaseOne);
			}
			break;
		default:
			break;
		}
		mTransactioin.commit();
	}

	private void hideAllFragment(FragmentTransaction transaction) {
		if (mReleaseOne != null) {
			transaction.hide(mReleaseOne);
		}
		if (mReleaseTwo != null) {
			transaction.hide(mReleaseTwo);
		}
		if (mReleaseThree != null) {
			transaction.hide(mReleaseThree);
		}
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
