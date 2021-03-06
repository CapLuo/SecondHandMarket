package com.secondhand.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import com.secondhand.fragment.FragmentCenter;
import com.secondhand.fragment.FragmentClassify;
import com.secondhand.fragment.FragmentHome;
import com.secondhand.fragment.FragmentMessage;

public class HomeActivity extends FragmentActivity implements OnClickListener {

	private FragmentManager mFragmentManager;
	private FragmentTransaction mTransaction;

	private FragmentHome mHome;
	private FragmentClassify mClassify;
	private FragmentCenter mCenter;
	private FragmentMessage mMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		initView();
	}

	private void initView() {
		mFragmentManager = getSupportFragmentManager();

		View bottomBar = findViewById(R.id.layout_bottom);
		bottomBar.findViewById(R.id._layout_home).setOnClickListener(this);
		bottomBar.findViewById(R.id._layout_classify).setOnClickListener(this);
		bottomBar.findViewById(R.id._layout_release).setOnClickListener(this);
		bottomBar.findViewById(R.id._layout_center).setOnClickListener(this);
		bottomBar.findViewById(R.id._layout_message).setOnClickListener(this);

		setFragmentContent(0);
	}

	private void setFragmentContent(int index) {
		mTransaction = mFragmentManager.beginTransaction();
		if (index != 2) {			
			hideAllFragment(mTransaction);
		}
		switch (index) {
		case 1:
			if (mClassify == null) {
				mClassify = new FragmentClassify();
				mTransaction.add(R.id.content_main, mClassify);
			} else {
				mTransaction.show(mClassify);
			}
			break;
		case 2:
			Intent intent = new Intent();
			intent.setClass(this, ReleaseActivity.class);
			this.startActivity(intent);
			break;
		case 3:
			if (mCenter == null) {
				mCenter = new FragmentCenter();
				mTransaction.add(R.id.content_main, mCenter);
			} else {
				mTransaction.show(mCenter);
			}
			break;
		case 4:
			if (mMessage == null) {
				mMessage = new FragmentMessage();
				mTransaction.add(R.id.content_main, mMessage);
			} else {
				mTransaction.show(mMessage);
			}
			break;
		case 0:
			if (mHome == null) {
				mHome = new FragmentHome();
				mTransaction.add(R.id.content_main, mHome);
			} else {
				mTransaction.show(mHome);
			}
			break;
		default:
			break;
		}
		mTransaction.commit();
	}

	private void hideAllFragment(FragmentTransaction ft) {
		if (mHome != null) {
			ft.hide(mHome);
		}
		if (mClassify != null) {
			ft.hide(mClassify);
		}
		if (mMessage != null) {
			ft.hide(mMessage);
		}
		if (mCenter != null) {
			ft.hide(mCenter);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id._layout_home:
			setFragmentContent(0);
			break;
		case R.id._layout_classify:
			setFragmentContent(1);
			break;
		case R.id._layout_release:
			setFragmentContent(2);
			break;
		case R.id._layout_center:
			setFragmentContent(3);
			break;
		case R.id._layout_message:
			setFragmentContent(4);
			break;
		default:
			break;
		}
	}

}
