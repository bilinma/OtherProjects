package demo.spring;

import javax.jws.*;

@WebService(endpointInterface = "demo.spring.SayHello")
public class SayHelloImpl implements SayHello {

	public String sayHi(String text){
		return "-->Say Hello to :"+text;
	}

}
