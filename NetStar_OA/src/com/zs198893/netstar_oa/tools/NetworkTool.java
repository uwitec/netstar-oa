package com.zs198893.netstar_oa.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * ���繤����
 * 
 * @author zhangshuai
 * 
 */
public class NetworkTool {

	/**
	 * ���������Ƿ���á���ҪȨ�ޣ�
	 * <p>
	 * <b> < uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE" /> </b>
	 * </p>
	 * 
	 * @param context
	 *            ������
	 * @return ��������򷵻�true�����򷵻�false
	 */
	public static boolean isAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return info != null && info.isAvailable();
	}

	/**
	 * ����Wifi�Ƿ�����
	 * 
	 * @param context
	 *            ������
	 * @return Wifi��������򷵻�true�����򷵻�false
	 */
	public static boolean isWIFIActivate(Context context) {
		return ((WifiManager) context.getSystemService(Context.WIFI_SERVICE))
				.isWifiEnabled();
	}

	/**
	 * �޸�Wifi״̬
	 * 
	 * @param context
	 *            ������
	 * @param status
	 *            trueΪ����Wifi��falseΪ�ر�Wifi
	 */
	public static void changeWIFIStatus(Context context, boolean status) {
		((WifiManager) context.getSystemService(Context.WIFI_SERVICE))
				.setWifiEnabled(status);
	}
}
