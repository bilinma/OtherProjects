package com.ucloudlink.kafka.test;

import com.ucloudlink.kafka.common.KafkaProperties;
import com.ucloudlink.kafka.producer.KafkaProducer;

public class KafkaProducerDemo {
	public static void main(String[] args) {
		KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
		producerThread.start();
	}
}
