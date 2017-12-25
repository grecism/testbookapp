package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *<p>Title	: Thread01Ch04_22_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午3:40:23
 */
public class Thread01Ch04_22_Test {
	/**4.2.2 类ReentrantReadWriteLock的使用:写写互斥**/
	//4.2.2 类ReentrantReadWriteLock的使用:写写互斥
	//-1获得锁的时间不相同。
	//-1使用写锁代码lock.writeLock()的效果就是同一时间只允许一个线程执行lock()方法后面的代码。
	public static void main(String[] args) {
		Thread01Ch04_22_Service service = new Thread01Ch04_22_Service();
		Thread01Ch04_22_Thread ta = new Thread01Ch04_22_Thread(service);
		ta.start();
		Thread01Ch04_22_Thread tb = new Thread01Ch04_22_Thread(service);
		tb.start();
	}
}

class Thread01Ch04_22_Service{
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public void method(){
		try {
			lock.writeLock().lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"获得写锁的时间为:"+System.currentTimeMillis());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.writeLock().unlock();
		}
	}
}

class Thread01Ch04_22_Thread extends Thread{
	private Thread01Ch04_22_Service service;
	public Thread01Ch04_22_Thread(Thread01Ch04_22_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.method();
	}
}





















