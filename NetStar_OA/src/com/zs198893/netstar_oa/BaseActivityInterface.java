package com.zs198893.netstar_oa;
/**
 * 
 * @author zhangshuai
 *
 */
public interface BaseActivityInterface {
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
