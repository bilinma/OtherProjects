package aa;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.danga.MemCached.SockIOPool.SockIO;

public class SimpleTest {
	protected static MemCachedClient mcc = new MemCachedClient();
	static {
		//部署了两台memcached服务器作水平分布式扩展
//		String[] servers = { "192.168.149.128:11211","192.168.149.128:11212" };
//		Integer[] weights = { 3, 3 };
		String[] servers = { "127.0.0.1:11211" };
		Integer[] weights = { 1 };
		// grab an instance of our connection pool
		SockIOPool pool = SockIOPool.getInstance();

		// set the servers and the weights
		pool.setServers(servers);
		pool.setWeights(weights);


		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);

		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		
		//不检查心跳
		pool.setAliveCheck(false);
		pool.setHashingAlg(pool.NEW_COMPAT_HASH);

		// initialize the connection pool
		pool.initialize();

		//压缩算法比较慢,不要采用
		mcc.setCompressEnable(false);
	}
	
	/**
	 * 简单测试Key，value是否支持中文
	 */
	public static void get() {
		System.out.println("in main get:" + mcc.get("fo乱七八糟，＋＋o2"));
	}

	public static void put() {
		mcc.set("fo乱七八糟，＋＋o2", "=========中国人，====sdfdfd==+++");

	}
	/**
	 * 测试key的值长度不能超过250个字符
	 */
	public static void largeKey(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<51;i++) sb.append("12345");
		String key = sb.toString();
		System.out.println("key.length="+key.length());
		mcc.set(key, new Date());
		System.out.println("get from large key :" + mcc.get(key));
	}
	/**
	 * 测试value不能超过1M
	 */
	public static void largeValue(){
		StringBuffer sb = new StringBuffer(2000000);
		for(int i=0;i<1024*1024;i++) sb.append("1");
		String value = sb.toString();
		System.out.println("value.length="+value.length());
		mcc.set("aa", value);
		System.out.println("get from large value :" + mcc.get("aa"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleTest.put();
		SimpleTest.get();
		
		SimpleTest.largeKey();
		SimpleTest.largeValue();
	}

}
