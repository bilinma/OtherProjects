package com.bilin.invoke;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.bilin.common.CodeResponse;
import com.bilin.common.utils.DESUtil;

@Component
public class InvokeUcpService {
	
	private static String url = "http://10.1.75.249:9000/bss/ucp/ucpsend/pushMassage/pushMassageToUcp";
	
	@Autowired
	private RestTemplate restTemplateClient;

	public CodeResponse pushMessage(String mvnoId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("sign", DESUtil.encrypt(String.valueOf(System.currentTimeMillis()), "ucp123456"));// DES 加密秘钥：UCP123456
		JSONObject message = new JSONObject();
		message.put("streamNo", "web_" + System.currentTimeMillis());
		message.put("templateCode", "T20181227055314043846");
		message.put("languageType", "en-US");
		message.put("mvnoId", mvnoId);
		message.put("customerId", "");
		message.put("userCode", "");
		message.put("toEmail", "393647941@qq.com");
		message.put("source", "1");
		HttpEntity<JSONObject> sendRequest = new HttpEntity(message, headers);

		CodeResponse retObj = (CodeResponse) restTemplateClient.postForObject(url, sendRequest, CodeResponse.class);
		return retObj;
	}
	
	public String sayHello(String name) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("name", name);
		String retObj =  restTemplateClient.getForObject("http://127.0.0.1:8765/hi?name={name}", String.class, param);
		return retObj;
	}
	
	
	

}
