package com.main.concurrent.concurrent01.chapter01;

/**
 * 
 *<p>Title : Concurrent01Ch01_01_Test.java</p>
 * @Description :
 * @author : admin
 * @date   : 2018年2月25日上午11:34:51
 */
public class Concurrent01Ch01_01_Test {
	/**1 并发编程的挑战**/
	//并发编程的目的是为了让程序运行的更快，但是，并不是启动更多的线程就能让程序最大限度地并发执行。在进行并发编程时，如果希望通过
	//多线程执行任务让程序运行得更快，会面临非常多的挑战，比如上下文切换的问题、死锁的问题，以及受限于硬件和软件的资源限制问题，本章
	//会介绍几种并发编程的挑战以及解决方案。
	/**1.1上下文切换**/
	//即使是单核处理器也支持多线程执行代码，CPU通过给每个线程分配CPU时间片来实现这个机制。时间片是CPU分配给各个线程的时间，因为
	//时间片非常短，所以CPU通过不停地切换线程执行，让我们感觉多个线程是同时执行的，时间片一般是几十毫秒（ms）。
	//CPU通过时间片分配算法来循环执行任务，当前任务执行一个时间片后会切换到下一个任务。但是，在切换前会保存上一个任务的状态，以便
	//下次切换回这个任务时，可以再加载这个任务的状态。所以任务从保存到在加载的过程就是一次上下文切换。上下文切换会影响多线程的执行速度。
	/**1.1.1多线程一定快吗**/
	//演示串行和并发执行并累加操作的时间，请分析：下面的代码并发执行一定比串行执行快吗？
	//1.1.1多线程一定快吗
	//-1不一定。
	//-1当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢。那么，为什么并发执行的速度会比串行慢呢？这是因为线程有创建和上下文切换的开销。
	private static final long count = 10000l;
	
	public static void main(String[] args) throws InterruptedException {
		serial();
		concurrency();
	}
	
	private static void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int a = 0;
				for (long i = 0; i < count; i++) {
					a+=5;
				}
			}
		});
		thread.start();
		int b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		thread.join();
		System.out.println("concurrency="+time+"ms,b="+b);
	}
	
	private static void serial() {
		long start = System.currentTimeMillis();
		int a = 0;
		for (int i = 0; i < count; i++) {
			a+=5;
		}
		int b = 0;
		for (int i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("serial="+time+"ms,a="+a+",b="+b);
	}
	
	
	
}























