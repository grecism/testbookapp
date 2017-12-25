package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_11_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午12:18:49
 */
public class Thread01Ch04_11_Test {
	//4.1.10 方法getHoldCount()、getQueueLength()和getWaitQueueLength()的测试
	//-2getQueueLength()
	//-2方法int getQueueLength()的作用是返回正等待获取此锁定的线程估计数,比如 有5个线程,1个线程首先执行await()方法,那么在调用getQueueLength()
	//方法后返回值是4,说明有4个线程同时在等待lock的释放。
	public static void main(String[] args) {
		try {
			Thread01Ch04_11_Service service = new Thread01Ch04_11_Service();
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					service.method();
				}
			};
			Thread[] threadArray = new Thread[10];
			for (int i = 0; i < threadArray.length; i++) {
				threadArray[i] = new Thread(runnable);
				threadArray[i].start();
			}
			Thread.sleep(2000);
			System.out.println("有线程数:"+service.lock.getQueueLength());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_11_Service{
	public ReentrantLock lock = new ReentrantLock();
	public void method(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"进入方法被锁定");
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}














