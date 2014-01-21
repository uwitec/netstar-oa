package com.zs198893.netstar_oa.login.engine;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.text.TextUtils;

import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.NetworkTool;

/**
 * 登录业务逻辑类
 * @author zhangshuai
 *
 */
public class LoginEngine {
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * 通用返回值对象
	 */
	private CommonResult commonResult;
	/**
	 * spring rest 请求模版
	 */
	RestTemplate restTemplate;
	
	public LoginEngine(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 验证登录
	 * @param acount 用户帐号
	 * @param pwd 用户密码
	 * @return 通用返回值对象
	 */
	public CommonResult authentication(String acount, String pwd){
		commonResult = new CommonResult();
		commonResult.setSuccess(false);
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		do{
			//检查网络
			if(!NetworkTool.isAvailable(context)){
				commonResult.setResult("网络不可用");
				break;
			}
			//判断用户名帐号
			if(TextUtils.isEmpty(acount) || TextUtils.isEmpty(pwd)){
				commonResult.setResult("用户名密码不得为空");
				break;
			}
			try{
				commonResult.setResult(restTemplate.getForObject(WebServerConfig.loginActionURL, String.class));
			}catch(Exception e){
				e.printStackTrace();
				commonResult.setSuccess(false);
				commonResult.setResult("出现了错误："+e.getCause().getLocalizedMessage());
			}
			commonResult.setSuccess(true);
			commonResult.setResult("登录成功");
			return commonResult;
		}while(false);
		return commonResult;
	}
}