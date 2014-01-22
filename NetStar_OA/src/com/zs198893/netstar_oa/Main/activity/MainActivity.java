package com.zs198893.netstar_oa.Main.activity;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;

public class MainActivity extends BaseActivity {
	private String[] fName = new String[]{"销售管理"};
	/**
	 * 功能表格布局
	 */
	@InjectView(R.id.main_activity_gv) GridView main_activity_gv;

	@Override
	public void subInitView() {
		setContentView(R.layout.main_layout);

	}

	@Override
	public void subInitParam() {
		main_activity_gv.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.main_grid_item, R.id.main_activity_tv_function_name, fName));
        actionBar.setTitle("主页面");
	}

	@Override
	public void subSetOnclick() {
		main_activity_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2==1){
					
				}else{
					Toast.makeText(MainActivity.this, "功能尚未开发", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void subRunSomeThing() {
		// TODO Auto-generated method stub

	}
}
