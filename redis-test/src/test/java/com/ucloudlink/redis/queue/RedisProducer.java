package com.ucloudlink.redis.queue;

import redis.clients.jedis.Jedis;

public class RedisProducer {
    /** 
     * jedis操作List 
     */  
    public static void main(String[] args){
 
    	Jedis jedis = new Jedis("localhost");
        for(int i = 0;i<10;i++) {
            Long size = jedis.lpush("informList", "value_" + i);
            System.out.println("当前未被处理消息条数为:" + size);
        }
        jedis.close();
    }

}
