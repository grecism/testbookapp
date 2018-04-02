package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午5:24:40
 */
public class Thread01Ch01_04_Test {
	/**1.2.2实现Runnable接口**/
	//1.2.2实现Runnable接口
	//-1实现Runnable接口
	//-1实现Runnable接口
	//使用继承Thread类的方式来开发多线程应用程序在设计上是有局限的,因为java是单继承,不支持多继承,所以为了改变这种限制,可以使用实现Runnable接口的方式来实现
	//多线程技术。Thread.java类也实现了Runnable接口,那也就是意味着Thread(Runnable target)不光可以传入Runnable接口的对象,还可以传入一个Thread类的
	//对象,这样做完全可以将一个Thread对象中的run()方法交由其他的线程进行调用。
	public static void main(String[] args) {
		Thread01Ch01_04_Thread thread = new Thread01Ch01_04_Thread();
		Thread t = new Thread(thread);
		t.start();
		System.out.println("主线程运行结束");
	}
}

class Thread01Ch01_04_Thread implements Runnable{

	@Override
	public void run() {
		System.out.println("Runnable子线程正在运行");
	}

}
