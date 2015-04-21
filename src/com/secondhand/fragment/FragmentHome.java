package com.secondhand.fragment;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.secondhand.market.GoodDetailsActivity;
import com.secondhand.market.R;
import com.secondhand.util.ControllerFromNet;
import com.secondhand.view.CirclePageIndicator;
import com.secondhand.view.FixGridView;

//首页页面
public class FragmentHome extends Fragment implements OnClickListener {

	private View mContentView;

	private View mTitleBar;
	private View mSchoolShowView;
	private TextView mSchoolName;

	private EditText mSchoolSearch;
	private View mSearchView;

	private FixGridView mGridView;
	private ViewPager mViewPage;
	private CirclePageIndicator mPageIndicator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_home, container,
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
		mTitleBar = mContentView.findViewById(R.id.home_title_bar);

		mSchoolShowView = mTitleBar.findViewById(R.id.home_school_dropdown);
		mSchoolShowView.setOnClickListener(this);
		mSchoolName = (TextView) mTitleBar.findViewById(R.id.home_school_name);
		mSchoolSearch = (EditText) mTitleBar
				.findViewById(R.id.home_school_seach_edit);
		mSearchView = (EditText) mTitleBar.findViewById(R.id.home_title_search);
		mSearchView.setOnClickListener(this);

		mGridView = (FixGridView) mContentView
				.findViewById(R.id.home_goods_gridview);
		mViewPage = (ViewPager) mContentView
				.findViewById(R.id.home_image_viewpage);
		mPageIndicator = (CirclePageIndicator) mContentView
				.findViewById(R.id.home_image_indicator);
	}

	private void initData() {
		ControllerFromNet.getAllDealInfo(1, 1, 1, new JsonHttpResponseHandler(
				"utf-8") {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				Log.e("@@@@", "success array");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				Log.e("@@@@", "success object");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				Log.e("@@@@", "success");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.e("@@@@", "faile");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONArray errorResponse) {
				Log.e("@@@@", "faile array");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				Log.e("@@@@", "faile object");
			}

		});
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.home_school_dropdown:
			break;
		case R.id.home_title_search:
			getDealInfoByKeywor();
			break;
		default:
			break;
		}
	}

	private void getDealInfoByKeywor() {
		Intent intent = new Intent();
		intent.setClass(getActivity(), GoodDetailsActivity.class);
		getActivity().startActivity(intent);
	}
}
