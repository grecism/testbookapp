package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread32_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午10:45:48
 */
public class Thread01Ch01_32_Test {
	//1.8.2 suspend与resume方法的缺点-独占
	//-2Thread01Ch01_32_Thread的情况控制台打印main end信息。
	//-2控制台打印main end信息。
	public static void main(String[] args) {
		try {
			Thread01Ch01_32_Thread thread = new Thread01Ch01_32_Thread();
			thread.start();
			Thread.sleep(1000);
			thread.suspend();
			System.out.println("main end!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_32_Thread extends Thread{
	private Long i = 0L;

	public Long getI() {
		return i;
	}

	public void setI(Long i) {
		this.i = i;
	}
	
	@Override
	public void run() {
		super.run();
		while(true){
			i++;
		}
	}
}
