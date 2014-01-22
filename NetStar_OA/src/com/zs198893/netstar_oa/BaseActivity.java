package com.zs198893.netstar_oa;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.IntentAction;

/**
 * activity基类
 * 
 * @author zhangshuai
 * 
 */
public abstract class BaseActivity extends RoboActivity implements
		BaseActivityInterface {
    public ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		subInitView();
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		if(actionBar!=null){
	        actionBar.setHomeAction(new IntentAction(this, AppManager.getAppManager().createIntent(this,com.zs198893.netstar_oa.Main.activity.MainActivity.class), R.drawable.ic_title_home_default));
	        //actionBar.setDisplayHomeAsUpEnabled(true);
		}
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
