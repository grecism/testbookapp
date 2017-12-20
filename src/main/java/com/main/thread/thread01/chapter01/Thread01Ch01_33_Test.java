package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread33_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午10:49:12
 */
public class Thread01Ch01_33_Test{
	//1.8.2 suspend与resume方法的缺点-独占
	//-3控制台未打印main end信息。
	//-3出现这样情况的原因是,当程序运行到println()方法内部停止时,同步锁未被释放,方法println()源代码：
	//public void println(long x){
	//synchronized (this){ print(x); newLine();}
	//} 锁不被释放
	//这导致当前PrintStream对象的println()方法一直呈"暂停"状态,并且"锁未释放",而main()方法中的代码
	//system.out.println("main end!");迟迟不能执行打印。
	public static void main(String[] args) {
		
		try {
			Thread thread = new Thread(){
				private Long i = 0L;
				@Override
				public void run() {
					super.run();
					while(true){
						i++;
						System.out.println(i);
					}
				}
			};
			thread.start();
			Thread.sleep(1000);
			thread.suspend();
			System.out.println("main end!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
