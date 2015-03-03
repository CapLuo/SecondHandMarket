package com.secondhand.market.view;

import com.secondhand.market.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentRegister extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mReturn;
	private View mRegisterToLogin;
	private View mRegister;

	private EditText mUser;
	private EditText mPassword;

	public FragmentRegister(ChoiceFragmentInterface inface) {
		setChoiceFragmentInterface(inface);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_register,
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
		mReturn = mContentView.findViewById(R.id.register_back);
		mReturn.setOnClickListener(this);

		mRegisterToLogin = mContentView.findViewById(R.id.register_to_login);
		mRegisterToLogin.setOnClickListener(this);

		mRegister = mContentView.findViewById(R.id.register_button);
		mRegister.setOnClickListener(this);

		mUser = (EditText) mContentView.findViewById(R.id.register_user_edittext);
		mPassword = (EditText) mContentView.findViewById(R.id.register_password_edittext);
	}

	private void initData() {

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.register_to_login:
			mChoice.setChoice(0);
			break;
		case R.id.register_back:
			getActivity().onBackPressed();
			break;
		case R.id.register_button:
			break;
		default:
			break;
		}
	}

}
