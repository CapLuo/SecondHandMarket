package com.secondhand.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.secondhand.market.R;
import com.secondhand.market.view.FixGridView;

//首页页面
public class FragmentHome extends Fragment implements OnClickListener {

	private View mContentView;

	private View mTitleBar;
	private View mSchoolShowView;
	private TextView mSchoolName;

	private EditText mSchoolSearch;

	private FixGridView mGridView;

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
		return mContentView;
	}

	private void initView() {
		mTitleBar = mContentView.findViewById(R.id.home_title_bar);

		mSchoolShowView = mTitleBar.findViewById(R.id.home_school_dropdown);
		mSchoolShowView.setOnClickListener(this);
		mSchoolName = (TextView) mTitleBar.findViewById(R.id.home_school_name);
		mSchoolSearch = (EditText) mTitleBar
				.findViewById(R.id.home_school_seach_edit);

		mGridView = (FixGridView) mContentView
				.findViewById(R.id.home_goods_gridview);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.home_school_dropdown:
			break;
		default:
			break;
		}
	}

}
