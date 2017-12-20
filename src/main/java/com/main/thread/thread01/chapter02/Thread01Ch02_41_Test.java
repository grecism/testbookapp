package com.main.thread.thread01.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *<p>Title	: Thread01Ch02_41_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午3:57:08
 */
public class Thread01Ch02_41_Test {
	/**2.3.5 使用原子类进行i++操作**/
	//2.3.5 使用原子类进行i++操作
	//除了在i++操作时使用synchronized关键字实现同步外,还可以使用AtomicInteger原子类进行实现。
	//原子操作是不能分割的整体,没有其他线程能够中断或检查正在原子操作中的变量。一个原子(atomic)类型就是一个原子操作可用的类型,它可以在没有锁的情况下做到
	//线程安全(thread-safe)。
	//-1成功累加到20000
	//-1成功累加到20000
	public static void main(String[] args) {
		//分别输出10000
		/*Thread01Ch02_41_Thread thread = new Thread01Ch02_41_Thread();
		Thread01Ch02_41_Thread thread2 = new Thread01Ch02_41_Thread();
		thread.start();
		thread2.start();*/
		
		//输出20000
		Thread01Ch02_41_Thread thread = new Thread01Ch02_41_Thread();
		Thread t1 =  new Thread(thread);
		t1.start();
		Thread t2 =  new Thread(thread);
		t2.start();
		
		
	}
}

class Thread01Ch02_41_Thread extends Thread{
	private AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10000; i++) {
			System.out.println(count.incrementAndGet());
		}
	}
}















