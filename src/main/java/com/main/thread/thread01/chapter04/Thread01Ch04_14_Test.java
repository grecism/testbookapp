package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_14_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午12:48:16
 */
public class Thread01Ch04_14_Test {
	//4.1.12 方法isFair()、isHeldByCurrentThread()和isLocked()的测试
	//-2isHeldByCurrentThread()
	//-3isLocked()
	//-2方法 boolean isHeldByCurrentThread()的作用是查询当前线程是否保持此锁定。
	//-3方法boolean isLocked()的作用是查询此锁定是否由任意线程保持。
	public static void main(String[] args) {
		Thread01Ch04_14_Service service = new Thread01Ch04_14_Service();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.method();
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}

class Thread01Ch04_14_Service{
	private ReentrantLock lock = new ReentrantLock();
	public void method(){
		try {
			//System.out.println("当前线程是否保持此锁定:"+lock.isHeldByCurrentThread());
			System.out.println("此锁定是否由任意线程保持:"+lock.isLocked());
			lock.lock();
			//System.out.println("当前线程是否保持此锁定:"+lock.isHeldByCurrentThread());
			System.out.println("此锁定是否由任意线程保持:"+lock.isLocked());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

		
	}
	
}

















