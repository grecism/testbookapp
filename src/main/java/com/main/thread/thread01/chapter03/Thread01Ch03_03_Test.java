package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午6:33:10
 */
public class Thread01Ch03_03_Test {
	/**3.1.3 等待/通知机制的实现**/
	//3.1.3 等待/通知机制的实现
	//方法wait()的作用是使当前执行代码的线程进行等待,wait()方法是Object类的方法,该方法用来将当前线程置入"预执行队列"中,并且在wait()所在的代码行处停止执行,
	//直到接到通知或被中断为止。在调用wait()之前,线程必须获得该对象的对象级别锁,即只能在同步方法或同步块中调用wait()方法。在执行wait()方法后,当前线程释放锁。
	//在从wait()返回前,线程与其它线程竞争重新获得锁。如果调用wait()时没有持有适当的锁,则抛出IllegalMonitorStateException,它是RuntimeException的
	//一个子类,因此,不需要try catch语句进行捕捉异常。
	//方法notify()也要在同步方法或同步块中调用,即在调用前,线程也必须获得该对象的对象级别锁。如果调用notify()时没有持有适当的锁,也会抛出IllegalMonitorStateException。
	//该方法用来通知那些可能等待该对象的对象锁的其他线程,如果有多个线程等待,则由线程规划器随机挑选出其中一个呈wait状态的线程,对其发出通知notify,并使他等待获取该对象的对象锁。
	//需要说明的是,在执行notify()方法后,当前线程不会马上释放该对象锁,呈wait状态的线程也并不能马上获取该对象锁,要等到执行notify()方法的线程将程序执行完,也就是退出synchronized
	//代码块后,当前线程才会释放锁,而呈wait状态所在的线程才可以获取该对象锁。当第一个获得了该对象锁的wait线程运行完毕以后,它会释放掉该对象锁,此时如果该对象没有再次使用
	//notify语句,则即便该对象已经空闲,其他wait状态等待的线程由于没有得到该对象的通知,还会继续阻塞在wait状态,直到这个对象发出一个notify或notifyAll。
	//总结wait和notify:wait使线程停止运行,而notify使停止的线程继续运行。
	//-1出现异常
	//-1出现异常的原因是没有"对象监视器",也就是没有同步加锁。
	public static void main(String[] args) {
		try {
			String string = new String();
			string.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


















