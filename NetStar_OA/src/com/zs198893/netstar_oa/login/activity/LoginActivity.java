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
 * OA ϵͳ�칫ϵͳ
 * ��½����
 * @author zhangshuai
 *
 */
public class LoginActivity extends BaseActivity{
	/**
	 * ���ð�ť
	 */
	@InjectView(R.id.login_bt_config) Button bt_login_config;
	/**
	 * �û���¼���������
	 */
	@InjectView(R.id.login_et_loging_name) EditText et_login_name;
	/**
	 * �û����������
	 */
	@InjectView(R.id.login_et_loging_pwd) EditText et_login_pwd;
	/**
	 * ��¼��ť
	 */
	@InjectView(R.id.login_bt_submit) Button bt_login_submit;
	/**
	 * ���ð�ť
	 */
	@InjectView(R.id.login_bt_reset) Button bt_login_reset;
	/**
	 * ��¼��ҵ���߼���
	 */
	@Inject LoginEngine loginEngine;
	/**
	 * ��¼���߳�
	 */
	private LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
	/**
	 * ���������
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
	 * ҳ����������
	 * @author zhangshuai
	 *
	 */
	private class OnLoginPageClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.login_bt_submit:
				Toast.makeText(LoginActivity.this, "�ύ", Toast.LENGTH_SHORT).show();
				break;
			case R.id.login_bt_reset:
				Toast.makeText(LoginActivity.this, "���", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	/**
	 * ��¼���߳�
	 */
	private class LoginAsyncTask extends AsyncTask<String, String, CommonResult>{

		@Override
		protected CommonResult doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
