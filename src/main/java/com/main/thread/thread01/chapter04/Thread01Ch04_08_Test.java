package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日上午11:05:30
 */
public class Thread01Ch04_08_Test {
	/**4.1.8 实现生产者/消费者模式:多对多交替打印**/
	//4.1.8 实现生产者/消费者模式:多对多交替打印
	//-1出现假死。
	//-1c.signal()不保证signal唤醒的是异类,也许是同类。
	//-2解决假死。
	//-2使用c.signalAll()。
	public static void main(String[] args) {
		try {
			Thread01Ch04_08_Service service = new Thread01Ch04_08_Service();
			Thread01Ch04_08_Thread_A[] taArray = new Thread01Ch04_08_Thread_A[5];
			Thread01Ch04_08_Thread_B[] tbArray = new Thread01Ch04_08_Thread_B[5];
			for (int i = 0; i < 5; i++) {
				taArray[i] = new Thread01Ch04_08_Thread_A(service);
				tbArray[i] = new Thread01Ch04_08_Thread_B(service);
				taArray[i].setName("生产者"+(i+1));
				taArray[i].start();
				tbArray[i].setName("消费者"+(i+1));
				tbArray[i].start();
			}
			
			Thread.sleep(5000);
			Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
			Thread.currentThread().getThreadGroup().enumerate(threadArray);
			for (int i = 0; i < threadArray.length; i++) {
				System.out.println("threadname="+threadArray[i].getName()+"threadstate="+threadArray[i].getState());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_08_Service{
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	private boolean flag = false;
	public void set(){
		try {
			lock.lock();
			while(flag == true){
				System.out.println("有可能**连续");
				c.await();
			}
			System.out.println("打印*");
			flag = true;
			//c.signal();
			c.signalAll();
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
				System.out.println("有可能~~~连续");
				c.await();
			}
			System.out.println("打印~~~");
			flag = false;
			//c.signal();
			c.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
}

class Thread01Ch04_08_Thread_A extends Thread{
	private  Thread01Ch04_08_Service service;
	public Thread01Ch04_08_Thread_A(Thread01Ch04_08_Service service) {
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

class Thread01Ch04_08_Thread_B extends Thread{
	private  Thread01Ch04_08_Service service;
	public Thread01Ch04_08_Thread_B(Thread01Ch04_08_Service service) {
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




















