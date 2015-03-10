package com.secondhand.fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.secondhand.adapter.AdapterInsertImageBrowsing;
import com.secondhand.market.R;
import com.secondhand.util.ToolsUtil;
import com.secondhand.view.ImageBrowsingLayout;

//发布页面
public class FragmentReleaseStepOne extends FragmentInterfaceChoice implements
		OnClickListener {

	private View mContentView;

	private View mStepNext;
	private ImageView mImgInsert;
	private ImageBrowsingLayout mBrowsingLayout;

	private AdapterInsertImageBrowsing mAdapterBrowsing;

	private PopupWindow mPop;

	private ArrayList<Uri> mReleaseImages;
	private String mSendImage;

	public FragmentReleaseStepOne(ChoiceFragmentInterface mInterface) {
		setChoiceFragmentInterface(mInterface);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(R.layout.fragment_release_step_one,
					container, false);
		}

		if (mContentView.getParent() != null) {
			((ViewGroup) mContentView.getParent()).removeView(mContentView);
		}

		initView();
		initData();
		return mContentView;
	}

	private void initView() {
		mStepNext = mContentView.findViewById(R.id.release_step_next);
		mStepNext.setOnClickListener(this);

		mImgInsert = (ImageView) mContentView.findViewById(R.id.release_pic);

		mAdapterBrowsing = new AdapterInsertImageBrowsing(getActivity());
		mBrowsingLayout = (ImageBrowsingLayout) mContentView
				.findViewById(R.id.release_browsing);
		mBrowsingLayout.setAdapter(mAdapterBrowsing);
	}

	private void initData() {
		mBrowsingLayout
				.setShowImgInterface(new ImageBrowsingLayout.ShowInsertImg() {
					@Override
					public void showImg(Uri uri, View v) {
						if (TextUtils.isEmpty(uri.toString())) {
							showPopuWindow(mImgInsert);
						} else {
							mImgInsert.setImageURI(uri);
						}
					}
				});
		mReleaseImages = new ArrayList<Uri>();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.release_step_next:
			break;
		case R.id.release_menu_camera:
			mPop.dismiss();
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
			mSendImage = ToolsUtil.getAppFilePath() + "/"
					+ new Date().getTime() + ".jpg";
			intent.putExtra("output", Uri.fromFile(new File(mSendImage)));
			intent.putExtra("outputFormat", "JPEG");
			startActivityForResult(intent, 2);
			break;
		case R.id.release_menu_albums:
			mPop.dismiss();
			Intent albums = new Intent(Intent.ACTION_GET_CONTENT);
			albums.addCategory(Intent.CATEGORY_OPENABLE);
			albums.setType("image/*");
			startActivityForResult(Intent.createChooser(albums, "选择图片"), 1);
			break;
		default:
			break;
		}
	}

	private void showPopuWindow(View parent) {
		if (mPop == null) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View popuView = inflater.inflate(R.layout.release_menu_popuwindow,
					null);
			mPop = new PopupWindow(popuView,
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			View camera = popuView.findViewById(R.id.release_menu_camera);
			camera.setOnClickListener(this);
			View albums = popuView.findViewById(R.id.release_menu_albums);
			albums.setOnClickListener(this);
			mPop.setBackgroundDrawable(new BitmapDrawable());
			mPop.setFocusable(true);
			mPop.setOutsideTouchable(true);
		}
		mPop.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (data == null) {
				return;
			}
			Uri uri = data.getData();
			mSendImage = ToolsUtil.getAppFilePath() + "/"
					+ new Date().getTime() + ".jpg";
			startPhotoZoom(uri);
		}
		if (requestCode == 2) {
			startPhotoZoom(Uri.fromFile(new File(mSendImage)));
		}
		if (requestCode == 3) {
			Uri uri = Uri.fromFile(new File(mSendImage));
			mImgInsert.setImageURI(uri);
			mReleaseImages.add(uri);
			mAdapterBrowsing.notifyDataList(mReleaseImages);
			mBrowsingLayout.setAdapter(mAdapterBrowsing);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void startPhotoZoom(Uri uri) {
		File imageFile = new File(mSendImage);
		try {
			if (!imageFile.exists()) {
				imageFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("scale", true);
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("output", Uri.fromFile(imageFile));
		intent.putExtra("outputFormat", "JPEG");
		startActivityForResult(intent, 3);
	}
}
