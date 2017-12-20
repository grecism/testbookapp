package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_14_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月7日下午3:12:06
 */
public class Thread01Ch02_14_Test {
	/**2.2.4 一半异步,一半同步**/
	//不在synchronized同步块中就是异步执行,在synchronized同步块中就是同步执行。
	//2.2.4 一半异步,一半同步
	//-1非同步时交叉打印,同步时排队执行
	//-1非同步时交叉打印,进入synchronized代码块后则排队执行
	public static void main(String[] args) {
		Thread01Ch02_14_Task task = new Thread01Ch02_14_Task();
		Thread01Ch02_14_A a = new Thread01Ch02_14_A(task);
		a.setName("a");
		a.start();
		Thread01Ch02_14_B b = new Thread01Ch02_14_B(task);
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_14_Task {
	public void taskmethod(){
		for (int i = 0; i < 100; i++) {
			System.out.println("nosynchronized threadname="+Thread.currentThread().getName()+" i="+(i+1));
		}
		System.out.println("=========================");
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				System.out.println("synchronized threadname="+Thread.currentThread().getName()+" i="+(i+1));
			}
		}
	}
}

class Thread01Ch02_14_A extends Thread{
	private Thread01Ch02_14_Task task;
	
	public Thread01Ch02_14_A(Thread01Ch02_14_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		task.taskmethod();
	}
}

class Thread01Ch02_14_B extends Thread{
	private Thread01Ch02_14_Task task;
	
	public Thread01Ch02_14_B(Thread01Ch02_14_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		task.taskmethod();
	}
}