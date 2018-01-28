package com.linkage.webservice.intf.webService;

public interface ICustomerService {
	public String businessService(String orderInfo,String areaCode,String channelId,String staffCode);
	
	public bss.crm.businessIntf.bmo.impl.bo.ChangePasswordResponse changePassword(java.lang.String accNbr, int accNbrType, java.lang.String oldPassword, java.lang.String newPassword, int passwordType, int encryptFlag, java.lang.String areaCode, java.lang.String channelId, java.lang.String staffCode) throws java.rmi.RemoteException;
}
