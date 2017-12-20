package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread26_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午6:21:09
 */
public class Thread01Ch01_26_Test {
	/**1.7.5 能停止的线程-暴力停止**/
	//1.7.5 能停止的线程-暴力停止
	//-1线程被暴力停止(stop)运行后图标呈灰色。
	//-1使用stop()方法停止线程则是非常暴力的。
	public static void main(String[] args) {
		try {
			Thread01Ch01_26_Thread thread = new Thread01Ch01_26_Thread();
			thread.start();
			Thread.sleep(8000);
			thread.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_26_Thread extends Thread{
	private int i =0;
	@Override
	public void run() {
		try {
			super.run();
			while(true){
				i++;
				System.out.println("i="+i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

