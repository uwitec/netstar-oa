package com.zs198893.netstar_oa.model;
/**
 * ͨ�ý��������
 * @author zhangshuai
 *
 */
public class CommonResult {
	/**
	 * �Ƿ�ɹ�
	 */
	private boolean isSuccess;
	/**
	 * �������Ϣ���п�����string ���߱��
	 */
	private Object result;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
