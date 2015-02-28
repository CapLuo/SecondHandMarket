package com.secondhand.market.view;

import com.secondhand.market.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//发布页面
public class FragmentReleaseStepOne extends FragmentRelease {

	private View mContentView;

	public FragmentReleaseStepOne(ChoiceFragmentInterface mInterface) {
		setChoiceFragmentInterface(mInterface);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_release_step_one,
					container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		return mContentView;
	}

	private void initView() {
	}
}
