package com.zs198893.netstar_oa;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

/**
 * activity基类
 * 
 * @author zhangshuai
 * 
 */
public abstract class BaseActivity extends RoboActivity implements
		BaseActivityInterface {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		subInitView();
		subInitParam();
		subSetOnclick();
		subRunSomeThing();
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}
}
