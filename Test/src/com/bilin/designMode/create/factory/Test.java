package com.bilin.designMode.create.factory;


/**
 * 工厂方法模式（Factory Method）
 * @author xiaobin.ma
 * 
 */
public class Test {

    public static void main(String[] args) {  
        Provider provider = new SendMailFactory();  
        Sender sender = provider.produce();  
        sender.Send();  
    }  
}
