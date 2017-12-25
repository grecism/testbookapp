package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *<p>Title	: Thread01Ch04_24_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午3:54:33
 */
public class Thread01Ch04_24_Test {
	/**4.2.3 类ReentrantReadWriteLock的使用:写读互斥**/
	//4.2.3 类ReentrantReadWriteLock的使用:写读互斥
	//-1获得锁的时间不相同。
	//-1此示例说明"写读"操作是互斥的,"读写","写读","写写"都是互斥的;而"读读"是异步的,非互斥的。
	public static void main(String[] args) {
		try {
			Thread01Ch04_24_Service service = new Thread01Ch04_24_Service();
			Thread01Ch04_24_Thread_B tb = new Thread01Ch04_24_Thread_B(service);
			tb.setName("B");
			tb.start();
			Thread.sleep(2000);
			Thread01Ch04_24_Thread_A ta = new Thread01Ch04_24_Thread_A(service);
			ta.setName("A");
			ta.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch04_24_Service{
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

class Thread01Ch04_24_Thread_A extends Thread{
	private Thread01Ch04_24_Service service;
	public Thread01Ch04_24_Thread_A(Thread01Ch04_24_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.read();
	}
}

class Thread01Ch04_24_Thread_B extends Thread{
	private Thread01Ch04_24_Service service;
	public Thread01Ch04_24_Thread_B(Thread01Ch04_24_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.write();
	}
}



















