package com.zs198893.netstar_oa.model;
/**
 * 用户详细信息
 * @author zhangshuai
 *
 */
public class UserInfoModel {
	/**
	 * 用户登录名
	 */
	private String userLoginName;
	/**
	 * 用户登录密码
	 */
	private String userLoginPWD;
	/**
	 * 是否自动登录
	 */
	private boolean auto_login;
	/**
	 * 是否保存密码
	 */
	private boolean save_pwd;
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getUserLoginPWD() {
		return userLoginPWD;
	}
	public void setUserLoginPWD(String userLoginPWD) {
		this.userLoginPWD = userLoginPWD;
	}
	public boolean isAuto_login() {
		return auto_login;
	}
	public void setAuto_login(boolean auto_login) {
		this.auto_login = auto_login;
	}
	public boolean isSave_pwd() {
		return save_pwd;
	}
	public void setSave_pwd(boolean save_pwd) {
		this.save_pwd = save_pwd;
	}
	
}
