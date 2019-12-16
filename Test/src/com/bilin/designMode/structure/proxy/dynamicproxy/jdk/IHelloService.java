package com.bilin.designMode.structure.proxy.dynamicproxy.jdk;

//委托类接口
public interface IHelloService {

 /**
  * 方法1
  * @param userName
  * @return
  */
 String sayHello(String userName);

 /**
  * 方法2
  * @param userName
  * @return
  */
 String sayByeBye(String userName);

}
