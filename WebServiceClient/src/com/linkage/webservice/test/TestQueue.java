package com.linkage.webservice.test;

import java.util.Queue;  
import java.util.LinkedList;

public class TestQueue {  
    public static void main(String[] args) {  
        Queue<String> queue = new LinkedList<String>();  
        queue.offer("Hello");  
        queue.offer("World!");  
        queue.offer("ÄãºÃ£¡");  
        System.out.println(queue.size());  
        String str;  
        while((str=queue.poll())!=null){  
            System.out.println(str);  
        }  
        System.out.println(queue.size());  
    }  
}  