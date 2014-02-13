package com.zs198893.netstar_oa.daily_log.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyLogQueryContentModel {
	private int total;
	private int records;
	private boolean success;
	private List<LogQueryModel> logQueryModel;

	public int getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	@JsonProperty("records")
	public void setRecords(int records) {
		this.records = records;
	}

	public boolean isSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<LogQueryModel> getLogQueryModel() {
		return logQueryModel;
	}
	@JsonProperty("list")
	public void setLogQueryModel(List<LogQueryModel> logQueryModel) {
		this.logQueryModel = logQueryModel;
	}



}
