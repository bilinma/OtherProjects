package com.ucloudlink.redis.topic;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RedisTopicEventListener {

    private static Logger logger = Logger.getLogger(RedisTopicEventListener.class);

    public void getMessage(String message, String channel) {
        logger.info(message);
    }
} 