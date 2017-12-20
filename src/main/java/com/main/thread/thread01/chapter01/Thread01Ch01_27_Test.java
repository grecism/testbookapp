package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread27_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午6:34:08
 */
public class Thread01Ch01_27_Test {
	/**1.7.6 方法stop()与java.lang.ThreadDeath异常**/
	//1.7.6 方法stop()与java.lang.ThreadDeath异常
	//-1出现异常。调用stop()方法时会抛出java.lang.ThreadDeath异常,但在通常情况下,此异常不需要显示地捕捉。
	//-1方法stop()已经被作废,因为如果强制让线程停止则有可能使一些请理性的工作得不到完成。
	//另一个情况就是对锁定的对象进行了"解锁",导致数据得不到同步的处理,出现数据不一致的问题。
	public static void main(String[] args) {
		Thread01Ch01_27_Thread thread = new Thread01Ch01_27_Thread();
		thread.start();
	}
}

class Thread01Ch01_27_Thread extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			this.stop();
		} catch (ThreadDeath e) {
			System.out.println("PracticeThread27 run exception");
			e.printStackTrace();
		}
	}
}