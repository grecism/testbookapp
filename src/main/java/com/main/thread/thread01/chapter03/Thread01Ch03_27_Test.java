package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_27_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午6:18:06
 */
public class Thread01Ch03_27_Test {
	/**3.2 方法join的使用**/
	//在很多情况下,主线程创建并启动子线程,如果子线程中要进行大量的耗时运算,主线程往往将早于子线程结束之前结束。这时,如果主线程想等待子线程执行完成之后再结束,
	//比如子线程处理一个数据,主线程要取得这个数据中的值,就要用到join()方法了。方法join()的作用是等待线程对象销毁。
	/**3.2.1 学习方法join前的铺垫**/
	/**3.2.2 用join方法来解决**/
	//3.2.1 学习方法join前的铺垫
	//3.2.2 用join方法来解决
	//-1方法sleep()中的值不能确定。
	//-2 主线程最后结束运行。
	//-1方法sleep()中的值不能确定。
	//-2 用join方法来解决。
	public static void main(String[] args) {
		try {
			Thread01Ch03_27_Thread t = new Thread01Ch03_27_Thread();
			t.start();
			//Thread.sleep(millis);
			//System.out.println("想要最后结束！");
			//System.out.println("sleep中的值是多少不能确定");
			t.join();
			System.out.println("t线程运行完毕之后在执行此句!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_27_Thread extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			int value = (int) (Math.random()*10000);
			System.out.println(value);
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}























