package com.secondhand.fragment;

import com.secondhand.adapter.AdapterClassify;
import com.secondhand.market.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentClassify extends Fragment implements OnItemClickListener {

	private View mContentView;

	private ListView mClassifyList;
	private AdapterClassify mClassifyAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_classify,
					container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		return mContentView;
	}

	private void initView() {
		mClassifyList = (ListView) mContentView
				.findViewById(R.id.classify_list);
		mClassifyAdapter = new AdapterClassify(getActivity());
		mClassifyList.setAdapter(mClassifyAdapter);
		mClassifyList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long arg3) {

	}
}
