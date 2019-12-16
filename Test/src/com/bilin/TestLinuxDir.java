package com.bilin;

import java.util.Scanner;

public class TestLinuxDir {

	public static void main(String[] args) {
		// 获取输入命令行数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Scanner sc1 = new Scanner(System.in);
        String basePath = "";
        for (int i=0;i<n;i++){
        	//获取输入命令
        	String cdCmd = sc1.nextLine();
        	if(cdCmd != null&&!"".equals(cdCmd)&&cdCmd.length()>2){
        		String path = cdCmd.substring(2).trim();
        		//判断是否返回上级目录
        		if("..".equals(path)){
        			basePath = basePath.substring(0, basePath.lastIndexOf("/"));
        		}else{
        			if(path.charAt(0)=='/'){
        				basePath = path;
        			}else{
        				basePath = basePath+"/"+path;
        			}
        		}
        	}else{
        		System.out.println("命令非法！");
        	}
        }
        System.out.println(basePath);
	}

}
