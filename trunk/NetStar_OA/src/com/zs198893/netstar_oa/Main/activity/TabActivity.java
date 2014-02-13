package com.zs198893.netstar_oa.Main.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import com.zs198893.netstar_oa.BaseActivity;
import com.zs198893.netstar_oa.R;
import com.zs198893.netstar_oa.Main.model.TabBottomItemModel;

/**
 * 仿tabhost的基类
 * 
 * @author zhangshuai
 * 
 */
public abstract class TabActivity extends BaseActivity {
	/**
	 * 获取TabHost  
	 */
	@InjectView(R.id.tabs)
    TabHost tabHost;
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * tab 底部，导航栏
	 */
	private TabBottomItemModel tabBottom_0 ;
	/**
	 * tab 底部，公司动态
	 */
	private TabBottomItemModel tabBottom_1 ;
	/**
	 * tab 底部，工作量统计
	 */
	private TabBottomItemModel tabBottom_2 ;
	/**
	 * 一个用于记录的map
	 */
	private Map<String, TabBottomItemModel> TabBottomItemModelMap = new HashMap<String, TabBottomItemModel>();
		/**
		 * 默认选择哪一个
		 */
		private int defaultTabId = 0;
		/**
		 * 已经选择背景图片
		 */
		private int selectedBG = R.drawable.bg_main_layout_tab_button_clicked;
		/**
		 * 未经选择背景图片
		 */
		private int unSelectedBG =R.drawable.bg_main_layout_tab_button_unclicked;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_layout);
		//初始化tab
		initTabSpec();
	}
	/**
	 * 初始化tab
	 */
	public void initTabSpec(){
		tabHost.setup();
		//清空数据
		TabBottomItemModelMap.clear();
		//导航
		initNewTabSpec(tabBottom_0,"导航",NavigationActivity.class);
		//公司动态
		initNewTabSpec(tabBottom_1,"公司动态",NewsActivity.class);
		//工作量统计
		initNewTabSpec(tabBottom_2,"工作量统计",WorkloadActivity.class);
		//初始化底部背景
		initTabBottomBG();
		//设置默认选中
		tabHost.setCurrentTab(defaultTabId);
		//底部tab切换监听
		tabHost.setOnTabChangedListener(new TabChangeListener());
	}
	/**
	 * 初始化一个新页面
	 * @param tabBottomItemModel
	 * @param name
	 * @param clazz
	 */
	private void initNewTabSpec(TabBottomItemModel tabBottomItemModel,String name,Class<? extends Activity> clazz){
		String tabId = (TabBottomItemModelMap.size()+1)+"";
		tabBottomItemModel = new TabBottomItemModel(View.inflate(context, R.layout.main_layout_bottom_item, null));
		tabBottomItemModel.getTitleTV().setText(name);
		tabHost.newTabSpec(tabId).setIndicator(tabBottomItemModel.getContentView()).setContent(new Intent(context, clazz));
		TabBottomItemModelMap.put(tabId, tabBottomItemModel);
	}
	/**
	 * tab切换监听
	 */
	private class TabChangeListener implements OnTabChangeListener{
		@Override
		public void onTabChanged(String tabId) {
			if(TabBottomItemModelMap.containsKey(tabId)){
				initTabBottomBG();
				SetTabBottomBG(TabBottomItemModelMap.get(tabId));
			}else{
				Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
			}
		}
	}
	/**
	 * 初始化底部背景
	 */
	private void initTabBottomBG(){
	    Iterator<Map.Entry<String, TabBottomItemModel>> entries = TabBottomItemModelMap.entrySet().iterator();  
	    while (entries.hasNext()) {  
	        Map.Entry<String, TabBottomItemModel> entry = entries.next();  
	        entry.getValue().getBgIV().setImageResource(unSelectedBG);   
	    }  
	}
	/**
	 * 初始化底部背景
	 */
	private void SetTabBottomBG(TabBottomItemModel tabBottomItemModelMap){
		tabBottomItemModelMap.getBgIV().setImageResource(selectedBG);
	}
}
