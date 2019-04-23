package com.bilin.service;

import com.bilin.common.CodeResponse;

public interface PushMessageService {
	
	public String sayHello(String name);

	public CodeResponse pushMessageToUcp(String mvnoId);
	
}
