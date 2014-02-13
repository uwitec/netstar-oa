package com.zs198893.netstar_oa.tools;

import android.content.Context;
import android.widget.EditText;

public class CommonTool {
	private Context context;

	private CommonTool(Context context) {
		super();
		this.context = context;
	}
	/**
	 * 从EditText中获取值
	 * @param editText
	 * @return
	 */
	public static String getStringFromEditText(EditText editText){
		String value = null;
		if(editText!=null){
			value = editText.getText().toString().trim();
		}
		return value;
	}
}
