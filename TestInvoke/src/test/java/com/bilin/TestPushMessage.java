package com.bilin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bilin.common.CodeResponse;
import com.bilin.invoke.InvokeUcpService;

@RunWith(SpringRunner.class)
//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
@SpringBootTest(classes={Application.class})
public class TestPushMessage {
	
	@Autowired
	private InvokeUcpService invokeUcpService;

	@Test
	public void testSend(){
		CodeResponse response = invokeUcpService.pushMessage("");
		System.out.println(response);
	}
	
	
}
