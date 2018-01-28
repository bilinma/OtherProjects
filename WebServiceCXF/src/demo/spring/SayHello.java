package demo.spring;

import javax.jws.WebService;

@WebService
public interface SayHello {

	String sayHi(String text);

}
