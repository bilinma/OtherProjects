package com.bilin.thread;

/**
 * wait()的作用是让当前线程由“运行状态”进入“等待(阻塞)状态”的同时，也会释放同步锁。而yield()的作用是让步，它也会让当前线程离开“运行状态”。它们的区别是：
 * (01) wait()是让线程由“运行状态”进入到“等待(阻塞)状态”，而yield()是让线程由“运行状态”进入到“就绪状态”。
 * (02) wait()是会线程释放它所持有对象的同步锁，而yield()方法不会释放锁。
 * @author xiaobin.ma
 *
 */
public class YieldLockTest {

	private static Object obj = new Object();

	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		ThreadA t2 = new ThreadA("t2");
		t1.start();
		t2.start();
	}

	static class ThreadA extends Thread {
		public ThreadA(String name) {
			super(name);
		}

		public void run() {
			// 获取obj对象的同步锁
			synchronized (obj) {
				for (int i = 0; i < 10; i++) {
					System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
					// i整除4时，调用yield
					if (i % 4 == 0)
						Thread.yield();
				}
			}
		}
	}
}