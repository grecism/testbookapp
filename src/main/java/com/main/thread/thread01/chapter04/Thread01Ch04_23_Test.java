package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *<p>Title	: Thread01Ch04_23_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午3:46:30
 */
public class Thread01Ch04_23_Test {
	/**4.2.3 类ReentrantReadWriteLock的使用:读写互斥**/
	//4.2.3 类ReentrantReadWriteLock的使用:读写互斥
	//-1获得锁的时间不相同。
	//-1此示例说明"读写"操作是互斥的,下一个示例说明"写读"操作也是互斥的。即只要出现"写操作"的过程,就是互斥的。
	public static void main(String[] args) {
		Thread01Ch04_23_Service service = new Thread01Ch04_23_Service();
		Thread01Ch04_23_Thread_A ta = new Thread01Ch04_23_Thread_A(service);
		ta.setName("A");
		ta.start();
		Thread01Ch04_23_Thread_B tb = new Thread01Ch04_23_Thread_B(service);
		tb.setName("B");
		tb.start();
	}
}

class Thread01Ch04_23_Service{
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public void read(){
		try {
			lock.readLock().lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"获得读锁的时间为:"+System.currentTimeMillis());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.readLock().unlock();
		}
	}
	
	public void write(){
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

class Thread01Ch04_23_Thread_A extends Thread{
	private Thread01Ch04_23_Service service;
	public Thread01Ch04_23_Thread_A(Thread01Ch04_23_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.read();
	}
}

class Thread01Ch04_23_Thread_B extends Thread{
	private Thread01Ch04_23_Service service;
	public Thread01Ch04_23_Thread_B(Thread01Ch04_23_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.write();
	}
}


























