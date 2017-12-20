package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午3:18:42
 */
public class Thread01Ch03_08_Test {
	//3.1.4 方法wait()锁释放与notify()锁不释放
	//-3必须执行完notify()方法所在的同步synchronized代码块后才释放锁
	//-3必须执行完notify()方法所在的同步synchronized代码块后才释放锁
	public static void main(String[] args) {
		Object lock = new Object();
		Thread01Ch03_08_Thread_A a = new Thread01Ch03_08_Thread_A(lock);
		a.start();
		Thread01Ch03_08_Thread_B b = new Thread01Ch03_08_Thread_B(lock);
		b.start();
		Thread01Ch03_08_Thread_C c = new Thread01Ch03_08_Thread_C(lock);
		c.start();
		
	}
}

class Thread01Ch03_08_Service{
	public void method(Object lock){
		try {
			synchronized (lock) {
				System.out.println("threadname="+Thread.currentThread().getName()+"wait begin");
				lock.wait();
				System.out.println("threadname="+Thread.currentThread().getName()+"wait end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void methodB(Object lock){
		try {
			synchronized (lock) {
				System.out.println("threadname="+Thread.currentThread().getName()+"notify begin time="+System.currentTimeMillis());
				lock.notify();
				Thread.sleep(5000);
				System.out.println("threadname="+Thread.currentThread().getName()+"notify end time="+System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_08_Thread_A extends Thread{
	private Object lock;
	public Thread01Ch03_08_Thread_A(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_08_Service service = new Thread01Ch03_08_Service();
		service.method(lock);
	}
}

class Thread01Ch03_08_Thread_B extends Thread{
	private Object lock;
	public Thread01Ch03_08_Thread_B(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_08_Service service = new Thread01Ch03_08_Service();
		service.methodB(lock);
	}
}

class Thread01Ch03_08_Thread_C extends Thread{
	private Object lock;
	public Thread01Ch03_08_Thread_C(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_08_Service service = new Thread01Ch03_08_Service();
		service.methodB(lock);
	}
}