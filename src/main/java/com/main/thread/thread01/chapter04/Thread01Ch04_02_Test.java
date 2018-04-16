package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午5:38:43
 */
public class Thread01Ch04_02_Test {
	/**4.1.2 使用ReentrantLock实现同步:测试2**/
	//4.1.2 使用ReentrantLock实现同步:测试2
	//-1全部同步运行了
	//-1调用lock.lock()代码的线程就持有了"对象监视器",其他线程只有等待锁被释放时再次争抢。效果和使用synchronized关键字一样,线程之间还是顺序执行的。
	//lock.unlock执行两次报错
	//Exception in thread "A" java.lang.IllegalMonitorStateException
	//at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:155)
	//at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1260)
	//at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:460)
	//at com.main.thread.thread01.chapter04.Thread01Ch04_02_Service.methodA(Thread01Ch04_02_Test.java:52)
	//at com.main.thread.thread01.chapter04.Thread01Ch04_02_Thread_A.run(Thread01Ch04_02_Test.java:79)
	//执行lock.unlock()执行源码会判断当前线程有没有获取到锁，如果没获取到锁就解锁会抛出 new IllegalMonitorStateException();
	//当前线程加的锁只能在当前线程中进行解锁，不能再其他线程中进行解锁。
	public static void main(String[] args) {
		try {
			Thread01Ch04_02_Service service = new Thread01Ch04_02_Service();
			Thread01Ch04_02_Thread_A ta = new Thread01Ch04_02_Thread_A(service);
			ta.setName("A");
			ta.start();
			Thread01Ch04_02_Thread_A taa = new Thread01Ch04_02_Thread_A(service);
			taa.setName("AA");
			taa.start();
			Thread.sleep(2000);
			Thread01Ch04_02_Thread_B tb = new Thread01Ch04_02_Thread_B(service);
			tb.setName("B");
			tb.start();
			Thread01Ch04_02_Thread_B tbb = new Thread01Ch04_02_Thread_B(service);
			tbb.setName("BB");
			tbb.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch04_02_Service{
	private ReentrantLock lock = new ReentrantLock();
	public void methodA(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"methodA begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("threadname="+Thread.currentThread().getName()+"methodA end time="+System.currentTimeMillis());

			//lock.unlock();   //lock.unlock执行两次报错
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void methodB(){
		try {
			lock.lock();
			System.out.println("threadname="+Thread.currentThread().getName()+"methodB begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("threadname="+Thread.currentThread().getName()+"methodB end time="+System.currentTimeMillis());

			//lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class Thread01Ch04_02_Thread_A extends Thread{
	private Thread01Ch04_02_Service service;
	public Thread01Ch04_02_Thread_A(Thread01Ch04_02_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodA();
	}
}

class Thread01Ch04_02_Thread_B extends Thread{
	private Thread01Ch04_02_Service service;
	public Thread01Ch04_02_Thread_B(Thread01Ch04_02_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.methodB();
	}
}














