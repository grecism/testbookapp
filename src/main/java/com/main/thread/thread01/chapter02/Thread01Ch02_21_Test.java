package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_21_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月8日下午7:21:17
 */
public class Thread01Ch02_21_Test {
	/**2.2.8 细化验证3个结论**/
	//"synchronized(非this对象x)"格式的写法是将x对象本身作为"对象监视器",这样就可以得出以下3个结论:
	//(1)当多个线程同时执行synchronized(x){}同步代码块时呈同步效果。
	//(2)当其他线程执行x对象中synchronized同步方法时呈同步效果。
	//(3)当其他线程执行x对象方法里面的synchronized(this)代码块时也呈同步效果。
	//但需要注意:如果其他线程调用不加synchronized关键字的方法时,还是异步调用。
	//2.2.8 细化验证3个结论
	//(1)验证当多个线程同时执行synchronized(x){}同步代码块时呈同步效果。
	//-1同步调用
	//-2异步调用
	//-1同步的原因是使用了同一个"对象监视器"。
	//-2异步调用的原因是使用了不同的"对象监视器"。
	public static void main(String[] args) {
		Thread01Ch02_21_Service service = new Thread01Ch02_21_Service();
		Thread01Ch02_21_Object object = new Thread01Ch02_21_Object();
		Thread01Ch02_21_Object object2 = new Thread01Ch02_21_Object();
		Thread01Ch02_21_A a = new Thread01Ch02_21_A(service,object);
		a.setName("a");
		a.start();
		Thread01Ch02_21_B b = new Thread01Ch02_21_B(service,object2);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_21_Object {

}

class Thread01Ch02_21_Service {
	public void method(Thread01Ch02_21_Object object){
		synchronized (object) {
			try {
				System.out.println("method threadname="+Thread.currentThread().getName()+" getLock time="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("method threadname="+Thread.currentThread().getName()+" releaseLock time="+System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread01Ch02_21_A extends Thread{
	private Thread01Ch02_21_Service service;
	private Thread01Ch02_21_Object object;
	
	public Thread01Ch02_21_A(Thread01Ch02_21_Service service,Thread01Ch02_21_Object object) {
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		service.method(object);
	}
}

class Thread01Ch02_21_B extends Thread{
	private Thread01Ch02_21_Service service;
	private Thread01Ch02_21_Object object;
	
	public Thread01Ch02_21_B(Thread01Ch02_21_Service service,Thread01Ch02_21_Object object) {
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		service.method(object);
	}
}
