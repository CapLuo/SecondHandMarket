package com.secondhand.fragment;

import com.secondhand.market.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentReleaseStepThree extends FragmentInterfaceChoice {

	private View mContentView;

	public FragmentReleaseStepThree(ChoiceFragmentInterface fragmentInterface) {

		setChoiceFragmentInterface(fragmentInterface);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(
					R.layout.fragment_release_step_three, container, false);
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
