package com.bilin.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bilin.service.PushMessageService;

@Component
public class TestTask {
	
	@Autowired
	private PushMessageService pushMessageService;
	
	//一分钟跑一次，每个邮箱每次发30个
	@Scheduled(fixedRate = 60000L)
	public void send() {
		List<String> mvnoIdList = new ArrayList<String>();
		mvnoIdList.add("5b8913504d9b233d6128b84f");
		mvnoIdList.add("5c073d334d9b231a1fd17d3f");
		mvnoIdList.add("5c1779594d9b233302b995f1");
		mvnoIdList.add("5baee68e4d9b2321f9d52356");
		mvnoIdList.add("5b0545924d9b23303883b32c");
		mvnoIdList.add("5affbbd34d9b2319a93f3b43");
		mvnoIdList.add("5a6af5424d9b23758e8b44f1");
		for(int i=0;i<1;i++){
			try {
				for(String mvnoId : mvnoIdList){
					pushMessageService.pushMessageToUcp(mvnoId);
					
					//pushMessageService.sayHello(mvnoId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
