package com.main.thread.thread01.chapter03;

import java.util.Date;

/**
 * 
 *<p>Title	: Thread01Ch03_37_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午3:14:28
 */
public class Thread01Ch03_37_Test {
	/**3.3.4 再次验证线程的隔离性**/
	//3.3.4 再次验证线程的隔离性
	//-1运行结果各有各的值(两个初始化的默认值)
	//-1子线程和主线程各有各自所拥有的值(两个初始化的默认值)
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("main getValue="+Thread01Ch03_37_Tools.tla.get());
				Thread.sleep(200);
			}
			Thread.sleep(3000);
			Thread01Ch03_37_Thread_A ta = new Thread01Ch03_37_Thread_A();
			ta.start();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_37_ThreadLocal_A extends ThreadLocal{
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
}

class Thread01Ch03_37_Tools{
	public static Thread01Ch03_37_ThreadLocal_A tla = new Thread01Ch03_37_ThreadLocal_A();
}

class Thread01Ch03_37_Thread_A extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 0; i < 10; i++) {
				System.out.println("ThreadA getValue="+Thread01Ch03_37_Tools.tla.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


















