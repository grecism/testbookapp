package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_15_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午12:53:08
 */
public class Thread01Ch04_15_Test {
	/**4.1.3 方法lockInterruptibly(),tryLock()和tryLock(long timeout,TimeUnit unit)的测试**/
	//4.1.3 方法lockInterruptibly(),tryLock()和tryLock(long timeout,TimeUnit unit)的测试
	//-1线程B被中断后没有出现异常,A、B线程正常结束,按钮变灰,使用lock.lock()方法。
	//-2线程B被中断后调用lock.lockInterruptibly()方法报异常。
	//-1-2
	//方法void lockInterruptibly()的作用是如果当线程未被中断,则获取锁定,如果已经被中断则出现异常。
	public static void main(String[] args) {
		try {
			final Thread01Ch04_15_Service service = new Thread01Ch04_15_Service();
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
			tb.interrupt();
			System.out.println("main end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_15_Service{
	private ReentrantLock lock = new ReentrantLock();
	public void method(){
		try {
			//lock.lock();
			lock.lockInterruptibly();
			System.out.println("threadname="+Thread.currentThread().getName()+"lock begin");
			for (int i = 0; i < 100; i++) {
				Math.random();
			}
			System.out.println("threadname="+Thread.currentThread().getName()+"lock end");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println(lock.isHeldByCurrentThread());
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}

	}
}


















