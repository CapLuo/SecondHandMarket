package com.secondhand.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.secondhand.market.view.FragmentCenter;
import com.secondhand.market.view.FragmentClassify;
import com.secondhand.market.view.FragmentHome;
import com.secondhand.market.view.FragmentMessage;

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
		switch (index) {
		case 1:
			if (mClassify == null) {
				mClassify = new FragmentClassify();
			}
			mTransaction.replace(R.id.content_main, mClassify);
			break;
		case 2:
			Intent intent = new Intent();
			intent.setClass(this, ReleaseActivity.class);
			this.startActivity(intent);
			break;
		case 3:
			if (mCenter == null) {
				mCenter = new FragmentCenter();
			}
			mTransaction.replace(R.id.content_main, mCenter);
			break;
		case 4:
			if (mMessage == null) {
				mMessage = new FragmentMessage();
			}
			mTransaction.replace(R.id.content_main, mMessage);
			break;
		case 0:
			if (mHome == null) {
				mHome = new FragmentHome();
			}
			mTransaction.replace(R.id.content_main, mHome);
			break;
		default:
			break;
		}
		mTransaction.commit();
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
