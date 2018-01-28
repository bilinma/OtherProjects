package com.linkage.webservice.testCall;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class TestWebService {

	/**
	 * @param args
	 * @throws ServiceException 
	 * @throws MalformedURLException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws ServiceException, MalformedURLException, RemoteException {
		String url = "http://127.0.0.1/sys.LoginWebServWeb/services/SyncSysService";
		Service service = new Service();
		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress(new URL(url));
		call.setOperationName("DataSync");
		call.setTimeout(5000);
		
		String SerialNumber="BSS00000000090";
		String TimeStamp="20110906165351718";
		String interfaceId = "S001";
		String reqBizArgs = 
			"<BizArgs version=\"1\">\n" +
			"  <rows>\n" + 
			"    <sn>890</sn>\n" + 
			"    <sys_flag>BSS</sys_flag>\n" + 
			"    <channel_id>150000235</channel_id>\n" + 
			"    <channel_no>njcs1001</channel_no>\n" + 
			"    <agent_id/>\n" + 
			"    <channel_nm>测试渠道变更</channel_nm>\n" + 
			"    <par_channel_id/>\n" + 
			"    <agent_nm/>\n" + 
			"    <assi_num>0</assi_num>\n" + 
			"    <agent_city/>\n" + 
			"    <concesiones>2</concesiones>\n" + 
			"    <branche_type>1</branche_type>\n" + 
			"    <status>10A</status>\n" + 
			"    <scope/>\n" + 
			"    <address>测试地址变更</address>\n" + 
			"    <remark/>\n" + 
			"    <contact></contact>\n" + 
			"    <tele>68089406</tele>\n" + 
			"    <fax/>\n" + 
			"    <place>1</place>\n" + 
			"    <place_type>1</place_type>\n" + 
			"    <grade>3</grade>\n" + 
			"    <access_type/>\n" + 
			"    <open_time></open_time>\n" + 
			"    <quit_time/>\n" + 
			"    <start_time></start_time>\n" + 
			"    <end_time></end_time>\n" + 
			"    <longitude/>\n" + 
			"    <latitude/>\n" + 
			"    <ownership>1</ownership>\n" + 
			"    <branche_area>110</branche_area>\n" + 
			"    <housing_area/>\n" + 
			"    <rent_paytype/>\n" + 
			"    <rent_starttime/>\n" + 
			"    <rent_endtime/>\n" + 
			"    <area>9</area>\n" + 
			"    <depend_branch/>\n" + 
			"    <eff_date/>\n" + 
			"    <exp_date/>\n" + 
			"    <map_channel_id>150000235</map_channel_id>\n" + 
			"  </rows>\n" + 
			"</BizArgs>";
		String Signature="BA91EE076A50B012D2841503687297CBE4D14A49";
		String CityId="025";
		
		String result = (String)call.invoke(new Object[]{SerialNumber, TimeStamp, interfaceId,reqBizArgs, Signature, CityId});
		System.out.println("result:" + result);

	}

}
