package com.zs198893.netstar_oa.login.engine;

import org.springframework.web.client.RestTemplate;

import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.model.CommonResult;

/**
 * 登录业务逻辑类
 * @author zhangshuai
 *
 */
public class LoginEngine {
	/**
	 * 通用返回值对象
	 */
	private CommonResult commonResult;
	/**
	 * spring rest 请求模版
	 */
	RestTemplate restTemplate;
	/**
	 * 验证登录
	 * @param acount 用户帐号
	 * @param pwd 用户密码
	 * @return 通用返回值对象
	 */
	public CommonResult authentication(String acount, String pwd){
		restTemplate = new RestTemplate();
		return commonResult;
	}
}