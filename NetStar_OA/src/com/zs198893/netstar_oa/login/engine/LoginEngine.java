package com.zs198893.netstar_oa.login.engine;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.text.TextUtils;

import com.zs198893.netstar_oa.AppContext;
import com.zs198893.netstar_oa.config.WebServerConfig;
import com.zs198893.netstar_oa.model.CommonResult;
import com.zs198893.netstar_oa.tools.NetworkTool;

/**
 * ��¼ҵ���߼���
 * @author zhangshuai
 *
 */
public class LoginEngine {
	/**
	 * ������
	 */
	private Context context;
	/**
	 * ͨ�÷���ֵ����
	 */
	private CommonResult commonResult;
	/**
	 * spring rest ����ģ��
	 */
	RestTemplate restTemplate;
	
	public LoginEngine(Context context) {
		super();
		this.context = context;
	}

	/**
	 * ��֤��¼
	 * @param acount �û��ʺ�
	 * @param pwd �û�����
	 * @return ͨ�÷���ֵ����
	 */
	public CommonResult authentication(String acount, String pwd){
		commonResult = new CommonResult();
		commonResult.setSuccess(false);
		restTemplate = new RestTemplate();
		restTemplate.set
		((SimpleClientHttpRequestFactory)(restTemplate.getRequestFactory())).setConnectTimeout(WebServerConfig.timeout);
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		do{
			//�������
			if(!NetworkTool.isAvailable(context)){
				commonResult.setResult("���粻����");
				break;
			}
			//�ж��û����ʺ�
			if(TextUtils.isEmpty(acount) || TextUtils.isEmpty(pwd)){
				commonResult.setResult("�û������벻��Ϊ��");
				break;
			}
			try{
				commonResult.setResult(restTemplate.getForObject(WebServerConfig.loginActionURL, String.class));
			}catch(Exception e){
				e.printStackTrace();
				commonResult.setSuccess(false);
				try{
					commonResult.setResult("�����˴���"+e.getCause().getLocalizedMessage());
				}catch(Exception e0){
					commonResult.setResult("δ֪����");
				}
			}
			commonResult.setSuccess(true);
			commonResult.setResult("��¼�ɹ�");
			return commonResult;
		}while(false);
		return commonResult;
	}
	private class Mofgsd extends  StringHttpMessageConverter {


		
	}
}