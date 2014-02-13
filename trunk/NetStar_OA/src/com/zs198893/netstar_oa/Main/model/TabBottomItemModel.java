package com.zs198893.netstar_oa.main.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zs198893.netstar_oa.R;

/**
 * tab底部每个对象
 * @author zhangshuai
 *
 */
public class TabBottomItemModel {
	/**
	 * 主体view
	 */
	private View contentView;
	/**
	 * 标题
	 */
	private TextView titleTV;
	/**
	 * 背景图片
	 */
	private ImageView bgIV;
	/**
	 * 构造器
	 */
	private TabBottomItemModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造器
	 * @param contentView
	 */
	public TabBottomItemModel(View contentView) {
		TabBottomItemModel tabBottomItemModel = new TabBottomItemModel();
		tabBottomItemModel.setContentView(contentView);
		tabBottomItemModel.setTitleTV((TextView)contentView.findViewById(R.id.main_tab_activity_tv));
		tabBottomItemModel.setBgIV((ImageView)contentView.findViewById(R.id.main_tab_activity_iv));
	}
	public View getContentView() {
		return contentView;
	}
	public void setContentView(View contentView) {
		this.contentView = contentView;
	}
	public TextView getTitleTV() {
		return titleTV;
	}
	public void setTitleTV(TextView titleTV) {
		this.titleTV = titleTV;
	}
	public ImageView getBgIV() {
		return bgIV;
	}
	public void setBgIV(ImageView bgIV) {
		this.bgIV = bgIV;
	}
}
