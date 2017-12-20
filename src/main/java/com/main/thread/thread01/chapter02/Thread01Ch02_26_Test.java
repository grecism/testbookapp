package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_26_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日上午11:55:37
 */
public class Thread01Ch02_26_Test {
	//2.2.9 静态同步方法synchronized方法与synchronized(class)代码块
	//验证同步synchronized(this)代码块的作用其实和synchronized static方法的作用一样。
	//-4同步运行
	//-4同步synchronized(this)代码块的作用其实和synchronized static方法的作用一样。
	public static void main(String[] args) {
		Thread01Ch02_26_Service service = new Thread01Ch02_26_Service();
		Thread01Ch02_26_Service service2 = new Thread01Ch02_26_Service();
		Thread01Ch02_26_A a = new Thread01Ch02_26_A(service);
		a.setName("a");
		a.start();
		Thread01Ch02_26_B b = new Thread01Ch02_26_B(service2);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_26_Service {
	 public  void printA(){
		 synchronized (Thread01Ch02_26_Service.class) {
			 try {
					System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printA");
					Thread.sleep(3000);
					System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printA");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		

	}
	
	 public  void printB(){
		 synchronized (Thread01Ch02_26_Service.class) {
			 System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printB");
				System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printB");
		}
		
	}
}

class Thread01Ch02_26_A extends Thread{
	private Thread01Ch02_26_Service service;
	
	public Thread01Ch02_26_A(Thread01Ch02_26_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printA();
	}
}

class Thread01Ch02_26_B extends Thread{
	private Thread01Ch02_26_Service service;
	
	public Thread01Ch02_26_B(Thread01Ch02_26_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.printB();
	}
}