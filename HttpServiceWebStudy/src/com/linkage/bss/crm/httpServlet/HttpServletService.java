package com.linkage.bss.crm.httpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linkage.bss.crm.httpService.SayHelloHttp;
import com.linkage.bss.crm.httpService.SayHelloHttpImpl;

public class HttpServletService extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("gbk");

		PrintWriter out = response.getWriter();
		String param1 = request.getParameter("param1");
		out.println("param1=" + param1);
		String param2 = request.getParameter("param2");
		out.println("param2=" + param2);
		if (param1 == null || "".equals("param1") || param1.length() <= 0) {
			out.println("http call failed,参数param1不能为空,程序退出");
		} else if (param2 == null || "".equals("param2")
				|| param2.length() <= 0) {
			out.println("http call failed,参数param2不能为空,程序退出");
		} else {
			out.println("---http call success---");
			SayHelloHttp sayHelloHttp = new SayHelloHttpImpl();
			String str = sayHelloHttp.sayHello(param1);
			out.println(str);
		}
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
