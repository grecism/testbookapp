package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午3:54:37
 */
public class Thread01Ch04_01_Test {
	/**4 Lock的使用**/
	//介绍使用Java5中lock对象也能实现同步的效果,而且在使用上更加方便。着重掌握如下2个只是点:
	//1.ReentrantLock类的使用。2.ReentrantReadWriteLock。
	/**4.1 使用ReentrantLock类**/
	//在java多线程中,可以使用synchronized关键字来实现线程之间同步互斥,但在JDK1.5中新增加了ReentrantLock类也能达到同样的效果,并且在扩展功能上也更加
	//强大,比如具有嗅探锁定,多路分支通知等功能,而且在使用上也比synchronized更加的灵活。
	/**4.1.1 使用ReentrantLock实现同步:测试1**/
	//4.1.1 使用ReentrantLock实现同步:测试1
	//-1同步运行
	//-1调用ReentrantLock对象的lock()方法获取锁,调用unlock()方法释放锁。
	//当前线程打印完毕之后将锁进行释放,其它线程才可以继续打印。线程打印的数据是分组打印,因为当前线程已经持有锁,但线程之间打印的顺序是随机的。
	public static void main(String[] args) {
		Thread01Ch04_01_Service service = new Thread01Ch04_01_Service();
		Thread01Ch04_01_Thread t1 = new Thread01Ch04_01_Thread(service);
		Thread01Ch04_01_Thread t2 = new Thread01Ch04_01_Thread(service);
		Thread01Ch04_01_Thread t3 = new Thread01Ch04_01_Thread(service);
		Thread01Ch04_01_Thread t4 = new Thread01Ch04_01_Thread(service);
		Thread01Ch04_01_Thread t5 = new Thread01Ch04_01_Thread(service);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

class Thread01Ch04_01_Service{
	private Lock lock = new ReentrantLock();
	public void method(){
		lock.lock();
		for (int i = 0; i < 5; i++) {
			System.out.println("threadname="+Thread.currentThread().getName()+"_"+(i+1));
		}
		lock.unlock();
	}
}

class Thread01Ch04_01_Thread extends Thread{
	private Thread01Ch04_01_Service service;
	public Thread01Ch04_01_Thread(Thread01Ch04_01_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.method();
	}
	
}



















