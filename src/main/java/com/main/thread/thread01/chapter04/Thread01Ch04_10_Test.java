package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

public class Thread01Ch04_10_Test {
	/**4.1.10 方法getHoldCount()、getQueueLength()和getWaitQueueLength()的测试**/
	//4.1.10 方法getHoldCount()、getQueueLength()和getWaitQueueLength()的测试
	//-1getHoldCount()
	//-1方法 int getHoldCount()的作用是查询当前线程保持此锁定的个数,也就是调用lock()方法的次数。
	public static void main(String[] args) {
		Thread01Ch04_10_Service service = new Thread01Ch04_10_Service();
		service.methodA();
	}
}

class Thread01Ch04_10_Service{
	private ReentrantLock lock = new ReentrantLock();
	public void methodA(){ 		
		try {
			lock.lock();
			System.out.println(" methodA 锁定个数为:"+lock.getHoldCount());
			methodB();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void methodB(){
		try {
			lock.lock();
			System.out.println(" methodB 锁定个数为:"+lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}























