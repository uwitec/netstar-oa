package com.zs198893.netstar_oa.login.activity;

import roboguice.inject.InjectView;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.inject.Inject;
import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.login.engine.LoginEngine;
import com.zs198893.netstar_oa.model.CommonResult;
/**
 * OA 系统办公系统
 * 登陆界面
 * @author zhangshuai
 *
 */
public class LoginActivity extends BaseActivity{
	/**
	 * 配置按钮
	 */
	@InjectView(R.id.login_bt_config) Button bt_login_config;
	/**
	 * 用户登录名称输入框
	 */
	@InjectView(R.id.login_et_loging_name) EditText et_login_name;
	/**
	 * 用户密码输入框
	 */
	@InjectView(R.id.login_et_loging_pwd) EditText et_login_pwd;
	/**
	 * 登录按钮
	 */
	@InjectView(R.id.login_bt_submit) Button bt_login_submit;
	/**
	 * 重置按钮
	 */
	@InjectView(R.id.login_bt_reset) Button bt_login_reset;
	/**
	 * 登录的业务逻辑类
	 */
	@Inject LoginEngine loginEngine;
	/**
	 * 登录子线程
	 */
	private LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
	/**
	 * 点击监听类
	 */
	private OnLoginPageClickListener onLoginPageClickListener = new OnLoginPageClickListener();

	@Override
	public void subInitView() {
		setContentView(R.layout.login_activity_main);
	}

	@Override
	public void subInitParam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subSetOnclick() {
		bt_login_submit.setOnClickListener(onLoginPageClickListener);
		bt_login_reset.setOnClickListener(onLoginPageClickListener);
	}

	@Override
	public void subRunSomeThing() {
		
	}
	/**
	 * 页面点击操作类
	 * @author zhangshuai
	 *
	 */
	private class OnLoginPageClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.login_bt_submit:
				Toast.makeText(LoginActivity.this, "提交", Toast.LENGTH_SHORT).show();
				break;
			case R.id.login_bt_reset:
				Toast.makeText(LoginActivity.this, "清空", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	/**
	 * 登录子线程
	 */
	private class LoginAsyncTask extends AsyncTask<String, String, CommonResult>{

		@Override
		protected CommonResult doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
