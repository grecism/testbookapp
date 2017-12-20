package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_29_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午2:13:23
 */
public class Thread01Ch02_29_Test {
	/**2.2.11 同步synchronized方法无限等待与解决**/
	//同步方法容易造成死循环。
	//2.2.11 同步synchronized方法无限等待与解决
	//-1运行结果是死循环。
	//-2不在出现同步等待的情况。
	//-1线程b永远得不到运行的机会,锁死了。
	//-2使用同步块解决这样的问题。
	public static void main(String[] args) {
		try {
			Thread01Ch02_29_Service service = new Thread01Ch02_29_Service();
			Thread01Ch02_29_A a = new Thread01Ch02_29_A(service);
			a.setName("a");
			a.start();
			Thread.sleep(3000);
			Thread01Ch02_29_B b = new Thread01Ch02_29_B(service);
			b.setName("b");
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_29_Service {
	/*synchronized public void methodA(){
		System.out.println("methodA begin");
		boolean isFlag = true;
		while(isFlag){
			
		}
		System.out.println("methodA end");
	}
	
	synchronized public void methodB(){
		System.out.println("methodB begin");
		System.out.println("methodB end");
	}*/
	Object object = new Object();
	 public void methodA(){
		 synchronized (object) {
			 System.out.println("methodA begin");
				boolean isFlag = true;
				while(isFlag){
					
				}
			 System.out.println("methodA end");
		}
		
	}
	 
	 Object object2 = new Object();
	 public void methodB(){
		synchronized (object2) {
			System.out.println("methodB begin");
			System.out.println("methodB end");
		}
	}
}

class Thread01Ch02_29_A extends Thread{
	private Thread01Ch02_29_Service service;
	
	public Thread01Ch02_29_A(Thread01Ch02_29_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.methodA();
	}
}

class Thread01Ch02_29_B extends Thread{
	private Thread01Ch02_29_Service service;
	
	public Thread01Ch02_29_B(Thread01Ch02_29_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.methodB();
	}
}