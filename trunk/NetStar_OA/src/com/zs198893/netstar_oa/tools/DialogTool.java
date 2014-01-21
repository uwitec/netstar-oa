package com.zs198893.netstar_oa.tools;

import android.content.Context;
import android.text.TextUtils;

import com.zs198893.netstar_oa.R;

public class DialogTool {
	private Context context;
	/**
	 * �ȴ�����
	 */
	public final static int TYPE_WaittingAlertDialog = 0x0;

	public DialogTool(Context context) {
		super();
		this.context = context;
	}
	/**
	 * ���ͨ�õȴ���
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
			message = "�����С�����";
		}
		waittingAlertDialog =  new WaittingAlertDialog(context, layoutResource, message);
		//ȡ������
		waittingAlertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		// ���õ����ĻDialog����ʧ
		waittingAlertDialog.setCancelable(false);
		return waittingAlertDialog;
	}
}
