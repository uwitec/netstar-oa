package com.zs198893.netstar_oa.tools;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zs198893.netstar_oa.R;

/**
 * 等待dialog
 * 
 * @author zhangshuai
 * 
 */
public class WaittingAlertDialog extends Dialog {
	private Context context;
	/**
	 * 布局文件
	 */
	public int resLayout;
	/**
	 * 提示文字
	 */
	public TextView messageView;
	/**
	 * 旋转的图片
	 */
	public ImageView common_dialog_wait_iv;
	/**
	 * 文字消息
	 */
	private String message;
	/**
	 * 自定义布局的构造方法
	 * 
	 * @param context
	 * @param resLayout
	 */
	public WaittingAlertDialog( Context context, int resLayout, String message) {
		super(context);
		this.resLayout = resLayout;
		this.message = message;
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(this.resLayout);
		common_dialog_wait_iv = (ImageView) getWindow().getDecorView().findViewById(R.id.common_dialog_wait_iv);
		((AnimationDrawable)(common_dialog_wait_iv.getDrawable())).start();
		messageView = (TextView) getWindow().getDecorView().findViewById(R.id.common_dialog_wait_tv);
		messageView.setText(message);
	}
}
