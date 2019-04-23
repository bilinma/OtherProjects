package com.bilin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilin.common.CodeResponse;
import com.bilin.invoke.InvokeUcpService;
import com.bilin.service.PushMessageService;

@Service
@Transactional
public class PushMessageServiceImpl implements  PushMessageService {

	@Autowired
	private InvokeUcpService invokeUcpService;
	
	
	@Override
	@Async
	public CodeResponse pushMessageToUcp(String mvnoId) {
		CodeResponse sendResponse  = invokeUcpService.pushMessage(mvnoId);
		System.out.println(sendResponse);
		return sendResponse;
	}

	@Override
	@Async
	public String sayHello(String name) {
		 String str = invokeUcpService.sayHello(name);
		 System.out.println(str);
		 return str;
	}

}
