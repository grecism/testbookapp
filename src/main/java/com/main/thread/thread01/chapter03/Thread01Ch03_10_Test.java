package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_10_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午4:41:08
 */
public class Thread01Ch03_10_Test {
	/**3.1.6 只通知一个线程**/
	/**3.1.7 唤醒所有线程**/
	//3.1.6 只通知一个线程
	//3.1.7 唤醒所有线程
	//-1仅有一个线程被唤醒
	//-1调用方法notify()一次只随机通知一个线程进行唤醒。
	//-2全部被唤醒
	//-2当多次调用notify()方法时,会随机将等待wait状态的线程进行唤醒。
	//-3使用notifyAll()唤醒全部线程
	//-3若notify()方法的调用次数小于线程对象的数量,会出现有部分线程对象无法被唤醒的情况。为了唤醒全部线程,可以使用notifyAll()方法。
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			Thread01Ch03_10_Thread_A a = new Thread01Ch03_10_Thread_A(lock);
			a.start();
			Thread01Ch03_10_Thread_B b = new Thread01Ch03_10_Thread_B(lock);
			b.start();
			Thread01Ch03_10_Thread_C c = new Thread01Ch03_10_Thread_C(lock);
			c.start();
			Thread.sleep(2000);
			Thread01Ch03_10_Thread_D d = new Thread01Ch03_10_Thread_D(lock);
			d.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_10_Service{
	public void method(Object lock){
		try {
			synchronized (lock) {
				System.out.println("threadname="+Thread.currentThread().getName()+"wait begin");
				lock.wait();
				System.out.println("threadname="+Thread.currentThread().getName()+"end begin");

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_10_Thread_A extends Thread{
	private Object lock;
	public Thread01Ch03_10_Thread_A(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_10_Service service = new Thread01Ch03_10_Service();
		service.method(lock);
	}
}

class Thread01Ch03_10_Thread_B extends Thread{
	private Object lock;
	public Thread01Ch03_10_Thread_B(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_10_Service service = new Thread01Ch03_10_Service();
		service.method(lock);
	}
}

class Thread01Ch03_10_Thread_C extends Thread{
	private Object lock;
	public Thread01Ch03_10_Thread_C(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_10_Service service = new Thread01Ch03_10_Service();
		service.method(lock);
	}
}

class Thread01Ch03_10_Thread_D extends Thread{
	private Object lock;
	public Thread01Ch03_10_Thread_D(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		synchronized (lock) {
			//lock.notify();
			
			//lock.notify();
			//lock.notify();
			//lock.notify();
			//lock.notify();
			lock.notifyAll();
		}
	}
}









