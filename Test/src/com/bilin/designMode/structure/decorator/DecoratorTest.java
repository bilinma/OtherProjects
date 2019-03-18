package com.bilin.designMode.structure.decorator;

public class DecoratorTest {  
	  
    public static void main(String[] args) {  
        Sourceable source = new Source();  
        Sourceable obj = new Decorator(source);  
        obj.method();  
    }  
}  