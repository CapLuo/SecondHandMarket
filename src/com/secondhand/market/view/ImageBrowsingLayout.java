package com.secondhand.market.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class ImageBrowsingLayout extends LinearLayout {

	private ShowInsertImg mShowInsertImg = new ShowInsertImg() {

		@Override
		public void showImg(String uri) {
		}
	};

	public ImageBrowsingLayout(Context context) {
		super(context);
	}

	public ImageBrowsingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setShowImgInterface(ShowInsertImg img) {
		mShowInsertImg = img;
	}

	public void setAdapter(BaseAdapter adapter) {
		int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			View v = adapter.getView(i, null, null);
			LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lparams.gravity = Gravity.CENTER_VERTICAL;
			lparams.leftMargin = 20;
			v.setLayoutParams(lparams);

			v.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					String uri = (String) view.getTag();
					mShowInsertImg.showImg(uri);
				}
			});
			addView(v);
		}
	}

	public interface ShowInsertImg {
		public void showImg(String uri);
	}
}
