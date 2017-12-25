package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_06_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日上午9:57:51
 */
public class Thread01Ch04_06_Test {
	/**4.1.6 使用多个Condition实现通知部分线程:正确用法**/
	//4.1.6 使用多个Condition实现通知部分线程:正确用法
	//-1线程B没有被唤醒
	//-1使用ReentrantLock对象可以唤醒指定种类的线程,这是控制部分线程行为的方便方式。
	public static void main(String[] args) {
		try {
			Thread01Ch04_06_Service service = new Thread01Ch04_06_Service();
			Thread01Ch04_06_Thread_A ta = new Thread01Ch04_06_Thread_A(service);
			ta.setName("A");
			ta.start();
			Thread01Ch04_06_Thread_B tb = new Thread01Ch04_06_Thread_B(service);
			tb.setName("B");
			tb.start();
			Thread.sleep(2000);
			service.signalAll_A();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_06_Service{
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	public void methodA(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"awaitA begin time="+System.currentTimeMillis());
			c1.await();
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
			c2.await();
			System.out.println("threadname="+Thread.currentThread().getName()+"awaitB end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}
	public void signalAll_A(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"signalAll  time="+System.currentTimeMillis());
			c1.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void signalAll_B(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"signalAll  time="+System.currentTimeMillis());
			c2.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class Thread01Ch04_06_Thread_A extends Thread{
	private Thread01Ch04_06_Service service;
	public Thread01Ch04_06_Thread_A(Thread01Ch04_06_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodA();
	}
}

class Thread01Ch04_06_Thread_B extends Thread{
	private Thread01Ch04_06_Service service;
	public Thread01Ch04_06_Thread_B(Thread01Ch04_06_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodB();
	}
}

