/**
 * AutoServiceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bss.crm.businessIntf.bmo.impl.bo;

public class AutoServiceResponse  implements java.io.Serializable {
    private java.lang.String accNbr;

    private java.lang.String areaCode;

    private java.lang.String channelId;

    private int result;

    private java.lang.String resultMsg;

    private java.lang.String staffCode;

    public AutoServiceResponse() {
    }

    public AutoServiceResponse(
           java.lang.String accNbr,
           java.lang.String areaCode,
           java.lang.String channelId,
           int result,
           java.lang.String resultMsg,
           java.lang.String staffCode) {
           this.accNbr = accNbr;
           this.areaCode = areaCode;
           this.channelId = channelId;
           this.result = result;
           this.resultMsg = resultMsg;
           this.staffCode = staffCode;
    }


    /**
     * Gets the accNbr value for this AutoServiceResponse.
     * 
     * @return accNbr
     */
    public java.lang.String getAccNbr() {
        return accNbr;
    }


    /**
     * Sets the accNbr value for this AutoServiceResponse.
     * 
     * @param accNbr
     */
    public void setAccNbr(java.lang.String accNbr) {
        this.accNbr = accNbr;
    }


    /**
     * Gets the areaCode value for this AutoServiceResponse.
     * 
     * @return areaCode
     */
    public java.lang.String getAreaCode() {
        return areaCode;
    }


    /**
     * Sets the areaCode value for this AutoServiceResponse.
     * 
     * @param areaCode
     */
    public void setAreaCode(java.lang.String areaCode) {
        this.areaCode = areaCode;
    }


    /**
     * Gets the channelId value for this AutoServiceResponse.
     * 
     * @return channelId
     */
    public java.lang.String getChannelId() {
        return channelId;
    }


    /**
     * Sets the channelId value for this AutoServiceResponse.
     * 
     * @param channelId
     */
    public void setChannelId(java.lang.String channelId) {
        this.channelId = channelId;
    }


    /**
     * Gets the result value for this AutoServiceResponse.
     * 
     * @return result
     */
    public int getResult() {
        return result;
    }


    /**
     * Sets the result value for this AutoServiceResponse.
     * 
     * @param result
     */
    public void setResult(int result) {
        this.result = result;
    }


    /**
     * Gets the resultMsg value for this AutoServiceResponse.
     * 
     * @return resultMsg
     */
    public java.lang.String getResultMsg() {
        return resultMsg;
    }


    /**
     * Sets the resultMsg value for this AutoServiceResponse.
     * 
     * @param resultMsg
     */
    public void setResultMsg(java.lang.String resultMsg) {
        this.resultMsg = resultMsg;
    }


    /**
     * Gets the staffCode value for this AutoServiceResponse.
     * 
     * @return staffCode
     */
    public java.lang.String getStaffCode() {
        return staffCode;
    }


    /**
     * Sets the staffCode value for this AutoServiceResponse.
     * 
     * @param staffCode
     */
    public void setStaffCode(java.lang.String staffCode) {
        this.staffCode = staffCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutoServiceResponse)) return false;
        AutoServiceResponse other = (AutoServiceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accNbr==null && other.getAccNbr()==null) || 
             (this.accNbr!=null &&
              this.accNbr.equals(other.getAccNbr()))) &&
            ((this.areaCode==null && other.getAreaCode()==null) || 
             (this.areaCode!=null &&
              this.areaCode.equals(other.getAreaCode()))) &&
            ((this.channelId==null && other.getChannelId()==null) || 
             (this.channelId!=null &&
              this.channelId.equals(other.getChannelId()))) &&
            this.result == other.getResult() &&
            ((this.resultMsg==null && other.getResultMsg()==null) || 
             (this.resultMsg!=null &&
              this.resultMsg.equals(other.getResultMsg()))) &&
            ((this.staffCode==null && other.getStaffCode()==null) || 
             (this.staffCode!=null &&
              this.staffCode.equals(other.getStaffCode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAccNbr() != null) {
            _hashCode += getAccNbr().hashCode();
        }
        if (getAreaCode() != null) {
            _hashCode += getAreaCode().hashCode();
        }
        if (getChannelId() != null) {
            _hashCode += getChannelId().hashCode();
        }
        _hashCode += getResult();
        if (getResultMsg() != null) {
            _hashCode += getResultMsg().hashCode();
        }
        if (getStaffCode() != null) {
            _hashCode += getStaffCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutoServiceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "AutoServiceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accNbr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "accNbr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "areaCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("channelId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "channelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "resultMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("staffCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "staffCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
