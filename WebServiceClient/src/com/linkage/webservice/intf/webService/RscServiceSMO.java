package com.linkage.webservice.intf.webService;

import java.util.List;
import java.util.Map;

/**
 * @author mosj
 *
 */
public interface RscServiceSMO {

	/**
	 * xml方式，modify by mosj 20050104
	 * 取得Tml列表
	 * @param tmlId	
	 * @return 
	 *  <?xml version='1.0' ?>
	 *	<tmls>
	 * 	 <tml>
	 *  	<tmlId></tmlId>                 
	 *  	<tmlTypeCd></tmlTypeCd>        
	 *   	<areaId></areaId>               
	 *   	<manageCd></manageCd>           
	 *   	<name></name>                
	 *   	<description></description>   
	 *   	<createDt></createDt>   
	 *   	<expDt></expDt>         		
	 *   	<version></version>      
	 *	</tml>
	 *	</tmls>
	 */
	public String getTmlXml(Integer tmlId) throws Exception;

	/**
	 * xml方式，modify by mosj 20050104
	 * 取得Tml列表
	 * @param Object 根据设备查询deviceCode;根据向某个属性查评 tmlName, tmlManageCd, tmlTypeCd, areaId ; 
	 * @return 
	 *  <?xml version='1.0' ?>
	 *	<tmls>
	 * 	 <tml>
	 *  	<tmlId></tmlId>
	 *  	<tmlTypeCd></tmlTypeCd>
	 *   	<areaId></areaId>
	 *   	<manageCd></manageCd>
	 *   	<name></name>
	 *   	<description></description>
	 *   	<createDt></createDt>
	 *   	<expDt></expDt>
	 *   	<version></version>
	 *	 </tml>
	 *	</tmls>
	 */
	public String getTmlXml(String deviceCode, String tmlName, String tmlManageCd, Integer tmlTypeCd, Integer areaId)
			throws Exception;

	/*
	 *  根据tmlId获取下级tmlId
	 * @param: tmlId	
	 * @return:List 每一个元素是丄个tmlId
	 */
	public List getTmlSub(Integer tmlId) throws Exception;

	/**
	 * 分配接入号码
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public List assignAn(String xml) throws Exception;

	/**
	 * 预占机身码
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public List allocAn(String xml) throws Exception;

	/**
	 * 释放号码
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public List releaseAn(String xml) throws Exception;
	
	/**
	 * 释放e8-c卡号
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public String releaseE8CTd(String xml) throws Exception;

	/**
	 * 增加号码
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public List addAn(String xml) throws Exception;

	/**
	 * 删除号码
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public List deleteAn(String xml) throws Exception;

	/**
	 * 查询号码
	 * @param xml
	 * @return
	 * @throws BssException
	 */
	public List getAn(String xml) throws Exception;

	/**
	 * 查询终端各类信息
	 * @param xml
	 * @return
	 * @throws BssException
	 * 输入

	终端设备规格ID/terminal_device/terminal_dev_spec/id

	查询条件

		条件1：设备编号（/terminal_device/device_numbers/device_number[an_type_cd =?-1”]/number

		条件2：号码类型编号码/terminal_device/device_numbers/device_number/an_type_cd+/terminal_device/device_numbers/device_number/number

	输出：终端设备实例ID、设备编号，终端设备实例属性，设备型号支持的服务规格数组

	接口XML格式

	<?xml version="1.0" encoding="UTF-8" ?> 
	- <terminalDevice>
			<id>102</id> 
			<code>SN0003</code> 
	- 		<terminalDevSpec>
				<id>10302008</id> 
				<name>小灵通手机</name> 
			</terminalDevSpec>
	- 		<deviceModel>
				<id>109</id> 
				<name>UT318</name> 
	- 			<manufacturer>
					<id>3</id> 
					<name>UT斯达康</name> 
				</manufacturer>
	- 			<servSpecs>
	- 				<servSpec>
						<id>35</id> 
						<isSupport>Y</isSupport> 
					</servSpec>
	- 				<servSpec>
						<id>36</id> 
						<isSupport>Y</isSupport> 
					</servSpec>
				</servSpecs>
			</deviceModel>
	- 		<accessNumbers>
	- 			<accessNumber>
					<anTypeCd>501</anTypeCd> 
					<name>小灵通机身码</name> 
					<anId>92</anId> 
					<number>002500000002</number> 
					<result>0</result> 
					<cause></cause> 
				 </accessNumber>
			</accessNumbers>
	- 		<items>
	- 			<item>
					<id>102</id> 
					<name>手机颜色</name> 
					<value></value> 
				</item>
			</items>
	</terminalDevice>*/

	public String getTerminalDevice(String xml) throws Exception;

	/**
	 * 根据areaId查询出本地网顶级的TmlId 
	 * @param areaId
	 * @return
	 * @throws BssException
	 */
	public Integer findTmlByAreaId(Integer areaId) throws Exception;

	/**
	 * xml方式取Tml
	 * @param manageCd
	 * @return
	 * <?xml version="1.0" encoding="UTF-8"?>
	 * <Tmls>
	 * <Tml><tmlId>44</tmlId>
	 * <tmlTypeCD>6</tmlTypeCD>
	 * <areaId>8</areaId>
	 * <manageCd>222</manageCd>
	 * <name>2202</name>
	 * <description/>
	 * <createdDt>2004-12-23 15:06:56.0</createdDt>
	 * <expDt>3000-01-01 00:00:01.0</expDt>
	 * <version>2004-12-23 15:06:56.0</version>
	 * <parentTmlId>2</parentTmlId>
	 * <tmlGroupId>1</tmlGroupId>
	 * </Tml>
	 * </Tmls>
	 * @throws BssException
	 */
	public String findTmlByManageCd(String manageCd) throws Exception;

	/**
	 * 根据ProdId取Tml
	 * @param prodId
	 * @return HashMap的List   key: TML_ID   NAME
	 * @throws BssException
	 */
	public List getTmlByProdId(Long prodId) throws Exception;

	/**
	 * 取交换机个数,如果为零,要新增设处

	 * @param areaId
	 * @param phoneNumber
	 * @param tmlId
	 * @return
	 * @throws BssException
	 */
	public int getNumberOfDevice(Integer areaId, String phoneNumber, Integer tmlId) throws Exception;

	/**
	 * 终端设备PIN码及PUK码查评

	 * @param String Map
	 * @return 返回Map
	 * @author WANGZH
	 * 输入：终端设备号码（以后还可增加

	 * 输出：终端设备PIN码及PUK

	*/
	public Map getTerminalDeviceCode(Map terminalDeviceInfo) throws Exception;

	/**
	 * 通过产品查询IMSI

	 * @param inParam
	 * @return 返回Map
	 * @author WANGBIAO
	 * 输入：Map
	 * 输出：IMSI

	*/
	public Map getIMSIByProdId(Map inParam) throws Exception;

	/** 20080902 cuiyongzhi add
	 * 根据CRM传入的地区及终端规格查询对应的设备ID及交换机名称并返回

	 * @param Map
	 * @return List
	 * @throws BssException
	 */
	public List getSwitchByAreaAndTerminalSpec(Map terminalDeviceInfo) throws Exception;

	/**资源确认
	 * @param Map  cond
	 * key     value    description
	 * addrid         地址id（产品对应的地址id,不是标准地址id

	 * @return   
	 *       Map
	 *          String result    1/0    1:调用成功  0:调用失败
	 * @author wanbiao
	 * @since BSS 1.0
	 * @throws DAOException
	 */

	public String confirmResourceNew(Map cond) throws Exception;

	//		public void setAccessNumberAssigner(AccessNumberAssignerBMO assigner);

	/**检查CDMA号码
	 * @param String cdmaNbr
	 * @param String anTypeCd
	 * @return
	 * @throws Exception
	 * @author 周颖
	 */
	public String checkCDMANbr(String cdmaNbr, String anTypeCd) throws Exception;

	/**
	* 判断是否跨局选号接口
	* @param areaId(是newAddressId的受理地区)
	* @param prodId
	* @param phoneNumber
	* @param oldAddressId
	* @param newAddressId
	* @return  返回是否需要改号标识
	* @throws BssException
	*/
	public String isJumpTmlSelNum(Integer areaId, Long prodId, String phoneNumber, Long oldAddressId, Long newAddressId)
			throws Exception;

	/**
	*鏍规嵁鐢ㄦ埛鍦板潃鏌ュ眬鍚??
	* @param addrId 鐢ㄦ埛鍦板潃
	* @return  灞??鍚戜緥琛?? 姣忎釜MAP鏈変袱涓厓绱狅細鈥淣AME鈥濓細灞??鍚戝悕绉?? 鈥淭ML_ID鈥濓細灞??鍚慖D	
	* @author panjb
	*/
	public List findTmlByAddrId(Long addrId) throws Exception;
	
	/**
	 * 营销审批预占号码
	 * 被占号码状态为 审批封锁，否则预占失败
	 * @param List {[anIds]}
	 * @return [ RES: Y 成功  N 失败
	 *               		 MSG：失败原因描述] 
	 */
	public Map maketingSelectAn(List anIds) throws Exception;
	
	/**
	 * 入参：phoneNumberId 已选C网号码ID，不能为空节点。
	 *	<terminalDevice>
	 *	<ESN></ESN>
	 *	<phoneNumberId></phoneNumberId>
	 *	</terminalDevice>
	 * @param xml
	 * @return
	 * 返回：按UIM卡校验返回格式返回
	 */
	public String allocAnByEsn(String xml);
	
	/**
	 *根据用户地址查局向和行政管理区信息
	 * @param addrId 用户地址
	 * @return  MAP
	 *             KEY       TML_LIST            管理区类表
	 *                         XZ_TML_LIST       行政管理区列表
	 *       列表中元素为MAP，每个MAP有两个元素：“NAME”：局向名称 “TML_ID”：局向ID
	 *
	 */
	public Map findTmlInfoByAddrId(Long addrId) throws Exception;
			
	/**资源提供crm用于受理校验终端接口
	 * @author shisw
	 * @date 2009-9-21
	 * @version BSS 2.0
	 * @param  String inXml 
	 *    <root>
	 *      <termiDevAccCheck>
	 *         <accNbr>手机号</accNbr>
	 *         <cardType>卡规格</cardType>
	 *         <terminalCode>序列号</terminalCode>
	 *      </termiDevAccCheck>
	 *    </root>
	 * @return String returnXml
	 * @throws BssException
	 */
	public String termiDevAccCheck(String inXml) throws Exception;
	
	/**资源提供crm用于独立写卡页面校验终端接口
	 * @author shisw
	 * @date 2009-9-21
	 * @version BSS 2.0
	 * @param  String inXml 
	 *    <root>
	 *      <termiDevCheck>
	 *         <nbrElement>手机号头</nbrElement>
	 *         <cardType>卡规格</cardType>
	 *         <terminalCode>序列号</terminalCode>
	 *      </termiDevCheck>
	 *    </root>
	 * @return String returnXml
	 * @throws BssException
	 */
	public String termiDevCheck(String inXml) throws Exception;
	
	/**crm写卡完毕后调用资源接口termiDevComfirm
	 * @author shisw
	 * @date 2009-9-21
	 * @version BSS 2.0
	 * @param  String inXml 
	 *    <root>
	 *      <termiDevComfirm>
	 *         <terminalCode>序列号</terminalCode>
	 *         <imsiCode>imsi号</imsiCode>
	 *         <writeFlag>写卡标识（1成功，0失败）</writeFlag>
	 *         <isAcc>是否是受理中Y 或N</isAcc>
	 *      </termiDevComfirm>
	 *    </root>
	 * @return String returnXml
	 * @throws BssException
	 */
	public String termiDevComfirm(String inXml) throws Exception;
	
	/**资源提供crm获取鉴权信息接口getCardAuthInfo
	 * @author shisw
	 * @date 2009-10-14
	 * @version BSS 2.0
	 * @param  String inXml 
	 *<root>
     *  <getCardAuthInfo>
     *     <serialNum>卡序列号</serialNum> 
     *     <iccidCode>iccid</iccidCode>
     *     <cImsiCode>cImsi</cImsiCode>
     *     <gImsiCode>CG双模卡要提供gImsi</gImsiCode>
     *  </getCardAuthInfo>
     *</root>  
	 * @return String returnXml
	 * @throws BssException
	 */
	public String getCardAuthInfo(String inXml) throws Exception;	
	
	/**资源提供crm旧卡数据回收接口recycleResourceData
	 * @author shisw
	 * @date 2009-10-14
	 * @version BSS 2.0
	 * @param  String inXml 
	 *<root>
	 *  <recycleResourceData>
	 *     <serialNum>卡序列号</serialNum> 
	 *     <iccidCode>iccid</iccidCode>
	 *     <cImsiCode>cImsi</cImsiCode>
	 *     <gImsiCode>CG双模卡要提供gImsi</gImsiCode>
	 *  </recycleResourceData>
	 *</root>   
	 * @return String returnXml
	 * @throws BssException
	 */
	public String recycleResourceData(String inXml) throws Exception;

	/**
	 * 固话选号后,判断此固话如果加入一号双机,调用资源接口返回对应的PHS成员的anId,
	 * 已有固话加入一号双机组合,调用资源接口返回对应PHS成员的anId.
	 * @param areaId
	 * @param anTypeCd
	 * @param inPhyNum
	 * @return Long
	 * @throws Exception
	 */
	public Long createPhsNum(Integer areaId,Integer anTypeCd, String inPhyNum) throws Exception;
	
	/**
	 * ITV产品移机,检查其主接入号是否符合要求，如果不符合调用资源接口生成新的主接口号码。
	 * @param in_area_Id
	 * @param in_prod_specId
	 * @param in_an_type_cd
	 * @return String
	 */
	public String getProdSpecAccessNumber(Integer in_area_Id, Integer in_prod_specId, Integer in_an_type_cd);

	/**
	 * 判断子产品加入虚拟网时判断2者是否在同一交换机上,如果不是,需要规则限制.
	 * 当固话/PHS虚拟网中的成员改号时，检查是否为同一交换机上,如果不是需要限制。
	 * @param phoneNumber
	 * @param compProdId
	 * @return String
	 * @throws Exception
	 */
	public String checkIsInOneOffice(Long phoneNumber, Long compProdId) throws Exception;
	
	/**
	 * iTV新装/移机，宽带改速率，ITV开高清服务时调用资源接口判断速率是否满足
	 * @param prodInfos
	 * @return String
	 * @throws Exception
	 */
	public String checkItvNeedSpeedforCrm(String prodInfos) throws Exception;

	/**
	 * 根据用户所选择的服务， 进行终端检验时，校验卡是否有卡有翼支付账号
	 * @param in_term_dev_id
	 * @return
	 * @throws Exception
	 */
	public String checkWPayAccountByTermDevId(Long in_term_dev_id) throws Exception;
	/**
	* 查询手机号码id(批量用到)
	* @param phoneNumber
	* @return
	*/
	public String queryPhoneNumberIdByPhoneNumber(String phoneNumber);
    /**
	 * 根据手机号码读取沃达丰卡号
	 * @param String pn
	 * 出参：返回的查询信息
	 	出参类型：String（xml格式的）
			 XML的节点说明：
				outFlag：查询标识，1：表示查询成功；0：表示查询失败
				outMsg：查询结果信息
				tdCode：手机号码绑定的沃达丰卡号（查询成功的时候才会返回该节点信息）
	
		 实例：
			查询成功：
				<?xml version="1.0" encoding="UTF-8"?>
			<outInfo>
			<outFlag>1</outFlag>
			<outMsg>查询成功</outMsg>
			<tdCode>8986031090527048100</tdCode>
			</outInfo>
					查询失败：
						<?xml version="1.0" encoding="UTF-8"?>
			<outInfo>
			<outFlag>0</outFlag>
			<outMsg>根据手机号码查询不到绑定的沃达丰卡</outMsg>
			</outInfo>
	 */
	public String getVodafoneTdByPn(String pn) throws Exception;
	
	
	/**
	  * 号码信息查询接口，返回固定电话号码，PHS号码或CDMA号码相关信息,号码状态为1空闲 5封锁
	  * startNbr长度为8位则返回固定电话号码和PHS号码，11位则返回CDMA号码
	  * @param startNbr 不能为空，长度为8位或11位
	  * @param endNbr   不能为空，长度同startNbr
	  * @return List: {Map:[PHONE_NUMBER_ID 号码ID，AN_TYPE_CD 号码类型CD，AN_TYPE_NAME 号码类型描述
	  *                     RSC_STATUS_CD 号码状态CD，RSC_STATUS_NAME 号码状态描述，POOL_NAME 号码池描述
	  *                     PN_LEVEL_NAME 号码等级描述， PN_LEVEL_DES 号码等级备注，PHONE_NUMBER 号码
	  *                     DESCRIPTION 号码描述, PN_LEVEL_ID 号码等级ID   ] }
	  * @author zhangr 2010-1-9
	  */
	 public List queuePhoneNumberInfo(String startNbr, String endNbr);
	/**
	 * 营销审批封锁号码
	 * 被封锁号码状态为 空闲或封锁，否则审批失败
	 * @param an List{[anId]}
	 * @return Map: [ RES: Y 成功  N 失败
	 *                MSG：失败原因描述] 
	 * @author zhangr 2010-1-9                
	 */
	 public Map maketingBlockAn(List an);
	
}
