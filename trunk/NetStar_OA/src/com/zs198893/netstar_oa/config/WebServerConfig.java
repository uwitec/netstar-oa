package com.zs198893.netstar_oa.config;
/**
 * ��������ط�������
 * @author zhangshuai
 *
 */
public class WebServerConfig {
	/**
	 * ��ʱʱ��
	 */
	public static int timeout = 10*1000;
	/**
	 * ������ ͷ��������
	 */
	private static String serverHttp= "http://";
	/**
	 * ������ ͷ������
	 */
	private static String serverHttps= "https://";
	/**
	 * ������ip
	 */
	private static String serverIp= "115.28.22.128";
	/**
	 * ������ �˿�
	 */
	private static String serverPort= "80";
	/**
	 * ��Ŀ����
	 */
	private static String serverName = "OA";
	/**
	 * ��������ַ
	 */
	private static String serverHttpUrl = serverHttp + serverIp + ":" + serverPort + "/" + serverName + "/";
	/**
	 * ��¼�Ľӿ�
	 */
	private static String loginAction = "LoginValidation";
	
	/**
	 * ��¼�Ľӿڵ�ַ
	 */
	public static String loginActionURL = serverHttpUrl + loginAction + "/";
	
	/**
	 * ��ʼ�� OA��Ŀ��ַ
	 * @param isHttp true Ϊ http�� false Ϊ https
	 */
	public static void initOAHttpUrl(boolean isHttp){
		serverHttpUrl = isHttp?serverHttp + serverIp + ":" + serverPort + "/" + serverName + "/":serverHttps + serverIp + ":" + serverPort + "/" + serverName + "/";
	}
}
