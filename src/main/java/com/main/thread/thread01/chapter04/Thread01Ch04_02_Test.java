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

			lock.unlock();
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

			lock.unlock();
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














