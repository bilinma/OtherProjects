package com.bilin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bilin.demo.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext-dubbo.xml")
public class TestDubbo {

	@Autowired
	public IStudentService studentService;

	@Test
	public void testStudentService(){
		System.out.println(studentService.sayHello("bilin"));
	}

}
