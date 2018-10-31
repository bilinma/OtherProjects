package com.bilin.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisJava {
    public static void main(String[] args) {
    	//testRedis();
    	
    	Map root = new HashMap();
		root.put("money", "100");
		root.put("amount", "1000");
		root.put("date", "2018-10-20");
    	
		System.out.println(JSONUtils.toJSONString(root));
    	//testRedisCluster();
    }
    
    
    /**
     * 单节点
     */
    public static void testRedis (){
    	//连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
        
        
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
        
        
        // 获取数据并输出
        Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
    }
    
    public static void testRedisCluster (){
    	String[] serverArray = new String[]{"10.1.75.29:7001","10.1.75.29:7002","10.1.75.29:7003"};
		Set<HostAndPort> nodes = new HashSet<>();
        
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        JedisCluster jedisCluster = new JedisCluster(nodes, 1000,3);
        jedisCluster.set("test", "testvalue");
        System.out.println("redis 存储的字符串为: "+ jedisCluster.get("test"));
        
        String count = jedisCluster.get("rediscount1");
	    System.out.println("count1="+count);
        jedisCluster.incr("rediscount1");
	    String count1 = jedisCluster.get("rediscount1");
	    System.out.println("count1="+count1);
    }
    
    
}
