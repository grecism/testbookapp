package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_23_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日上午11:24:18
 */
public class Thread01Ch02_23_Test {
	/**2.2.9 静态同步方法synchronized方法与synchronized(class)代码块**/
	//关键字synchronized还可以应用在static静态方法上,如果这样写,那是对当前的*.java文件对应的class类进行持锁。
	//2.2.9 静态同步方法synchronized方法与synchronized(class)代码块
	//-1运行结果是同步效果
	//-1从运行结果来看,并没有什么特别之处,都是同步的效果,和将synchronized关键字加到非static方法上使用的效果是一样的。其实还是有本质上
	//的不同的,synchronized关键字加到static静态方法上是给Class类上锁,而关键字加到非static静态方法上是给对象上锁。
	public static void main(String[] args) {
		Thread01Ch02_23_A a = new Thread01Ch02_23_A();
		a.setName("a");
		a.start();
		Thread01Ch02_23_B b = new Thread01Ch02_23_B();
		b.setName("b");
		b.start();
	}
}

class Thread01Ch02_23_Service {
	synchronized public static void printA(){
		try {
			System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printA");
			Thread.sleep(3000);
			System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printA");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	synchronized public static void printB(){
		System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printB");
		System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printB");
	}
}

class Thread01Ch02_23_A extends Thread{
	@Override
	public void run() {
		super.run();
		Thread01Ch02_23_Service.printA();
	}
}

class Thread01Ch02_23_B extends Thread{
	@Override
	public void run() {
		super.run();
		Thread01Ch02_23_Service.printB();
	}
}