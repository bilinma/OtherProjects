package aa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.danga.MemCached.MemCachedClient;

public class TestSpringMemcache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] path=new String[]{"context.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(path);
		MemCachedClient client = (MemCachedClient)context.getBean("memcacheClient");
		client.set("aa", "i am zhaoxin");
		System.out.println("hah :"+client.get("aa"));
		for(int i =0;i<10;i++){
			Worker worker = new Worker(i,client);
			worker.start();
		}
	}
	
	private static class Worker extends Thread {   
		private int index ;
		private MemCachedClient client;
		Worker( int index,MemCachedClient client ) {
			this.index = index;
			this.client = client;
		}

		public void run() { 
			for(int i =0;i<1000;i++){
				System.out.println("i am worker["+this.index+"]");
				client.set("worker["+index+"]"+".index"+i, "worker["+index+"]"+".index"+i);
				System.out.println("worker["+this.index+"]:"+client.get("worker["+index+"]"+".index"+i));
			}

		}
	}

}
