package com.zs198893.netstar_oa;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

/**
 * activity����
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
		// ���Activity����ջ
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// ����Activity&�Ӷ�ջ���Ƴ�
		AppManager.getAppManager().finishActivity(this);
	}
}
