package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread40_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日下午5:04:00
 */
public class Thread01Ch01_40_Test {
	/**1.11 守护线程**/
	//在java线程中有两种线程,一种是用户线程,另一种是守护线程。
	//守护线程是一种特殊的线程,它的特性有"陪伴"的含义,当进程中不存在非守护线程了,则守护线程自动销毁。
	//典型的守护线程就是垃圾回收线程,当进程中没有非守护线程了,则垃圾回收线程也就没有存在的必要了,自动销毁。
	//Daemon的作用是为其他线程的运行提供便利,守护线程最典型的应用就是GC(垃圾回收器),它就是一个很称职的守护者。
	//1.11 守护线程
	//-1守护线程也退出了。
	//-1守护线程也退出了。
	public static void main(String[] args) {
		try {
			Thread thread01 = new Thread(){
				private int i = 0;
				@Override
				public void run() {
					super.run();
					try {
						while(true){
							i++;
							System.out.println("i="+i);
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			thread01.setDaemon(true);
			thread01.start();
			Thread.sleep(5000);
			System.out.println("end 之后,thread01也就不再打印了,也就是停止了！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
