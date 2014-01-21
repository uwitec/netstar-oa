package com.zs198893.netstar_oa.login.activity;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.inject.Inject;
import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.Main.activity.MainActivity;
import com.zs198893.netstar_oa.login.engine.LoginEngine;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.DialogTool;
import com.zs198893.netstar_oa.tools.WaittingAlertDialog;
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
	private LoginEngine loginEngine;
	/**
	 * 登录子线程
	 */
	private LoginAsyncTask loginAsyncTask;
	/**
	 * 点击监听类
	 */
	private OnLoginPageClickListener onLoginPageClickListener = new OnLoginPageClickListener();
	/**
	 * 弹出框工具
	 */
	private DialogTool dialogTool;
	/**
	 * 等待框
	 */
	private WaittingAlertDialog waittingAlertDialog;

	@Override
	public void subInitView() {
		setContentView(R.layout.login_activity_main);
	}

	@Override
	public void subInitParam() {
		dialogTool = new DialogTool(this);
		loginEngine = new LoginEngine(this);
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
				//登录线程
				loginAsyncTask = new LoginAsyncTask();
				loginAsyncTask.execute("");
				break;
			case R.id.login_bt_reset:
				et_login_name.setText("");
				et_login_pwd.setText("");
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
			return loginEngine.authentication(et_login_name.getText().toString().trim(), et_login_pwd.getText().toString().trim());
		}

		@Override
		protected void onPostExecute(CommonResult result) {
			super.onPostExecute(result);
			waittingAlertDialog.dismiss();
			waittingAlertDialog = null;
			//判断登录是否成功
			if(result.isSuccess()){
				//成功，跳转界面
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}else{
				//失败，显示原因
				Toast.makeText(LoginActivity.this, (String)result.getResult(), Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onPreExecute() {
			//开启等待界面
			waittingAlertDialog = dialogTool.getWaittingDialog(-1, "登陆中，请稍候。。。");
			waittingAlertDialog.show();
			super.onPreExecute();
		}
		
	}
}
