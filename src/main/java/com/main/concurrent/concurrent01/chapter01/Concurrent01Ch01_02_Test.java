package com.main.concurrent.concurrent01.chapter01;

/**
 * 
 *<p>Title : Concurrent01Ch01_02_Test.java</p>
 * @Description :
 * @author : admin
 * @date   : 2018年2月25日下午3:18:24
 */
public class Concurrent01Ch01_02_Test {
	/**1.1.2测试上下文切换次数和时长**/
	//1.1.2测试上下文切换次数和时长
	//（1）使用Lmbench3可以测量上下文切换的时长。
	//（2）使用vmstat可以测量上下文切换的次数。
	/**1.1.3如何减少上下文切换**/
	//1.1.3如何减少上下文切换
	//减少上下文切换的方法有无锁并发编程、CAS算法、使用最少线程和使用协程。
	//（1）无锁并发编程。多线程竞争锁时，会引起上下文切换，所以多线程处理数据时，可以用一些办法来避免使用锁，如将数据的ID按照Hash算法
	//取模分段，不同的线程处理不同段的数据。
	//（2）CAS算法。Java的Atomic包使用CAS算法来更新数据，而不需要加锁。
	//（3）使用最少线程。避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态。
	//（4）协程：在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换。
	/**1.1.4减少上下文切换实战**/
	//本节将通过减少线上大量WAITING的线程，来减少上下文切换次数。
	//1.1.4减少上下文切换实战
	//（1）第一步：用jstack命令dump线程信息，看看pid为3117的进程里的线程都在做什么。
	//（2）第二步：统计所有线程分别处于什么状态，发现300多个线程处于WAITING（onobject-monitor）状态。
	//（3）第三步：打开dump文件查看处于WAITING（onobject-monitor）的线程在做什么。发现这些线程基本全是JBOSS的工作线程，在await。
	//          说明JBOSS线程池里线程接收到的任务太少，大量线程都闲着。
	//（4）第四步：减少JBOSS的工作线程数，找到JBOSS的线程池配置信息，将maxThreads降到100。
	//（5）第五步：重启JBOSS，再dump线程信息，然后统计WAITING（onobject-monitor）的线程，发现减少了175个。WAITING的线程少了，系统
	//			上下文切换的次数就会少，因为每一次从WAITING到RUNNABLE都会进行一次上下文的切换。也可使用vmstat命令测试。
	/**1.2死锁**/
	//1.2死锁
	//锁是个非常有用的工具，运用场景非常多，因为它使用起来非常简单，而且易于理解。但同时它也会带来一些困扰，那就是可能会引起死锁，一旦产生死锁，就会
	//造成系统功能不可用。让我们先来看一段代码，这段代码会引起死锁，使线程t1和线程t2互相等待对方释放锁。
	//-1线程t1和线程t2互相等待对方释放锁。
	//-1现在我们介绍避免死锁的几个常见方法。
	//（1）避免一个线程同时获取多个锁。
	//（2）避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源。
	//（3）尝试使用定时锁，使用lock.tryLock(timeout)来替代使用内部锁机制。
	//（4）对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
	private static String A = "A";
	private static String B = "B";
	
	public static void main(String[] args) {
		new Concurrent01Ch01_02_Test().deadLock();
	}

	private void deadLock() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (A) {
					System.out.println("t1 begin");
					try {
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (B) {
						System.out.println("t1");
					}
					System.out.println("t1 end");
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (B) {
					System.out.println("t2 begin");
					synchronized (A) {
						System.out.println("t2");
					}
					System.out.println("t2 end");
				}
			}
		});
		t1.start();
		t2.start();
	}
}
