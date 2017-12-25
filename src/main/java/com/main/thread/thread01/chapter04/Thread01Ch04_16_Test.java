package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_16_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午1:14:48
 */
public class Thread01Ch04_16_Test {
	//4.1.3 方法lockInterruptibly(),tryLock()和tryLock(long timeout,TimeUnit unit)的测试
	//-2tryLock()
	//-2方法boolean tryLock()的作用是,仅在调用时锁定未被另一个线程保持的情况下,才获取该锁定。
	public static void main(String[] args) {
		try {
			Thread01Ch04_16_Service service = new Thread01Ch04_16_Service();
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
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

class Thread01Ch04_16_Service{
	private ReentrantLock lock = new ReentrantLock();
	public void method(){
		if(lock.tryLock()){
			System.out.println("threadname="+Thread.currentThread().getName()+"获得锁");
		}else{
			System.out.println("threadname="+Thread.currentThread().getName()+"没有获得锁");
		}
	}
}






















