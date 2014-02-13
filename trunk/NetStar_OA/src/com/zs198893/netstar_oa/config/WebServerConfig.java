package com.zs198893.netstar_oa.config;
/**
 * 服务器相关服务配置
 * @author zhangshuai
 *
 */
public class WebServerConfig {
	/**
	 * 超时时间
	 */
	public static int timeout = 10*1000;
	/**
	 * 在 sharedpreference 中 cookie
	 */
	public static String cookieName = "Set-Cookie";
	/**
	 * 服务器 头，不加密
	 */
	private static String serverHttp= "http://";
	/**
	 * 服务器 头，加密
	 */
	private static String serverHttps= "https://";
	/**
	 * 服务器ip
	 */
	private static String serverIp= "115.28.22.128";
	/**
	 * 服务器 端口
	 */
	private static String serverPort= "8080";
	/**
	 * 项目名称
	 */
	private static String serverName = "OA";
	/**
	 * 服务器地址
	 */
	private static String serverHttpUrl = serverHttp + serverIp + ":" + serverPort + "/" + serverName + "/";
	/**
	 * 登录的接口
	 */
	public static String loginAction = "LoginValidation";
	/**
	 * 查询日志列表接口 LogQuery/search_Content.json
	 */
	public static String dailyLogQueryListAction = "LogQuery/advancedquery.json";
	/**
	 * 查询日志详细 
	 */
	public static String dailyLogQueryContentAction = "LogQuery/search_Content.json";
	/**
	 * 编辑的时候产看日志详细
	 */
	public static String dailyLogEditQueryContentAction = "LogInput/selectDay.json";
	/**
	 * 编辑 提交日志
	 */
	public static String dailyLogEditContentAction = "LogInput/editlog.json";
	
	/**
	 * 获得地址
	 */
	public static String getUrl(String action){
		return serverHttpUrl + action;
	}
	/**
	 * 初始化 OA项目地址
	 * @param isHttp true 为 http， false 为 https
	 */
	public static void initOAHttpUrl(boolean isHttp){
		serverHttpUrl = isHttp?serverHttp + serverIp + ":" + serverPort + "/" + serverName + "/":serverHttps + serverIp + ":" + serverPort + "/" + serverName + "/";
	}
	/**
	 * 登录参数对应的key
	 * @author zhangshuai
	 *
	 */
	public static class LoginRequestParamKeys{
		/**
		 * 用户登录名
		 */
		public final static String LOGIN_NAME = "";
		/**
		 * 用户登录密码
		 */
		public final static String LOGIN_PWD = "";
	}
}
