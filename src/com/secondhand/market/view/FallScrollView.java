package com.secondhand.market.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class FallScrollView extends ScrollView {

	private Handler handler;
	private View view;

	public FallScrollView(Context context) {
		super(context);
	}

	public FallScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FallScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// 总高度
	public int computeVerticalScrollRange() {
		return super.computeHorizontalScrollRange();
	}

	public int computeVerticalScrollOffset() {
		return super.computeVerticalScrollOffset();
	}

	/***
	 * 初始化
	 */
	private void init() {

		this.setOnTouchListener(onTouchListener);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// process incoming messages here
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					if (view.getMeasuredHeight() <= getScrollY() + getHeight()) {
						if (onScrollListener != null) {
							onScrollListener.onBottom();
						}

					} else if (getScrollY() == 0) {
						if (onScrollListener != null) {
							onScrollListener.onTop();
						}
					} else {
						if (onScrollListener != null) {
							onScrollListener.onScroll();
						}
					}
					break;
				default:
					break;
				}
			}
		};

	}

	OnTouchListener onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				if (view != null && onScrollListener != null) {
					handler.sendMessageDelayed(handler.obtainMessage(1), 200);
				}
				break;

			default:
				break;
			}
			return false;
		}

	};

	/**
	 * 获得参考的View，主要是为了获得它的MeasuredHeight，然后和滚动条的ScrollY+getHeight作比较。
	 */
	public void getView() {
		this.view = getChildAt(0);
		if (view != null) {
			init();
		}
	}

	/**
	 * 定义接口
	 * 
	 * @author admin
	 * 
	 */
	public interface OnScrollListener {
		void onBottom();

		void onTop();

		void onScroll();
	}

	private OnScrollListener onScrollListener;

	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
}
