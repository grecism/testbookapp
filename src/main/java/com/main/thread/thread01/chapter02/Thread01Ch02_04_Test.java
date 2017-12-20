package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午2:14:50
 */
public class Thread01Ch02_04_Test {
	/**2.1.4 synchronized与锁对象**/
	//证明线程锁的是对象
	//2.1.4 synchronized与锁对象
	//-1两个线程可一同进入methodA方法
	//-2排队进入方法   在methodA方法前加入了关键字synchronized进行同步处理。
	//-1-2
	//得到结论,调用关键字synchronized声明的方法一定是排队运行的。需要牢记"共享"两个字,只有共享资源的读写访问才需要同步化,
	//如果不是共享资源,根本就没有同步的必要。
	public static void main(String[] args) {
		Thread01Ch02_04_Object object = new Thread01Ch02_04_Object();
		Thread01Ch02_04_A a = new Thread01Ch02_04_A(object);
		a.setName("A");
		Thread01Ch02_04_B b = new Thread01Ch02_04_B(object);
		b.setName("B");
		a.start();
		b.start();
	}
}

class Thread01Ch02_04_Object {
	/*public void methodA(){
		try {
			System.out.println("begin methodA threadName"+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	synchronized public void methodA(){
		try {
			System.out.println("begin methodA threadName"+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_04_A extends Thread{
	private Thread01Ch02_04_Object object;
	
	public Thread01Ch02_04_A(Thread01Ch02_04_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.methodA();
	}
}

class Thread01Ch02_04_B extends Thread{
	private Thread01Ch02_04_Object object;
	
	public Thread01Ch02_04_B(Thread01Ch02_04_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.methodA();
	}
}