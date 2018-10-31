package com.bilin.springmongo.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WbOrder")
public class WbOrder implements Serializable {
	
	private static final long serialVersionUID = -7523232411634112356L;
	@Id  
    private String id;  
	private double rentAmount;
	private double depositAmount;
	private Double amount;
	private double refundRentAmount;
	private double refundDepositAmount;
	private double refundAmount;
	private String cabinetId;
	private String cabinetName;
	private Set<String> checkFailReason = new TreeSet();

	private long checkTime;

	private String deliveryCode;

	private long deliveryTime;

	private long startTime;

	private long expireTime;

	private String imei;

	private String deviceRFID;

	private String operatorId;

	private String operatorName;

	private String orderSN;

	private long returnTime;

	private String rtnCabinetId;

	private String rtnCabinetName;

	private String status;

	private String weChatName;

	private String weChatOpenId;

	private String phone;

	private String remark;

	private Long serviceDuration;

	private String tmlStatus = "";

	private String pkgId;

	private String pkgName;

	private Integer terminalCount = Integer.valueOf(1);
	private String returnRemark;

	public double getRentAmount() {
		return this.rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	public double getDepositAmount() {
		return this.depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public double getRefundRentAmount() {
		return this.refundRentAmount;
	}

	public void setRefundRentAmount(double refundRentAmount) {
		this.refundRentAmount = refundRentAmount;
	}

	public double getRefundDepositAmount() {
		return this.refundDepositAmount;
	}

	public void setRefundDepositAmount(double refundDepositAmount) {
		this.refundDepositAmount = refundDepositAmount;
	}

	public String getCabinetId() {
		return this.cabinetId;
	}

	public void setCabinetId(String cabinetId) {
		this.cabinetId = cabinetId;
	}

	public String getCabinetName() {
		return this.cabinetName;
	}

	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
	}

	public Set<String> getCheckFailReason() {
		return this.checkFailReason;
	}

	public void setCheckFailReason(Set<String> checkFailReason) {
		this.checkFailReason = checkFailReason;
	}

	public long getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(long checkTime) {
		this.checkTime = checkTime;
	}

	public String getDeliveryCode() {
		return this.deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public long getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDeviceRFID() {
		return this.deviceRFID;
	}

	public void setDeviceRFID(String deviceRFID) {
		this.deviceRFID = deviceRFID;
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOrderSN() {
		return this.orderSN;
	}

	public void setOrderSN(String orderSN) {
		this.orderSN = orderSN;
	}

	public long getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(long returnTime) {
		this.returnTime = returnTime;
	}

	public String getRtnCabinetId() {
		return this.rtnCabinetId;
	}

	public void setRtnCabinetId(String rtnCabinetId) {
		this.rtnCabinetId = rtnCabinetId;
	}

	public String getRtnCabinetName() {
		return this.rtnCabinetName;
	}

	public void setRtnCabinetName(String rtnCabinetName) {
		this.rtnCabinetName = rtnCabinetName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWeChatName() {
		return this.weChatName;
	}

	public void setWeChatName(String weChatName) {
		this.weChatName = weChatName;
	}

	public String getWeChatOpenId() {
		return this.weChatOpenId;
	}

	public void setWeChatOpenId(String weChatOpenId) {
		this.weChatOpenId = weChatOpenId;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTmlStatus() {
		return this.tmlStatus;
	}

	public void setTmlStatus(String tmlStatus) {
		this.tmlStatus = tmlStatus;
	}

	public double getRefundAmount() {
		return this.refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Long getServiceDuration() {
		return this.serviceDuration;
	}

	public void setServiceDuration(Long serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public String getPkgId() {
		return this.pkgId;
	}

	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}

	public String getPkgName() {
		return this.pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public Integer getTerminalCount() {
		return this.terminalCount;
	}

	public void setTerminalCount(Integer terminalCount) {
		this.terminalCount = terminalCount;
	}

	public String getReturnRemark() {
		return this.returnRemark;
	}

	public void setReturnRemark(String returnRemark) {
		this.returnRemark = returnRemark;
	}

	public String toString() {
		return "WbOrder [rentAmount=" + this.rentAmount + ", depositAmount=" + this.depositAmount + ", amount="
				+ this.amount + ", refundRentAmount=" + this.refundRentAmount + ", refundDepositAmount="
				+ this.refundDepositAmount + ", refundAmount=" + this.refundAmount + ", cabinetId=" + this.cabinetId
				+ ", cabinetName=" + this.cabinetName + ", checkFailReason=" + this.checkFailReason + ", checkTime="
				+ this.checkTime + ", deliveryCode=" + this.deliveryCode + ", deliveryTime=" + this.deliveryTime
				+ ", startTime=" + this.startTime + ", expireTime=" + this.expireTime + ", imei=" + this.imei
				+ ", deviceRFID=" + this.deviceRFID + ", operatorId=" + this.operatorId + ", operatorName="
				+ this.operatorName + ", orderSN=" + this.orderSN + ", returnTime=" + this.returnTime
				+ ", rtnCabinetId=" + this.rtnCabinetId + ", rtnCabinetName=" + this.rtnCabinetName + ", status="
				+ this.status + ", weChatName=" + this.weChatName + ", weChatOpenId=" + this.weChatOpenId + ", phone="
				+ this.phone + ", remark=" + this.remark + ", serviceDuration=" + this.serviceDuration + ", tmlStatus="
				+ this.tmlStatus + ", pkgId=" + this.pkgId + ", pkgName=" + this.pkgName + ", terminalCount="
				+ this.terminalCount + ", returnRemark=" + this.returnRemark + "]";
	}
}
