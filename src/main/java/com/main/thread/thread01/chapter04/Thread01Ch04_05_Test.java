package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午6:35:09
 */
public class Thread01Ch04_05_Test {
	/**4.1.5 使用多个Condition实现通知部分线程:错误用法**/
	//4.1.5 使用多个Condition实现通知部分线程:错误用法
	//-1线程A和线程B都被唤醒。
	//-1如果想单独唤醒部分线程,这时就有必要使用多个Condition对象了,也就是Condition对象可以唤醒部分指定线程,有助于提升程序运行效率。可以先对程序进行分组,
	//然后在唤醒指定组中的线程。
	public static void main(String[] args) {
		try {
			Thread01Ch04_05_Service service = new Thread01Ch04_05_Service();
			Thread01Ch04_05_Thread_A ta = new Thread01Ch04_05_Thread_A(service);
			ta.setName("A");
			ta.start();
			Thread01Ch04_05_Thread_B tb = new Thread01Ch04_05_Thread_B(service);
			tb.setName("B");
			tb.start();
			Thread.sleep(2000);
			service.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_05_Service{
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	public void methodA(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"awaitA begin time="+System.currentTimeMillis());
			c.await();
			System.out.println("threadname="+Thread.currentThread().getName()+"awaitA end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}
	public void methodB(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"awaitB begin time="+System.currentTimeMillis());
			c.await();
			System.out.println("threadname="+Thread.currentThread().getName()+"awaitB end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}
	public void signalAll(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"signalAll  time="+System.currentTimeMillis());
			c.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class Thread01Ch04_05_Thread_A extends Thread{
	private Thread01Ch04_05_Service service;
	public Thread01Ch04_05_Thread_A(Thread01Ch04_05_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodA();
	}
}

class Thread01Ch04_05_Thread_B extends Thread{
	private Thread01Ch04_05_Service service;
	public Thread01Ch04_05_Thread_B(Thread01Ch04_05_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodB();
	}
}














