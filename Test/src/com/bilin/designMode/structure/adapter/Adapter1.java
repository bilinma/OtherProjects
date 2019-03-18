package com.bilin.designMode.structure.adapter;

/**
 * 01、类的适配器模式
 * @author xiaobin.ma
 *
 */
public class Adapter1 extends Source implements Targetable {  
	  
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  
}  
