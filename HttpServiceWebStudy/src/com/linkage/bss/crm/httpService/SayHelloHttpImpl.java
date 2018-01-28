package com.linkage.bss.crm.httpService;


import org.springframework.web.servlet.DispatcherServlet;
public class SayHelloHttpImpl implements SayHelloHttp{

	public String sayHello(String inStr) {
		return "ÄãºÃ£º"+inStr;
	}

}
