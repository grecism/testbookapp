package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread24_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午5:51:51
 */
public class Thread01Ch01_24_Test{
	//1.7.4在沉睡中被停止
	//-2先执行interrupt停止线程,再遇到了sleep。
	//-2出现异常。
	//-3先执行interrupt停止线程,在执行普通代码。
	//-3正常执行。
	public static void main(String[] args) {
		Thread01Ch01_24_Thread thread = new Thread01Ch01_24_Thread();
		thread.start();
		thread.interrupt();
		System.out.println("end!");
	}
}

class Thread01Ch01_24_Thread extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 0; i < 100000; i++) {
				System.out.println("i="+(i+1));
			}
			System.out.println("run begin");
			Thread.sleep(200000);
			/*for (int i = 100000; i < 200000; i++) {
				System.out.println("i="+(i+1));
			}*/
			System.out.println("run end");
		} catch (Exception e) {
			System.out.println("Thread01Ch01_24_Thread run exception 先停止,再遇到了sleep");
			e.printStackTrace();
		}
	}
}
