package com.linkage.webservice.intf.webService;

public interface ICrmWebServiceForOss {
	
	
	
	/**
	 * 数据总线根据流程实例ID向CRM抽取购物车信息数据
	 * @param requestxml
	 * @return
	 */
	public String getOrderListByFlowInstId(String requestxml);
	/**
	 * 返回物品信息
	 * @param requestXml:channelId,termDevSpecId,termCode
	 * @return RESULT,OUTMSG
	 */
	public String validateTerminalCoupon(String requestXml);
	
	/**
	 * 数据总线根据CRM客户ID向CRM抽取客户信息数据。
	 * @param requestxml
	 * @return
	 */
	public String  getCustInfoByPartyId(String requestxml); 
	
	
	/**
	 * CRM提供给OSS报竣和退单接口
	 * @param requestXml
	 * @return xml
	 */
	public String boReplyMsg(String requestXml);
	
	 /**
	  * 数据数据总线根据CRM产品实例ID向CRM抽取销售品信息数据。
	  * @param requestxml
	  * @return
	  */
   public String getProdOffferInfoByProdId(String requestxml);
   
   
   /**
    * 数据总线根据CRM产品实例ID向CRM抽取账户信息
    * @param requestxml
    * @return
    */
	public String getAccountInfoByProdId(String requestxml);
}
