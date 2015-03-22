package com.secondhand.data;

import java.util.List;

import android.net.Uri;

public class GoodReleaseInfo {

	private List<Uri> mImages;
	private List<byte[]> mImageByte;
	private String mTitle;
	private String mDescription;
	private String mProperty;
	private String mOldPrice;
	private String mNewPrice;
	private String mTradingPlace;
	private String mPhone;

	public List<byte[]> getmImageByte() {
		return mImageByte;
	}

	public void setmImageByte(List<byte[]> mImageByte) {
		this.mImageByte = mImageByte;
	}

	public List<Uri> getmImages() {
		return mImages;
	}

	public void setmImages(List<Uri> mImages) {
		this.mImages = mImages;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getmProperty() {
		return mProperty;
	}

	public void setmProperty(String mProperty) {
		this.mProperty = mProperty;
	}

	public String getmOldPrice() {
		return mOldPrice;
	}

	public void setmOldPrice(String mOldPrice) {
		this.mOldPrice = mOldPrice;
	}

	public String getmNewPrice() {
		return mNewPrice;
	}

	public void setmNewPrice(String mNewPrice) {
		this.mNewPrice = mNewPrice;
	}

	public String getmTradingPlace() {
		return mTradingPlace;
	}

	public void setmTradingPlace(String mTradingPlace) {
		this.mTradingPlace = mTradingPlace;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

}
