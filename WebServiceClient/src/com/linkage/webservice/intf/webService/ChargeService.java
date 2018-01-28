package com.linkage.webservice.intf.webService;

import java.rmi.RemoteException;

public interface ChargeService extends java.rmi.Remote{

	/**
	 * 算费接口
	 * @author fanxiang
	 * @param olId        购物车ID
	 * @param areaCode    地区编码（如：025）
	 * @param channelId   受理渠道ID
	 * @param staffId     受理员工ID
	 * @return String JSON格式：{resultCode :"",chargeTotal :"",resultMsg :""}   
	 */
	public String calChargeTotalInfo(String olId,String areaCode,
			String channelId,String staffId) throws RemoteException;
	
	/**
	 * 算费详情接口
	 * @param olId
	 * @param areaCode
	 * @param channelId
	 * @param staffId
	 * @return
	 * @throws RemoteException
	 */
	public String calChargeDetailInfo(String olId,String areaCode,
			String channelId,String staffId) throws RemoteException;
	
}
