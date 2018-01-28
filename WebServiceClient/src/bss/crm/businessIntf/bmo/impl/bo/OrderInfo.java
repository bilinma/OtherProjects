/**
 * OrderInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bss.crm.businessIntf.bmo.impl.bo;

public class OrderInfo  implements java.io.Serializable {
    private int actionType;

    private bss.crm.businessIntf.bmo.impl.bo.Prop[] properties;

    private java.lang.String serviceId;

    public OrderInfo() {
    }

    public OrderInfo(
           int actionType,
           bss.crm.businessIntf.bmo.impl.bo.Prop[] properties,
           java.lang.String serviceId) {
           this.actionType = actionType;
           this.properties = properties;
           this.serviceId = serviceId;
    }


    /**
     * Gets the actionType value for this OrderInfo.
     * 
     * @return actionType
     */
    public int getActionType() {
        return actionType;
    }


    /**
     * Sets the actionType value for this OrderInfo.
     * 
     * @param actionType
     */
    public void setActionType(int actionType) {
        this.actionType = actionType;
    }


    /**
     * Gets the properties value for this OrderInfo.
     * 
     * @return properties
     */
    public bss.crm.businessIntf.bmo.impl.bo.Prop[] getProperties() {
        return properties;
    }


    /**
     * Sets the properties value for this OrderInfo.
     * 
     * @param properties
     */
    public void setProperties(bss.crm.businessIntf.bmo.impl.bo.Prop[] properties) {
        this.properties = properties;
    }

    public bss.crm.businessIntf.bmo.impl.bo.Prop getProperties(int i) {
        return this.properties[i];
    }

    public void setProperties(int i, bss.crm.businessIntf.bmo.impl.bo.Prop _value) {
        this.properties[i] = _value;
    }


    /**
     * Gets the serviceId value for this OrderInfo.
     * 
     * @return serviceId
     */
    public java.lang.String getServiceId() {
        return serviceId;
    }


    /**
     * Sets the serviceId value for this OrderInfo.
     * 
     * @param serviceId
     */
    public void setServiceId(java.lang.String serviceId) {
        this.serviceId = serviceId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrderInfo)) return false;
        OrderInfo other = (OrderInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.actionType == other.getActionType() &&
            ((this.properties==null && other.getProperties()==null) || 
             (this.properties!=null &&
              java.util.Arrays.equals(this.properties, other.getProperties()))) &&
            ((this.serviceId==null && other.getServiceId()==null) || 
             (this.serviceId!=null &&
              this.serviceId.equals(other.getServiceId())));
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
        _hashCode += getActionType();
        if (getProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServiceId() != null) {
            _hashCode += getServiceId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "OrderInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "actionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("properties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "properties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "Prop"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bo.impl.bmo.businessIntf.crm.bss", "serviceId"));
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
