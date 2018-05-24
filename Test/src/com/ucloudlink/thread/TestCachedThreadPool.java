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
				//下面这行代码注释的话，线程池会新建10个线程，不注释的话，因为会复用老线程，不会产生10个线程 
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					 System.out.println("线程："+Thread.currentThread()+"负责了"+index+"次任务");  
				}
			});
		}
	}

}
