package com.ucloudlink.kafka.test;

import com.ucloudlink.kafka.common.KafkaProperties;
import com.ucloudlink.kafka.consumer.KafkaConsumer;

public class KafkaConsumerDemo {
	public static void main(String[] args) {
		KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
		consumerThread.start();
	}
}
