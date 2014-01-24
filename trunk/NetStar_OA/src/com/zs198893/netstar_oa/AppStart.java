package com.zs198893.netstar_oa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.login.activity.LoginActivity;

/**
 * 应用开启 进入界面
 * 
 * @author zhangshuai
 * 
 */
public class AppStart extends Activity implements BaseActivityInterface {
	protected void onCreate(Bundle savedInstanceState) {
		subInitView();
		subInitParam();
		subSetOnclick();
		subRunSomeThing();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void subInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void subInitParam() {
		//初始化配置
		WebServerConfig.initOAHttpUrl(true);
	}

	@Override
	public void subSetOnclick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void subRunSomeThing() {
		final View view = View.inflate(this, R.layout.start, null);
		setContentView(view);
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(500);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}

	/**
	 * 跳转到...
	 */
	private void redirectTo() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
