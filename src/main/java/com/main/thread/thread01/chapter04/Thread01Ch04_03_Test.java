package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午5:52:59
 */
public class Thread01Ch04_03_Test {
	/**4.1.4 使用Condition实现等待/通知:错误用法与解决**/
	//关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模式,类ReentrantLock也可以实现同样的功能,但需要借助于Condition
	//对象。Condition类是在JDK5中出现的技术,使用它有更好的灵活性,比如可以实现多路通知功能,也就是在一个Lock对象里面可以创建多个Condition(即对象监视器)实例,
	//线程对象可以注册在指定的Condition中,从而可以有选择性地进行线程通知,在调度线程上更加灵活。
	//在使用notify()/notifyAll()方法进行通知时,被通知的线程却是由JVM随机选择的。但使用ReentrantLock结合Condition类是可以实现前面介绍过的"选择性通知",
	//这个功能是非常重要的,而且在Condition类中是默认提供的。
	//而synchronized就相当于整个Lock对象中只有一个单一的Condition对象,所有的线程都注册在它一个对象的身上。线程开始notifyAll(),需要通知所有的Waiting
	//线程,没有选择权,会出现相当大的效率问题。
	//4.1.4 使用Condition实现等待/通知:错误用法与解决
	//-1出现异常(无监视器对象)
	//-1异常信息是监视器出错,解决办法是在condition.method()方法调用之前调用lock.lock()代码获得同步监视器。
	//-2只打印字母A
	//-2在控制台只打印一个字母A,原因是调用了Condition对象的await()方法,使当前执行任务的线程进入了等待waiting状态。
	public static void main(String[] args) {
		Thread01Ch04_03_Service service = new Thread01Ch04_03_Service();
		Thread01Ch04_03_Thread t = new Thread01Ch04_03_Thread(service);
		t.start();
	}
}

class Thread01Ch04_03_Service{
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	
	public void method(){
		try {
			lock.lock();
			System.out.println("A begin");
			c.await();
			System.out.println("A end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class Thread01Ch04_03_Thread extends Thread{
	private Thread01Ch04_03_Service service;
	public Thread01Ch04_03_Thread(Thread01Ch04_03_Service service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.method();
	}
}



















