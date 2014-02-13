package com.zs198893.netstar_oa.daily_log.activity;

import roboguice.inject.InjectView;
import android.R.anim;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.config.DefaultConfig;
import com.zs198893.netstar_oa.daily_log.engine.LogQueryEngine;
import com.zs198893.netstar_oa.daily_log.engine.LogQueryEngine.QueryParamModel;
import com.zs198893.netstar_oa.daily_log.model.DailyLogQueryListModel;
import com.zs198893.netstar_oa.daily_log.model.DailyLogTitleModel;
import com.zs198893.netstar_oa.model.CommonResult;

public class LogQueryActivity extends BaseActivity {
	private LogQueryEngine logQueryEngine;
	/**
	 * 当前的点击位置
	 */
	private int currentSelectPosition = -1;
	/**
	 * 点击监听器
	 */
	private OnViewClickListener onViewClickListener;
	/**
	 * 时间列表适配器
	 */
	private LogDateListAdapter logDateListAdapter;
	/**
	 * 返回的列表数据
	 */
	private DailyLogQueryListModel dailyLogQueryListModel;
	/**
	 * 部门
	 */
	@InjectView(R.id.daily_log_query_et_type_department)
	EditText daily_log_query_et_type_department;
	/**
	 * 创建人
	 */
	@InjectView(R.id.daily_log_query_et_type_owner)
	EditText daily_log_query_et_type_owner;
	/**
	 * 开始日期
	 */
	@InjectView(R.id.daily_log_query_et_type_date1)
	EditText daily_log_query_et_type_date1;
	/**
	 * 结束日期
	 */
	@InjectView(R.id.daily_log_query_et_type_date2)
	EditText daily_log_query_et_type_date2;
	/**
	 * 查询按钮
	 */
	@InjectView(R.id.daily_log_query_bt_query)
	Button daily_log_query_bt_query;
	/**
	 * 下一页
	 */
	@InjectView(R.id.daily_log_query_bt_nextpage)
	Button daily_log_query_bt_nextpage;
	/**
	 * 上一页
	 */
	@InjectView(R.id.daily_log_query_bt_lastpage)
	Button daily_log_query_bt_lastpage;
	/**
	 * 页数
	 */
	@InjectView(R.id.daily_log_query_et_page)
	EditText daily_log_query_et_page;
	/**
	 * 跳页
	 */
	@InjectView(R.id.daily_log_query_bt_jump)
	Button daily_log_query_bt_jump;
	/**
	 * 分页信息
	 */
	@InjectView(R.id.daily_log_query_tv_page_info)
	TextView daily_log_query_tv_page_info;
	/**
	 * 日期列表
	 */
	@InjectView(R.id.daily_log_query_lv_date_name)
	ListView daily_log_query_lv_date_name;
	/**
	 * 日志内容
	 */
	@InjectView(R.id.daily_log_query_wb_conent)
	WebView daily_log_query_wb_conent;

	@Override
	public void subInitView() {
		setContentView(R.layout.daily_log_query_activity);
	}

	@Override
	public void subInitParam() {
		daily_log_query_wb_conent.getSettings().setDefaultTextEncodingName("UTF -8") ;
		currentSelectPosition = -1;
		logQueryEngine = new LogQueryEngine(this);
		// 初始化查询参数
		logQueryEngine.initQueryParam();
		// 点击监听器
		onViewClickListener = new OnViewClickListener();
		// 初始化参数
		initQueryParam();
		// 列表适配器
		logDateListAdapter = new LogDateListAdapter();
		daily_log_query_lv_date_name.setAdapter(logDateListAdapter);
		daily_log_query_lv_date_name
				.setOnItemClickListener(new OnItemClickListener() {
					CommonResult commonResult;

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						currentSelectPosition = arg2;
						logDateListAdapter.notifyDataSetChanged();
						commonResult = logQueryEngine
								.getLogContent(((DailyLogTitleModel) (logDateListAdapter
										.getItem(arg2))).getId() + "");
					}
				});
	}

	/**
	 * 显示日志内容
	 * 
	 * @param commonResult
	 */
	public void showLogContent(CommonResult commonResult) {
		daily_log_query_wb_conent.loadData(DefaultConfig.DailyLogContentHead
				+ (String) commonResult.getResult()
				+ DefaultConfig.DailyLogContentFoot, "text/html; charset=UTF-8", null);
	}

	@Override
	public void subSetOnclick() {
		// 开始日期
		daily_log_query_et_type_date1.setOnClickListener(onViewClickListener);
		// 结束日期
		daily_log_query_et_type_date2.setOnClickListener(onViewClickListener);
		// 查询按钮
		daily_log_query_bt_query.setOnClickListener(onViewClickListener);
		// 下一页
		daily_log_query_bt_nextpage.setOnClickListener(onViewClickListener);
		// 上一页
		daily_log_query_bt_lastpage.setOnClickListener(onViewClickListener);
		// 页数
		daily_log_query_et_page.setOnClickListener(onViewClickListener);
		// 跳页
		daily_log_query_bt_jump.setOnClickListener(onViewClickListener);
	}

	@Override
	public void subRunSomeThing() {
		// TODO Auto-generated method stub

	}

	private class OnViewClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 开始日期
			case R.id.daily_log_query_et_type_date1:
				logQueryEngine.createDialog(daily_log_query_et_type_date1)
						.show();
				break;
			// 结束日期
			case R.id.daily_log_query_et_type_date2:
				logQueryEngine.createDialog(daily_log_query_et_type_date2)
						.show();
				break;
			// 查询按钮
			case R.id.daily_log_query_bt_query:
				// 拼装数据
				QueryParamModel.departmengt = daily_log_query_et_type_department
						.getText().toString().trim();
				QueryParamModel.owner = daily_log_query_et_type_owner.getText()
						.toString().trim();
				QueryParamModel.date1 = daily_log_query_et_type_date1.getText()
						.toString().trim();
				QueryParamModel.date2 = daily_log_query_et_type_date2.getText()
						.toString().trim();
				QueryParamModel.pageIndex = 1;
				logQueryEngine.getDateNameList();
				break;
			// 下一页
			case R.id.daily_log_query_bt_nextpage:
				checkPageIndex(QueryParamModel.pageIndex+1);
				break;
			// 上一页
			case R.id.daily_log_query_bt_lastpage:
				checkPageIndex(QueryParamModel.pageIndex-1);
				break;
			// 页数
			case R.id.daily_log_query_et_page:
				break;
			// 跳页
			case R.id.daily_log_query_bt_jump:
				int index = 1;
				try{
					index = Integer.parseInt(daily_log_query_et_page.getText().toString().trim());
					checkPageIndex(index);
				}catch(Exception e){
					daily_log_query_et_page.setText("");
					Toast.makeText(LogQueryActivity.this, "页数格式错误", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		}
	}
	public void checkPageIndex(int pageIndex){
		String msg = "";
		do{
			if(dailyLogQueryListModel==null){
				msg = "请先查询数据";
				break;
			}
			if(dailyLogQueryListModel.getTotal()<1){
				msg = "数据为空";
				break;
			}
			if(pageIndex>dailyLogQueryListModel.getTotal()){
				msg = "不能超过最后一页";
				break;
			}
			if(pageIndex<1){
				msg = "不能小于第一页";
				break;
			}
		}while(false);
		if(TextUtils.isEmpty(msg)){
			QueryParamModel.pageIndex = pageIndex;
			logQueryEngine.getDateNameList();
		}else{
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * 初始化查询参数
	 */
	private void initQueryParam() {

	}

	/**
	 * 当数据获的完毕
	 */
	public void onLogListGetFinish(CommonResult commonResult) {
		if (commonResult.isSuccess()) {
			dailyLogQueryListModel = (DailyLogQueryListModel) commonResult
					.getResult();
			if(dailyLogQueryListModel==null){
				daily_log_query_tv_page_info.setText("0/0 页");
			}else{
				daily_log_query_tv_page_info.setText(QueryParamModel.pageIndex+"/"+dailyLogQueryListModel.getTotal()+" 页");
			}
			logDateListAdapter.notifyDataSetChanged();
		} else {
			Toast.makeText(this, (String) commonResult.getResult(),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 时间列表适配器
	 */
	private class LogDateListAdapter extends BaseAdapter {
		private Holder holder;
		private DailyLogTitleModel dailyLogTitleModel;

		@Override
		public int getCount() {
			int count = 0;
			if (dailyLogQueryListModel != null
					&& dailyLogQueryListModel.getDailyLogTitleModel() != null) {
				count = dailyLogQueryListModel.getDailyLogTitleModel().size();
			}
			return count;
		}

		@Override
		public Object getItem(int position) {
			return dailyLogQueryListModel.getDailyLogTitleModel().get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(LogQueryActivity.this,
						R.layout.daily_log_query_list_item, null);
				holder = new Holder();
				convertView.setTag(holder);
				holder.date = (TextView) convertView
						.findViewById(R.id.daily_log_query_tv_list_item_date);
				holder.owner = (TextView) convertView
						.findViewById(R.id.daily_log_query_tv_list_item_owner);
				holder.department = (TextView) convertView
						.findViewById(R.id.daily_log_query_tv_list_item_department);
			} else {
				holder = (Holder) convertView.getTag();
			}
			dailyLogTitleModel = (DailyLogTitleModel) getItem(position);
			holder.date.setText(dailyLogTitleModel.getCreate_Date());
			holder.owner.setText(dailyLogTitleModel.getUserName());
			holder.department.setText(dailyLogTitleModel.getDeptName());
			if (currentSelectPosition == position) {
				convertView.setBackgroundColor(getResources().getColor(
						R.color.list_selected));
			} else {
				convertView.setBackgroundColor(getResources().getColor(
						R.color.list_not_selected));
			}
			return convertView;
		}

		private class Holder {
			public TextView date;
			public TextView owner;
			public TextView department;
		}
	}
	@Override
	public void subInitContentView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}
}
