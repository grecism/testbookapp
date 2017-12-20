package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午2:47:22
 */
public class Thread01Ch02_05_Test {
	//2.1.4 synchronized与锁对象
	//其他的方法在被调用时会是什么效果?如何查看Lock锁对象的效果?
	//-3线程B异步调用非同步方法
	//-4同步运行 (在methodB()方法前面加上synchronized关键字)
	//-3虽然线程A先持有了object对象的锁,但线程B完全可以异步调用非synchronized类型的方法。
	//-4同步运行 
	//-3-4
	//1)A线程先持有objcet对象的Lock锁,B线程可以以异步的方式调用object对象中的非synchronize类型的方法。
	//2)A线程先持有object对象的Lock锁,B线程如果在这时调用object对象中的synchronized类型的方法则需要等待,也就是同步。
	public static void main(String[] args) {
		Thread01Ch02_05_Object object = new Thread01Ch02_05_Object();
		Thread01Ch02_05_A a = new Thread01Ch02_05_A(object);
		a.setName("A");
		Thread01Ch02_05_B b = new Thread01Ch02_05_B(object);
		b.setName("B");
		a.start();
		b.start();
	}
}

class Thread01Ch02_05_Object {
	synchronized public void methodA(){
		try {
			System.out.println("begin methodA threadName"+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*public void methodB(){
		try {
			System.out.println("begin methodA threadName"+Thread.currentThread().getName()+
					" begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end"+" end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	synchronized public void methodB(){
		try {
			System.out.println("begin methodA threadName"+Thread.currentThread().getName()+
					" begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end"+" end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_05_A extends Thread{
	private Thread01Ch02_05_Object object;
	
	public Thread01Ch02_05_A(Thread01Ch02_05_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.methodA();
	}
}

class Thread01Ch02_05_B extends Thread{
	private Thread01Ch02_05_Object object;
	
	public Thread01Ch02_05_B(Thread01Ch02_05_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.methodB();
	}
}