/**
 * SetIDInfoResponseBo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bss.crm.businessIntf.bmo.impl.bo;

public class SetIDInfoResponseBo  implements java.io.Serializable {
    private int result;

    private java.lang.String resultMsg;

    public SetIDInfoResponseBo() {
    }

    public SetIDInfoResponseBo(
           int result,
           java.lang.String resultMsg) {
           this.result = result;
           this.resultMsg = resultMsg;
    }


    /**
     * Gets the result value for this SetIDInfoResponseBo.
     * 
     * @return result
     */
    public int getResult() {
        return result;
    }


    /**
     * Sets the result value for this SetIDInfoResponseBo.
     * 
     * @param result
     */
    public void setResult(int result) {
        this.result = result;
    }


    /**
     * Gets the resultMsg value for this SetIDInfoResponseBo.
     * 
     * @return resultMsg
     */
    public java.lang.String getResultMsg() {
        return resultMsg;
    }


    /**
     * Sets the resultMsg value for this SetIDInfoResponseBo.
     * 
     * @param resultMsg
     */
    public void setResultMsg(java.lang.String resultMsg) {
        this.resultMsg = resultMsg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetIDInfoResponseBo)) return false;
        SetIDInfoResponseBo other = (SetIDInfoResponseBo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.result == other.getResult() &&
            ((this.resultMsg==null && other.getResultMsg()==null) || 
             (this.resultMsg!=null &&
              this.resultMsg.equals(other.getResultMsg())));
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
        _hashCode += getResult();
        if (getResultMsg() != null) {
            _hashCode += getResultMsg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetIDInfoResponseBo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "SetIDInfoResponseBo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
