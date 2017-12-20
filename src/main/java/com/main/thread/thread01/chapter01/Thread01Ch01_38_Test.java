package com.main.thread.thread01.chapter01;

import java.util.Random;

/**
 * 
 *<p>Title	: PracticeThread38_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日下午3:14:32
 */
public class Thread01Ch01_38_Test {
	/**1.10.3 优先级具有随机性**/
	//前面案例介绍了优先级较高则优先执行完run()方法中的任务,但这个结果不能说的太肯定,因为线程的优先级还具有"随机性",也就是优先级较高的线程不一定每一次都先执行完。
	//1.10.3 优先级具有随机性
	//-1优先级较高的线程并不一定每一次都先执行完run()方法中的任务。
	//-1那么,可以得出结论,不要把线程的优先级与运行结果的顺序作为衡量的标准,优先级较高的线程并不一定每一次都先执行完run()方法中的任务,
	//也就是说,线程优先级与打印顺序无关,不要将两者相关联,他们的关系具有不确定性和随机性。
	public static void main(String[] args) {
		for (int i = 0; i < 6; i++) {
			Thread01Ch01_38_A a = new Thread01Ch01_38_A();
			a.setPriority(5);
			a.start();
			Thread01Ch01_38_B b = new Thread01Ch01_38_B();
			b.setPriority(6);
			b.start();
		}
	}
}

class Thread01Ch01_38_A extends Thread{
	@Override
	public void run() {
		super.run();
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Random random = new Random();
			random.nextInt();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("thread01 use time="+(endTime-beginTime));
	}
}

class Thread01Ch01_38_B extends Thread{
	@Override
	public void run() {
		super.run();
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Random random = new Random();
			random.nextInt();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("thread02 use time="+(endTime-beginTime));
	}
}

