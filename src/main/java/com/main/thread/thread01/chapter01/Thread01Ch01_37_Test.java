package com.main.thread.thread01.chapter01;

import java.util.Random;

/**
 * 
 *<p>Title	: PracticeThread37_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日下午2:49:32
 */
public class Thread01Ch01_37_Test {
	/**1.10.2 优先级具有规则性**/
	//高优先级的线程总是大部分先执行完,但不代表高优先级的线程全部先执行完。另外,不要以为t01线程先被main线程所调用就会先执行完,
	//出现这样的结果全是因为t01线程的优先级是最高值为10造成的。当线程优先级的等级差距很大时,谁先执行完和代码的调用顺序无关。
	//也就验证了线程的优先级与代码执行顺序无关,只是优先级比较高。说明线程的优先级具有一定的规则性,也就是CPU尽量将执行资源让给优先级比较高的线程。
	//1.10.2 优先级具有规则性
	//-1高优先级的线程总是大部分先执行完。
	//-1说明线程的优先级具有一定的规则性,也就是CPU尽量将执行资源让给优先级比较高的线程。
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			Thread01Ch01_37_A a = new Thread01Ch01_37_A();
			a.setPriority(10);//高优先级的线程总是大部分先执行完
			//a.setPriority(1);
			a.start();
			Thread01Ch01_37_B b = new Thread01Ch01_37_B();
			b.setPriority(1);
			//b.setPriority(10);//高优先级的线程总是大部分先执行完
			b.start();
		}
	}
}

class Thread01Ch01_37_A extends Thread{
	@Override
	public void run() {
		super.run();
		long beginTime = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 50000; j++) {
				Random random = new Random();
				random.nextInt();
				count = count +i;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("thread01 use time="+(endTime-beginTime));
	}
}

class Thread01Ch01_37_B extends Thread{
	@Override
	public void run() {
		super.run();
		long beginTime = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 50000; j++) {
				Random random = new Random();
				random.nextInt();
				count = count +i;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("thread02 use time="+(endTime-beginTime));
	}
}
