package com.secondhand.adapter;

import java.util.ArrayList;

import com.secondhand.market.R;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdapterInsertImageBrowsing extends BaseAdapter {

	private Context mContext;

	private ArrayList<Uri> mList;

	public AdapterInsertImageBrowsing(Context context) {
		mContext = context;

		mList = new ArrayList<Uri>();
	}

	public void notifyDataList(ArrayList<Uri> list) {
		mList = list;
	}

	@Override
	public int getCount() {
		int size = mList.size();
		if (size >= 4) {
			return size;
		}
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
			view.setTag(Uri.parse(""));
		} else {
			((ImageView) view).setImageURI(mList.get(position));
			view.setTag(mList.get(position));
		}
		return view;
	}

}
