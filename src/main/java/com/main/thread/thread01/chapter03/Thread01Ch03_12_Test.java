package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_12_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午5:32:13
 */
public class Thread01Ch03_12_Test {
	/**3.1.9 通知过早**/
	//3.1.9 通知过早
	//-1正常运行结果
	//-1正常运行结果
	//-2通知过早,方法wait永远不会被通知
	//-2通知过早,则会打乱程序正常的运行逻辑。
	public static void main(String[] args) {
		try {
			Thread01Ch03_12_Runnable run = new Thread01Ch03_12_Runnable();
			/*Thread a = new Thread(run.runnableA);
			a.start();
			Thread b = new Thread(run.runnableB);
			b.start();*/
			
			Thread b = new Thread(run.runnableB);
			b.start();
			Thread.sleep(100);
			Thread a = new Thread(run.runnableA);
			a.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}
}

class Thread01Ch03_12_Runnable{
	private String lock = new String("");
	public Runnable runnableA = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("wait begin");
					lock.wait();
					System.out.println("wait end");
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
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}