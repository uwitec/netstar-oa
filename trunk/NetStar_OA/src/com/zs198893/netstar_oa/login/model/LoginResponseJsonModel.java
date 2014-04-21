package com.zs198893.netstar_oa.login.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 登录返回json反序列对象
 * @author zhangshuai
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResponseJsonModel {
	/**
	 * 是否登录成功（1 true，0 false）
	 */
	private int loginResult;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 部门id
	 */
	private String deptId;
	/**
	 * 返回信息
	 */
	private String loginMessage;

	public int getLoginResult() {
		return loginResult;
	}
	@JsonProperty("loginresult")
	public void setLoginResult(int loginResult) {
		this.loginResult = loginResult;
	}
	public String getUserName() {
		return userName;
	}
	@JsonProperty("userAccount")
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	@JsonProperty("empid")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeptId() {
		return deptId;
	}
	@JsonProperty("deptID")
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getLoginMessage() {
		return loginMessage;
	}
	@JsonProperty("result")
	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	
}
