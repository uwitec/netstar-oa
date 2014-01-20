package com.zs198893.netstar_oa.tools;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zs198893.netstar_oa.R;

public class DialogTool {
	private Context context;
	/**
	 * �ȴ�����
	 */
	public int TYPE_WAITTING = 0x0;
	/**
	 * ������
	 */
	public Dialog dialog;

	public DialogTool(Context context) {
		super();
		this.context = context;
	}

	/**
	 * �ȴ�dialog
	 * 
	 * @author zhangshuai
	 * 
	 */
	public class WaittingAlertDialog{
		/**
		 * ��ʾ��Ϣ
		 */
		public TextView message;
		/**
		 * �ȴ��ı���view
		 */
		public View common_dialog_wait_layout;

		public WaittingAlertDialog() {
			super();
			common_dialog_wait_layout = View.inflate(context,
					R.layout.common_dialog_wait_layout, null);
			this.message = (TextView) common_dialog_wait_layout
					.findViewById(R.id.common_dialog_wait_tv);
		}

		public Dialog getWaittingAlertDialog(String message) {
			dialog = new Dialog(context);
			dialog.setContentView(common_dialog_wait_layout);
			if (TextUtils.isEmpty(message)) {
				this.message.setText(message);
			} else {
				this.message.setText("");
			}
			return dialog;
		}
	}
}
