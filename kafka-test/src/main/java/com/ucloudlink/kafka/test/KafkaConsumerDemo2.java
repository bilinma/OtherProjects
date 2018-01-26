package com.ucloudlink.kafka.test;

import com.ucloudlink.kafka.common.KafkaProperties;
import com.ucloudlink.kafka.consumer.KafkaConsumer2;

public class KafkaConsumerDemo2 {
	public static void main(String[] args) {
		KafkaConsumer2 consumerThread = new KafkaConsumer2(KafkaProperties.topic);
		consumerThread.start();
	}
}
