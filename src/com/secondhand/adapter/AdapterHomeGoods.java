package com.secondhand.adapter;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.secondhand.data.GoodDealInfo;
import com.secondhand.market.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdapterHomeGoods extends BaseAdapter {

	private Context mContext;

	private ArrayList<GoodDealInfo> mInfos;
	private DisplayImageOptions mOptions;

	public AdapterHomeGoods(Context context) {
		mContext = context;
		mInfos = new ArrayList<GoodDealInfo>();

		mOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
	}

	public void notifyListChange(ArrayList<GoodDealInfo> infos) {
		mInfos = infos;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mInfos.size();
	}

	@Override
	public Object getItem(int position) {
		if (position >= 0 && position < mInfos.size()) {
			return mInfos.get(position);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int id) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(
					R.layout.item_home_goods, null);
			holder = new ViewHolder();
			holder.image = (ImageView) view
					.findViewById(R.id.item_home_good_image);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) (view.getTag());
		}
		holder.image.setImageResource(R.drawable.user);
		String photopath = mInfos.get(position).getPhotosPath();
		String[] paths = photopath.split(",");
		ImageLoader.getInstance()
				.displayImage(paths[0], holder.image, mOptions);
		return view;
	}

	static class ViewHolder {
		ImageView image;
	}
}
