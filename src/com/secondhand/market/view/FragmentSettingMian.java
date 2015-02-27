package com.secondhand.market.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.secondhand.market.R;
import com.secondhand.market.SettingActivity;

public class FragmentSettingMian extends Fragment implements OnClickListener {

	private View mContentView;

	private View mBackView;
	private View mMessageRemind;
	private View mAbout;
	private View mHelp;
	private View mFeedback;
	private View mClear;
	private View mSignOut;

	private SettingActivity mActivity;

	public FragmentSettingMian(SettingActivity activity) {
		mActivity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_settings_main,
					container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();

		return mContentView;
	}

	private void initView() {
		mBackView = mContentView.findViewById(R.id.settings_back);
		mBackView.setOnClickListener(this);
		mMessageRemind = mContentView
				.findViewById(R.id.settings_message_remind);
		mMessageRemind.setOnClickListener(this);
		mAbout = mContentView.findViewById(R.id.settings_about);
		mAbout.setOnClickListener(this);
		mHelp = mContentView.findViewById(R.id.settings_help);
		mHelp.setOnClickListener(this);
		mFeedback = mContentView.findViewById(R.id.settings_feedback);
		mFeedback.setOnClickListener(this);
		mClear = mContentView.findViewById(R.id.settings_clear);
		mClear.setOnClickListener(this);
		mSignOut = mContentView.findViewById(R.id.setting_sign_out);
		mSignOut.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.settings_back:
			mActivity.onBackPressed();
			break;
		case R.id.settings_message_remind:
			mActivity.setFragmentChoice(1);
			break;
		case R.id.settings_about:
			mActivity.setFragmentChoice(2);
			break;
		case R.id.settings_help:
			mActivity.setFragmentChoice(3);
			break;
		case R.id.settings_feedback:
			mActivity.setFragmentChoice(4);
			break;
		case R.id.settings_clear:
			break;
		case R.id.setting_sign_out:
			break;
		default:
			break;
		}
	}
}
