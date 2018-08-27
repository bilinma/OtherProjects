package com.ucloudlink.redis.topic;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;

public class Publisher {
	
    public static final String CHANNEL_KEY = "channel:message";
    private Jedis jedis = new Jedis("localhost");
 
    public void publishMessage(String message) {
        if(StringUtils.isBlank(message)) {
            return;
        }
        jedis.publish(CHANNEL_KEY, message);
    }
 
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publishMessage("Hello Redis!");
    }

}
