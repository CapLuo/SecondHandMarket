package com.secondhand.market.view;

import com.secondhand.market.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class FragmentFeedback extends Fragment implements OnClickListener {

	private View mContentView;

	private View mFeedbackBack;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_feedback,
					container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();

		return mContentView;
	}

	private void initView() {
		mFeedbackBack = mContentView.findViewById(R.id.feedback_back);
		mFeedbackBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.feedback_back:
			getActivity().onBackPressed();
			break;
		default:
			break;
		}
	}
}
