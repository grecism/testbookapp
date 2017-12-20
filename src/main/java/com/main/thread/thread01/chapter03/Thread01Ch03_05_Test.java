package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午1:08:35
 */
public class Thread01Ch03_05_Test {
	//3.1.3 等待/通知机制的实现
	//-3使用等待/通知方法
	//-3使用等待/通知方法
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			Thread01Ch03_05_Thread_A a = new Thread01Ch03_05_Thread_A(lock);
			a.start();
			Thread.sleep(3000);
			Thread01Ch03_05_Thread_B b = new Thread01Ch03_05_Thread_B(lock);
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_05_Thread_A extends Thread{
	private Object lock;
	public Thread01Ch03_05_Thread_A(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		try {
			synchronized (lock) {
				System.out.println("wait begin time="+System.currentTimeMillis());
				lock.wait();
				System.out.println("wait end time="+System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_05_Thread_B extends Thread{
	private Object lock;
	public Thread01Ch03_05_Thread_B(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		try {
			synchronized (lock) {
				System.out.println("notify begin time="+System.currentTimeMillis());
				lock.notify();
				System.out.println("notify end time="+System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}