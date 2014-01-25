package com.zs198893.netstar_oa.daily_log.engine;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.Header;

import android.text.TextUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.daily_log.activity.LogInputActivity;
import com.zs198893.netstar_oa.daily_log.model.DailyLogEditQueryModel;
import com.zs198893.netstar_oa.daily_log.model.DailyLogSubmitResponseModel;
import com.zs198893.netstar_oa.model.CommonResult;

public class LogInputEngine {
	private Format f = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar c = Calendar.getInstance();
	private LogInputActivity logInputActivity;
	private CommonResult commonResult;
	private RequestParams params;
	public LogInputEngine(LogInputActivity logInputActivity) {
		super();
		this.logInputActivity = logInputActivity;
	}

	/**
	 * 获得日志内容
	 * 
	 * @return
	 */
	public CommonResult getLogContent(int num) {
		commonResult = new CommonResult();
		commonResult.setSuccess(false);
		commonResult.setResult("未知错误");
		params = new RequestParams();
		params.add("num", num + "");
		((AppContext) logInputActivity.getApplicationContext()).asyncHttpClient
				.post(WebServerConfig
						.getUrl(WebServerConfig.dailyLogEditQueryContentAction),
						params,
						new BaseJsonHttpResponseHandler<DailyLogEditQueryModel>() {

							@Override
							public void onFinish() {
								super.onFinish();
							}

							@Override
							public void onStart() {
								commonResult = new CommonResult();
								super.onStart();
							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									Throwable arg2, String arg3,
									DailyLogEditQueryModel arg4) {
								commonResult.setSuccess(false);
								if (arg2 != null
										&& arg2.getCause() != null
										&& arg2.getCause()
												.getLocalizedMessage() != null) {
									commonResult.setResult("数据获取失败："
											+ arg2.getCause().getMessage());
								} else {
									commonResult.setResult("数据获取失败");
								}
								logInputActivity
										.onGetContentFinish(commonResult);
							}

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									String arg2, DailyLogEditQueryModel arg3) {
								commonResult.setSuccess(true);
								commonResult.setResult(arg3);
								logInputActivity
										.onGetContentFinish(commonResult);
							}

							@Override
							protected DailyLogEditQueryModel parseResponse(
									String arg0) throws Throwable {
								return new ObjectMapper().readValues(
										new JsonFactory().createParser(arg0),
										DailyLogEditQueryModel.class).next();
							}
						});
		return commonResult;
	}

	/**
	 * 提交日志
	 * @param dailyLogModel 
	 * 
	 * @return
	 */
	public CommonResult submitLogContent() {
		commonResult = new CommonResult();
		commonResult.setSuccess(false);
		commonResult.setResult("未知错误");
		params = new RequestParams();
		params.add("log_Content", logInputActivity.daily_log_input_et_content.getText().toString());
		params.add("num", logInputActivity.daily_log_input_sp_date.getSelectedItemPosition()+"");
		if(logInputActivity.dailyLogModel==null||TextUtils.isEmpty(logInputActivity.dailyLogModel.getLog_Date())){
			params.add("create_Date", f.format(c.getTime()));
		}else{
			params.add("update_Date", f.format(c.getTime()));
		}
		((AppContext) logInputActivity.getApplicationContext()).asyncHttpClient
		.post(WebServerConfig
				.getUrl(WebServerConfig.dailyLogEditContentAction),
				params,
				new BaseJsonHttpResponseHandler<DailyLogSubmitResponseModel>() {

					@Override
					public void onFinish() {
						super.onFinish();
					}

					@Override
					public void onStart() {
						commonResult = new CommonResult();
						super.onStart();
					}

					@Override
					public void onFailure(int arg0, Header[] arg1,
							Throwable arg2, String arg3,
							DailyLogSubmitResponseModel arg4) {
						commonResult.setSuccess(false);
						if(arg2!=null&&arg2.getCause()!=null&&arg2.getCause().getLocalizedMessage()!=null){
							commonResult.setResult("提交失败："
									+ arg2.getCause().getMessage());
						}else{
							commonResult.setResult("提交失败");
						}
						logInputActivity.onSubmitFinish(commonResult);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1,
							String arg2, DailyLogSubmitResponseModel arg3) {
						if(arg3.isSuccess()){
							commonResult.setSuccess(true);
							commonResult.setResult("提交成功");
						}else{
							commonResult.setSuccess(false);
							commonResult.setResult("提交失败");
						}
						logInputActivity.onSubmitFinish(commonResult);
					}

					@Override
					protected DailyLogSubmitResponseModel parseResponse(
							String arg0) throws Throwable {
						return new ObjectMapper().readValues(
								new JsonFactory().createParser(arg0),
								DailyLogSubmitResponseModel.class).next();
					}
				});
		return commonResult;
	}
}
