package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread29_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午9:51:47
 */
public class Thread01Ch01_29_Test {
	/**1.7.8 使用return停止线程**/
	//将方法interrupt()与return结合使用也能实现停止线程的效果。
	//1.7.8 使用return停止线程
	//-1成功停止运行。
	//-1不过还是建议使用"抛异常"的方法来实现线程的停止,因为在catch块中还可以将异常向上抛,使线程停止的事件得以传播。
	public static void main(String[] args) {
		try {
			Thread01Ch01_29_Thread thread = new Thread01Ch01_29_Thread();
			thread.start();
			Thread.sleep(5000);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_29_Thread extends Thread{
	private int count = 0;
	@Override
	public void run() {
		super.run();
		/*while(true){
			if(this.isInterrupted()){
				System.out.println("停止了！");
				return;
			}
			System.out.println("time="+System.currentTimeMillis());
		}*/
		while(count == 0 ){
			if(this.isInterrupted()){
				System.out.println("停止了！");
				return;
			}
			System.out.println("time="+System.currentTimeMillis());
		}
		System.out.println("while后面的语句！");
	}
}

