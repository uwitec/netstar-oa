package com.zs198893.netstar_oa.daily_log.engine;

import java.util.Calendar;

import org.apache.http.Header;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.config.DefaultConfig;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.daily_log.activity.LogQueryActivity;
import com.zs198893.netstar_oa.daily_log.model.DailyLogQueryListModel;
import com.zs198893.netstar_oa.daily_log.model.DailyLogQueryContentModel;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.DialogTool;
import com.zs198893.netstar_oa.tools.WaittingAlertDialog;

public class LogQueryEngine {
	private LogQueryActivity logQueryActivity;
	private RequestParams listParams;
	private RequestParams contenttParams;
	/**
	 * 弹出框工具
	 */
	private DialogTool dialogTool;
	/**
	 * 等待框
	 */
	private WaittingAlertDialog waittingAlertDialog;
	/**
	 * 通用返回值对象
	 */
	private CommonResult commonResult;

	public LogQueryEngine(LogQueryActivity logQueryActivity) {
		super();
		listParams = new RequestParams();
		contenttParams = new RequestParams();
		listParams.put("rows", DefaultConfig.DailyLogShowRow);
		listParams.put("sord", "asc");
		initQueryParam();
		this.logQueryActivity = logQueryActivity;
		dialogTool = new DialogTool(logQueryActivity);
		// 初始化等待界面
		waittingAlertDialog = dialogTool.getWaittingDialog(-1, "获取数据中，请稍候。。。");
	}

	public void initQueryParam() {
		QueryParamModel.date1 = "";
		QueryParamModel.date2 = "";
		QueryParamModel.owner = "";
		QueryParamModel.departmengt = "";
		QueryParamModel.pageIndex = 0;
		setQueryParam();
	}

	public void setQueryParam() {
		listParams = new RequestParams();
		listParams.put("page", QueryParamModel.pageIndex + "");
		listParams.put("deptName", QueryParamModel.departmengt);
		listParams.put("userName", QueryParamModel.owner);
		listParams.put("beginDate", QueryParamModel.date1);
		listParams.put("endDate", QueryParamModel.date2);
	}

	/**
	 * 获得时间列表
	 */
	public void getDateNameList() {
		commonResult = new CommonResult();
		setQueryParam();
		((AppContext) logQueryActivity.getApplicationContext()).asyncHttpClient
				.post(WebServerConfig
						.getUrl(WebServerConfig.dailyLogQueryListAction),
						listParams,
						new BaseJsonHttpResponseHandler<DailyLogQueryListModel>() {

							@Override
							public void onFinish() {
								waittingAlertDialog.dismiss();
								super.onFinish();
							}

							@Override
							public void onStart() {
								commonResult = new CommonResult();
								waittingAlertDialog.show();
								super.onStart();
							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									Throwable arg2, String arg3,
									DailyLogQueryListModel arg4) {
								commonResult.setSuccess(false);
								if(arg2!=null&&arg2.getCause()!=null&&arg2.getCause().getLocalizedMessage()!=null){
									commonResult.setResult("数据获取失败："
											+ arg2.getCause().getMessage());
								}else{
									commonResult.setResult("数据获取失败");
								}
								logQueryActivity
										.onLogListGetFinish(commonResult);
							}

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									String arg2, DailyLogQueryListModel arg3) {
								commonResult.setSuccess(true);
								commonResult.setResult(arg3);
								logQueryActivity
										.onLogListGetFinish(commonResult);
							}

							@Override
							protected DailyLogQueryListModel parseResponse(
									String arg0) throws Throwable {
								return new ObjectMapper().readValues(
										new JsonFactory().createParser(arg0),
										DailyLogQueryListModel.class).next();
							}
						});
	}

	/**
	 * 查询日志详细
	 * 
	 * @return
	 */
	public CommonResult getLogContent(String id) {
		contenttParams = new RequestParams();
		contenttParams.put("id", id);
		commonResult = new CommonResult();
		commonResult.setSuccess(false);
		commonResult.setResult("未知错误");
		((AppContext) logQueryActivity.getApplicationContext()).asyncHttpClient
				.post(WebServerConfig
						.getUrl(WebServerConfig.dailyLogQueryContentAction),
						contenttParams,
						new BaseJsonHttpResponseHandler<DailyLogQueryContentModel>() {

							@Override
							public void onFinish() {
								waittingAlertDialog.dismiss();
								super.onFinish();
							}

							@Override
							public void onStart() {
								commonResult = new CommonResult();
								waittingAlertDialog.show();
								super.onStart();
							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									Throwable arg2, String arg3,
									DailyLogQueryContentModel arg4) {
								commonResult.setSuccess(false);
								if(arg2!=null&&arg2.getCause()!=null&&arg2.getCause().getLocalizedMessage()!=null){
									commonResult.setResult("数据获取失败："
											+ arg2.getCause().getMessage());
								}else{
									commonResult.setResult("数据获取失败");
								}
								logQueryActivity.showLogContent(commonResult);
							}

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									String arg2, DailyLogQueryContentModel arg3) {
								commonResult.setSuccess(true);
								if (arg3 != null
										&& arg3.getLogQueryModel() != null
										&& arg3.getLogQueryModel().get(0) != null
										&& !TextUtils.isEmpty(arg3
												.getLogQueryModel().get(0)
												.getLog_Content())) {
									commonResult.setResult(arg3
											.getLogQueryModel().get(0)
											.getLog_Content());
								} else {
									commonResult.setResult("数据为空");
								}
								logQueryActivity.showLogContent(commonResult);
							}

							@Override
							protected DailyLogQueryContentModel parseResponse(
									String arg0) throws Throwable {
								return new ObjectMapper().readValues(
										new JsonFactory().createParser(arg0),
										DailyLogQueryContentModel.class).next();
							}
						});
		return commonResult;
	}

	/**
	 * 查询参数对象
	 * 
	 * @author zhangshuai
	 * 
	 */
	public static class QueryParamModel {
		/**
		 * 部门
		 */
		public static String departmengt;
		/**
		 * 创建人
		 */
		public static String owner;
		/**
		 * 开始时间
		 */
		public static String date1;
		/**
		 * 结束时间
		 */
		public static String date2;
		/**
		 * 第几页
		 */
		public static int pageIndex;
	}

	public Dialog createDialog(final TextView textView) {
		// 用来获取日期和时间的
		Calendar calendar = Calendar.getInstance();
		Dialog dialog = null;
		DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datePicker, int year, int month,
					int dayOfMonth) {
				// Calendar月份是从0开始,所以month要加1
				textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
			}
		};
		dialog = new DatePickerDialog(logQueryActivity, dateListener,
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return dialog;
	}
}
