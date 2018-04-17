package com.main.thread.thread01.chapter04;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_19_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午1:43:42
 */
public class Thread01Ch04_19_Test {
	/**4.1.15 方法awaitUntil()的使用**/
	//4.1.15 方法awaitUntil()的使用
	//-1 10秒后自动唤醒自己
	//-1 10秒后自动唤醒自己
	//-2 2秒后被其他线程所唤醒
	//-2说明线程在等待时间到达前,可以被其他线程提前唤醒。
	public static void main(String[] args) {
		try {
			/*Thread01Ch04_19_Service service = new Thread01Ch04_19_Service();
			Thread01Ch04_19_Thread_A ta = new Thread01Ch04_19_Thread_A(service);
			ta.start();*/
			
			Thread01Ch04_19_Service service = new Thread01Ch04_19_Service();
			Thread01Ch04_19_Thread_A ta = new Thread01Ch04_19_Thread_A(service);
			ta.start();
			Thread.sleep(2000);
			Thread01Ch04_19_Thread_B tb = new Thread01Ch04_19_Thread_B(service);
			tb.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_19_Service{
	private ReentrantLock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	public void methodA(){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, 10);
			lock.lock();
			System.out.println("signalA begin time="+System.currentTimeMillis());
			c.awaitUntil(calendar.getTime());
			System.out.println("signalA end time="+System.currentTimeMillis());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		
	}
	
	public void methodB(){
		try {
			lock.lock();
			System.out.println("signalB begin time="+System.currentTimeMillis());
			c.signalAll();
			System.out.println("signalB end time="+System.currentTimeMillis());

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		
	}
}

class Thread01Ch04_19_Thread_A extends Thread{
	private Thread01Ch04_19_Service service;
	public Thread01Ch04_19_Thread_A(Thread01Ch04_19_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodA();
	}
}

class Thread01Ch04_19_Thread_B extends Thread{
	private Thread01Ch04_19_Service service;
	public Thread01Ch04_19_Thread_B(Thread01Ch04_19_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodB();
	}
}
 	















