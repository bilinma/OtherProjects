package com.ucloudlink.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * @author xiaobin.ma
 */
public class TestCachedThreadPool {

	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(index);
				}
			});
		}
	}

}
