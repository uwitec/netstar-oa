package com.zs198893.netstar_oa.config;

import com.zs198893.netstar_oa.login.model.LoginResponseJsonModel;

/**
 * 基本配置
 * @author Administrator
 *
 */
public class DefaultConfig {
	/**
	 * 用户对象模型
	 */
	public static LoginResponseJsonModel loginResponseJsonModel = new LoginResponseJsonModel();
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
