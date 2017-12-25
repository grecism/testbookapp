package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日上午10:36:48
 */
public class Thread01Ch04_07_Test {
	/**4.1.7 实现生产者/消费者模式:一对一交替打印**/
	//4.1.7 实现生产者/消费者模式:一对一交替打印
	//-1交替打印
	//-1通过使用Condition对象,成功实现交替打印效果。
	public static void main(String[] args) {
		Thread01Ch04_07_Service service = new Thread01Ch04_07_Service();
		Thread01Ch04_07_Thread_A ta = new Thread01Ch04_07_Thread_A(service);
		ta.start();
		Thread01Ch04_07_Thread_B tb = new Thread01Ch04_07_Thread_B(service);
		tb.start();
		
	}
}

class Thread01Ch04_07_Service{
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	private boolean flag = false;
	public void set(){
		try {
			lock.lock();
			while(flag == true){
				c.await();
			}
			System.out.println("打印*");
			flag = true;
			c.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void get(){
		try {
			lock.lock();
			while(flag == false){
				c.await();
			}
			System.out.println("打印~~~");
			flag = false;
			c.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
}

class Thread01Ch04_07_Thread_A extends Thread{
	private  Thread01Ch04_07_Service service;
	public Thread01Ch04_07_Thread_A(Thread01Ch04_07_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			service.set();
		}
	}
}

class Thread01Ch04_07_Thread_B extends Thread{
	private  Thread01Ch04_07_Service service;
	public Thread01Ch04_07_Thread_B(Thread01Ch04_07_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			service.get();
		}
	}
}















