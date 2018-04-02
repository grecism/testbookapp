package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread23_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午5:46:39
 */
public class Thread01Ch01_23_Test {
	/**1.7.4 在沉睡中停止**/
	//1.7.4在沉睡中被停止
	//-1线程在sleep状态下 停止线程 出现异常。
	//-2如果在sleep状态下停止某一线程,会进入catch语句,并且清除停止状态值,使之变成false。
	public static void main(String[] args) {
		try {
			Thread01Ch01_23_Thread thread = new Thread01Ch01_23_Thread();
			thread.start();
			Thread.sleep(200);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

class Thread01Ch01_23_Thread extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			System.out.println("run begin");
			Thread.sleep(20000);
			System.out.println("run end");
		} catch (InterruptedException e) {
			System.out.println("thread run exception 在沉睡中被停止");
			System.out.println("thread 是否停止1？"+this.isInterrupted());
			System.out.println("thread 是否停止2？"+this.interrupted());
			e.printStackTrace();
		}
	}
}
