package com.secondhand.market.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.secondhand.market.R;

public class FragmentLogin extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mReturn;
	private View mToRegister;
	private View mLogin;

	private EditText mUser;
	private EditText mPassword;

	public FragmentLogin(ChoiceFragmentInterface inface) {
		setChoiceFragmentInterface(inface);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_login, container,
					false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		initData();

		return mContentView;
	}

	private void initView() {
		mReturn = mContentView.findViewById(R.id.login_back);
		mReturn.setOnClickListener(this);

		mToRegister = mContentView.findViewById(R.id.login_register);
		mToRegister.setOnClickListener(this);

		mLogin = mContentView.findViewById(R.id.login_button);
		mLogin.setOnClickListener(this);

		mUser = (EditText) mContentView.findViewById(R.id.login_user_edittext);
		mPassword = (EditText) mContentView.findViewById(R.id.login_password_edittext);
	}

	private void initData() {

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.login_register:
			mChoice.setChoice(1);
			break;
		case R.id.login_back:
			getActivity().onBackPressed();
			break;
		case R.id.login_button:
			break;
		default:
			break;
		}
	}

}
