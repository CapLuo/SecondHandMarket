package com.secondhand.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secondhand.market.R;
import com.secondhand.util.FieldUtil;

public class FragmentAbout extends Fragment implements OnClickListener {

	private View mContentView;

	private View mBack;

	private View mAboutLayout;
	private View mContactLayout;

	private TextView mVersion;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_about, container,
					false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();

		return mContentView;
	}

	private void initView() {

		mBack = mContentView.findViewById(R.id.about_back);
		mBack.setOnClickListener(this);

		mAboutLayout = mContentView
				.findViewById(R.id.about_check_update_layout);
		mAboutLayout.setOnClickListener(this);
		mContactLayout = mContentView
				.findViewById(R.id.about_contact_us_layout);
		mContactLayout.setOnClickListener(this);

		mVersion = (TextView) mContentView
				.findViewById(R.id.about_check_update_version);

		String versionName = getAppInfo();
		if (!TextUtils.isEmpty(versionName)) {
			mVersion.setText(versionName);
		} else {
			mVersion.setText("1.0.0");
		}
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.about_back:
			getActivity().onBackPressed();
			break;
		case R.id.about_check_update_layout:
			needUpdate();
			break;
		case R.id.about_contact_us_layout:
			break;
		default:
			break;
		}
	}

	private String getAppInfo() {
		try {
			String pkName = getActivity().getPackageName();
			String versionName = getActivity().getPackageManager()
					.getPackageInfo(pkName, 0).versionName;
			return versionName;
		} catch (Exception e) {
			Log.e(FieldUtil.TAG, e.getMessage());
		}
		return null;
	}

	private void needUpdate() {

	}
}
