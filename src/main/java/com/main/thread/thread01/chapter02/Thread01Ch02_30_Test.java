package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_30_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午3:02:41
 */
public class Thread01Ch02_30_Test {
	/**2.2.12 多线程的死锁**/
	//java线程死锁是一个经典的多线程问题,因为不同的线程都在等待根本不可能被释放的锁,从而导致所有的任务都无法继续完成。在多线程技术中,"死锁"是必须避免的,因为这会造成线程的"假死"。
	//2.2.12 多线程的死锁
	//-1出现死锁
	//-1死锁是程序设计的bug,在设计程序时就要避免双方互相持有对方的锁的情况。需要说明的是,本实验使用synchronized嵌套的代码结构来实现死锁,其实不使用嵌套的
	//synchronized代码结构也会出现死锁,与嵌套不嵌套无任何的关系,不要被代码结构所误导。只要互相等待对方释放锁就有可能出现死锁。
	public static void main(String[] args) {
		try {
			Thread01Ch02_30_Thread thread = new Thread01Ch02_30_Thread();
			
			thread.setFlag("a");
			Thread t1 = new Thread(thread);
			t1.start();
			
			//Thread.sleep(5000);//都会被执行
			Thread.sleep(100);
			
			thread.setFlag("b");
			Thread t2 = new Thread(thread);
			t2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_30_Thread implements Runnable{
	private String username;
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void setFlag(String username){
		this.username = username;
	}
	
	@Override
	public void run() {
		if("a".equals(username)){
			synchronized (lock1) {
				try {
					System.out.println("username="+username);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println("按lock1->lock2代码顺序执行了");
				}
			}
		}
		
		if("b".equals(username)){
			synchronized (lock2) {
				try {
					System.out.println("username="+username);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock1) {
					System.out.println("按lock2->lock1代码顺序执行了");
				}
			}
		}
	}

}

