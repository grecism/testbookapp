package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午3:07:54
 */
public class Thread01Ch06_02_Test {
	/**6.2 延迟加载/"懒汉模式"**/
	//什么是延迟加载,延迟加载就是在调用get()方法时实例才被创建,常见的实现办法就是在get()方法中进行new实例化。而延迟加载从中文的语境来看,是"缓慢"、"不急迫"
	//的含义,所以也称为"懒汉模式"。
	//6.2 延迟加载/"懒汉模式"
	//1.延迟加载/"懒汉模式"解析
	//2.延迟加载/"懒汉模式"的缺点
	//-1懒汉模式成功取出一个实例。
	//-1此示例虽然取得一个对象的实例,但如果是在多线程的环境中,就会出现取出多个实例的情况,与单例模式的初衷是相背离的。此示例根本不能实现保持单例的状态。
	//-2懒汉模式在多线程环境中创建出多例。
	//-2控制台打印的数据为3个hashCode值,说明创建出了3个对象,并不是单例的。
	public static void main(String[] args) {
		/*Thread01Ch06_02_Thread t1 = new Thread01Ch06_02_Thread();
		t1.start();*/
		
		Thread01Ch06_02_Thread t1 = new Thread01Ch06_02_Thread();
		Thread01Ch06_02_Thread t2 = new Thread01Ch06_02_Thread();
		Thread01Ch06_02_Thread t3 = new Thread01Ch06_02_Thread();
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread01Ch06_02_Object{
	private static Thread01Ch06_02_Object object;
	private Thread01Ch06_02_Object() {
		
	}
	public static Thread01Ch06_02_Object getInstance(){
		//延迟加载
		if(object != null){
		}else{
			object = new Thread01Ch06_02_Object();
		}
		return object;
		
	}
}

class Thread01Ch06_02_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(Thread01Ch06_02_Object.getInstance().hashCode());
	}
}




























