package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日上午11:44:51
 */
public class Thread01Ch02_01_Test {
	/**2 对象及变量的并发访问**/
	/**2.1 synchronized同步方法**/
	//"非线程安全"	其实会在多个线程对同一个对象中的实例变量进行并发访问时发生,产生的后果就是"脏读",也就是取到的数据其实是被更改过的。而"线程安全"就是以获得的
	//实例变量的值是经过同步处理的,不会出现脏读的现象。
	/**2.1.1 方法内的变量为线程安全**/
	//2.1.1 方法内的变量为线程安全
	//-1方法中的变量呈线程安全状态
	//-1"非线程安全"问题存在于"实例变量"中,如果是方法内部的私有变量,则不存在"非线程安全"问题,所得结果也就是"线程安全"的了。
	//可见,方法中的变量不存在线程安全问题,永远都是线程安全的。这是方法内部的变量是私有的特性造成的。
	public static void main(String[] args) {
		Thread01Ch02_01_PrivateData privateData = new Thread01Ch02_01_PrivateData();
		Thread01Ch02_01_A a = new Thread01Ch02_01_A(privateData);
		a.start();
		Thread01Ch02_01_B b = new Thread01Ch02_01_B(privateData);
		b.start();
	}

}

class Thread01Ch02_01_PrivateData {
	public void addNum(String username){
		try {
			int num = 0;
			if("a".equals(username)){
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);
			}else{
				num = 200;
				System.out.println("b set over");
			}
			System.out.println("username="+username+" num="+num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_01_A extends Thread{
	private Thread01Ch02_01_PrivateData privateData;
	
	public Thread01Ch02_01_A(Thread01Ch02_01_PrivateData privateData) {
		this.privateData = privateData;
	}
	
	@Override
	public void run() {
		super.run();
		privateData.addNum("a");
	}
}

class Thread01Ch02_01_B extends Thread{
	private Thread01Ch02_01_PrivateData privateData;
	
	public Thread01Ch02_01_B(Thread01Ch02_01_PrivateData privateData) {
		this.privateData = privateData;
	}
	
	@Override
	public void run() {
		super.run();
		privateData.addNum("b");
	}
}
