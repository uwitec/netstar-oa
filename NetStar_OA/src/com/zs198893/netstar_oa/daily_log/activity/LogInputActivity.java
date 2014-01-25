package com.zs198893.netstar_oa.daily_log.activity;

import roboguice.inject.InjectView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.config.DefaultConfig;
import com.zs198893.netstar_oa.daily_log.engine.LogInputEngine;
import com.zs198893.netstar_oa.daily_log.model.DailyLogEditQueryModel;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.DialogTool;
import com.zs198893.netstar_oa.tools.WaittingAlertDialog;

public class LogInputActivity extends BaseActivity {
	/**
	 * 弹出框工具
	 */
	private DialogTool dialogTool;
	/**
	 * 等待框
	 */
	private WaittingAlertDialog waittingAlertDialog;
	private LogInputEngine logInputEngine;
	public DailyLogEditQueryModel.DailyLogModel dailyLogModel;
	/**
	 * 时间选择
	 */
	@InjectView(R.id.daily_log_input_sp_date)
	public Spinner daily_log_input_sp_date;
	/**
	 * 提交按钮
	 */
	@InjectView(R.id.daily_log_input_bt_submit)
	public Button daily_log_input_bt_submit;
	/**
	 * 内容录入
	 */
	@InjectView(R.id.daily_log_input_et_content)
	public EditText daily_log_input_et_content;
	@Override
	public void subInitView() {
		setContentView(R.layout.daily_log_input_activity);
	}

	@Override
	public void subInitParam() {
		dialogTool = new DialogTool(this);
		// 初始化等待界面
		waittingAlertDialog = dialogTool.getWaittingDialog(-1, "获取数据中，请稍候。。。");
		actionBar.setTitle("日志录入");
		logInputEngine = new LogInputEngine(this);
		daily_log_input_sp_date.setAdapter(new ArrayAdapter<String>(LogInputActivity.this, R.layout.daily_log_input_activity_datetype_item, R.id.daily_log_input_activity_tv_type, DefaultConfig.DailyLogDateType));
	}

	@Override
	public void subSetOnclick() {
		daily_log_input_sp_date.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(arg2!=0){
					waittingAlertDialog.show();
					dailyLogModel = null;
					logInputEngine.getLogContent(arg2);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		daily_log_input_bt_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(daily_log_input_sp_date.getSelectedItemPosition()!=0){
					waittingAlertDialog.show();
					logInputEngine.submitLogContent();
				}else{
					Toast.makeText(LogInputActivity.this, "请选择日期", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	/**
	 * 当接收数据陈功
	 */
	public void onGetContentFinish(CommonResult commonResult){
		if(commonResult!=null&&commonResult.isSuccess()){
			dailyLogModel = ((DailyLogEditQueryModel)(commonResult.getResult())).getDailyLogModel();
			daily_log_input_et_content.setText(((DailyLogEditQueryModel)(commonResult.getResult())).getDailyLogModel().getLog_Content());
		}else{
			Toast.makeText(this, commonResult.getResult()!=null?(String)commonResult.getResult():"未知错误", Toast.LENGTH_SHORT).show();
		}
		waittingAlertDialog.dismiss();
	}
	/**
	 * 当提交成功
	 */
	public void onSubmitFinish(CommonResult commonResult){
		Toast.makeText(this, commonResult.getResult()!=null?(String)commonResult.getResult():"未知错误", Toast.LENGTH_SHORT).show();
		waittingAlertDialog.dismiss();
	}
	@Override
	public void subRunSomeThing() {
		// TODO Auto-generated method stub

	}

}
