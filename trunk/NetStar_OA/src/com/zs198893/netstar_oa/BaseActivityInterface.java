package com.zs198893.netstar_oa;
/**
 * 
 * @author zhangshuai
 *
 */
public interface BaseActivityInterface {
	/**
	 * ��ʼ��view
	 */
	public void subInitView();

	/**
	 * ��ʼ������
	 */
	public void subInitParam();

	/**
	 * ��ʼ�� onClick
	 */
	public void subSetOnclick();

	/**
	 * ��һЩ����
	 */
	public void subRunSomeThing();
}
