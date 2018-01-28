package com.linkage.webservice.intf.webService;


/**
 * 统一认证平台webservice服务接口,
 * 协议见<江苏电信统一认证平台接入规范_总册20100613>
 * @author 林志鹏
 *
 */
public interface IUamWSClient {
	/**
	 * 集中认证服务接口
	 * @param ReqContent
	 * @return
	 */
	public String authReq(String reqContent);
	
	/**
	 * 密码重置接口
	 * @param reqContent
	 * @return
	 */
	public String pwdReset(String reqContent);
}
