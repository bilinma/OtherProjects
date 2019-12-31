package com.bilin.java8;

public class TestJava8Interface {

	public static void main(String args[]){
		Formula formula = new Formula(){
		    @Override
		    public double calculate(int a) {
		        return sqrt(a * 100);
		    }
		};
		System.out.println(formula.calculate(10));
	}

}
