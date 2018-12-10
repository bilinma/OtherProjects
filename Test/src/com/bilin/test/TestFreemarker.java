package com.bilin.test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilin.bo.Student;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreemarker {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		cfg.setDirectoryForTemplateLoading(new File(""));
		List<Student> strList = new ArrayList<Student>();
		for (int i = 0; i < 100; i++) {
			Student student = new Student();
			student.setId(i);
			student.setName("name"+i);
			student.setAge(i);
			strList.add(student);
		}

		Map root = new HashMap();
		root.put("username", "220");
		root.put("message", "232");
		root.put("strList", strList);
		root.put("username1111", "23123");
		Template t = cfg.getTemplate("config/test.txt");

		// 最关键在这里，不使用与文件相关的Writer
		StringWriter stringWriter = new StringWriter();

		try {
			t.process(root, stringWriter);

			// 这里打印的就是通过模板处理后得到的字符串内容
			System.out.println("stringWriter: \n" + stringWriter.toString());
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

}