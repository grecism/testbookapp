package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_09_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日上午11:33:56
 */
public class Thread01Ch04_09_Test {
	/**4.1.9 公平锁与非公平锁**/
	//公平锁与非公平锁:锁Lock分为"公平锁"和"非公平锁",公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的,即先来先得的FIFO先进先出顺序。而非公平锁就是一
	//种获取锁的抢占机制,是随机获得锁的,和公平锁不一样的就是先来的不一定先得到锁,这个方式可能造成某些线程一直拿不到锁,结果也就是不公平的了。
	//4.1.9 公平锁与非公平锁
	//-1公平锁。true
	//-1打印结果基本呈有序的状态,这就是公平锁的特点。
	//-2非公平锁。false
	//-2非公平锁的运行结果基本上是乱序的,说明先start()启动的线程不代表先获得锁。
	public static void main(String[] args) {
		//Thread01Ch04_09_Service service = new Thread01Ch04_09_Service(true);
		final Thread01Ch04_09_Service service = new Thread01Ch04_09_Service(false);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("		threadname="+Thread.currentThread().getName()+"运行了");
				service.method();
			}
		};
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i] = new Thread(runnable);
			threadArray[i].start();
		}
	}
}

class Thread01Ch04_09_Service{
	private ReentrantLock lock;
	public Thread01Ch04_09_Service(boolean flag) {
		lock = new ReentrantLock(flag);
	}
	public void method(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"被锁定了");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
}



















