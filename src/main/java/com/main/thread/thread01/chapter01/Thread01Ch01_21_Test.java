package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread21_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午5:01:25
 */
public class Thread01Ch01_21_Test {
	/**1.7.3 能停止的线程,异常法**/
	//1.7.3 能停止的线程,异常法
	//-1for后面的语句继续运行。
	//-1在for循环中判断是否是停止状态,是就停止执行下面的代码。
	//虽然停止了线程,但如果for语句下面还有语句,还是会继续运行的。
	public static void main(String[] args) {
		try {
			Thread01Ch01_21_Thread thread = new Thread01Ch01_21_Thread();
			thread.start();
			thread.sleep(2);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

class Thread01Ch01_21_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i <5000; i++) {
			if(this.interrupted()){
				System.out.println("已经是停止状态了!我要退出了！");
				break;
			}
			System.out.println("i="+(i+1));
		}
		System.out.println("如果此代码是for又继续运行,线程并未停止！");
	}
}

