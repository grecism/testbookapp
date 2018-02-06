package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_18_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午1:31:49
 */
public class Thread01Ch04_18_Test {
	/**4.1.14 方法awaitUninterruptibly()**/
	//4.1.14 方法awaitUninterruptibly()
	//-1程序运行后出现异常。
	//-2正常运行并没有异常发生。
	//-1-2
	//使用awaitUninterruptibly()方法线程被中断后可以正常运行。
	public static void main(String[] args) {
		try {
			final Thread01Ch04_18_Service service = new Thread01Ch04_18_Service();
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					service.method();
				}
			};
			Thread ta = new Thread(runnable);
			ta.start();
			Thread.sleep(2000);
			ta.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_18_Service{
	private ReentrantLock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	public void method(){
		try {
			lock.lock();
			System.out.println("method await begin");
			//c.await();
			c.awaitUninterruptibly();
			System.out.println("method await end");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}



















