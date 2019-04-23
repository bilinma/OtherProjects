package com.bilin.common;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
	private Logger logger = Logger.getLogger(ThreadPoolConfig.class);

	@Value("${threadPool.corePoolSize}")
	private int corePoolSize;

	@Value("${threadPool.maxPoolSize}")
	private int maxPoolSize;

	@Value("${threadPool.queueCapacity}")
	private int queueCapacity;

	@Value("${threadPool.keepAlive}")
	private int keepAlive;

	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(this.corePoolSize);
		executor.setMaxPoolSize(this.maxPoolSize);
		executor.setQueueCapacity(this.queueCapacity);
		executor.setThreadNamePrefix("msgExecutor-");
		executor.setKeepAliveSeconds(this.keepAlive);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		this.logger.info("Initialize Msg Thread Pool End!");
		return executor;
	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
