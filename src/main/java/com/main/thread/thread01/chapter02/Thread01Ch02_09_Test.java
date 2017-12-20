package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_09_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午5:19:38
 */
public class Thread01Ch02_09_Test {
	/**2.1.7 出现异常,锁自动释放**/
	//2.1.7 出现异常,锁自动释放
	//当一个线程执行的代码出现异常时,其所持有的锁会自动释放。
	//-1a线程异常,b线程正常打印
	//-1线程a出现异常并释放锁,线程b进入方法正常打印,实验的结论就是出现异常的锁被自动释放了。
	public static void main(String[] args) {
		//System.out.println(Math.random());
		try {
			Thread01Ch02_09_Object object = new Thread01Ch02_09_Object();
			Thread01Ch02_09_A a = new Thread01Ch02_09_A(object);
			a.setName("a");
			a.start();
			Thread.sleep(500);
			Thread01Ch02_09_B b = new Thread01Ch02_09_B(object);
			b.setName("b");
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_09_Object {
	
	synchronized public void noLockMethod(){
		if("a".equals(Thread.currentThread().getName())){
			System.out.println("threadname="+Thread.currentThread().getName()+" run time"+System.currentTimeMillis());
			int i = 1;
			while(i == 1){
				if((""+Math.random()).substring(0,8).equals("0.123456")){
					System.out.println("threadname="+Thread.currentThread().getName()+" run time"+System.currentTimeMillis());
					Integer.parseInt("a");
				}
			}
		}else{
			System.out.println("threaname=b run time"+System.currentTimeMillis());
		}
	}
}

class Thread01Ch02_09_A extends Thread{
	private Thread01Ch02_09_Object object;
	
	public Thread01Ch02_09_A(Thread01Ch02_09_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.noLockMethod();
	}
}

class Thread01Ch02_09_B extends Thread{
	private Thread01Ch02_09_Object object;
	
	public Thread01Ch02_09_B(Thread01Ch02_09_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.noLockMethod();
	}
}
