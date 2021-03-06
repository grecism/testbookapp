package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread14_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日上午11:41:08
 */
public class Thread01Ch01_14_Test {
	//1.4 isAlive()方法  
	//-3程序运行结果如下:
//	构造方法====================begin
//	当前线程名字=main
//	当前线程活动状态=true
//	Thread.currentThread()==this :false
//	Thread.currentThread()==this :true
//	this.getName=Thread-0
//	this.isAlive活动状态=false
//	构造方法====================end
//	main======begin alive=false
//	main======end alive=true
//	run======begin
//	当前线程名字=A
//	当前线程活动状态=true
//	Thread.currentThread()==this :false
//	Thread.currentThread()==this :true
//	this.getName=Thread-0
//	this.isAlive活动状态=false
//	run======end
	public static void main(String[] args) {
		Thread01Ch01_14_Thread thread = new Thread01Ch01_14_Thread();
		Thread t = new Thread(thread);
		System.out.println("main======begin alive="+t.isAlive());
		t.setName("A");
		t.start();
		System.out.println("main======end alive="+t.isAlive());

	}
}

class Thread01Ch01_14_Thread extends Thread{
	public Thread01Ch01_14_Thread() {
		System.out.println("构造方法====================begin");
		System.out.println("当前线程名字="+Thread.currentThread().getName());
		System.out.println("当前线程活动状态="+Thread.currentThread().isAlive());
		System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
		System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this.currentThread()));
		System.out.println("this.getName="+this.getName());
		System.out.println("this.isAlive活动状态="+this.isAlive());
		System.out.println("构造方法====================end");
	}
	@Override
	public void run() {
		//super.run();
		System.out.println("run======begin");
		System.out.println("当前线程名字="+Thread.currentThread().getName());
		System.out.println("当前线程活动状态="+Thread.currentThread().isAlive());
		System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
		System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this.currentThread()));
		System.out.println("this.getName="+this.getName());
		System.out.println("this.isAlive活动状态="+this.isAlive());
		System.out.println("run======end");
	}
}
