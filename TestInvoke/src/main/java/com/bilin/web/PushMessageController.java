package com.bilin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bilin.invoke.InvokeUcpService;
import com.bilin.task.TestTask;

//@RestController
public class PushMessageController {
	
	
	@Autowired
	private InvokeUcpService invokeUcpService;
	
	@Autowired
	private TestTask testTask;

	@RequestMapping(value = "/invoke", method = RequestMethod.GET)
	public String hello() {
		return invokeUcpService.pushMessage("").toString();
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public void sendEmail() {
		testTask.send();
	}
	
}
