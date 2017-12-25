package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午3:22:19
 */
public class Thread01Ch06_03_Test {
	//6.2 延迟加载/"懒汉模式"
	//3. 延迟加载/"懒汉模式"的解决方案
	//(1)声明sychronized关键字
	//既然多个线程可以同时进入getInstance()方法,那么只需要对getInstance()方法声明sychronized关键字即可。
	//(2)尝试同步代码块
	//(3)针对某些重要的代码进行单独的同步
	//(4)使用DCL双检查锁机制(双重检查 Double-check-Locking)
	//-1控制台运行结果相同,效率低,线程安全。
	//-1此方法加入同步sychronized关键字得到相同实例的对象,但此种方法的运行效率非常低下,是同步运行的,下一个线程想要取得对象,则必须等上一个线程释放锁之后,
	//才可以继续执行。
	//-2控制台运行结果相同,效率低,线程安全。
	//-2此方法加入同步sychronized语句块得到相同实例的对象,但此种方法的运行效率也是非常低下的,和sychronized同步方法一样是同步运行的。
	//-3控制台运行结果不相同,效率高,非线程安全。
	//-3此方法加入同步sychronized语句块,只对实例化对象的关键代码进行同步,从语句的结构上来讲,运行的效率的确得到了提升。但如果是遇到多线程的情况下还是无法解决
	//得到同一个实例对象的结果。
	//-4控制台运行结果相同,效率高,线程安全。
	//-4使用双重检查锁功能,成功地解决了"懒汉模式"遇到多线程的问题。DCL也是大多数多线程结合单例模式使用的解决方案。
	public static void main(String[] args) {
		Thread01Ch06_03_Thread t1 = new Thread01Ch06_03_Thread();
		Thread01Ch06_03_Thread t2 = new Thread01Ch06_03_Thread();
		Thread01Ch06_03_Thread t3 = new Thread01Ch06_03_Thread();
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread01Ch06_03_Object{
	private static Thread01Ch06_03_Object object;
	private Thread01Ch06_03_Object() {
		
	}
	//设置同步方法效率太低了,整个方法被上锁。
	/*synchronized public static Thread01Ch06_03_Object getInstance(){
		try {
			//延迟加载
			if(object != null){
			}else{
				Thread.sleep(3000);
				object = new Thread01Ch06_03_Object();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}*/
	
	//此种写法等同于(1)的写法,效率一样很低,全部代码被上锁。
    /*public static Thread01Ch06_03_Object getInstance(){
		try {
			synchronized (Thread01Ch06_03_Object.class) {
				//延迟加载
				if(object != null){
				}else{
					Thread.sleep(3000);
					object = new Thread01Ch06_03_Object();
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}*/
	
	//虽然部分代码被上锁,但还是有非线程安全问题。
	/*public static Thread01Ch06_03_Object getInstance(){
		try {
			//延迟加载
			if(object != null){
			}else{
				Thread.sleep(3000);
				synchronized (Thread01Ch06_03_Object.class) {
					object = new Thread01Ch06_03_Object();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}*/
	
	//使用双检测机制来解决问题,既保证了不需要同步代码的异步执行性,又保证了单例的效果。
	public static Thread01Ch06_03_Object getInstance(){
		try {
			//延迟加载
			if(object != null){
			}else{
				Thread.sleep(3000);
				synchronized (Thread01Ch06_03_Object.class) {
					if(object == null){
						object = new Thread01Ch06_03_Object();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}
}

class Thread01Ch06_03_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(Thread01Ch06_03_Object.getInstance().hashCode());
	}
}






















