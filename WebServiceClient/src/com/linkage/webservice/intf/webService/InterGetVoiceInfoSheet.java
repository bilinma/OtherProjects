/**
 * InterGetVoiceInfoSheet_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkage.webservice.intf.webService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterGetVoiceInfoSheet extends Remote {
	
	/**
	 * 华为调用通话历史查询接口，作为智能语音质检的数据来源
	 * @param beginDateTime
	 * @param endDateTime
	 * @param userType
	 * @param cityCode
	 * @param userGrade
	 * @return
	 * @throws RemoteException
	 */
    public String getEmergencyQTVoice(String beginDateTime, String endDateTime, String userType, String cityCode, String userGrade) throws RemoteException;
}
