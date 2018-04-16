package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_13_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午6:07:56
 */
public class Thread01Ch03_13_Test {
	//3.1.9 通知过早
	//-3仅仅执行了notify方法
	//-3仅仅执行了notify方法
	//-4正常运行
	//-4正常运行
	public static void main(String[] args) {
		try {
			Thread01Ch03_13_Runnable run = new Thread01Ch03_13_Runnable();
			
//			Thread b = new Thread(run.runnableB);
//			b.start();
//			Thread.sleep(100);
//			Thread a = new Thread(run.runnableA);
//			a.start();
			
			Thread a = new Thread(run.runnableA);
			a.start();
			Thread.sleep(100);
			Thread b = new Thread(run.runnableB);
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_13_Runnable{
	private String lock = new String("");
	private boolean flag = false;
	public Runnable runnableA = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					if(flag == false){
						System.out.println("wait begin");
						lock.wait();
						System.out.println("wait end");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public Runnable runnableB = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("notify begin");
					lock.notify();
					System.out.println("notify end");
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}