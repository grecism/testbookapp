package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread25_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午5:56:29
 */
public class Thread01Ch01_25_Test {
	//1.7.4在沉睡中被停止
	//-4在沉睡中被停止 实现runnable。
	//-4出现异常。
	public static void main(String[] args) {
		try {
			Thread01Ch01_25_Thread thread = new Thread01Ch01_25_Thread();
			Thread t = new Thread(thread);
			t.start();
			Thread.sleep(200);
			System.out.println("Thread.currentThread.getName="+Thread.currentThread().getName());
			t.interrupt();
			//Thread.sleep(200000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

class Thread01Ch01_25_Thread implements Runnable{
	@Override
	public void run() {
		try {
			System.out.println("run begin");
			System.out.println("Thread.currentThread.getName="+Thread.currentThread().getName());
			Thread.sleep(200000);
			System.out.println("run end");
		} catch (Exception e) {
			System.out.println("Thread01Ch01_25_Thread run exception 在沉睡中被停止");
			e.printStackTrace();
		}
	}

}
