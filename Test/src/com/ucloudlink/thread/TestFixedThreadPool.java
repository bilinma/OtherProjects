package com.ucloudlink.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * @author xiaobin.ma
 */
public class TestFixedThreadPool {

	public static void main(String[] args) throws InterruptedException {
		
		/**
		 * CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
		 * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
		 */
		CountDownLatch countDown  = new CountDownLatch(10);
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
		    final int count = i;
		    fixedThreadPool.execute(new Runnable() {
		        @Override
		        public void run() {
		            try {
		            	System.out.println("线程："+Thread.currentThread()+"负责了"+count+"次任务");  
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }finally{
		            	countDown.countDown();
		            }
		        }
		    });
		}
		
		countDown.await();
		fixedThreadPool.shutdown();
		
		/*ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new WorkThread(i);
			//executor.execute(worker);
			executor.submit(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");*/
	}

}
