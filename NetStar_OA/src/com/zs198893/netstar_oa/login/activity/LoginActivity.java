package com.zs198893.netstar_oa.login.activity;

import static android.text.TextUtils.isEmpty;

import org.apache.http.Header;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.login.engine.LoginEngine;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.DialogTool;
import com.zs198893.netstar_oa.tools.NetworkTool;
import com.zs198893.netstar_oa.tools.WaittingAlertDialog;

/**
 * OA 系统办公系统 登陆界面
 * 
 * @author zhangshuai
 * 
 */
public class LoginActivity extends BaseActivity {
	/**
	 * 配置按钮
	 */
	@InjectView(R.id.login_bt_config)
	Button bt_login_config;
	/**
	 * 用户登录名称输入框
	 */
	@InjectView(R.id.login_et_loging_name)
	EditText et_login_name;
	/**
	 * 用户密码输入框
	 */
	@InjectView(R.id.login_et_loging_pwd)
	EditText et_login_pwd;
	/**
	 * 登录按钮
	 */
	@InjectView(R.id.login_bt_submit)
	Button bt_login_submit;
	/**
	 * 重置按钮
	 */
	@InjectView(R.id.login_bt_reset)
	Button bt_login_reset;
	/**
	 * 登录的业务逻辑类
	 */
	private LoginEngine loginEngine;
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
	private CommonResult commonResult;

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
	 * 
	 * @author zhangshuai
	 * 
	 */
	private class OnLoginPageClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_bt_submit:
				// 登录线程
				login(et_login_name.getText().toString().trim(), et_login_pwd
						.getText().toString().trim());
				break;
			case R.id.login_bt_reset:
				et_login_name.setText("");
				et_login_pwd.setText("");
				break;
			}
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param acount
	 * @param pwd
	 */
	private void login(String acount, String pwd) {
		commonResult = new CommonResult();
		do {
			// 检查网络
			if (!NetworkTool.isAvailable(this)) {
				commonResult.setResult("网络不可用");
				break;
			}
			// 判断用户名帐号
			if (isEmpty(acount) || TextUtils.isEmpty(pwd)) {
				commonResult.setResult("用户名密码不得为空");
				break;
			}
		} while (false);
		RequestParams params = new RequestParams();
		params.put("username", acount);
		params.put("password", pwd);
		params.put("login", "1");
		//清理cookie
		((AppContext)getApplication()).clearCookie();
		//访问网络
		((AppContext)getApplication()).asyncHttpClient.post(WebServerConfig.getUrl(WebServerConfig.loginAction), params,
				new AsyncHttpResponseHandler() {
					
					@Override
					public void onFinish() {

						super.onFinish();
					}

					@Override
					public void onStart() {
						// 开启等待界面
						waittingAlertDialog = dialogTool.getWaittingDialog(-1,
								"登陆中，请稍候。。。");
						waittingAlertDialog.show();
						commonResult = new CommonResult();
						super.onStart();
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
						commonResult.setSuccess(false);
						commonResult.setResult("登录失败");
						checkResult();
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						if(content.contains("修改密码")){
							commonResult.setSuccess(true);
							commonResult.setResult("登录成功");
						}else{
							commonResult.setSuccess(false);
							commonResult.setResult("登录失败");
						}
						checkResult();
					}
					public void checkResult(){
						waittingAlertDialog.dismiss();
						waittingAlertDialog = null;
						// 判断登录是否成功
						if (commonResult.isSuccess()) {
							// 成功，跳转界面
							Intent intent = new Intent(
									LoginActivity.this,
									com.zs198893.netstar_oa.Main.activity.MainActivity.class);
							startActivity(intent);
							finish();
						} else {
							// 失败，显示原因
							Toast.makeText(LoginActivity.this, (String)commonResult.getResult(),
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
