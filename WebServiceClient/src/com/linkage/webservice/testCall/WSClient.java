package com.linkage.webservice.testCall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.xml.namespace.QName;
import javax.xml.rpc.encoding.Deserializer;
import javax.xml.rpc.encoding.DeserializerFactory;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.SimpleDeserializer;
import org.apache.axis.wsdl.gen.Parser;
import org.apache.axis.wsdl.symbolTable.BaseType;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.Parameter;
import org.apache.axis.wsdl.symbolTable.Parameters;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.axis.wsdl.symbolTable.SymTabEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.symbolTable.TypeEntry;
import org.apache.axis.wsdl.toJava.Utils;
import org.apache.log4j.Logger;

public class WSClient {
	private static Logger logger = Logger.getLogger(WSClient.class);
	private Parser wsdlParser = null;
	@SuppressWarnings("unchecked")
	private Map services = null;

	public WSClient(String wsdlURL) throws Exception {
		this.wsdlParser = new Parser();
		this.wsdlParser.run(wsdlURL);
		this.services = enumSymTabEntry(ServiceEntry.class);
	}
	
	//不可行
	public String simpleInvoke ( String URL, String method, String parameters ) throws Exception {
		//分解参数
		String [] paras = parameters.split(",");
		int len = paras.length;
		String paraStrs = "";
		for ( int i = 0; i<len; i++ ) {
			if ( i < len -1 ) {
				paraStrs = paraStrs + "\"" + paras[i] + "\"," ;
			} else {
				paraStrs = paraStrs + "\"" + paras[i] + "\"" ;
			}
		}
		
		logger.info("ws-paras:" + paraStrs ); 
		Object [] objs = { paraStrs };
		
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(URL));
		call.setOperationName(method);
//		call.addParameter("infType", org.apache.axis.Constants.XSD_STRING,ParameterMode.IN);
//		call.setReturnType(org.apache.axis.Constants.XSD_STRING);
		
		String ret = (String) call.invoke(objs);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public String invoke(String methodName, String value) throws Exception {
		String serviceName = null;
		String portName = null;
		String operationName = null;
		Iterator iPort;
		Iterator iOperation;
		
		for (Iterator iService = this.services.keySet().iterator(); iService
				.hasNext();) {
			serviceName = (String) iService.next();
			ServiceEntry serviceEntry = (ServiceEntry) this.services
					.get(serviceName);

			Map ports = serviceEntry.getService().getPorts();
			for ( iPort = ports.keySet().iterator(); iPort.hasNext();) {
				portName = (String) iPort.next();
				BindingEntry entry = getBindingEntry(serviceName, portName);
				Set operations = entry.getOperations();
				for ( iOperation = operations.iterator(); iOperation
						.hasNext();) {
					Operation o = (Operation) iOperation.next();
					operationName = o.getName();

					if (methodName.equalsIgnoreCase(operationName))
						return invoke(serviceName, portName, operationName,
								value);
				}
			}
		}
		
		throw new Exception("no \"" + methodName + "\" this method");
	}

	@SuppressWarnings("unchecked")
	private String invoke(String serviceName, String portName,
			String operationName, String parameterValue) throws Exception {
		Vector inputs = new Vector();
		ServiceEntry serviceEntry = (ServiceEntry) this.services
				.get(serviceName);
		javax.wsdl.Service service = serviceEntry.getService();
		org.apache.axis.client.Service clientService = new org.apache.axis.client.Service(
				this.wsdlParser, service.getQName());
		javax.xml.rpc.Call call = clientService.createCall(QName
				.valueOf(portName), QName.valueOf(operationName));

		((org.apache.axis.client.Call) call).setTimeout(new Integer(15000));
		BindingEntry bindingEntry = getBindingEntry(serviceName, portName);
		Operation o = getOperation(bindingEntry, operationName);
		Parameters parameters = bindingEntry.getParameters(o);
		Parameter p = (Parameter) parameters.list.get(0);
		inputs.add(getParamData((org.apache.axis.client.Call) call, p,
				parameterValue));

		Object ret = call.invoke(inputs.toArray());

		if (ret != null) {
			return (String) ret;
		}
		return null;
	}

	private BindingEntry getBindingEntry(String serviceName, String portName) {
		ServiceEntry serviceEntry = (ServiceEntry) this.services.get(serviceName);
		Port port = serviceEntry.getService().getPort(portName);
		Binding binding = port.getBinding();
		SymbolTable table = this.wsdlParser.getSymbolTable();
		return table.getBindingEntry(binding.getQName());
	}

	@SuppressWarnings("unchecked")
	private Operation getOperation(BindingEntry entry, String operationName) {
		Set operations = entry.getOperations();
		for (Iterator i = operations.iterator(); i.hasNext();) {
			Operation o = (Operation) i.next();
			if (operationName.equals(o.getName())) {
				return o;
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map enumSymTabEntry(Class cls) {
		HashMap ret = new HashMap();
		HashMap map = this.wsdlParser.getSymbolTable().getHashMap();
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			QName key = (QName) entry.getKey();
			Vector v = (Vector) entry.getValue();
			int size = v.size();
			for (int i = 0; i < size; ++i) {
				SymTabEntry symTabEntry = (SymTabEntry) v.elementAt(i);
				if (cls.isInstance(symTabEntry)) {
					logger.info(key.getLocalPart() + "=" + symTabEntry.getQName());
					ret.put(key.getLocalPart(), symTabEntry);
				}
			}
		}

		return ret;
	}

	private Object getParamData(org.apache.axis.client.Call c, Parameter p,
			String arg) throws Exception {
		QName paramType = Utils.getXSIType(p);
		TypeEntry type = p.getType();
		if ((type instanceof BaseType) && (((BaseType) type).isBaseType())) {
			DeserializerFactory factory = c.getTypeMapping().getDeserializer(
					paramType);

			Deserializer deserializer = factory
					.getDeserializerAs("Axis SAX Mechanism");

			if (deserializer instanceof SimpleDeserializer) {
				return ((SimpleDeserializer) deserializer).makeValue(arg);
			}
		}
		throw new RuntimeException("not know how to convert '" + arg + "' into " + c);
	}

	public static void main(String[] args) {
		try {
			String wsdlUrl = "http://132.228.213.26:9080/ODPROJ/services/RECServer?wsdl";
			String operation = "revMsgFromRes";
			String param = "报文";
			Service service = new Service(); 
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
			call.setOperationName(operation);
			String ret = (String) call.invoke(new Object[]{param});
			System.out.println("~~~~~~~~~~~~~~~~~~~"+ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

