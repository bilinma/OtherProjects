/**
 * FlowBusFacecade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkage.webservice.intf.webService;

public interface FlowBusFacecade extends java.rmi.Remote {
    public java.lang.String createProcessInstance(java.lang.String reqXML) throws java.rmi.RemoteException;
    public java.lang.String startProcessInstance(java.lang.String reqXML) throws java.rmi.RemoteException;
    public java.lang.String finishWorkitem(java.lang.String requestXml) throws java.rmi.RemoteException;
    public java.lang.String unlock(java.lang.String requestXml) throws java.rmi.RemoteException;
    public java.lang.String lockOrder(java.lang.String requestXml) throws java.rmi.RemoteException;
    public java.lang.String revokeProcess(java.lang.String requestXml) throws java.rmi.RemoteException;
    public boolean rollBackMsg(java.lang.Long processInstanceId, java.lang.Long reasonId, java.lang.String retDesc) throws java.rmi.RemoteException;
    public java.lang.String touchConstruct(java.lang.String requestXml) throws java.rmi.RemoteException;
    public java.lang.Object getDocument(java.lang.String path) throws java.rmi.RemoteException;
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
}
