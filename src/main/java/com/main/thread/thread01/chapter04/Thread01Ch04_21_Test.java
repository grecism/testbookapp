package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 *<p>Title	: Thread01Ch04_21_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午2:57:56
 */
public class Thread01Ch04_21_Test {
	/**4.2 使用ReentrantReadWriteLock类**/
	//类ReentrantLock具有完全互斥排他的效果,即同一时间只有一个线程在执行ReentrantLock.lock()方法后面的任务。这样做虽然保证了实例变量的线程安全性,但
	//效率却是非常低下的。所以在JDK中提供了一种读写锁ReentrantReadWriteLock类,使用它可以加快运行效率,在某些不需要操作实例变量的方法中,完全可以使用读写
	//锁ReentrantReadWriteLock来提升该方法的代码运行速度。
	//读写锁表示也有两个锁,一个是读操作相关的锁,也称为共享锁;另一个是写操作相关的锁,也叫排他锁。也就是多个读锁之间不互斥,读锁与写锁互斥,写锁与写锁互斥。在没有
	//线程Thread进行写入操作时,进行读取操作的多个Thread都可以获取读锁,而进行写入操作的Thread只有在获取写锁后才能进行写入操作。即多个Thread可以同时进行
	//读取操作,但是同一时刻只允许一个Thread进行写入操作。
	/**4.2.1 类ReentrantReadWriteLock的使用:读读共享**/
	//4.2.1 类ReentrantReadWriteLock的使用:读读共享
	//-1获得锁的时间几乎相同。
	//-1从打印的时间来看,两个线程几乎同时进入lock()方法后面的代码。说明在此使用了lock.readLock()读锁可以提高程序运行效率,允许多个线程同时执行lock()方法
	//后面的代码。
	public static void main(String[] args) {
		Thread01Ch04_21_Service service = new Thread01Ch04_21_Service();
		Thread01Ch04_21_Thread ta = new Thread01Ch04_21_Thread(service);
		ta.start();
		Thread01Ch04_21_Thread tb = new Thread01Ch04_21_Thread(service);
		tb.start();
	}
}

class Thread01Ch04_21_Service{
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public void method(){
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
}

class Thread01Ch04_21_Thread extends Thread{
	private Thread01Ch04_21_Service service;
	public Thread01Ch04_21_Thread(Thread01Ch04_21_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.method();
	}
}
















