package com.secondhand.fragment;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.json.JSONException;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.secondhand.market.FleastreetApplication;
import com.secondhand.market.R;
import com.secondhand.util.ControllerFromNet;
import com.secondhand.util.FieldUtil;

import android.os.Bundle;
import android.util.Log;
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

	private FleastreetApplication mApp;

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

		mUser = (EditText) mContentView
				.findViewById(R.id.register_user_edittext);
		mPassword = (EditText) mContentView
				.findViewById(R.id.register_password_edittext);
	}

	private void initData() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mApp = (FleastreetApplication) getActivity().getApplication();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.register_to_login:
			setChoic(0);
			break;
		case R.id.register_back:
			getActivity().onBackPressed();
			break;
		case R.id.register_button:
			try {
				ControllerFromNet.loginCreate(getActivity(), mUser.getText()
						.toString(), mPassword.getText().toString(),
						new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int status, Header[] heades,
									byte[] resultCode) {
								Log.i(FieldUtil.TAG,
										status
												+ "---------> Http request login success."
												+ (resultCode == null ? ""
														: new String(resultCode)));
								mApp.setLogin(true);
								getActivity().onBackPressed();
							}

							@Override
							public void onFailure(int status, Header[] heads,
									byte[] resultCode, Throwable throwable) {
								Log.i(FieldUtil.TAG,
										status
												+ "---------> Http request login failure."
												+ (resultCode == null ? ""
														: new String(resultCode)));
								mApp.setLogin(false);
							}

						});
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
