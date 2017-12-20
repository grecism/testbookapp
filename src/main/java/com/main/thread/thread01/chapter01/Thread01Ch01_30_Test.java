package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread30_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午10:02:33
 */
public class Thread01Ch01_30_Test {
	/**1.8 暂停线程**/
	//暂停线程意味着此线程还可以恢复运行。在java多线程中,可以使用suspend()方法暂停线程,使用resume()方法恢复线程的执行。
	/**1.8.1 suspend与resume方法的使用**/
	//1.8.1 suspend与resume方法的使用
	//-1暂停与恢复线程。
	//-1暂停与恢复线程。
	public static void main(String[] args) {
		try {
			Thread01Ch01_30_Thread thread = new Thread01Ch01_30_Thread();
			thread.start();
			Thread.sleep(5000);
			//暂停
			thread.suspend();
			System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
			Thread.sleep(5000);
			System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
			//恢复
			thread.resume();
			Thread.sleep(5000);
			//暂停
			thread.suspend();
			System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
			Thread.sleep(5000);
			System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_30_Thread extends Thread{
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

