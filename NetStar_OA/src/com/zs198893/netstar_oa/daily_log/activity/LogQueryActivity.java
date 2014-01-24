package com.zs198893.netstar_oa.daily_log.activity;

import roboguice.inject.InjectView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.daily_log.engine.LogQueryEngine;
import com.zs198893.netstar_oa.daily_log.model.DailyLogQueryListModel;
import com.zs198893.netstar_oa.daily_log.model.DailyLogTitleModel;
import com.zs198893.netstar_oa.model.CommonResult;

public class LogQueryActivity extends BaseActivity {
	private LogQueryEngine logQueryEngine;
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
	@InjectView(R.id.daily_log_query_page_info)
	TextView daily_log_query_page_info;
	/**
	 * 日期列表
	 */
	@InjectView(R.id.daily_log_query_lv_date_name)
	ListView daily_log_query_lv_date_name;
	/**
	 * 日志内容
	 */
	@InjectView(R.id.daily_log_query_tv_conent)
	TextView daily_log_query_tv_conent;

	@Override
	public void subInitView() {
		setContentView(R.layout.daily_log_query_activity);
	}

	@Override
	public void subInitParam() {
		actionBar.setTitle("日志查询");
		logQueryEngine = new LogQueryEngine(this);
		// 初始化查询参数
		logQueryEngine.initQueryParam();
		// 点击监听器
		onViewClickListener = new OnViewClickListener();
		//初始化参数
		initQueryParam();
		//列表适配器
		logDateListAdapter = new LogDateListAdapter();
		daily_log_query_lv_date_name.setAdapter(logDateListAdapter);
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
				break;
			// 结束日期
			case R.id.daily_log_query_et_type_date2:
				break;
			// 查询按钮
			case R.id.daily_log_query_bt_query:
				logQueryEngine.getDateNameList();
				break;
			// 下一页
			case R.id.daily_log_query_bt_nextpage:
				break;
			// 上一页
			case R.id.daily_log_query_bt_lastpage:
				break;
			// 页数
			case R.id.daily_log_query_et_page:
				break;
			// 跳页
			case R.id.daily_log_query_bt_jump:
				break;
			default:
				break;
			}
		}
	}
	/**
	 * 初始化查询参数
	 */
	private void initQueryParam(){
		
	}
	/**
	 * 当数据获的完毕
	 */
	public void onLogListGetFinish(CommonResult commonResult){
		if(commonResult.isSuccess()){
			dailyLogQueryListModel = (DailyLogQueryListModel) commonResult.getResult();
			logDateListAdapter.notifyDataSetChanged();
		}else{
			Toast.makeText(this,(String)commonResult.getResult(), Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * 时间列表适配器
	 */
	private class LogDateListAdapter extends BaseAdapter{
		private Holder holder ;
		private DailyLogTitleModel dailyLogTitleModel;
		@Override
		public int getCount() {
			int count = 0;
			if(dailyLogQueryListModel!=null&&dailyLogQueryListModel.getDailyLogTitleModel()!=null){
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
			if(convertView==null){
				convertView = View.inflate(LogQueryActivity.this, R.layout.daily_log_query_list_item, null);
				holder = new Holder();
				convertView.setTag(holder);
				holder.date = (TextView)convertView.findViewById(R.id.daily_log_query_tv_list_item_date);
				holder.owner = (TextView)convertView.findViewById(R.id.daily_log_query_tv_list_item_owner);
				holder.department = (TextView)convertView.findViewById(R.id.daily_log_query_tv_list_item_department);
			}else{
				holder = (Holder) convertView.getTag();
			}
			dailyLogTitleModel = (DailyLogTitleModel) getItem(position);
			holder.date.setText(dailyLogTitleModel.getCreate_Date());
			holder.owner.setText(dailyLogTitleModel.getUserName());
			holder.department.setText(dailyLogTitleModel.getDeptName());
			return convertView;
		}
		private  class Holder{
			public TextView date;
			public TextView owner;
			public TextView department;
		}
	}
}
