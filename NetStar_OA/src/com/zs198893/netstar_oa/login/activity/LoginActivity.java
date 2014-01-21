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
	private LoginEngine loginEngine;
	/**
	 * ��¼���߳�
	 */
	private LoginAsyncTask loginAsyncTask;
	/**
	 * ���������
	 */
	private OnLoginPageClickListener onLoginPageClickListener = new OnLoginPageClickListener();
	/**
	 * �����򹤾�
	 */
	private DialogTool dialogTool;
	/**
	 * �ȴ���
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
	 * ҳ����������
	 * @author zhangshuai
	 *
	 */
	private class OnLoginPageClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.login_bt_submit:
				//��¼�߳�
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
	 * ��¼���߳�
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
			//�жϵ�¼�Ƿ�ɹ�
			if(result.isSuccess()){
				//�ɹ�����ת����
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}else{
				//ʧ�ܣ���ʾԭ��
				Toast.makeText(LoginActivity.this, (String)result.getResult(), Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onPreExecute() {
			//�����ȴ�����
			waittingAlertDialog = dialogTool.getWaittingDialog(-1, "��½�У����Ժ򡣡���");
			waittingAlertDialog.show();
			super.onPreExecute();
		}
		
	}
}
