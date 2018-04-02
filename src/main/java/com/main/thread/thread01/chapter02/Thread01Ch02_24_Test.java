package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_24_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日上午11:37:55
 */
public class Thread01Ch02_24_Test {
	//2.2.9 静态同步方法synchronized方法与synchronized(class)代码块
	//验证不是同一把锁(synchronized关键字加到静态方法与非静态方法)
	//-2方法printC()为异步运行
	//-2异步的原因是持有不同的锁,一个是对象锁,另外一个是Class锁,而Class锁可以对类的所有对象实例起作用。
	public static void main(String[] args) {
		Thread01Ch02_24_Service service = new Thread01Ch02_24_Service();
		Thread01Ch02_24_A a = new Thread01Ch02_24_A(service);
		a.setName("a");
		a.start();
		Thread01Ch02_24_B b = new Thread01Ch02_24_B(service);
		b.setName("b");
		b.start();
		Thread01Ch02_24_C c = new Thread01Ch02_24_C(service);
		c.setName("c");
		c.start();
	}
}

class Thread01Ch02_24_Service {
	synchronized public static void printA(){
		try {
			System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printA");
			Thread.sleep(3000);
			System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printA");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	synchronized public static void printB(){
		System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printB");
		System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printB");
	}
	
	synchronized public void printC(){
		System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printC");
		System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printC");
	}
}

class Thread01Ch02_24_A extends Thread{
	private Thread01Ch02_24_Service service;
	
	public Thread01Ch02_24_A(Thread01Ch02_24_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printA();
	}
}

class Thread01Ch02_24_B extends Thread{
	private Thread01Ch02_24_Service service;
	
	public Thread01Ch02_24_B(Thread01Ch02_24_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printB();
	}
}

class Thread01Ch02_24_C extends Thread{
	private Thread01Ch02_24_Service service;
	
	public Thread01Ch02_24_C(Thread01Ch02_24_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printC();
	}
}
