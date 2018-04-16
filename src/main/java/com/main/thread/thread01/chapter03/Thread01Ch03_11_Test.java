package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_11_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午4:58:42
 */
public class Thread01Ch03_11_Test {
	/**3.1.8 方法wait(long)的使用**/
	//方法wait(long)的使用
	//-15秒后自动被唤醒
	//-15秒后自动被唤醒
	//-25秒内由其他线程进行唤醒
	//-25秒内由其他线程进行唤醒
	public static void main(String[] args) {
		try {
			//Thread thread = new Thread(runnable1);
			//thread.start();
			
			Thread t1 = new Thread(runnable1);
			t1.start();
			Thread.sleep(3000);
			Thread t2 = new Thread(runnable2);
			t2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static Object lock = new Object();
	private static Runnable runnable1 = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("threadname="+Thread.currentThread().getName()+" wait begin");
					lock.wait(5000);
					System.out.println("threadname="+Thread.currentThread().getName()+" end begin");

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static Runnable runnable2 = new Runnable() {
		
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("threadname="+Thread.currentThread().getName()+" notify begin");
				lock.notify();
				System.out.println("threadname="+Thread.currentThread().getName()+" notify begin");
			}
		}
	};
}

