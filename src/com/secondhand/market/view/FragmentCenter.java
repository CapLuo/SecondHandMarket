package com.secondhand.market.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.secondhand.market.R;
import com.secondhand.market.SettingActivity;

public class FragmentCenter extends Fragment implements OnClickListener {

	private View mContentView;

	private ImageView mSettings;

	private ImageView mHeadPhoto;
	private TextView mUserDisplayName;

	private View mSell;
	private View mSelled;
	private View mBuy;
	private View mFavorites;

	private ListView mGoodsList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_center,
					container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		return mContentView;
	}

	private void initView() {
		mSettings = (ImageView) mContentView.findViewById(R.id.center_settings);
		mSettings.setOnClickListener(this);

		mHeadPhoto = (ImageView) mContentView
				.findViewById(R.id.center_user_photo);
		mHeadPhoto.setOnClickListener(this);
		mUserDisplayName = (TextView) mContentView
				.findViewById(R.id.center_user_name);

		mSell = mContentView.findViewById(R.id.center_sell);
		mSell.setOnClickListener(this);
		mSelled = mContentView.findViewById(R.id.center_selled);
		mSelled.setOnClickListener(this);
		mBuy = mContentView.findViewById(R.id.center_buy);
		mBuy.setOnClickListener(this);
		mFavorites = mContentView.findViewById(R.id.center_favorites);
		mFavorites.setOnClickListener(this);

		mGoodsList = (ListView) mContentView.findViewById(R.id.classify_list);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.center_settings:
			Intent intent = new Intent();
			intent.setClass(getActivity(), SettingActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.center_user_photo:
			break;
		case R.id.center_sell:
			break;
		case R.id.center_selled:
			break;
		case R.id.center_buy:
			break;
		case R.id.center_favorites:
			break;
		default:
			break;
		}
	}
}
