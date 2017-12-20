package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午5:45:09
 */
public class Thread01Ch01_05_Test {
	/**1.2.3实例变量与线程安全**/
	//1.2.3实例变量与线程安全
	//-1不共享数据的运行结果
	//-1一共创建了3个线程,每个线程都有各自的count变量,自己减少自己的count变量的值。这样的情况就是变量不共享,此示例并不存在多个线程访问同一个实例变量的情况。
	public static void main(String[] args) {
		Thread01Ch01_05_Thread t1 = new Thread01Ch01_05_Thread("A");
		Thread01Ch01_05_Thread t2 = new Thread01Ch01_05_Thread("B");
		Thread01Ch01_05_Thread t3 = new Thread01Ch01_05_Thread("C");
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread01Ch01_05_Thread extends Thread{
	private int count = 5;
	
	public Thread01Ch01_05_Thread(String name) {
		this.setName(name);//设置线程名称
	}
	
	@Override
	public void run() {
		super.run();
		while(count > 0 ){
			count --;
			System.out.println("由"+this.currentThread().getName()+"计算,count="+count);
		}
	}
}
