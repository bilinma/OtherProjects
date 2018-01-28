package com.linkage.webservice.intf.webService;

public interface IOrderLock extends java.rmi.Remote{

	/**
	 * 锁单
	 * @param requestXml
	 * @return
	 */
	public String orderLock(String requestXml);
	
	/**
	 * 解锁
	 * @param requestXml
	 * @return
	 */
	public String orderUnLock(String requestXml);
	/**
	 * 接入号自动生成
	 * @param inXml
	 * @return
	 */
	public String assignAn(String inXml);
	
	public String getActiveProdServOrder(String requestXml);
}
