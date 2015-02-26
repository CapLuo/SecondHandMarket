package com.secondhand.market.view;

import com.secondhand.market.R;
import com.secondhand.market.SettingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentCenter extends Fragment implements OnClickListener {

	private View mContentView;

	private ImageView mSettings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_center, container,
					false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		return mContentView;
	}

	private void initView() {
		mSettings = (ImageView) mContentView.findViewById(R.id.center_settings);
		mSettings.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.center_settings:
			Intent intent = new Intent();
			intent.setClass(getActivity(), SettingActivity.class);
			getActivity().startActivity(intent);
			break;

		default:
			break;
		}
	}
}
