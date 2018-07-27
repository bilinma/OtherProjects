package com.ucloudlink.springmongo.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ucloudlink.springmongo.common.dto.Condition;
import com.ucloudlink.springmongo.common.dto.Formula;
import com.ucloudlink.springmongo.model.WbOrder;
import com.ucloudlink.springmongo.service.user.IWbOrderService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mongodb.xml");
 
		long scopeDay = 6 * 30 *24 * 3600 * 1000L;
		long currentTime = System.currentTimeMillis();
		long createTime = currentTime - scopeDay;
		System.out.println("createTime="+createTime);
		IWbOrderService wbOrderService = (IWbOrderService) context.getBean("wbOrderServiceImpl");
		Condition condition = new Condition();

		condition.addOrItem("expireTime", currentTime, Formula.LE);
		condition.addOrItem("deliveryTime", Long.valueOf(currentTime - 1*3600*1000),Formula.LE);
		condition.addItem("tmlStatus", "NORMAL", Formula.EQ);
		condition.addItem("status", "WAIT_RETURN", Formula.EQ);
		condition.addItem("createTime", createTime, Formula.GE);
		condition.setOrderby("createTime", true);
		condition.setCurrentPage(1);
		condition.setPageSize(10);
		List<WbOrder> orderList = wbOrderService.findByConditionNew(condition);
		for(int i=0 ;i<orderList.size();i++){
			System.out.println(orderList.get(i));
		}
		
	}

}
