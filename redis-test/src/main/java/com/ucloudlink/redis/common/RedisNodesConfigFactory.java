package com.ucloudlink.redis.common;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;

public class RedisNodesConfigFactory implements FactoryBean<Set<RedisNode>> ,InitializingBean{
	
	private String nodes;
	
	private Set<RedisNode> redisNodes;
	
	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	@Override
	public Set<RedisNode> getObject() throws Exception {
		return redisNodes;
	}

	@Override
	public Class<?> getObjectType() {
		return Set.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] serverArray = nodes.split("\\|");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
		System.out.println(this.nodes);
		Set<RedisNode> redisNodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            redisNodes.add(new RedisClusterNode(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        this.redisNodes = redisNodes;
        
	}

}
