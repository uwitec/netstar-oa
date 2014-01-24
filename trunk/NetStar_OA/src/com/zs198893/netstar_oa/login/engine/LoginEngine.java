package com.zs198893.netstar_oa.login.engine;

import static android.text.TextUtils.isEmpty;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.HttpUtility;
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
	HttpURLConnection httpConn;
	HttpUtility httpUtility;
	
	public LoginEngine(Context context) {
		super();
		this.context = context;
		httpUtility = HttpUtility.getInstance(context);
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
		do{
			//检查网络
			if(!NetworkTool.isAvailable(context)){
				commonResult.setResult("网络不可用");
				break;
			}
			//判断用户名帐号
			if(isEmpty(acount) || TextUtils.isEmpty(pwd)){
				commonResult.setResult("用户名密码不得为空");
				break;
			}
			Map<String,String> param = new HashMap<String, String>();
			param.put("username", acount);
			param.put("password", pwd);
			RequestParams params = new RequestParams();
			params.put("username", acount);
			params.put("password", pwd);
			try{
				AsyncHttpClient client = new AsyncHttpClient();
				client.post("dsfsd"+WebServerConfig.getUrl(WebServerConfig.dailyLogQueryListAction), params,new AsyncHttpResponseHandler() {
					int statusCode = 0;
					@Override
					public void onFinish() {
						Log.i("shuai", "onFinish  " + statusCode);
						super.onFinish();
					}

					@Override
					public void onStart() {
						Log.i("shuai", "onStart  " + statusCode);
						super.onStart();
					}
				    @Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
						// TODO Auto-generated method stub
				    	Log.i("shuai", "onFailure  "+statusCode);
					}
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						Log.i("shuai", "onSuccess  "+statusCode);
					}
				});
			}catch(Exception e){
				e.printStackTrace();
				commonResult.setSuccess(false);
				try{
					commonResult.setResult("出现了错误："+e.getCause().getLocalizedMessage());
				}catch(Exception e0){
					commonResult.setResult("未知错误");
				}
			}
			return commonResult;
		}while(false);
		return commonResult;
	}
	private class Mofgsd extends  AsyncHttpResponseHandler {


		
	}
}