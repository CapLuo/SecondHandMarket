package com.secondhand.fragment;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.secondhand.data.GoodReleaseInfo;
import com.secondhand.data.PersionInfo;
import com.secondhand.market.FleastreetApplication;
import com.secondhand.market.R;
import com.secondhand.market.ReleaseActivity;
import com.secondhand.util.ControllerFromNet;
import com.secondhand.util.FieldUtil;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class FragmentReleaseStepThree extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mReleaseGoods;
	private EditText mOldPrice; // 原价
	private EditText mNewPrice; // 现价
	private EditText mTrading; // 交易地点
	private EditText mPhone; // 联系方式
	private TextView mDealType; // 货物类型

	private TextView mErrorOld;
	private TextView mErrorNow;
	private TextView mErrorTrading;
	private TextView mErrorContact;
	private TextView mErrorDealType;

	private PopupWindow mPop;
	private View mPopLayout;
	private HashMap<String, String> mDealTypeMap;

	private ArrayList<String> mAccessUrl;
	private int mPicCount = 0;
	private int mDealTypeValue = -1;

	private GoodReleaseInfo mInfo;
	private int phoneOrQQ = -1; // 1 phone 2 qq

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
		initData();
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
		mErrorDealType = (TextView) mContentView
				.findViewById(R.id.release_error_deal_type);

		mDealType = (TextView) mContentView
				.findViewById(R.id.release_choice_goods_type);
		mDealType.setOnClickListener(this);

		if (mPop == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			mPopLayout = layoutInflater.inflate(
					R.layout.release_deal_type_popuwindow, null);
			mPop = new PopupWindow(mPopLayout,
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			mPop.setBackgroundDrawable(new BitmapDrawable());
			mPop.setFocusable(true);
			mPop.setOutsideTouchable(true);
		}
	}

	private void initData() {
		ControllerFromNet.getDealType(new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.e(FieldUtil.TAG, throwable.getMessage());
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				try {
					if (mDealTypeMap == null) {
						mDealTypeMap = new HashMap<String, String>();
					}
					mDealTypeMap.clear();
					for (int i = 0; i < response.length(); i++) {
						JSONObject object = response.getJSONObject(i);
						String displayName = object.getString("DisplayName");
						String value = object.getString("Value");
						mDealTypeMap.put(displayName, value);
						TextView textView = new TextView(getActivity());
						textView.setText(displayName);
						textView.setTag(displayName);
						float size = getActivity().getResources().getDimension(
								R.dimen.release_dropdown_list_width);
						textView.setWidth((int) size);
						textView.setGravity(Gravity.CENTER);
						((ViewGroup) mPopLayout).addView(textView);
						textView.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View view) {
								String text = getActivity()
										.getResources()
										.getString(
												R.string.release_already_deal_type);
								text = text + (String) (view.getTag());
								mDealType.setText(text);
								mErrorDealType.setVisibility(View.INVISIBLE);
								mDealTypeValue = Integer.parseInt(mDealTypeMap
										.get((String) view.getTag()));
								mPop.dismiss();
							}
						});
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e(FieldUtil.TAG, e.getMessage());
				}
			}

		});
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
		if (view.getId() == R.id.release_choice_goods_type) {
			mPop.showAsDropDown(view);
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
		if (mDealTypeValue != -1) {
			mErrorDealType.setVisibility(View.INVISIBLE);
		} else {
			mErrorDealType.setVisibility(View.VISIBLE);
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
			phoneOrQQ = 2;
			return true;
		}
		phoneOrQQ = 1;
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
			mInfo = mActivity.getGoodInfo();
			mInfo.setmPhone(mPhone.getText().toString());
			mInfo.setmNewPrice(mNewPrice.getText().toString());
			mInfo.setmOldPrice(mOldPrice.getText().toString());
			mInfo.setmTradingPlace(mTrading.getText().toString());
			if (mAccessUrl == null) {
				mAccessUrl = new ArrayList<String>();
			}
			mAccessUrl.clear();
			if (mInfo.getmImages().size() > 1) {
				mPicCount = mInfo.getmImages().size();
				ControllerFromNet.UpdatePics(getActivity(),
						mInfo.getmImageByte(), "1", "1", responseHandlerMore);
			} else if (mInfo.getmImages().size() == 1) {
				mPicCount = mInfo.getmImages().size();
				ControllerFromNet.UpdatePic(getActivity(), new File(mInfo
						.getmImageByte().get(0)), "1", "1", responseHandler);
			}
		}
	}

	private JsonHttpResponseHandler responseHandlerMore = new JsonHttpResponseHandler(
			"utf-8") {

		@Override
		public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
			Log.e(FieldUtil.TAG, "status code " + statusCode);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONArray response) {
			try {
				for (int i = 0; i < response.length(); i++) {
					JSONObject object = response.getJSONObject(i);
					String accessUrl = object.getString("AccessUrl");
					mAccessUrl.add(accessUrl);
				}
				if (mAccessUrl.size() == mPicCount) {
					finalReleaseToCreateDeal();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			try {
				String accessUrl = response.getString("AccessUrl");
				mAccessUrl.add(accessUrl);
				if (mAccessUrl.size() == mPicCount) {
					finalReleaseToCreateDeal();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	};
	private JsonHttpResponseHandler responseHandler = new JsonHttpResponseHandler(
			"utf-8") {

		@Override
		public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
			Log.e(FieldUtil.TAG, "status code " + statusCode);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			try {
				String accessUrl = response.getString("AccessUrl");
				mAccessUrl.add(accessUrl);
				if (mAccessUrl.size() == mPicCount) {
					finalReleaseToCreateDeal();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONArray response) {
			try {
				for (int i = 0; i < response.length(); i++) {
					JSONObject object = response.getJSONObject(i);
					String accessUrl = object.getString("AccessUrl");
					mAccessUrl.add(accessUrl);
				}
				if (mAccessUrl.size() == mPicCount) {
					finalReleaseToCreateDeal();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	};

	private void finalReleaseToCreateDeal() throws JSONException {
		FleastreetApplication app = (FleastreetApplication) getActivity()
				.getApplication();
		PersionInfo info = app.getInfo();
		JSONObject object = new JSONObject();
		JSONObject goodInfo = new JSONObject();
		JSONObject dealInfo = new JSONObject();
		goodInfo.put("Name", mInfo.getmTitle());
		goodInfo.put("Description", mInfo.getmDescription());
		goodInfo.put("Price", mInfo.getmNewPrice());
		StringBuilder photoPath = new StringBuilder();
		for (int i = 0; i < mAccessUrl.size(); i++) {
			String url = mAccessUrl.get(i);
			photoPath.append(url);
			if (i != (mAccessUrl.size() - 1)) {
				photoPath.append(",");
			}
		}
		goodInfo.put("PhotosPath", photoPath.toString());

		dealInfo.put("UserId", info.getId());
		dealInfo.put("DealTitle", mInfo.getmTitle());
		dealInfo.put("DealAddress", mInfo.getmTradingPlace());
		if (phoneOrQQ == 2) {
			dealInfo.put("QQ", mInfo.getmPhone());
			dealInfo.put("Email", "null");
			dealInfo.put("CallNumber", 1);
		} else {
			dealInfo.put("QQ", 1);
			dealInfo.put("Email", "null");
			dealInfo.put("CallNumber", mInfo.getmPhone());
		}
		dealInfo.put("Remark", mInfo.getmDescription());
		dealInfo.put("DealStatus", -1);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		dealInfo.put("CreateTime", sdf.format(date));
		dealInfo.put("DealType", mDealTypeValue);

		object.put("DealInfoView", dealInfo);
		object.put("GoodsInfoView", goodInfo);
		ControllerFromNet.dealCreate(getActivity(), object,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int stateCode, Header[] head,
							byte[] bytes) {
						Log.e("@@@@", "stateCode = " + stateCode + " ");
					}

					@Override
					public void onFailure(int stateCode, Header[] head,
							byte[] bytes, Throwable error) {
						Log.e("@@@@",
								"stateCode = "
										+ stateCode
										+ " "
										+ error.getMessage()
										+ " "
										+ (bytes == null ? "" : new String(
												bytes)));
					}
				});
	}
}
