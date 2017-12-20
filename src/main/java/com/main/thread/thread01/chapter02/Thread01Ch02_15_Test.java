package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_15_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月7日下午3:26:57
 */
public class Thread01Ch02_15_Test {
	/**2.2.5 synchronized代码块间的同步性**/
	//在使用synchronized(this)同步代码块时需要注意的是,当一个线程访问object的一个synchronized同步代码块时,其他线程对同一个object
	//中所有其他synchronized同步代码块的访问将被阻塞,这说明synchronized使用的"对象监视器"是一个。
	//2.2.5 synchronized代码块间的同步性
	//-1两个同步代码块按顺序执行
	//-1两个同步代码块按顺序执行
	public static void main(String[] args) {
		Thread01Ch02_15_Object object = new Thread01Ch02_15_Object();
		Thread01Ch02_15_A a = new Thread01Ch02_15_A(object);
		a.setName("a");
		a.start();
		Thread01Ch02_15_B b = new Thread01Ch02_15_B(object);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_15_Object {
	public void methodA(){
		try {
			synchronized (this) {
				System.out.println("A beginTime="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("A endTime="+System.currentTimeMillis());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void methodB(){
		try {
			synchronized (this) {
				System.out.println("B beginTime="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("B endTime="+System.currentTimeMillis());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_15_A extends Thread{
	private Thread01Ch02_15_Object object;
	
	public Thread01Ch02_15_A(Thread01Ch02_15_Object object) {
		this.object  = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.methodA();
	}
}

class Thread01Ch02_15_B extends Thread{
	private Thread01Ch02_15_Object object;
	
	public Thread01Ch02_15_B(Thread01Ch02_15_Object object) {
		this.object  = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.methodB();
	}
}