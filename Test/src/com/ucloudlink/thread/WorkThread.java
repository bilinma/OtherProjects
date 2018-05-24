package com.ucloudlink.thread;

public class WorkThread implements Runnable{
	
	private int index;


	public WorkThread (){
		System.out.println("create");
	}

	public WorkThread (int index){
		this.index = index;
		System.out.println("create-"+index);
	}
	
	
	@Override
	public void run() {
		 System.out.println(Thread.currentThread().getName()+" Start. index = "+index);
		try {
			Thread.sleep(2000);
			System.out.println(index);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" End.");
	}

}
