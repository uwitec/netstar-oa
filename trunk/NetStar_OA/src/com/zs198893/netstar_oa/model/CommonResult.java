package com.zs198893.netstar_oa.model;
/**
 * 通用结果返回类
 * @author zhangshuai
 *
 */
public class CommonResult {
	/**
	 * 是否成功
	 */
	private boolean isSuccess;
	/**
	 * 具体的信息，有可能是string 或者别的
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
