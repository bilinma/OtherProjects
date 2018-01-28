
package com.linkage.webservice.intf.webService;
/**
 * 
 * @author Ma_xbin
 *
 */
public interface IOSSInterfaceForCRM {
	/**
	 * 2.18	营销审批占用号码
	 * @param xmlStr
	 * @return
	 */
	public String maketingSelectAn(String xmlStr);
	/**
	 * 选址、选号页面信息返回
	 * @param areaCode
	 * @param uuId
	 * @return
	 */
	public String getPageReturnVal(String areaCode, String uuId);
	/**
	 * CRM受理宽带、学子E行等业务时校验账号，资源系统判断账号是否存在，如不存在则新增账号，并返回新增的账号；
	 * 如存在则返回校验的结果。不同的产品在调用资源接口校验账号时传入不同的账号类型。
	 * @param xmlStr
	 * @return
	 */
	public String addAn(String xmlStr);
	/**
	 * 占用号码
	 * CRM营业受理选号确定时生成一张游离的工单，对于选号后临时预占的码号，将其码号状态设置为占用，并返回执行的结果。
	 * 同时对于批量受理购物车的情况也需要调用号码占用接口
	 * @param xmlStr
	 * @return
	 */
	public String allocAn(String xmlStr);
	/**
	 * 固定电话与固话虚拟网是否同一交换机
	 * @param busiNumber
	 * @param anTypeCd
	 * @param compProdId
	 * @param areaCode
	 * @return
	 */
	public String queryIsInOneBureau(String busiNumber,String anTypeCd,String compProdId,String areaCode);
	/**
	 * 2.15	营销审批查询号码
	 * @param areaCode
	 * @param startNbr
	 * @param endNbr
	 * @return
	 */
	public String queuePhoneNumberInfo(String areaCode,String startNbr,String endNbr);
	/**
	 * 2.22	接入号PUK码查询
	 * @param busiNumber
	 * @param areaCode
	 * @return
	 */
	public String getTerminalDeviceCode(String busiNumber,String areaCode);
	/**
	 * 2.4	批量受理根据接入号返回接入号ID
	 * @param accessNumber
	 * @param anTypeCd
	 * @param areaCode
	 * @return
	 */
	public String queryAnIdByAccessNum(String accessNumber,String anTypeCd,String areaCode);
	/**
	 * 2.6	释放占用码号
	 * CRM营业受理放弃提交时，对于选号后占用的码号（事务状态为修改），
	 * 利用占用时候产生的工单反回滚，将其码号业务状态设置为空闲（事务状态为正常），并返回执行的结果。
	 * @param xmlStr
	 * @return
	 */
	public String releaseAn(String xmlStr);
	/**
	 * CRM营业受理取消已校验的账号或者放弃提交时，资源系统删除账号校验时新增的账号。
	 * @param xmlStr
	 * @return
	 */
	public String deleteAn(String xmlStr);
	/**
	 * CRM受理CDMA、PHS等移动业务时，对UIM卡/PHS机身码等校验，资源系统将UIM卡的状态更新为占用状态（事务状态为修改），
	 * IMSI号更新为占用（事务状态为修改）（内部是分2步，先查询是否存在UIM卡，存在，则调用allocNbr接口修改状态；不存在，则返回失败原因）
	 * （工单驱动以上变更）
	 * @param xmlStr
	 * @return
	 */
	public String getTerminalDevice(String xmlStr);
	/**
	 *	营销审批预占号码
	 * @param xmlStr
	 * @return
	 */
	public String marketingBlockNbr(String xmlStr);
	/**
	 * 2.21	规则判断：固话移机是否跨局
	 * @param busiNumber
	 * @param prodId
	 * @param areaCode
	 * @param oldAddrId
	 * @param newAddrId
	 * @return
	 */
	public String queryIsSlopoverBureau(String busiNumber,String prodId,String areaCode, String oldAddrId, String newAddrId);
	/**
	 * 2.12	UIM卡校验
	 * @param xmlStr
	 * @return
	 */
	public String allocNbr(String xmlStr);
	/**
	 * 	业务号码/接入号码自动生成
	 * 用于生成宽带接入号、带宽型业务接入号、其他虚拟产品的接入号等
	 * @param xmlStr
	 * @return
	 */
	public String getAn(String xmlStr);
	
	/**
	 * 释放IP
	 * @param woId
	 * @return
	 * @throws java.lang.RuntimeException
	 */
	public String releaseIP (String areaCode,String woId) throws java.lang.RuntimeException;
	/**
	 * 占用选择IP资源
	 * @param areaCode
	 * @param woId
	 * @return
	 * @throws Java.lang.RuntimeException
	 */
	public String occupyIP (String areaCode,String woId) throws java.lang.RuntimeException;
	
	/**
	 * 远程写卡--请求可用资源数据（获取卡资源信息）
	 * @param reqXml
	 * @return
	 */
	public String termiDevAccCheck(String reqXml);
	
	/**
	 * 远程写卡--写卡后记录写卡事件（空白卡回写）
	 * @param reqXml
	 * @return
	 */
	public String termiDevComfirm(String reqXml);
	
	/**
	 * 2.41	C网交换机查询接口
	 * @param xml
	 * @return
	 */
	public String getSwitchByAreaAndTerminalSpec(String xml);
	
	/**
	 * 2.42	CDMA号码合法性校验接口
	 * @param xml
	 * @return
	 */
	public String checkCDMANbr (String xml);
	
	/**
	 * 2.43	携机入网根据ESN校验UIM卡接口
	 * @param xml
	 * @return
	 */
	public String allocAnByEsn (String xml);
	/**
	 * 2.52	参考配号接口
	 * 连选组分配接入号
	 * @param xml
	 * @return
	 */
	public String assignAnByRefAn (String xml);	
	/**
	 * 无条件宽带资源判断
	 * @param areaId
	 * @param addrId
	 * @param prodSpecId
	 * @param prodId
	 * @param orderTypeId
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String showRscCapaForCrm(String areaCode, Long addrId, Integer prodSpecId, Long prodId,
			Integer orderTypeId, String value);	
	
	
	public String isTmlCoveredByAddressId(String xml);
}
