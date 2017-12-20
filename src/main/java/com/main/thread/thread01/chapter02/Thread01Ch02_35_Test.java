package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_35_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午5:03:36
 */
public class Thread01Ch02_35_Test {
	/**2.2.16 锁对象的改变**/
	//在将任何数据类型作为同步锁时,需要注意的是,是否有多个线程同时持有锁对象,如果同时持有相同的锁对象,则这些线程之间就是同步的;如果分别
	//获得锁对象,这些线程之间就是异步的。
	//2.2.16 锁对象的改变
	//-1异步输出
	//-2同步输出
	//-1因为50毫秒过后B线程取得的锁时"456"。
	//-2线程A和线程B持有的锁都是"123",虽然将锁改成了"456",但结果还是同步的,因为A和B共同争抢到的锁是"123"。
	public static void main(String[] args) {
		try {
			Thread01Ch02_35_Service service = new Thread01Ch02_35_Service();
			Thread01Ch02_35_A a = new Thread01Ch02_35_A(service);
			a.setName("A");
			Thread01Ch02_35_B b = new Thread01Ch02_35_B(service);
			b.setName("B");
			
			a.start();
			//Thread.sleep(50);//存在50毫秒
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_35_Service {
	private String lock = "123";
	
	public void method(){
		synchronized (lock) {
			try {
				System.out.println("在"+System.currentTimeMillis()+" threadname="+Thread.currentThread().getName()+" begin");
				lock = "456";
				Thread.sleep(2000);
				System.out.println("在"+System.currentTimeMillis()+" threadname="+Thread.currentThread().getName()+" end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class Thread01Ch02_35_A extends Thread{
	private Thread01Ch02_35_Service service;
	
	public Thread01Ch02_35_A(Thread01Ch02_35_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.method();
	}
	
}

class Thread01Ch02_35_B extends Thread{
	private Thread01Ch02_35_Service service;
	
	public Thread01Ch02_35_B(Thread01Ch02_35_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.method();
	}
}
