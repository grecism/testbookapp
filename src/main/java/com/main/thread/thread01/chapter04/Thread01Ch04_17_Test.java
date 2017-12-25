package com.main.thread.thread01.chapter04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_17_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午1:21:14
 */
public class Thread01Ch04_17_Test {
	//4.1.3 方法lockInterruptibly(),tryLock()和tryLock(long timeout,TimeUnit unit)的测试
	//-3ttryLock(long timeout,TimeUnit unit) 线程B超时未获得锁
	//-3方法boolean tryLock(long timeout,TimeUnit unit)的作用是,如果锁定在给定等待时间内没有被另一个线程保持,且当前线程未被中断,则获取该锁定。
	public static void main(String[] args) {
		try {
			Thread01Ch04_17_Service service = new Thread01Ch04_17_Service();
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					System.out.println("threadname="+Thread.currentThread().getName()+"调用method方法的时间:"+System.currentTimeMillis());
					service.method();
				}
			};
			Thread ta = new Thread(runnable);
			ta.setName("A");
			ta.start();
			Thread.sleep(2000);
			Thread tb = new Thread(runnable);
			tb.setName("B");
			tb.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_17_Service{
	private ReentrantLock lock = new ReentrantLock();
	public void method(){
		try {
			if(lock.tryLock(3,TimeUnit.SECONDS)){
				System.out.println("threadname="+Thread.currentThread().getName()+"获取锁定的时间:"+System.currentTimeMillis());
			Thread.sleep(5000);
			}else{
				System.out.println("threadname="+Thread.currentThread().getName()+" 没有获取锁定");

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}
	}
}



















