package com.main.thread.thread01.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *<p>Title	: Thread01Ch02_42_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午4:13:21
 */
public class Thread01Ch02_42_Test {
	/**2.3.6 原子类也并不完全安全**/
	//2.3.6 原子类也并不完全安全
	//原子类在具有逻辑性的情况下输出结果也具有随机性。
	//-1结果值是正确的但累加值的顺序及累加值是错误的。
	//-2结果值是正确的累加值的顺序及累加值是正确的。
	//-1打印顺序出错了,应该每加一次100再加一次1。出现这样的情况是因为addAndGet()方法是原子的,但方法和方法之间的调用却不是原子的。解决这样的问题必须要用同步。
	//-2打印正确,是每次加100再加1,这就是我们想要得到的过程,结果是505的同时还保证在过程中累加的顺序也是正确的。
	public static void main(String[] args) {
		Thread01Ch02_42_Service service = new Thread01Ch02_42_Service();
		Thread01Ch02_42_Thread[] threadArray = new Thread01Ch02_42_Thread[5];
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i] = new Thread01Ch02_42_Thread(service);
		}
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i].start();
		}
		
	}
}

class Thread01Ch02_42_Service{
	private AtomicInteger count = new AtomicInteger();
	
	/*public void addNum(){
		System.out.println(Thread.currentThread().getName()+"加了100之后的值是:"+count.addAndGet(100));
		
		count.addAndGet(1);
	}*/
	
	synchronized public void addNum(){
		System.out.println(Thread.currentThread().getName()+"加了100之后的值是:"+count.addAndGet(100));
		
		count.addAndGet(1);
	}
}

class Thread01Ch02_42_Thread extends Thread{
	private Thread01Ch02_42_Service service;
	
	public Thread01Ch02_42_Thread(Thread01Ch02_42_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.addNum();
	}
}












