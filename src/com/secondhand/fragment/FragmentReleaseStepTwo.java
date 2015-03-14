package com.secondhand.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.secondhand.market.R;

public class FragmentReleaseStepTwo extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mDropDown;
	private View mNextStep;
	private EditText mTitle;
	private EditText mContent;
	private TextView mGoodDescription;

	private PopupWindow mPop;

	public FragmentReleaseStepTwo(
			FragmentInterfaceChoice.ChoiceFragmentInterface listener) {
		this.setChoiceFragmentInterface(listener);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_release_step_two,
					container, false);
		}
		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		return mContentView;
	}

	private void initView() {
		mDropDown = mContentView.findViewById(R.id.release_property);
		mDropDown.setOnClickListener(this);

		mGoodDescription = (TextView) mContentView
				.findViewById(R.id.release_text_property);

		mTitle = (EditText) mContentView.findViewById(R.id.release_goods_title);
		mContent = (EditText) mContentView
				.findViewById(R.id.release_goods_description);

		mNextStep = mContentView.findViewById(R.id.release_step_next);
		mNextStep.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.release_property:
			showPopuWindow(view);
			break;
		case R.id.release_description_new:
			mPop.dismiss();
			mGoodDescription.setText(R.string.release_description_new);
			break;
		case R.id.release_description_second:
			mPop.dismiss();
			mGoodDescription.setText(R.string.release_description_second);
			break;
		case R.id.release_description_change:
			mPop.dismiss();
			mGoodDescription.setText(R.string.release_description_change);
			break;
		case R.id.release_description_shopping:
			mPop.dismiss();
			mGoodDescription.setText(R.string.release_description_shopping);
			break;
		case R.id.release_step_next:
			setChoic(2);
			break;
		default:
			break;
		}
	}

	private void showPopuWindow(View parent) {
		if (mPop == null) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View popuView = inflater.inflate(
					R.layout.release_description_popuwindow, null);
			mPop = new PopupWindow(popuView,
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			mPop.setBackgroundDrawable(new BitmapDrawable());
			mPop.setFocusable(true);
			mPop.setOutsideTouchable(true);
			popuView.findViewById(R.id.release_description_new)
					.setOnClickListener(this);
			popuView.findViewById(R.id.release_description_second)
					.setOnClickListener(this);
			popuView.findViewById(R.id.release_description_change)
					.setOnClickListener(this);
			popuView.findViewById(R.id.release_description_shopping)
					.setOnClickListener(this);
		}
		mPop.showAsDropDown(parent);
	}
}
