package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread12_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日上午11:14:28
 */
public class Thread01Ch01_12_Test {
	//1.3currentThread()方法  
	//-3程序运行结果如下:
	//	PracticeThread12======begin
	//	当前线程名字=main
	//	this.getName=Thread-0
	//	PracticeThread12======end
	//	run======begin
	//	当前线程名字=A
	//	this.getName=A
	//	run======end
	//-4程序运行结果如下:
	//	PracticeThread12======begin
	//	当前线程名字=main
	//	this.getName=Thread-0
	//	PracticeThread12======end
	//	run======begin
	//	当前线程名字=A
	//	this.getName=Thread-0
	//	run======end
	public static void main(String[] args) {
		Thread01Ch01_12_Thread thread = new Thread01Ch01_12_Thread();
		//practice12.setName("A");
		//practice12.start();
		Thread t = new Thread(thread);
		t.setName("A");
		t.start();
	}
}

class Thread01Ch01_12_Thread extends Thread{
	public Thread01Ch01_12_Thread() {
		System.out.println("PracticeThread12======begin");
		System.out.println("当前线程名字="+Thread.currentThread().getName());
		System.out.println("this.getName="+this.getName());
		System.out.println("PracticeThread12======end");
	}
	
	@Override
	public void run() {
		//super.run();
		System.out.println("run======begin");
		System.out.println("当前线程名字="+Thread.currentThread().getName());
		System.out.println("this.getName="+this.getName());
		System.out.println("run======end");
	}
}

