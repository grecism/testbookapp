package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_13_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午12:39:20
 */
public class Thread01Ch04_13_Test {
	/**4.1.12 方法isFair()、isHeldByCurrentThread()和isLocked()的测试**/
	//4.1.12 方法isFair()、isHeldByCurrentThread()和isLocked()的测试
	//-1isFair()
	//-1方法boolean isFair()的作用是判断是不是公平锁。默认情况下,ReentrantLock类使用的是非公平锁。
	public static void main(String[] args) {
		final Thread01Ch04_13_Service service = new Thread01Ch04_13_Service(true);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.mehtod();
			}
		};
		Thread thread = new Thread(runnable);
		thread.setName("A");
		thread.start();
		
		final Thread01Ch04_13_Service service2 = new Thread01Ch04_13_Service(false);
		runnable = new Runnable() {
			
			@Override
			public void run() {
				service2.mehtod();
			}
		};
		thread = new Thread(runnable);
		thread.setName("B");
		thread.start();
	}
}

class Thread01Ch04_13_Service{
	private ReentrantLock lock;
	public Thread01Ch04_13_Service(boolean flag) {
		lock = new ReentrantLock(flag);
	}
	public void mehtod(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"是否是公平锁:"+lock.isFair());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}


















