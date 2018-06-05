package aa;

import com.danga.MemCached.*;
import java.util.*;

public class PerformanceTest {
	public static int runs = 5000;
	public static int start = 1;
	public static int threads = 10;
	public static MemCachedClient mc = new MemCachedClient();

	static {

		String[] serverlist = { "192.168.149.128:11211","192.168.149.128:11212" };
		Integer[] weights = { 3 , 3};

		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(serverlist);
		pool.setWeights(weights);
		pool.setInitConn(threads);
		pool.setMinConn(threads);
		pool.setMaxConn(500);
		pool.setMaintSleep(30);

		pool.setNagle(false);

		pool.setAliveCheck(false);
		// pool.setHashingAlg(pool.NEW_COMPAT_HASH);
		pool.initialize();

		mc.setCompressEnable(false);
	}

	private static class WorkerStat {
		public int start, runs;
		public long setterTime, getterTime;

		public WorkerStat() {
			start = runs = 0;
			setterTime = getterTime = 0;
		}
	}

	public static void main(String[] args) throws Exception {
		for(int i=0;i<2;i++){
			System.out.println("¿ªÊ¼£½£½£½£½£½£½£½£½£½£½£½£½£½£½£½£½£½");
			doPerformance();

			System.out.println("½áÊø£½£½£½£½£½£½£½£½£½£½£½£½£½£½£½£½£½");
		}

	}

	private static void doPerformance() throws InterruptedException {

		WorkerStat[] statArray = new WorkerStat[threads];
		Thread[] threadArray = new Thread[threads];
		WorkerStat totalStat = new WorkerStat();

		long begin = System.currentTimeMillis();

		for (int i = 0; i < threads; i++) {
			statArray[i] = new WorkerStat();
			statArray[i].start = start + i * runs;
			statArray[i].runs = runs;
			threadArray[i] = new SetterThread(statArray[i]);
			threadArray[i].start();
		}

		for (int i = 0; i < threads; i++) {
			threadArray[i].join();
		}
		totalStat.setterTime = System.currentTimeMillis() - begin;

		begin = System.currentTimeMillis();

		for (int i = 0; i < threads; i++) {
			threadArray[i] = new GetterThread(statArray[i]);
			threadArray[i].start();
		}

		for (int i = 0; i < threads; i++) {
			threadArray[i].join();
		}

		totalStat.getterTime = System.currentTimeMillis() - begin;

		System.out.println("Thread\tstart\truns\tset time(ms)\tget time(ms)");
		for (int i = 0; i < threads; i++) {
			System.out.println("" + i + "\t" + statArray[i].start + "\t"
					+ statArray[i].runs + "\t" + statArray[i].setterTime
					+ "\t\t" + statArray[i].getterTime);

			totalStat.runs = totalStat.runs + statArray[i].runs;
		}

		System.out.println("\nAvg\t\t" + runs + "\t" + totalStat.setterTime
				+ "\t\t" + totalStat.getterTime);

		System.out.println("\nTotal\t\t" + totalStat.runs + "\t"
				+ totalStat.setterTime + "\t\t" + totalStat.getterTime);
		System.out.println("\treq/sec\tset - " + 1000 * totalStat.runs
				/ totalStat.setterTime + "\tget - " + 1000 * totalStat.runs
				/ totalStat.getterTime);
		System.out.println("\tms/req\tset - " + totalStat.setterTime*1000.00/totalStat.runs
				+ "\tget - " + totalStat.getterTime*1000.00/totalStat.runs );

	}

	private static class SetterThread extends Thread {
		private WorkerStat stat;

		SetterThread(WorkerStat stat) {
			this.stat = stat;
		}

		public void run() {

			String keyBase = "testKey";
			String value = "This is a test of an object blah blah es, "
					+ "serialization does not seem to slow things down so much.  "
					+ "The gzip compression is horrible horrible performance, "
					+ "so we only use it for very large objects.  "
					+ "I have not done any heavy benchmarking recently";
			long begin = System.currentTimeMillis();
			for (int i = stat.start; i < stat.start + stat.runs; i++) {
				mc.set(keyBase+i, value);
			}
			long end = System.currentTimeMillis();

			stat.setterTime = end - begin;
		}
	}

	private static class GetterThread extends Thread {
		private WorkerStat stat;

		GetterThread(WorkerStat stat) {
			this.stat = stat;
		}

		public void run() {

			String keyBase = "testKey";

			long begin = System.currentTimeMillis();
			for (int i = stat.start; i < stat.start + stat.runs; i++) {
				mc.get(keyBase+i);
			}
			long end = System.currentTimeMillis();

			stat.getterTime = end - begin;
		}
	}
}
