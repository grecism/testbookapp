package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_18_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月8日下午5:37:03
 */
public class Thread01Ch02_18_Test {
	//2.2.7 将任意对象作为对象监视器
	//使用"synchronized(非this对象x)同步代码块"格式时,持有不同的对象监视器是异步的效果。
	//验证synchronized(非this对象x)与同步synchronized方法是异步调用的效果。
	//-3执行结果是异步运行效果
	//-3由于对象监视器不同,所以运行结果就是异步的。
	public static void main(String[] args) {
		Thread01Ch02_18_Service service = new Thread01Ch02_18_Service();
		Thread01Ch02_18_A a = new Thread01Ch02_18_A(service);
		a.setName("A");
		a.start();
		Thread01Ch02_18_B b = new Thread01Ch02_18_B(service);
		b.setName("B");
		b.start();
	}
}

class Thread01Ch02_18_Service {
	private String anyString = new String();
	
	public void methodA(){
		try {
			synchronized (anyString) {
				System.out.println("A begin run");
				Thread.sleep(3000);
				System.out.println("A end run");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void methodB(){
		System.out.println("B begin run");
		System.out.println("B end run");
	}
}

class Thread01Ch02_18_A extends Thread{
	private Thread01Ch02_18_Service service;
	
	public Thread01Ch02_18_A(Thread01Ch02_18_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.methodA();
	}
}

class Thread01Ch02_18_B extends Thread{
	private Thread01Ch02_18_Service service;
	
	public Thread01Ch02_18_B(Thread01Ch02_18_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.methodB();
	}
}