package com.secondhand.data;

import org.json.JSONException;
import org.json.JSONObject;

public class GoodDealInfo {

	// deal info
	private int id;
	private String DealTitle;
	private String DealAddress;
	private String QQ;
	private String Email;
	private String CallNumber;
	private String Remark;
	private int DealStatus;
	private String CreateTime;
	private String EndTime;
	private int Userid;
	private int DealType;
	private int Schoolid;

	// photo info
	private String Name;
	private String Description;
	private String Price;
	private String PhotosPath;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getPhotosPath() {
		return PhotosPath;
	}

	public void setPhotosPath(String photosPath) {
		PhotosPath = photosPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDealTitle() {
		return DealTitle;
	}

	public void setDealTitle(String dealTitle) {
		DealTitle = dealTitle;
	}

	public String getDealAddress() {
		return DealAddress;
	}

	public void setDealAddress(String dealAddress) {
		DealAddress = dealAddress;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCallNumber() {
		return CallNumber;
	}

	public void setCallNumber(String callNumber) {
		CallNumber = callNumber;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public int getDealStatus() {
		return DealStatus;
	}

	public void setDealStatus(int dealStatus) {
		DealStatus = dealStatus;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public int getUserid() {
		return Userid;
	}

	public void setUserid(int userid) {
		Userid = userid;
	}

	public int getDealType() {
		return DealType;
	}

	public void setDealType(int dealType) {
		DealType = dealType;
	}

	public int getSchoolid() {
		return Schoolid;
	}

	public void setSchoolid(int schoolid) {
		Schoolid = schoolid;
	}

	public static GoodDealInfo parmsDealInfo(JSONObject json)
			throws JSONException {
		GoodDealInfo info = new GoodDealInfo();
		JSONObject mDealInfoView = json.getJSONObject("DealInfoView");
		JSONObject mGoodsInfoView = json.getJSONObject("GoodsInfoView");
		info.setId(Integer.parseInt(mDealInfoView.getString("Id")));
		info.setDealTitle(mDealInfoView.getString("DealTitle"));
		info.setDealAddress(mDealInfoView.getString("DealAddress"));
		info.setQQ(mDealInfoView.getString("QQ"));
		info.setEmail(mDealInfoView.getString("Email"));
		info.setCallNumber(mDealInfoView.getString("CallNumber"));
		info.setRemark(mDealInfoView.getString("Remark"));
		info.setDealStatus(Integer.parseInt(mDealInfoView
				.getString("DealStatus")));
		String time = changeTime(mDealInfoView.getString("CreateTime"));
		info.setCreateTime(time);
		String endTime = changeTime(mDealInfoView.getString("EndTime"));
		info.setEndTime(endTime);
		info.setUserid(Integer.parseInt(mDealInfoView.getString("UserId")));
		info.setDealType(Integer.parseInt(mDealInfoView.getString("DealType")));
		info.setDealType(Integer.parseInt(mDealInfoView.getString("SchoolId")));

		info.setName(mGoodsInfoView.getString("Name"));
		info.setDescription(mGoodsInfoView.getString("Description"));
		info.setPrice(mGoodsInfoView.getString("Price"));
		info.setPhotosPath(mGoodsInfoView.getString("PhotosPath"));
		return info;
	}

	private static String changeTime(String time) {
		String final_time = time.replace("T", "");
		String times[] = final_time.split(".");
		return times[0];
	}
}
