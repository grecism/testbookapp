package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_09_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午4:26:57
 */
public class Thread01Ch03_09_Test {
	/**3.1.5 当interrupt方法遇到wait方法**/
	//3.1.5 当interrupt方法遇到wait方法
	//-1停止wait状态下的线程出现异常
	//-1
	//(1)执行完同步代码块就会释放对象的锁。
	//(2)在执行同步代码块的过程中,遇到异常而导致线程终止,锁也会被释放。
	//(3)在执行同步代码块的过程中,执行了锁所属对象的wait()方法,这个线程会释放对象锁,而此线程对象会进入线程等待池中,等待被唤醒。
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			Thread01Ch03_09_Thread thread = new Thread01Ch03_09_Thread(lock);
			thread.start();
			Thread.sleep(3000);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_09_Service{
	public void method(Object lock){
		try {
			synchronized (lock) {
				System.out.println("wait begin");
				lock.wait();
				System.out.println("wait end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("出现异常了,因为呈wait状态的线程被interrupt了");
		}
	}
}

class Thread01Ch03_09_Thread extends Thread{
	private Object lock;
	public Thread01Ch03_09_Thread(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_09_Service service = new Thread01Ch03_09_Service();
		service.method(lock);
	}
}











