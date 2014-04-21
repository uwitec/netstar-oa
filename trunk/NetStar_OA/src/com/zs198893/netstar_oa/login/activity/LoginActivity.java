package com.zs198893.netstar_oa.login.activity;

import org.apache.http.Header;

import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.login.engine.LoginEngine;
import com.zs198893.netstar_oa.login.model.LoginResponseJsonModel;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.model.UserInfoModel;

/**
 * OA 系统办公系统 登陆界面
 * 
 * @author zhangshuai
 * 
 */
public class LoginActivity extends BaseActivity {
	private Context context;
	/**
	 * 登录名
	 */
	@InjectView(R.id.login_et_loging_name)
	EditText login_et_loging_name;
	/**
	 * 登录密码
	 */
	@InjectView(R.id.login_et_loging_pwd)
	EditText login_et_loging_pwd;
	/**
	 * 自动登录
	 */
	@InjectView(R.id.login_cb_autologin)
	CheckBox login_cb_autologin;
	/**
	 * 记住密码
	 */
	@InjectView(R.id.login_cb_savepwd)
	CheckBox login_cb_savepwd;
	/**
	 * 登录按钮
	 */
	@InjectView(R.id.login_bt_submit)
	Button login_bt_submit;
	/**
	 * 页面view 点击事件
	 */
	private OnViewClickListener onViewClickListener;
	/**
	 * 业务逻辑类
	 */
	private LoginEngine loginEngine;


	@Override
	public void subInitContentView(Bundle savedInstanceState) {
		setContentView(R.layout.login_activity_main);
	}

	@Override
	public void subInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void subInitParam() {
		// 页面view点击事件
		onViewClickListener = new OnViewClickListener();
		loginEngine = new LoginEngine(this);
		context = this;

	}

	@Override
	public void subSetOnclick() {
		login_bt_submit.setOnClickListener(onViewClickListener);
	}

	@Override
	public void subRunSomeThing() {
		// TODO Auto-generated method stub

	}

	/**
	 * 页面 view 点击事件
	 * 
	 * @author zhangshuai
	 * 
	 */
	private class OnViewClickListener implements OnClickListener {
		private UserInfoModel userInfoModel;
		private CommonResult commonResult;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_bt_submit:
				// 登录按钮
				// 登录

				break;
			default:
				break;
			}
		}
	}


}
