package com.secondhand.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.secondhand.market.R;
import com.secondhand.market.view.ImageBrowsingLayout;

//发布页面
public class FragmentReleaseStepOne extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mStepNext;
	private ImageView mImgInsert;
	private ImageBrowsingLayout mBrowsingLayout;

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
		initData();
		return mContentView;
	}

	private void initView() {
		mStepNext = mContentView.findViewById(R.id.release_step_next);
		mStepNext.setOnClickListener(this);

		mImgInsert = (ImageView) mContentView.findViewById(R.id.release_pic);

		mBrowsingLayout = (ImageBrowsingLayout) mContentView
				.findViewById(R.id.release_browsing);
	}

	private void initData() {
		mBrowsingLayout
				.setShowImgInterface(new ImageBrowsingLayout.ShowInsertImg() {
					@Override
					public void showImg(String uri) {
						if (TextUtils.isEmpty(uri)) {

						} else {
							// to do mImgInsert show img
						}
					}
				});
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.release_step_next:

			break;

		default:
			break;
		}
	}
}
