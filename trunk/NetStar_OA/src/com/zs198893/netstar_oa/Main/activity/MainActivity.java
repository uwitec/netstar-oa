package com.zs198893.netstar_oa.Main.activity;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.daily_log.activity.LogInputActivity;
import com.zs198893.netstar_oa.daily_log.activity.LogQueryActivity;

public class MainActivity extends BaseActivity {
	private String[] fName = new String[]{"���۹���","�������","�ۺϹ���","��־¼��","��־��ѯ"};
	/**
	 * ���ܱ�񲼾�
	 */
	@InjectView(R.id.main_activity_gv) GridView main_activity_gv;

	@Override
	public void subInitView() {
		setContentView(R.layout.main_layout);

	}

	@Override
	public void subInitParam() {
		main_activity_gv.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.main_grid_item, R.id.main_activity_tv_function_name, fName));
        actionBar.setTitle("��ҳ��");
	}

	@Override
	public void subSetOnclick() {
		main_activity_gv.setOnItemClickListener(new OnItemClickListener() {
			Intent intent;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				intent = new Intent();
				if(arg2==3){
					intent.setClass(MainActivity.this, LogInputActivity.class);
				}else if(arg2==4){
					intent.setClass(MainActivity.this, LogQueryActivity.class);
				}else{
					Toast.makeText(MainActivity.this, "������δ����", Toast.LENGTH_SHORT).show();
					return ;
				}
				startActivity(intent);
			}
		});
	}

	@Override
	public void subRunSomeThing() {
		// TODO Auto-generated method stub

	}
}
