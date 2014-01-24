package com.zs198893.netstar_oa.daily_log.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogQueryModel {
	private String url;
	private String bllState;
	private String userID;
	private int companyID;
	private int rows;
	private int page;
	private int id;
	private String log_Content;
	private String deptName;
	private String userName;
	private String log_Abstract;
	private String log_Date;
	private String create_Date;
	private String infor;
	private String create_By;
	private String beginDate;
	private String endDate;
	private String deptID;

	public String getUrl() {
		return url;
	}
	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	public String getBllState() {
		return bllState;
	}
	@JsonProperty("bllState")
	public void setBllState(String bllState) {
		this.bllState = bllState;
	}

	public String getUserID() {
		return userID;
	}
	@JsonProperty("userID")
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getCompanyID() {
		return companyID;
	}
	@JsonProperty("companyID")
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public int getRows() {
		return rows;
	}
	@JsonProperty("rows")
	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}
	@JsonProperty("page")
	public void setPage(int page) {
		this.page = page;
	}

	public int getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	public String getLog_Content() {
		return log_Content;
	}
	@JsonProperty("log_Content")
	public void setLog_Content(String log_Content) {
		this.log_Content = log_Content;
	}

	public String getDeptName() {
		return deptName;
	}
	@JsonProperty("deptName")
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}
	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLog_Abstract() {
		return log_Abstract;
	}
	@JsonProperty("log_Abstract")
	public void setLog_Abstract(String log_Abstract) {
		this.log_Abstract = log_Abstract;
	}

	public String getLog_Date() {
		return log_Date;
	}
	@JsonProperty("log_Date")
	public void setLog_Date(String log_Date) {
		this.log_Date = log_Date;
	}

	public String getCreate_Date() {
		return create_Date;
	}
	@JsonProperty("create_Date")
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}

	public String getInfor() {
		return infor;
	}
	@JsonProperty("infor")
	public void setInfor(String infor) {
		this.infor = infor;
	}

	public String getCreate_By() {
		return create_By;
	}
	@JsonProperty("create_By")
	public void setCreate_By(String create_By) {
		this.create_By = create_By;
	}

	public String getBeginDate() {
		return beginDate;
	}
	@JsonProperty("beginDate")
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}
	@JsonProperty("endDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDeptID() {
		return deptID;
	}
	@JsonProperty("deptID")
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

}
