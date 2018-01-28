/**
 * FactorageManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkage.webservice.intf.webService;

public interface FactorageManager extends java.rmi.Remote {
    public java.lang.String createParty(java.lang.String partyInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryStaffInfo(java.lang.String partyId, java.lang.String areaCode, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryOfferingsForChannelId(java.lang.String qryChannelId, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryRelaTerminalSpec(java.lang.String queryCondition, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryProdInfo(java.lang.String queryCondition) throws java.rmi.RemoteException;

    public java.lang.String rscNumberHandle(java.lang.String handleInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String changePassWord(java.lang.String changeInfo) throws java.rmi.RemoteException;

    public java.lang.String qureySpecInfo(java.lang.String queryCondition) throws java.rmi.RemoteException;

	public java.lang.String rscValidation(java.lang.String validateInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String rscRebind(java.lang.String bindInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

	public java.lang.String rscNumberQuery(java.lang.String prodSpecId, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryStaffBindNbr(java.lang.String qryStaffCode, java.lang.String areaCode, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryCustInfo(java.lang.String queryCondition) throws java.rmi.RemoteException;

	public java.lang.String queryMaterialInfo(java.lang.String queryCondition, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryChannels(int channelType, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String queryStoreInfoForChannelId(java.lang.String queryCondition, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

	public java.lang.String loginCheck(java.lang.String areaCode, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String modfiyParty(java.lang.String partyInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode, java.lang.String password) throws java.rmi.RemoteException;

    public java.lang.String modifyAcct(java.lang.String acctInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode) throws java.rmi.RemoteException;
 
	/**
	 * 代理商受理接口
	 * @param orderInfo ：订单信息
	 * @param areaCode ：受理地区
	 * @param channelId ：受理渠道
	 * @param staffCode ：工号
	 * @return
	 * @throws java.rmi.RemoteException
	 */
    public java.lang.String businessService(java.lang.String orderInfo, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode) throws java.rmi.RemoteException;

	/**
	 * 新增帐户接口，add by biany 20110114
	 * @param acctInfo
	 * @param areaCode
	 * @param channelId
	 * @param staffCode
	 * @param password
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public java.lang.String createAcct(java.lang.String acctInfo, java.lang.String areaCode,
			java.lang.String channelId, java.lang.String staffCode, java.lang.String password)
			throws java.rmi.RemoteException;

	/**
	 * 综合信息查询接口，add by biany 20110424
	 * @param queryCondition
	 * <queryCondition>
			<channelId></channelId>
			<staffCode></staffCode>
			<areaCode></areaCode>
			<accNbrType></accNbrType>
			<accNbr></accNbr>
			<prodspecId></prodspecId>
			<queryParam>
	    		<queryType></queryType>
	    		<queryMode></queryMode>
	    		<queryPage>
	    			<page></page> 
					<pageMax></pageMax>
	    		</queryPage>
			</queryParam>
	   </queryCondition>
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public java.lang.String queryInfoService(java.lang.String queryCondition) throws java.rmi.RemoteException;
}
