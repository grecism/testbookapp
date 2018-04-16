package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午2:36:29
 */
public class Thread01Ch03_07_Test {
	/**3.1.4 方法wait()锁释放与notify()锁不释放**/
	//3.1.4 方法wait()锁释放与notify()锁不释放
	//-1当方法wait()被执行后,锁被自动释放
	//-1方法wait()自动释放锁
	//-2wait改成sleep是同步效果
	//-2wait改成sleep是同步效果
	public static void main(String[] args) {
		Object lock = new Object();
		Thread01Ch03_07_Thread_A a = new Thread01Ch03_07_Thread_A(lock);
		a.start();
		Thread01Ch03_07_Thread_B b = new Thread01Ch03_07_Thread_B(lock);
		b.start();
		
	}
}

class Thread01Ch03_07_Service{
	public void method(Object lock){
		try {
			synchronized (lock) {
				System.out.println("wait begin");
				//lock.wait();
				Thread.sleep(3000);
				System.out.println("wait end");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_07_Thread_A extends Thread{
	private Object lock;
	public Thread01Ch03_07_Thread_A(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_07_Service service = new Thread01Ch03_07_Service();
		service.method(lock);
	}
}

class Thread01Ch03_07_Thread_B extends Thread{
	private Object lock;
	public Thread01Ch03_07_Thread_B(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		super.run();
		Thread01Ch03_07_Service service = new Thread01Ch03_07_Service();
		service.method(lock);
	}
}