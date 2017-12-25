package com.main.thread.thread01.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *<p>Title	: Thread01Ch04_20_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午2:12:20
 */
public class Thread01Ch04_20_Test {
	/**4.1.16 使用Condition实现顺序执行**/
	//4.1.16 使用Condition实现顺序执行
	//-1按顺序打印。
	//-1使用Condition对象可以对线程执行的业务进行排序规划。
	volatile private static int flag = 1;
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition cA = lock.newCondition();
	private static Condition cB = lock.newCondition();
	private static Condition cC = lock.newCondition();
	public static void main(String[] args) {
		Thread ta = new Thread(){
			@Override
			public void run() {
				try {
					super.run();
					lock.lock();
					while(flag !=1 ){
						cA.await();
					}
					for (int i = 0; i < 3; i++) {
						System.out.println("threadnameA="+Thread.currentThread().getName()+(i+1));
					}
					flag = 2;
					cB.signalAll();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		};
		
		Thread tb = new Thread(){
			@Override
			public void run() {
				try {
					super.run();
					lock.lock();
					while(flag !=2 ){
						cB.await();
					}
					for (int i = 0; i < 3; i++) {
						System.out.println("threadnameB="+Thread.currentThread().getName()+(i+1));
					}
					flag = 3;
					cC.signalAll();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		};
		
		Thread tc = new Thread(){
			@Override
			public void run() {
				try {
					super.run();
					lock.lock();
					while(flag !=3 ){
						cC.await();
					}
					for (int i = 0; i < 3; i++) {
						System.out.println("threadnameC="+Thread.currentThread().getName()+(i+1));
					}
					flag = 1;
					cA.signalAll();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		};
		
		Thread[] tAArray = new Thread[5];
		Thread[] tBArray = new Thread[5];
		Thread[] tCArray = new Thread[5];
		for (int i = 0; i < 5; i++) {
			tAArray[i] = new Thread(ta);
			tBArray[i] = new Thread(tb);
			tCArray[i] = new Thread(tc);
			tAArray[i].setName("A");
			tBArray[i].setName("B");
			tCArray[i].setName("C");
			tAArray[i].start();
			tBArray[i].start();
			tCArray[i].start();
		}
	}
}





















 
