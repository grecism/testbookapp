package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_28_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午1:13:33
 */
public class Thread01Ch02_28_Test {
	//2.2.10 数据类型String的常量池特性
	//将synchronized(string)同步块与String联合使用时,要注意常量池以带来的一些例外。
	//-2死循环
	//-3交替打印
	//-2出现这样的情况就是因为String的两个值都是AA,两个线程持有相同的锁,所以造成线程b不能执行。这就是String常量池所带来的问题。因此在大多数的情况下,同步
	//synchronized代码块都不使用String作为锁对象,而改用其他,比如new Object()实例化一个Object对象,但它并不放入缓存中。
	//-3交替打印的原因是持有的锁不是一个。
	public static void main(String[] args) {
		Thread01Ch02_28_Service service = new Thread01Ch02_28_Service();
		Thread01Ch02_28_A a = new Thread01Ch02_28_A(service);
		a.setName("a");
		a.start();
		Thread01Ch02_28_B b = new Thread01Ch02_28_B(service);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_28_Service {
	/*public void print(String param){
		synchronized (param) {
			try {
				while(true){
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
	public void print(Object param){
		synchronized (param) {
			try {
				while(true){
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread01Ch02_28_A extends Thread{
	private Thread01Ch02_28_Service service;
	
	public Thread01Ch02_28_A(Thread01Ch02_28_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		//service.print("AA");
		service.print(new Object());
	}
}

class Thread01Ch02_28_B extends Thread{
	private Thread01Ch02_28_Service service;
	
	public Thread01Ch02_28_B(Thread01Ch02_28_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		//service.print("AA");
		service.print(new Object());
	}
}