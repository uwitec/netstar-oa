package com.zs198893.netstar_oa.tools;

import android.content.Context;
import android.text.TextUtils;

import com.zs198893.netstar_oa.R;

public class DialogTool {
	private Context context;
	/**
	 * 等待类型
	 */
	public final static int TYPE_WaittingAlertDialog = 0x0;

	public DialogTool(Context context) {
		super();
		this.context = context;
	}
	/**
	 * 获得通用等待狂
	 * @param layoutResource
	 * @param message
	 * @return
	 */
	public WaittingAlertDialog getWaittingDialog(int layoutResource, String message){
		WaittingAlertDialog waittingAlertDialog;
		if(layoutResource<0){
			layoutResource = R.layout.common_dialog_wait_layout;
		}
		if(TextUtils.isEmpty(message)){
			message = "操作中。。。";
		}
		waittingAlertDialog =  new WaittingAlertDialog(context, layoutResource, message);
		//取消背景
		waittingAlertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		// 设置点击屏幕Dialog不消失
		waittingAlertDialog.setCancelable(false);
		return waittingAlertDialog;
	}
}
