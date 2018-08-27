package com.ucloudlink.redis.queue;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisConsumer {

	/**
	 * jedis操作List
	 */
	public static void main(String[] args) {
		RedisConsumer redisConsumer = new RedisConsumer();
		ScheduleMQ mq = redisConsumer.new ScheduleMQ();
		mq.start();
		ScheduleMQ mq2 = redisConsumer.new ScheduleMQ();
		mq2.start();
	}

	class ScheduleMQ extends Thread {
		@Override
		public void run() {
			while (true) {
				Jedis jedis = new Jedis("localhost");
				// 阻塞式brpop，List中无数据时阻塞       
				// 参数0表示一直阻塞下去，直到List出现数据
				List<String> messages = jedis.brpop(0, "informList");
				
				//由于该指令可以监听多个Key,所以返回的是一个列表
				//列表由2项组成，1) 列表名，2)数据
				String keyName = messages.get(0);
				//如果返回的是MESSAGE_KEY的消息
				if("informList".equals(keyName)) {
					String message = messages.get(1);
					System.out.println("消费****"+message);
				}
				jedis.close();
			}
		}
	}

}
