package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread22_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午5:11:16
 */
public class Thread01Ch01_22_Test {
	//1.7.3 能停止的线程,异常法
	//-2for后面的语句不会继续运行。
	//-2在for循环中判断是否是停止状态,是就抛出异常停止执行下面的代码。
	public static void main(String[] args) {
		try {
			Thread01Ch01_22_Thread thread = new Thread01Ch01_22_Thread();
			thread.start();
			//thread.sleep(2000);
			Thread.sleep(2000);
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

class Thread01Ch01_22_Thread extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 0; i < 500000; i++) {
				if(this.interrupted()){
					System.out.println("已经是停止状态了!我要退出了！");
					throw new InterruptedException();
				}
				System.out.println("i="+(i+1));
			}
			System.out.println("for下面的代码。。。。。。");
		} catch (InterruptedException e) {
			System.out.println("PracticeThread22 run InterruptedException");
			e.printStackTrace();
		}
	}
}

