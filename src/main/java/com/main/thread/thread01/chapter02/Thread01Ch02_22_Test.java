package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_22_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日上午11:11:34
 */
public class Thread01Ch02_22_Test {
	//2.2.8 细化验证3个结论
	//验证(2)当其他线程执行x对象中synchronized同步方法时呈同步效果。
	//验证(3)当其他线程执行x对象方法里面的synchronized(this)代码块时也呈同步效果。
	//-3同步效果
	//-3当其他线程执行x对象中synchronized同步方法时呈同步效果。
	//-4同步效果
	//-4当其他线程执行x对象方法里面的synchronized(this)代码块时也呈同步效果。
	public static void main(String[] args) {
		try {
			Thread01Ch02_22_Service service = new Thread01Ch02_22_Service();
			Thread01Ch02_22_Object object = new Thread01Ch02_22_Object();
			Thread01Ch02_22_A a = new Thread01Ch02_22_A(service,object);
			a.setName("a");
			a.start();
			Thread.sleep(200);
			Thread01Ch02_22_B b = new Thread01Ch02_22_B(object);
			b.setName("b");
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_22_Object {
	/*synchronized public void printString(){
		System.out.println("printString threadname="+Thread.currentThread().getName()+" getLock time="+System.currentTimeMillis());
		System.out.println("printString threadname="+Thread.currentThread().getName()+" releaseLock time="+System.currentTimeMillis());

	}*/
	
    public void printString(){
    	synchronized (this) {
    		System.out.println("printString threadname="+Thread.currentThread().getName()+" getLock time="+System.currentTimeMillis());
    		System.out.println("printString threadname="+Thread.currentThread().getName()+" releaseLock time="+System.currentTimeMillis());
		}

	}
}

class Thread01Ch02_22_Service {
	public void method(Thread01Ch02_22_Object object){
		synchronized (object) {
			try {
				System.out.println("printString threadname="+Thread.currentThread().getName()+" getLock time="+System.currentTimeMillis());
				Thread.sleep(5000);
				System.out.println("printString threadname="+Thread.currentThread().getName()+" releaseLock time="+System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread01Ch02_22_A extends Thread{
	private Thread01Ch02_22_Service service;
	private Thread01Ch02_22_Object object;
	
	public Thread01Ch02_22_A(Thread01Ch02_22_Service service,Thread01Ch02_22_Object object) {
		this.service = service;
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		service.method(object);
	}
}

class Thread01Ch02_22_B extends Thread{
	private Thread01Ch02_22_Object object;
	
	public Thread01Ch02_22_B(Thread01Ch02_22_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.printString();
	}
}