package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_12_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月7日下午2:47:16
 */
public class Thread01Ch02_12_Test {
	/**2.2.2 synchronized同步代码块的使用**/
	//当两个并发线程访问同一个对象object中的synchronized(this)同步代码块时,一段时间内只能有一个线程被执行,另一个线程必须等待当前线程
	//执行完这个代码块以后才能执行该代码块。
	//2.2.2 synchronized同步代码块的使用
	//-1同步调用
	//-1执行的效率还是没有提高,执行的效果还是同步运行的。
	public static void main(String[] args) {
		Thread01Ch02_12_Object object = new Thread01Ch02_12_Object();
		Thread01Ch02_12_A a = new Thread01Ch02_12_A(object);
		a.setName("a");
		a.start();
		Thread01Ch02_12_B b = new Thread01Ch02_12_B(object);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_12_Object {
	public void method(){
		try {
			synchronized (this) {
				System.out.println("threadname="+Thread.currentThread().getName()+" begin Time="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("threadname="+Thread.currentThread().getName()+" end Time="+System.currentTimeMillis());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_12_A extends Thread{
	private Thread01Ch02_12_Object object;
	
	public Thread01Ch02_12_A(Thread01Ch02_12_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.method();
	}
}

class Thread01Ch02_12_B extends Thread{
	private Thread01Ch02_12_Object object;
	
	public Thread01Ch02_12_B(Thread01Ch02_12_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.method();
	}
}