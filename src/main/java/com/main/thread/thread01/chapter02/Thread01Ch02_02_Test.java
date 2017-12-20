package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午1:15:21
 */
public class Thread01Ch02_02_Test {
	/**2.1.2 实例变量非线程安全**/
	//如果多个线程共同访问一个对象中的实例变量,则有可能出现非线程安全问题。用线程访问的对象中如果有多个实例变量,则运行的结果有可能
	//出现交叉的情况。如果对象仅有一个实例变量,则有可能出现覆盖的情况。
	//2.1.2 实例变量非线程安全
	//-1单例模式中的实例变量呈非线程安全状态
	//-2同步后线程安全了
	//-1本实验是两个线程同时访问一个没有同步的方法,如果两个线程同时操作业务对象中的实例变量,则有可能出现"非线程安全"问题。
	//-2线程安全的情况需要在方法前面加上synchronized,在两个线程访问同一个对象中的同步方法时一定是线程安全的。由于同步访问,先打印a,再打印b。
	public static void main(String[] args) {
		Thread01Ch02_02_PrivateData privateData = new Thread01Ch02_02_PrivateData();
		Thread01Ch02_02_A a = new Thread01Ch02_02_A(privateData);
		a.start();
		Thread01Ch02_02_B b = new Thread01Ch02_02_B(privateData);
		b.start();
	}
}

class Thread01Ch02_02_PrivateData {
	private int num = 0;
	/*public void addNum(String username){
		try {
			
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
	}*/
	
	synchronized public void addNum(String username){
		try {
			
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

class Thread01Ch02_02_A extends Thread{
	private Thread01Ch02_02_PrivateData privateData;
	
	public Thread01Ch02_02_A(Thread01Ch02_02_PrivateData privateData) {
		this.privateData = privateData;
	}
	
	@Override
	public void run() {
		super.run();
		privateData.addNum("a");
	}
}

class Thread01Ch02_02_B extends Thread{
	private Thread01Ch02_02_PrivateData privateData;
	
	public Thread01Ch02_02_B(Thread01Ch02_02_PrivateData privateData) {
		this.privateData = privateData;
	}
	
	@Override
	public void run() {
		super.run();
		privateData.addNum("b");
	}
}
