package com.zs198893.netstar_oa.daily_log.engine;

import org.apache.http.Header;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.config.DefaultConfig;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.daily_log.activity.LogQueryActivity;
import com.zs198893.netstar_oa.daily_log.model.DailyLogQueryListModel;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.DialogTool;
import com.zs198893.netstar_oa.tools.WaittingAlertDialog;

public class LogQueryEngine {
	private LogQueryActivity logQueryActivity;
	private RequestParams params;
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
		params = new RequestParams();
		params.put("rows", DefaultConfig.DailyLogShowRow);
		params.put("sord", "asc");
		initQueryParam();
		this.logQueryActivity = logQueryActivity;
		dialogTool = new DialogTool(logQueryActivity);
		// 初始化等待界面
		waittingAlertDialog = dialogTool.getWaittingDialog(-1,
				"获取数据中，请稍候。。。");
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
		params = new RequestParams();
		params.put("page", QueryParamModel.pageIndex + "");
		params.put("deptName", QueryParamModel.departmengt);
		params.put("userName", QueryParamModel.owner);
		params.put("beginDate", QueryParamModel.date1);
		params.put("endDate", QueryParamModel.date2);
	}

	/**
	 * 获得时间列表
	 */
	public void getDateNameList() {
		((AppContext) logQueryActivity.getApplicationContext()).asyncHttpClient.post(
				WebServerConfig.getUrl(WebServerConfig.dailyLogQueryListAction), params,
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
						commonResult.setResult("数据获取失败："+arg2.getCause().getMessage());
						logQueryActivity.onLogListGetFinish(commonResult);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2,
							DailyLogQueryListModel arg3) {
						commonResult.setSuccess(true);
						commonResult.setResult(arg3);
						logQueryActivity.onLogListGetFinish(commonResult);
					}

					@Override
					protected DailyLogQueryListModel parseResponse(String arg0)
							throws Throwable {
						return new ObjectMapper().readValues(new JsonFactory().createParser(arg0), DailyLogQueryListModel.class).next();
					}
				});
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
}
