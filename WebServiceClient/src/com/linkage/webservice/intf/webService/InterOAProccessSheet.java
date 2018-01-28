package com.linkage.webservice.intf.webService;

public interface InterOAProccessSheet {

	/**
	 * 
	 * @param serSupplier
	 * @param sheettype
	 * @return
	 */
	public String isAlive(String serSupplier, String sheettype);

	
	/**
	 * 
	 * @param serialNo      客服工单流水号
	 * @param serSupplier   服务提供方
	 * @param serCaller     服务调用方
	 * @param callTime      服务调用时间
	 * @param callerPwd     服务调用方口令/密码
	 * @param replyCorp     回复单位
	 * @param replyPerson   回复人
	 * @param replyDepart   回复人所属部门
	 * @param replyContact  回复人联系方式
	 * @param replyTime     回复时间
	 * @param endResult     最终处理结果
	 * @return
	 * @throws Exception 
	 */
	public String replyWorkSheet(String serialNo, String serSupplier,
			String serCaller, String callTime, String callerPwd,
			String replyCorp, String replyPerson, String replyDepart,
			String replyContact, String replyTime, String endResult) throws Exception;
	
	
	/**
	 * 
	 * @param serialNo      客服工单流水号
	 * @param serSupplier   服务提供方
	 * @param serCaller     服务调用方
	 * @param callTime      服务调用时间
	 * @param callerPwd     服务调用方口令/密码
	 * @param replyCorp     回复单位
	 * @param replyPerson   回复人
	 * @param replyDepart   回复人所属部门
	 * @param replyContact  回复人联系方式
	 * @param replyTime     回复时间
	 * @param endResult     阶段处理结果
	 * @return
	 * @throws Exception 
	 */
	public String notifyWorkfSheet(String serialNo, String serSupplier,
			String serCaller, String callTime, String callerPwd,
			String replyCorp, String replyPerson, String replyDepart,
			String replyContact, String replyTime, String endResult)throws Exception;
	
	/**
	 * 
	 * @param serialNo      客服工单流水号
	 * @param serSupplier   服务提供方
	 * @param serCaller     服务调用方
	 * @param callTime      服务调用时间
	 * @param callerPwd     服务调用方口令/密码
	 * @param replyCorp     退单回复单位
	 * @param replyPerson   退单回复人
	 * @param replyDepart   退单回复人所属部门
	 * @param replyContact  退单回复人联系方式
	 * @param replyTime     退单回复时间
	 * @param endResult     退单处理结果
	 * @return
	 * @throws Exception 
	 */
	public String untreadWorkSheet(String serialNo, String serSupplier,
			String serCaller, String callTime, String callerPwd,
			String replyCorp, String replyPerson, String replyDepart,
			String replyContact, String replyTime, String endResult)throws Exception;

}
