package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午6:23:13
 */
public class Thread01Ch04_04_Test {
	/**4.1.4 正确使用Condition实现等待/通知**/
	//4.1.4 正确使用Condition实现等待/通知
	//-1成功实现等待/通知模式。
	//-1Object类中的wait()方法相当于Condition类中的await()方法。Object类中的wait(long timeout)方法相当于Condition类中的await(long time,TimeUnit unit)
	//方法。Object类中的notify()方法相当于Condition类中的signal()方法。Object类中的notifyAll()方法相当于Condition类中的signalAll()方法。
	public static void main(String[] args) {
		try {
			Thread01Ch04_04_Service service = new Thread01Ch04_04_Service();
			Thread01Ch04_04_Thread t = new Thread01Ch04_04_Thread(service);
			t.start();
			Thread.sleep(3000);
			service.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_04_Service{
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	
	public void await(){
		try {
			lock.lock();
			System.out.println("await begin time="+System.currentTimeMillis());
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void signal(){
		try {
			lock.lock();
			System.out.println("signal begin time="+System.currentTimeMillis());
			c.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class Thread01Ch04_04_Thread extends Thread{
	private Thread01Ch04_04_Service service;
	public Thread01Ch04_04_Thread(Thread01Ch04_04_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.await();
	}
}

















