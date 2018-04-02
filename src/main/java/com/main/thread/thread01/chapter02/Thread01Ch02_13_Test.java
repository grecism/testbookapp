package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_13_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月7日下午3:02:04
 */
public class Thread01Ch02_13_Test {
	/**2.2.3 用同步代码块解决同步方法的弊端**/
	//2.2.3 用同步代码块解决同步方法的弊端
	//-1运行速度很快
	//-1当一个线程访问object的一个synchronized同步代码块时,另一个线程仍然可以访问该object对象中的非synchronized(this)同步代码块。
	//时间缩短,运行效率加快。
	public static void main(String[] args) {
		Thread01Ch02_13_Task task = new Thread01Ch02_13_Task();
		Thread01Ch02_13_A a = new Thread01Ch02_13_A(task);
		a.setName("a");
		a.start();
		Thread01Ch02_13_B b = new Thread01Ch02_13_B(task);
		b.setName("b");
		b.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long beginTime = Thread01Ch02_11_Util.beginTime1;
		if(Thread01Ch02_11_Util.beginTime2 < Thread01Ch02_11_Util.beginTime1){
			beginTime = Thread01Ch02_11_Util.beginTime2;
		}
		long endTime = Thread01Ch02_11_Util.endTime1;
		if(Thread01Ch02_11_Util.endTime2 > Thread01Ch02_11_Util.endTime1){
			endTime = Thread01Ch02_11_Util.endTime2;
		}
		System.out.println("耗时:"+((endTime-beginTime)/1000));
	}
}

class Thread01Ch02_13_Task {
	private String data1;
	private String data2;
	
	public void taskMethod(){
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			
			//出现线程安全问题。
			/*String datavalue1 = "长时间处理任务返回的值data1 threadname="+Thread.currentThread().getName();
			String datavalue2 = "长时间处理任务返回的值data2 threadname="+Thread.currentThread().getName();
			synchronized (this) {
				System.out.println("线程名"+Thread.currentThread().getName());
				data1 = datavalue1;
				data2 = datavalue2;
			}*/
			
			synchronized (this) {
				System.out.println("线程名"+Thread.currentThread().getName());
				String datavalue1 = "长时间处理任务返回的值data1 threadname="+Thread.currentThread().getName();
				String datavalue2 = "长时间处理任务返回的值data2 threadname="+Thread.currentThread().getName();
				data1 = datavalue1;
				data2 = datavalue2;
			}
			
			System.out.println("data1="+data1);
			System.out.println("data2="+data2);
			System.out.println("end task");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_13_A extends Thread{
	private Thread01Ch02_13_Task task;
	
	public Thread01Ch02_13_A(Thread01Ch02_13_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		Thread01Ch02_11_Util.beginTime1 = System.currentTimeMillis();
		task.taskMethod();
		Thread01Ch02_11_Util.endTime1 = System.currentTimeMillis();
	}
}

class Thread01Ch02_13_B extends Thread{
	private Thread01Ch02_13_Task task;
	
	public Thread01Ch02_13_B(Thread01Ch02_13_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		Thread01Ch02_11_Util.beginTime2 = System.currentTimeMillis();
		task.taskMethod();
		Thread01Ch02_11_Util.endTime2 = System.currentTimeMillis();
	}
}

