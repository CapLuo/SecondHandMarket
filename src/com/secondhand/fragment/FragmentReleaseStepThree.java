package com.secondhand.fragment;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.secondhand.data.GoodReleaseInfo;
import com.secondhand.market.R;
import com.secondhand.market.ReleaseActivity;
import com.secondhand.util.ControllerFromNet;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentReleaseStepThree extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mReleaseGoods;
	private EditText mOldPrice; // 原价
	private EditText mNewPrice; // 现价
	private EditText mTrading; // 交易地点
	private EditText mPhone; // 联系方式

	private TextView mErrorOld;
	private TextView mErrorNow;
	private TextView mErrorTrading;
	private TextView mErrorContact;

	public FragmentReleaseStepThree(ChoiceFragmentInterface fragmentInterface) {

		setChoiceFragmentInterface(fragmentInterface);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(
					R.layout.fragment_release_step_three, container, false);
		}
		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		return mContentView;
	}

	private void initView() {
		mReleaseGoods = mContentView.findViewById(R.id.release_final_release);
		mReleaseGoods.setOnClickListener(this);

		mOldPrice = (EditText) mContentView
				.findViewById(R.id.release_old_price);
		mNewPrice = (EditText) mContentView
				.findViewById(R.id.release_now_price);
		mTrading = (EditText) mContentView
				.findViewById(R.id.release_trading_places);
		mPhone = (EditText) mContentView.findViewById(R.id.release_the_contact);

		mErrorOld = (TextView) mContentView
				.findViewById(R.id.release_error_old_price);
		mErrorNow = (TextView) mContentView
				.findViewById(R.id.release_error_now_price);
		mErrorTrading = (TextView) mContentView
				.findViewById(R.id.release_error_trading);
		mErrorContact = (TextView) mContentView
				.findViewById(R.id.release_error_contact);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.release_final_release) {
			if (VerifyAllInputNull()) {
				try {
					finalRelease();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean VerifyAllInputNull() {
		if (VerifyInputNull(mOldPrice)) {
			mErrorOld.setVisibility(View.VISIBLE);
			return false;
		} else {
			mErrorOld.setVisibility(View.INVISIBLE);
		}
		if (VerifyInputNull(mNewPrice)) {
			mErrorNow.setVisibility(View.VISIBLE);
			return false;
		} else {
			mErrorNow.setVisibility(View.INVISIBLE);
		}
		if (VerifyInputNull(mTrading)) {
			mErrorTrading.setVisibility(View.VISIBLE);
			return false;
		} else {
			mErrorTrading.setVisibility(View.INVISIBLE);
		}
		if (!VerifyInputNull(mPhone) && VerifyPhoneOrQQ(mPhone)) {
			mErrorContact.setVisibility(View.INVISIBLE);
		} else {
			mErrorContact.setVisibility(View.VISIBLE);
			return false;
		}
		return true;
	}

	private boolean VerifyInputNull(EditText edit) {
		if (TextUtils.isEmpty(edit.getText().toString())) {
			return true;
		}
		return false;
	}

	private boolean VerifyPhoneOrQQ(EditText edit) {
		String qq = "^[1-9][0-9]{4,}$";
		String phone = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		String tel = "^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$";
		String str = edit.getText().toString();
		if (str.matches(qq)) {
			return true;
		}
		if (str.matches(phone)) {
			return true;
		}
		if (str.matches(tel)) {
			return true;
		}
		return false;
	}

	private void finalRelease() throws UnsupportedEncodingException,
			JSONException {
		if (getActivity() instanceof ReleaseActivity) {
			ReleaseActivity mActivity = (ReleaseActivity) getActivity();
			GoodReleaseInfo info = mActivity.getGoodInfo();
			if (info.getmImages().size() > 1) {
				ControllerFromNet.UpdatePics(getActivity(),
						info.getmImageByte(), "1", "1", responseHandler);
			} else if (info.getmImages().size() == 1) {
				ControllerFromNet.UpdatePic(getActivity(), info.getmImageByte()
						.get(0), "1", "1", responseHandler);
			}
			Log.e("@@@@", "asdasd" + info.getmImages().size());
		}
	}

	JsonHttpResponseHandler responseHandler = new JsonHttpResponseHandler(
			"UTF-8") {

		@Override
		public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
			Log.e("@@@@", "status code " + statusCode);
			super.onFailure(statusCode, headers, responseString, throwable);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONArray response) {
			Log.e("@@@@", "status code " + statusCode);
			try {
				for (int i = 0; i < response.length(); i++) {
					JSONObject object = response.getJSONObject(i);
					Log.e("@@@@", object.getString("AccessUrl"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	};
}
