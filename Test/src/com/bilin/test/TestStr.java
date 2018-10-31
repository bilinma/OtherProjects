package com.bilin.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStr {

	public static void main(String[] args) {
		
		int a = 011;
        int b = 0x11;
        System.out.println(a);
        System.out.println(b);
		// 被替换关键字的的数据源
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("cat", "Garfield");
		tokens.put("beverage", "coffee");

		// 匹配类似velocity规则的字符串
		String template = "${cat} really \needs some ${beverage}.";
		// 生成匹配模式的正则表达式
		//String patternString = "\\$\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}";
		String patternString = "\\$\\{(cat|beverage)\\}";

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);

		// 两个方法：appendReplacement, appendTail
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);

		// out: Garfield really needs some coffee.
		System.out.println(sb.toString());

		// 对于特殊含义字符"\","$"，使用Matcher.quoteReplacement消除特殊意义
		matcher.reset();
		// out: cat really needs some beverage.
		System.out.println(matcher.replaceAll("$1"));
		// out: $1 really needs some $1.
		System.out.println(matcher.replaceAll(Matcher.quoteReplacement("$1")));

		// 到得邮箱的前缀名。插一句，其实验证邮箱的正则多种多样，根据自己的需求写对应的正则才是王道
		/*String emailPattern = "^([a-z0-9_\\.\\-\\+]+)@([\\da-z\\.\\-]+)\\.([a-z\\.]{2,6})$";
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher("test@qq.com");
		// 验证是否邮箱
		System.out.println(matcher.find());
		// 得到@符号前的邮箱名 out: test
		System.out.println(matcher.replaceAll("$1"));

		// 获得匹配值
		String temp = "<meta-data android:name=\"appid\" android:value=\"joy\"></meta-data>";
		pattern = Pattern.compile("android:(name|value)=\"(.+?)\"");
		matcher = pattern.matcher(temp);
		while (matcher.find()) {
			// out: appid, joy
			System.out.println(matcher.group(2));
		}*/
		
		
		int random=(int) Math.round(Math.random()*25+97);
		char temp=(char) random;
		System.out.println(temp);
			
			
		char[] A_Z = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R'
				,'S','T','U','V','W','X','Y','Z'};
		Random rd = new Random();
		//随即10个拿出来看看
		for(int i=0;i<=10;i++){
			System.out.println(A_Z[rd.nextInt(26)]+" ");
		}
		
		
		String URL = "https://s3-ap-southeast-1.amazonaws.com/ukl-devtest-bss-terminal-configure-001/mpms/ae39f3ea4581cc45a4310eea9c89cc8f.doc";
		
		System.out.println(URL.substring(URL.lastIndexOf("/")+1));
	}
}
