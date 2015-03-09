package com.secondhand.adapter;

import java.util.ArrayList;

import com.secondhand.market.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdapterInsertImageBrowsing extends BaseAdapter {

	private Context mContext;

	private ArrayList<String> mList;

	public AdapterInsertImageBrowsing(Context context) {
		mContext = context;

		mList = new ArrayList<String>();
	}

	public void notifyDataList(ArrayList<String> list) {
		mList = list;
	}

	@Override
	public int getCount() {
		return mList.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = new ImageView(mContext);
		}
		if (position == mList.size()) {
			((ImageView) view).setImageResource(R.drawable.add_release_pic);
			view.setTag("");
		} else {

		}
		return view;
	}

}
