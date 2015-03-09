package com.secondhand.fragment;

import com.secondhand.market.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class FragmentHelp extends Fragment implements OnClickListener {

	private View mContentView;

	private View mHelpBack;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_help, container,
					false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();

		return mContentView;
	}

	private void initView() {
		mHelpBack = mContentView.findViewById(R.id.help_back);
		mHelpBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.help_back:
			getActivity().onBackPressed();
			break;
		default:
			break;
		}
	}

}
