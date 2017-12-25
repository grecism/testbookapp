package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午2:33:19
 */
public class Thread01Ch06_01_Test {
	/**6 单例模式与多线程**/
	//如何使单例模式遇到多线程是安全的、正确的。使用多线程技术的单例模式时会出现一些意想不到的情况。
	/**6.1 立即加载/"饿汉模式"**/
	//什么是立即加载,立即加载就是使用类的时候已经将对象创建完毕,常见的实现办法就是直接new实例化。而立即加载从中文的语境来看,有"着急"、"急迫"的含义,所以也称为
	//"饿汉模式"。	
	//6.1 立即加载/"饿汉模式
	//-1"饿汉模式"的运行结果值相同。
	//-1控制台打印结果说明,hashCode是同一个值,说明对象是同一个,也就实现了立即加载型单例设计模式。
	public static void main(String[] args) {
		Thread01Ch06_01_Thread t1 = new Thread01Ch06_01_Thread();
		Thread01Ch06_01_Thread t2 = new Thread01Ch06_01_Thread();
		Thread01Ch06_01_Thread t3 = new Thread01Ch06_01_Thread();
		t1.start();
		t2.start();
		t3.start();
		
	}
}

class Thread01Ch06_01_Object{
	//立即加载方式==饿汉模式
	private static Thread01Ch06_01_Object object = new Thread01Ch06_01_Object();
	private Thread01Ch06_01_Object(){
		
	}
	public static Thread01Ch06_01_Object getInstance(){
		//因为getInStance()方法没有同步,所以有可能出现非线程安全问题。
		return object;
	}
}

class Thread01Ch06_01_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(Thread01Ch06_01_Object.getInstance().hashCode());
	}
}



















