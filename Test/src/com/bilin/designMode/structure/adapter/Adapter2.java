package com.bilin.designMode.structure.adapter;

/**
 * 02、对象的适配器模式
 * @author xiaobin.ma
 *
 */
public class Adapter2 implements Targetable {  
	  
    private Source source;  
      
    public Adapter2(Source source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  
  
    @Override  
    public void method1() {  
        source.method1();  
    }  
}  