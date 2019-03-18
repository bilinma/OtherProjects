package com.bilin.designMode.structure.adapter;

public class AdapterTest {  
	  
    public static void main(String[] args) {
    	//01、类的适配器模式
        Targetable target1 = new Adapter1();  
        target1.method1();  
        target1.method2();
        //02、对象的适配器模式
        Source source = new Source();
        Targetable target2 = new Adapter2(source);  
        target2.method1();  
        target2.method2();  
    }  
}  