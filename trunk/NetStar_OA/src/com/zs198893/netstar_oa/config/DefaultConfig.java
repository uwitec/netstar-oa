package com.zs198893.netstar_oa.config;
/**
 * 基本配置
 * @author Administrator
 *
 */
public class DefaultConfig {
	/**
	 * sharedprefernce 配置文件的名称
	 */
	public static String DefaultConfigFileName = "DefaultConfigFile";
	/**
	 * 日志可以看几行
	 */
	public static String DailyLogShowRow = "20";
	/**
	 * log日志标签
	 */
	public static String DailyLogContentHead = "<html><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" ></head><body>";
	/**
	 * log日志标签
	 */
	public static String DailyLogContentFoot = "</body></htlm>";
	/**
	 * 日志录入的选择天数
	 */
	public static String[] DailyLogDateType = new String[]{"请选择","今天","昨天","前天"};
}
