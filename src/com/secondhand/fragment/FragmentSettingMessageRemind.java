package com.secondhand.fragment;

import com.secondhand.market.R;
import com.secondhand.util.FieldUtil;
import com.secondhand.util.ShareperefenceUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

public class FragmentSettingMessageRemind extends Fragment implements
		OnClickListener, OnCheckedChangeListener {

	private View mContentView;

	private View mBack;

	private ToggleButton mGoodsBussiness;
	private ToggleButton mMessage;
	private ToggleButton mHotGoods;
	private ToggleButton mCustomLable;

	private EditText mCustomLableEdit;
	private View mSave;
	private View mCustomLayout;
	private View mCustomEditLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater
					.inflate(R.layout.fragment_settings_message_remind,
							container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();

		return mContentView;
	}

	private void initView() {
		mBack = mContentView.findViewById(R.id.remind_back);
		mBack.setOnClickListener(this);

		mGoodsBussiness = (ToggleButton) mContentView
				.findViewById(R.id.remind_goods_bussiness_button);
		mGoodsBussiness.setOnCheckedChangeListener(this);
		mMessage = (ToggleButton) mContentView
				.findViewById(R.id.remind_message_button);
		mMessage.setOnCheckedChangeListener(this);
		mHotGoods = (ToggleButton) mContentView
				.findViewById(R.id.remind_hot_goods_button);
		mHotGoods.setOnCheckedChangeListener(this);
		mCustomLable = (ToggleButton) mContentView
				.findViewById(R.id.remind_custom_lable_button);
		mCustomLable.setOnCheckedChangeListener(this);

		mCustomLayout = mContentView.findViewById(R.id.remind_custom_lable);
		mCustomLayout.setOnClickListener(this);

		mCustomEditLayout = mContentView
				.findViewById(R.id.remind_custom_lable_layout);
		mCustomLableEdit = (EditText) mContentView
				.findViewById(R.id.remind_custom_lable_edit);
		mSave = mContentView.findViewById(R.id.remind_custom_lable_ok);
		mSave.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.remind_back:
			getActivity().onBackPressed();
			break;
		case R.id.remind_custom_lable:
			if (mCustomEditLayout != null
					&& mCustomEditLayout.getVisibility() != View.VISIBLE) {
				mCustomLableEdit.setText("");
				mCustomEditLayout.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.remind_custom_lable_ok:
			mCustomEditLayout.setVisibility(View.INVISIBLE);
			String keyWord = mCustomLableEdit.getText().toString();
			if (!TextUtils.isEmpty(keyWord)) {
				ShareperefenceUtils.writeSharedPreferences(getActivity(),
						FieldUtil.CUSTOM_LABLE_WORD, keyWord);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton button, boolean isCheck) {
		switch (button.getId()) {
		case R.id.remind_goods_bussiness_button:
			ShareperefenceUtils.writeSharedPreferences(getActivity(),
					FieldUtil.GOODS_BUSINESS, isCheck + "");
			break;
		case R.id.remind_message_button:
			ShareperefenceUtils.writeSharedPreferences(getActivity(),
					FieldUtil.MESSAGE_REMIND, isCheck + "");
			break;
		case R.id.remind_hot_goods_button:
			ShareperefenceUtils.writeSharedPreferences(getActivity(),
					FieldUtil.HOT_GOODS, isCheck + "");
			break;
		case R.id.remind_custom_lable_button:
			ShareperefenceUtils.writeSharedPreferences(getActivity(),
					FieldUtil.CUSTOM_LABLE, isCheck + "");
			break;
		default:
			break;
		}
	}
}
