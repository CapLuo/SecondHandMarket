package com.secondhand.adapter;

import com.secondhand.market.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterClassify extends BaseAdapter {

	private int[] images = { R.drawable.phone, R.drawable.computer,
			R.drawable.digital, R.drawable.bike, R.drawable.book,
			R.drawable.sport, R.drawable.life, R.drawable.another };
	private String[] titles = { "手机", "电脑", "数码", "骑行", "书籍", "运动", "生活", "其他" };
	private String[] descriptions = { "iPhone/小米/三星/手机配件...",
			"mac/华硕/联想/宏碁/戴尔/电脑配件...", "iPad/相机/游戏机/U盘/耳机...",
			"自行车/电瓶车/旱冰鞋...", "教科书/资料书/杂志/小说/课堂笔记...", "篮球/足球/球拍/健身器材...",
			"衣服/用品/交通卡...", "没有包含在分类里的宝贝" };
	private Context mContext;

	public AdapterClassify(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return titles.length;
	}

	@Override
	public Object getItem(int position) {
		return titles[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_classify, null);
			holder = new Holder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_classify_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.item_classify_title);
			holder.description = (TextView) convertView
					.findViewById(R.id.item_classify_description);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.image.setImageResource(images[position]);
		holder.title.setText(titles[position]);
		holder.description.setText(descriptions[position]);

		return convertView;
	}

	class Holder {
		ImageView image;
		TextView title;
		TextView description;
	}
}
