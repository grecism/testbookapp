package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread39_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日下午3:28:32
 */
public class Thread01Ch01_39_Test {
	/**1.10.4 看谁运行的快**/
	//1.10.4 看谁运行的快
	//-1优先级高的运行的快。
	//-1 优先级高的运行的快。
	public static void main(String[] args) {
		try {
			Thread01Ch01_39_Thread t1 = new Thread01Ch01_39_Thread();
			Thread01Ch01_39_Thread t2 = new Thread01Ch01_39_Thread();
			t1.setPriority(Thread.NORM_PRIORITY - 3);
			t1.start();
			t2.setPriority(Thread.NORM_PRIORITY + 3);
			t2.start();
			Thread.sleep(20000);
			t1.stop();
			t2.stop();
			System.out.println("thread01="+t1.getI());
			System.out.println("thread02="+t2.getI());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_39_Thread extends Thread{
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
