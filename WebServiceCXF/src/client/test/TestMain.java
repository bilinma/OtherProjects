package client.test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.spring.SayHello;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// START SNIPPET: client
        ClassPathXmlApplicationContext context 
            = new ClassPathXmlApplicationContext(new String[] {"client/test/client-beans.xml"});

        SayHello client = (SayHello)context.getBean("helloClient");

        String response = client.sayHi("Joe");
        System.out.println("Response: " + response);
        System.exit(0);
        // END SNIPPET: client

	}

}
