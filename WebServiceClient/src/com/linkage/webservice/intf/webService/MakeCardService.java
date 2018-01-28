package com.linkage.webservice.intf.webService;

/**
 * 现场写卡跨本地网服务调用
 * @author tangminjun
 *
 */
public interface MakeCardService extends java.rmi.Remote {
	
    /**
     * 独立写卡：查找交换机列表
     * @param terminalSpec : 1：小灵通  2：CDMA卡
     * @param switchAreaId
     * @return
     * @throws java.rmi.RemoteException
     */
    public java.lang.String findSwitchList(java.lang.String terminalSpec, java.lang.String switchAreaId) throws java.rmi.RemoteException;
    
	/**
	 * 现场写卡--独立写卡校验终端接口
	 * @param   xml:
	 *          	<root>
	 *  			   <termiDevCheck>
	 *  			      <swtId>交换机</swtId> 
	 * 				      <cardType>卡类型（对应物品规格ID）</cardType>
	 *				      <terminalCode>卡序列号</terminalCode> 
	 *				   </termiDevCheck>
	 *				</root>
	 * @author  hts
	 * @version 2009-9-28
	 */
    public java.lang.String termiDevCheck(java.lang.String xml) throws java.rmi.RemoteException;
    
    /**
     * 独立写卡：写卡回填接口
     * @param xml
     * @return
     * @throws java.rmi.RemoteException
     */
    public java.lang.String termiDevComfirm(java.lang.String xml) throws java.rmi.RemoteException;
    
    /**
     * 省中心组件下发接口
     * @param xml
     * @return
     * @throws java.rmi.RemoteException
     */
    public java.lang.String updateCardDllInfo(java.lang.String xml) throws java.rmi.RemoteException;
    
	/**
	 * 现场写卡--受理写卡校验终端接口
	 * @param   xml:
	 *          	<root>
	 *  			   <termiDevAccCheck>
	 *  			      <accNbr>手机号</accNbr> 
	 * 				      <cardType>卡类型（对应物品规格ID）</cardType>
	 *				      <terminalCode>卡序列号</terminalCode> 
	 *				   </termiDevAccCheck>
	 *				</root>
	 * @author  hts
	 * @version 2009-10-05
	 */
    public java.lang.String termiDevAccCheck(java.lang.String xml) throws java.rmi.RemoteException;
    
	/**
	 * 卡片检测时从资源获取卡鉴权信息
	 * @author  xuejuan
	 * @version 2009-11-03
     * @throws java.rmi.RemoteException
	 */
	public String getCardAuthInfo(String reqxml) throws java.rmi.RemoteException;
	
	/** 卡片资源回收
	 * @param reqXml
	 * @return
     * @throws java.rmi.RemoteException
	 */
	public String recycleResourceData(String reqXml)throws java.rmi.RemoteException;
	
	/**
	 * 去资源进行卡终端校验
	 * 
	 * @param xml，具体格式详见《RM提供给CRM调用的写卡接口文档.doc》
	 * @return，具体格式详见《RM提供给CRM调用的写卡接口文档.doc》
	 * @throws BssException
	 * @author 蒿鳳廷 2010-6-16，端午节
	 */
	public String getTerminalDevice(String reqXml)throws java.rmi.RemoteException;
	
	/**
	 * 去资源进行imsi的预占
	 * 
	 * @param xml，具体格式详见《RM提供给CRM调用的写卡接口文档.doc》
	 * @return，具体格式详见《RM提供给CRM调用的写卡接口文档.doc》
	 * @throws BssException
	 * @author 蒿鳳廷 2010-6-16，端午节
	 */
	public String  allocAn(String reqXml)throws java.rmi.RemoteException;
	
}
