package com.zs198893.netstar_oa.daily_log.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 日志模型
 * 
 * @author zhangshuai
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyLogSubmitResponseModel {
	private boolean success;
	private DailyLogModel dailyLogModel;

	public boolean isSuccess() {
		return success;
	}
	@JsonProperty("success")
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public DailyLogModel getDailyLogModel() {
		return dailyLogModel;
	}
	@JsonProperty("logInputModel")
	public void setDailyLogModel(DailyLogModel dailyLogModel) {
		this.dailyLogModel = dailyLogModel;
	}

	public static class DailyLogModel {
		private String entityState;
		private int id;
		private String log_Content;
		private String log_Date;
		private int create_By;
		private String create_Date;
		private int update_By;
		private String update_Date;
		private String num;
		private String tableName;
		private boolean isNew;
		private boolean isDeleted;
		private boolean isChanged;

		public String getEntityState() {
			return entityState;
		}
		@JsonProperty("entityState")
		public void setEntityState(String entityState) {
			this.entityState = entityState;
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

		public String getLog_Date() {
			return log_Date;
		}
		@JsonProperty("log_Date")
		public void setLog_Date(String log_Date) {
			this.log_Date = log_Date;
		}

		public int getCreate_By() {
			return create_By;
		}
		@JsonProperty("create_By")
		public void setCreate_By(int create_By) {
			this.create_By = create_By;
		}

		public String getCreate_Date() {
			return create_Date;
		}
		@JsonProperty("create_Date")
		public void setCreate_Date(String create_Date) {
			this.create_Date = create_Date;
		}

		public int getUpdate_By() {
			return update_By;
		}
		@JsonProperty("update_By")
		public void setUpdate_By(int update_By) {
			this.update_By = update_By;
		}

		public String getUpdate_Date() {
			return update_Date;
		}
		@JsonProperty("update_Date")
		public void setUpdate_Date(String update_Date) {
			this.update_Date = update_Date;
		}

		public String getNum() {
			return num;
		}
		@JsonProperty("num")
		public void setNum(String num) {
			this.num = num;
		}

		public String getTableName() {
			return tableName;
		}
		@JsonProperty("tableName")
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public boolean isNew() {
			return isNew;
		}
		@JsonProperty("new")
		public void setNew(boolean isNew) {
			this.isNew = isNew;
		}

		public boolean isDeleted() {
			return isDeleted;
		}
		@JsonProperty("deleted")
		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public boolean isChanged() {
			return isChanged;
		}
		@JsonProperty("changed")
		public void setChanged(boolean isChanged) {
			this.isChanged = isChanged;
		}

	}
}
