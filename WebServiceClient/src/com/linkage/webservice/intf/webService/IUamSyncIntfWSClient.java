package com.linkage.webservice.intf.webService;


/**
 * 统一认证平台新提供服务接口,
 * @author 马小斌
 *
 */
public interface IUamSyncIntfWSClient {
	/**
	 * 1.7	设置产品服务密码
	 * @param reqXml
	 * @return
	 */
	public String setProdPwd(String reqXml);
	
	/**
	 * 1.13	密码修改接口
	 * @param reqXml
	 * @return
	 */
	public String modifyPassword(String reqXml);
	
	/**
	 * 1.15	密码重置接口
	 * @param reqXml
	 * @return
	 */
	public String resetPassword(String reqXml);
	
	/**
	 * 1.9	设置管理帐号
	 * @param reqXml
	 * @return
	 */
	public String modifyManager(String reqXml);
	
}
