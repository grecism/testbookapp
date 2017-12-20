package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_25_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日上午11:49:04
 */
public class Thread01Ch02_25_Test {
	//2.2.9 静态同步方法synchronized方法与synchronized(class)代码块
	//验证Class锁可以对类的所有对象实例起作用。
	//-3虽然是不同对象但静态的同步方法还是同步运行。
	//-3虽然是不同对象但静态的同步方法还是同步运行。
	public static void main(String[] args) {
		Thread01Ch02_24_Service service = new Thread01Ch02_24_Service();
		Thread01Ch02_24_Service service2 = new Thread01Ch02_24_Service();
		Thread01Ch02_25_A a = new Thread01Ch02_25_A(service);
		a.setName("a");
		a.start();
		Thread01Ch02_25_B b = new Thread01Ch02_25_B(service2);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_25_A extends Thread{
	private Thread01Ch02_24_Service service;
	
	public Thread01Ch02_25_A(Thread01Ch02_24_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printA();
	}
}

class Thread01Ch02_25_B extends Thread{
	private Thread01Ch02_24_Service service;
	
	public Thread01Ch02_25_B(Thread01Ch02_24_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printB();
	}
}