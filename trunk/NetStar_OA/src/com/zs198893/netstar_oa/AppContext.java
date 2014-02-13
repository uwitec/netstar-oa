package com.zs198893.netstar_oa;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.zs198893.netstar_oa.config.SharedPreferencesConfig;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.model.UserInfoModel;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * 
 * @author zhangshuai
 * 
 */
public class AppContext extends Application {
	/**
	 * 网络请求对象
	 */
	public AsyncHttpClient asyncHttpClient;
	/**
	 * cookie
	 */
	public PersistentCookieStore asyncHttpClientCookieStore;
	/**
	 * 配置文件对象
	 */
	private SharedPreferences sharedPreferences;
	/**
	 * 配置文件操作对象
	 */
	private Editor editor;

	@Override
	public void onCreate() {
		asyncHttpClient = new AsyncHttpClient();
		asyncHttpClientCookieStore = new PersistentCookieStore(this);
		asyncHttpClient.setCookieStore(asyncHttpClientCookieStore);
		sharedPreferences = getSharedPreferences(
				SharedPreferencesConfig.DEFAULT_SP_FILE_NAME,
				Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		super.onCreate();
	}

	/**
	 * 获得用户信息
	 * 
	 * @return 用户详情
	 */
	public UserInfoModel getUserInfo() {
		UserInfoModel infoModel = new UserInfoModel();
		// 获得用户登录名
		infoModel.setUserLoginName(sharedPreferences.getString(
				SharedPreferencesConfig.LOGIN_USER_CODE, ""));
		// 通过判断用户登录名来判断，上次有没有登陆成功
		if (TextUtils.isEmpty(infoModel.getUserLoginName())) {
			infoModel = null;
		} else {
			// 获得用户登录密码
			infoModel.setUserLoginPWD(sharedPreferences.getString(
					SharedPreferencesConfig.LOGIN_USER_PWD, ""));
			// 是否自动登录
			infoModel.setAuto_login(sharedPreferences.getBoolean(
					SharedPreferencesConfig.LOGIN_AUTO_LOGIN, false));
			// 是否保存密码
			infoModel.setSave_pwd(sharedPreferences.getBoolean(
					SharedPreferencesConfig.LOGIN_SAVE_PWD, false));
		}
		return infoModel;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param userInfoModel
	 *            用户详细对象
	 * @return 是否保存成功
	 */
	public CommonResult setUserInfo(UserInfoModel userInfoModel) {
		CommonResult commonResult = new CommonResult();
		commonResult.setSuccess(true);
		try {
			editor.putString(SharedPreferencesConfig.LOGIN_USER_CODE,
					userInfoModel.getUserLoginName());
			editor.putString(SharedPreferencesConfig.LOGIN_USER_PWD,
					userInfoModel.getUserLoginPWD());
			editor.putBoolean(SharedPreferencesConfig.LOGIN_AUTO_LOGIN,
					userInfoModel.isAuto_login());
			editor.putBoolean(SharedPreferencesConfig.LOGIN_SAVE_PWD,
					userInfoModel.isSave_pwd());
			editor.commit();
		} catch (Exception e) {
			commonResult.setSuccess(false);
			commonResult.setResult(e.getLocalizedMessage());
		}
		return commonResult;
	}

	/**
	 * 清除cookie
	 */
	public void clearCookie() {
		asyncHttpClientCookieStore.clear();
	}
}
