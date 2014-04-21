package com.zs198893.netstar_oa.login.engine;

import org.apache.http.Header;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.config.SharedPreferencesKeyConfig;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.login.model.LoginResponseJsonModel;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.model.UserInfoModel;
import com.zs198893.netstar_oa.tools.CommonTool;

/**
 * 登录业务逻辑类
 * 
 * @author zhangshuai
 * 
 */
public class LoginEngine {
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * 通用返回值对象
	 */
	private CommonResult commonResult;

	/**
	 * 配置文件对象
	 */
	private SharedPreferences sharedPreferences;
	/**
	 * 配置文件操作对象
	 */
	private Editor editor;
	/**
	 * 网络请求类
	 */
	private AsyncHttpClient asyncHttpClient;
	/**
	 * 登录回调类
	 */
	private LoginBaseJsonHttpResponseHandler baseJsonHttpResponseHandler;
	
	public LoginEngine(Context context) {
		super();
		this.context = context;
		sharedPreferences = context.getSharedPreferences(
				SharedPreferencesKeyConfig.DEFAULT_SP_FILE_NAME,
				Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		asyncHttpClient = ((AppContext) (context.getApplicationContext())).asyncHttpClient;
		baseJsonHttpResponseHandler = new LoginBaseJsonHttpResponseHandler();
	}

	/**
	 * 检验格式是否正确
	 * 
	 * @param userCode
	 * @param userPwd
	 * @return
	 */
	public CommonResult checkData(UserInfoModel userInfoModel) {
		commonResult = new CommonResult();
		commonResult.setSuccess(true);
		// 判断登录名是否为空
		if (TextUtils.isEmpty(userInfoModel.getUserLoginName())) {
			commonResult.setSuccess(false);
			commonResult.setResult("登录名为空");
		}
		// 判断登录密码是否为空
		if (TextUtils.isEmpty(userInfoModel.getUserLoginPWD())) {
			commonResult.setSuccess(false);
			commonResult.setResult("登录密码为空");
		}
		return commonResult;
	}

	/**
	 * 获得用户详细信息
	 * 
	 * @return 用户详情对象
	 */
	public UserInfoModel getUserInfo() {
		return ((AppContext) (context.getApplicationContext())).getUserInfo();
	}

	/**
	 * 保存用户信息
	 * 
	 * @param userInfoModel
	 * @return 是否成功
	 */
	public CommonResult setUserInfo(UserInfoModel userInfoModel) {
		return ((AppContext) (context.getApplicationContext()))
				.setUserInfo(userInfoModel);
	}

	/**
	 * 拼凑用户model
	 * 
	 * @param login_et_loging_name
	 *            用户登录名
	 * @param login_et_loging_pwd
	 *            登录密码
	 * @param login_cb_autologin
	 *            是否自动登录
	 * @param login_cb_savepwd
	 *            是否保存密码
	 */
	public UserInfoModel initUserInfo(EditText login_et_loging_name,
			EditText login_et_loging_pwd, CheckBox login_cb_autologin,
			CheckBox login_cb_savepwd) {
		UserInfoModel userInfoModel = new UserInfoModel();
		userInfoModel.setUserLoginName(CommonTool.getStringFromEditText(login_et_loging_name));
		userInfoModel.setUserLoginPWD(CommonTool.getStringFromEditText(login_et_loging_pwd));
		userInfoModel.setAuto_login(login_cb_autologin.isChecked());
		userInfoModel.setSave_pwd(login_cb_savepwd.isChecked());
		return userInfoModel;
	}
	/**
	 * 拼装请求参数
	 * @param userInfoModel 用户详情对象
	 * @return 请求参数
	 */
	public RequestParams getRequestParams(String userName,  String userPassword){
		RequestParams params = new RequestParams();
		params.add(WebServerConfig.LoginRequestParamKeys.LOGIN_NAME, userName);
		params.add(WebServerConfig.LoginRequestParamKeys.LOGIN_PWD, userPassword);
		return params;
	}
	/**
	 * 当请求结束后
	 * @param commonResult 请求的结果
	 */
	public void onLoginFinished(CommonResult commonResult){
		//判断是否登陆成功
		if(commonResult.isSuccess()){
			//登录成功
			//保存用户信息到配置
			
		}else{
			Toast.makeText(context, (String) commonResult.getResult(),
					Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * 当请求结束后
	 * @param commonResult 请求的结果
	 */
	public CommonResult startLogin(EditText userName, EditText userPassword){
		CommonResult commonResult = new CommonResult();
		commonResult.setSuccess(false);
		commonResult.setResult("未知错误");
		String userNameStr = userName.getText().toString();
		String userPasswordStr = userPassword.getText().toString();
		do{
			//验证登录帐号
			if(TextUtils.isEmpty(userNameStr)){
				commonResult.setSuccess(false);
				commonResult.setResult("登录帐号不得为空");
			}
			//验证登录密码
			if(TextUtils.isEmpty(userPasswordStr)){
				commonResult.setSuccess(false);
				commonResult.setResult("登录密码不得为空");
			}
			asyncHttpClient.post(
						WebServerConfig.getUrl(WebServerConfig.loginAction),
						getRequestParams(userNameStr, userPasswordStr),
					baseJsonHttpResponseHandler);
		}while(false);

		return commonResult;
	}
	/**
	 * 登录回调类
	 * 
	 * @author zhangshuai
	 * 
	 */
	private class LoginBaseJsonHttpResponseHandler extends
			BaseJsonHttpResponseHandler<LoginResponseJsonModel> {
		private CommonResult commonResult;

		@Override
		public void onFailure(int arg0, Header[] arg1, Throwable arg2,
				String arg3, LoginResponseJsonModel arg4) {
			commonResult = new CommonResult();
			commonResult.setSuccess(false);
			commonResult.setResult(arg2.getLocalizedMessage());
		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, String arg2,
				LoginResponseJsonModel arg3) {
			commonResult = new CommonResult();
			if (true) {
				commonResult.setSuccess(true);
				commonResult.setResult(arg3);
			} else {
				commonResult.setSuccess(false);
				commonResult.setResult("未知错误");
			}
		}

		@Override
		protected LoginResponseJsonModel parseResponse(String arg0)
				throws Throwable {
			return new ObjectMapper().readValues(
					new JsonFactory().createParser(arg0),
					LoginResponseJsonModel.class).next();
		}
	}
}