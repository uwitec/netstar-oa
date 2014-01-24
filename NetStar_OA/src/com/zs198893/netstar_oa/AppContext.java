package com.zs198893.netstar_oa;

import android.app.Application;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.zs198893.netstar_oa.model.CommonResult;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * 
 * @author zhangshuai
 * 
 */
public class AppContext extends Application {
	public AsyncHttpClient asyncHttpClient  ;
	/**
	 * cookie
	 */
	public PersistentCookieStore asyncHttpClientCookieStore ;
	@Override
	public void onCreate() {
		Log.i("oa", "onCreate");
		asyncHttpClient = new AsyncHttpClient();
		asyncHttpClientCookieStore = new PersistentCookieStore(this);
		asyncHttpClient.setCookieStore(asyncHttpClientCookieStore);
		super.onCreate();
	}
	public void clearCookie(){
		asyncHttpClientCookieStore.clear();
	}
}
