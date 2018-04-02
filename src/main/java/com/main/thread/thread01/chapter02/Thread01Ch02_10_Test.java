package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_10_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午5:42:49
 */
public class Thread01Ch02_10_Test {
	/**2.1.8 同步不具有继承性**/
	//同步不可以继承
	//2.1.8 同步不具有继承性
	//-1子类产生非同步调用
	//-1同步不能继承
	//-2父类子类都可以同步调用  在子类的方法中添加synchronized关键字
	//-2父类子类都可以同步调用
	public static void main(String[] args) {
		try {
			Thread01Ch02_10_Sub sub = new Thread01Ch02_10_Sub();
			Thread01Ch02_10_A a = new Thread01Ch02_10_A(sub);
			a.setName("A");
			a.start();
			Thread.sleep(500);
			Thread01Ch02_10_B b = new Thread01Ch02_10_B(sub);
			b.setName("B");
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_10_Prarent {
	synchronized public void serviceMethod(){
		try {
			System.out.println("init parent begin threadname="+Thread.currentThread().getName()+" run time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("init parent end threadname="+Thread.currentThread().getName()+" run time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_10_Sub extends Thread01Ch02_10_Prarent{
	/*@Override
	public  void serviceMethod() {
		try {
			System.out.println("init sub begin threadname="+Thread.currentThread().getName()+" run time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("init sub end threadname="+Thread.currentThread().getName()+" run time="+System.currentTimeMillis());
			super.serviceMethod();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	synchronized public  void serviceMethod() {
		try {
			System.out.println("init sub begin threadname="+Thread.currentThread().getName()+" run time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("init sub end threadname="+Thread.currentThread().getName()+" run time="+System.currentTimeMillis());
			super.serviceMethod();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_10_A extends Thread{
	private Thread01Ch02_10_Sub sub;
	
	public Thread01Ch02_10_A(Thread01Ch02_10_Sub sub) {
		this.sub = sub;
	}
	
	@Override
	public void run() {
		super.run();
		sub.serviceMethod();
	}
}

class Thread01Ch02_10_B extends Thread{
	private Thread01Ch02_10_Sub sub;
	
	public Thread01Ch02_10_B(Thread01Ch02_10_Sub sub) {
		this.sub = sub;
	}
	
	@Override
	public void run() {
		super.run();
		sub.serviceMethod();
	}
}