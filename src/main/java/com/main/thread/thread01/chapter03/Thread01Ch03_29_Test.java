package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_29_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日上午10:58:07
 */
class Thread01Ch03_29_Test {
	/**3.2.4 方法join(long)的使用**/
	//方法join(long)中的参数是设定等待的时间。
	//3.2.4 方法join(long)的使用
	//-1join(long)运行结果是等待了2秒
	//-2sleep(long)运行结果也是等待了2秒
	//-1-2
	//运行结果都是等待了2秒,join(long)和sleep(long)的区别?示例中的运行效果并没有区别,区别主要还是来自于这两个方法对同步的处理上。
	public static void main(String[] args) {
		try {
			Thread01Ch03_29_Thread t = new Thread01Ch03_29_Thread();
			t.start();
			t.join(2000);
			//Thread.sleep(2000);
			System.out.println("end time"+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_29_Thread extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			System.out.println("begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
















