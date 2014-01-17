package com.zs198893.netstar_oa;

import android.app.Application;
/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * @author zhangshuai
 *
 */
public class AppContext extends Application {
	/**
	 * 实例对象，采用单例模式
	 */
	public static AppContext appContext = new AppContext();

	
}
