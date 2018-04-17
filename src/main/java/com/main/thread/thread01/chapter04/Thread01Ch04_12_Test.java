package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_12_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午12:30:34
 */
public class Thread01Ch04_12_Test {
	//4.1.10 方法getHoldCount()、getQueueLength()和getWaitQueueLength()的测试
	//-3getWaitQueueLength()
	//-3方法int getWaitQueueLength(Condition condition)的作用是返回等待与此锁定相关的给定条件Condition的线程估计数,比如有5个线程,每个线程都
	//执行了同一个condition对象的await(),则调用getWaitQueueLength(Condition condition)方法时返回的int值是5。
	public static void main(String[] args) {
		try {
			final Thread01Ch04_12_Service service = new Thread01Ch04_12_Service();
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					service.await();
				}
			};
			Thread[] threadArray = new Thread[10];
			for (int i = 0; i < threadArray.length; i++) {
				threadArray[i] = new Thread(runnable);
				threadArray[i].start();
			}
			Thread.sleep(2000);
			service.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
}

class Thread01Ch04_12_Service{
	private ReentrantLock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	public void await(){
		try {
			lock.lock();
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
			//c.signal(); //添加9个 不添加10个
			System.out.println("有"+lock.getWaitQueueLength(c)+"个线程被await了并且正在等待Conditon后执行signal");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

















