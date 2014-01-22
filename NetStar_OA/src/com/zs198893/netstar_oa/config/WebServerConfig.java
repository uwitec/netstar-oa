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
	private static String serverPort= "80";
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
	private static String loginAction = "LoginValidation";
	
	/**
	 * 登录的接口地址
	 */
	public static String loginActionURL = serverHttpUrl + loginAction + "/";
	
	/**
	 * 初始化 OA项目地址
	 * @param isHttp true 为 http， false 为 https
	 */
	public static void initOAHttpUrl(boolean isHttp){
		serverHttpUrl = isHttp?serverHttp + serverIp + ":" + serverPort + "/" + serverName + "/":serverHttps + serverIp + ":" + serverPort + "/" + serverName + "/";
	}
}
