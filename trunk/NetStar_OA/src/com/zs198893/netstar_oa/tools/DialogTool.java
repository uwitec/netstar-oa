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
	 * 等待类型
	 */
	public int TYPE_WAITTING = 0x0;
	/**
	 * 构造器
	 */
	public Dialog dialog;

	public DialogTool(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 等待dialog
	 * 
	 * @author zhangshuai
	 * 
	 */
	public class WaittingAlertDialog{
		/**
		 * 提示信息
		 */
		public TextView message;
		/**
		 * 等待的背景view
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
