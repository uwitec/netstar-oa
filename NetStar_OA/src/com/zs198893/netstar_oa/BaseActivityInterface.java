package com.zs198893.netstar_oa;

import android.os.Bundle;

/**
 * 
 * @author zhangshuai
 *
 */
public interface BaseActivityInterface {
	/**
	 * 设置布局
	 */
	public void subInitContentView(Bundle savedInstanceState);
	/**
	 * 初始化view
	 */
	public void subInitView();

	/**
	 * 初始化参数
	 */
	public void subInitParam();

	/**
	 * 初始化 onClick
	 */
	public void subSetOnclick();

	/**
	 * 做一些动作
	 */
	public void subRunSomeThing();
}
