package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_16_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月8日下午4:03:35
 */
public class Thread01Ch02_16_Test {
	/**2.2.6 验证synchronized(this) 同步代码块时锁定当前对象的**/
	//和synchronized方法一样,synchronized(this)代码块也是锁定当前对象的。
	//2.2.6 验证synchronized(this) 同步代码块时锁定当前对象的
	//-1异步打印
	//-2同步打印
	//-1-2
	//和synchronized方法一样,synchronized(this)代码块也是锁定当前对象的。
	public static void main(String[] args) {
		try {
			Thread01Ch02_16_Task  task = new Thread01Ch02_16_Task();
			Thread01Ch02_16_A a = new Thread01Ch02_16_A(task);
			a.setName("A");
			a.start();
			Thread.sleep(200);
			Thread01Ch02_16_B b = new Thread01Ch02_16_B(task);
			b.setName("B");
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_16_Task {
	public void taskmethodA(){
		synchronized (this) {
			for (int i = 0; i < 10000; i++) {
				System.out.println("synchronized taskmethodA run threadname="+Thread.currentThread().getName()+" i="+(i+1));
			}
		}
	}
	
    /*public void taskmethodB(){
		System.out.println("================================== taskmethodB run");
	}*/
	
	synchronized public void taskmethodB(){
		System.out.println("================================== taskmethodB run");
	}
}

class Thread01Ch02_16_A extends Thread{
	private Thread01Ch02_16_Task task;
	
	public Thread01Ch02_16_A(Thread01Ch02_16_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		task.taskmethodA();
	}
}

class Thread01Ch02_16_B extends Thread{
	private Thread01Ch02_16_Task task;
	
	public Thread01Ch02_16_B(Thread01Ch02_16_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		task.taskmethodB();
	}
}