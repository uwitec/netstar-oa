package com.zs198893.netstar_oa.login.engine;

import org.springframework.web.client.RestTemplate;

import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.model.CommonResult;

/**
 * ��¼ҵ���߼���
 * @author zhangshuai
 *
 */
public class LoginEngine {
	/**
	 * ͨ�÷���ֵ����
	 */
	private CommonResult commonResult;
	/**
	 * spring rest ����ģ��
	 */
	RestTemplate restTemplate;
	/**
	 * ��֤��¼
	 * @param acount �û��ʺ�
	 * @param pwd �û�����
	 * @return ͨ�÷���ֵ����
	 */
	public CommonResult authentication(String acount, String pwd){
		restTemplate = new RestTemplate();
		return commonResult;
	}
}