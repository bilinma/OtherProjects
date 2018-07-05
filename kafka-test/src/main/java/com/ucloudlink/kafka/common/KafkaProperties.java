package com.ucloudlink.kafka.common;

public interface KafkaProperties {
	final static String zkConnect = "127.0.0.1:2181";

	final static String groupId1 = "group1";
	
	final static String groupId2 = "group2";

	final static String topic = "topic1";

	final static String kafkaServerURL = "127.0.0.1";

	final static int kafkaServerPort = 9092;

	final static int kafkaProducerBufferSize = 64 * 1024;

	final static int connectionTimeOut = 20000;

	final static int reconnectInterval = 10000;

	final static String topic2 = "topic2";

	final static String topic3 = "topic3";

	final static String clientId = "SimpleConsumerDemoClient";

}
