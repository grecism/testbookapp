package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread34_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午11:04:49
 */
public class Thread01Ch01_34_Test {
	/**1.8.3 suspend与resume方法的缺点-不同步**/
	//在使用suspend与resume方法时也容易出现因为线程的暂停而导致数据不同步的情况。
	//1.8.3 suspend与resume方法的缺点-不同步
	//-1程序运行的结果出现值不同步的情况,运行结果如下:a线程暂停了！ a=11
	//-1程序运行的结果出现值不同步的情况,在程序中使用suspend()方法要格外注意。
	public static void main(String[] args) {
		try {
			final Thread01Ch01_34_Object object = new Thread01Ch01_34_Object();
			Thread thread = new Thread(){
				@Override
				public void run() {
					super.run();
					object.setValue("a", "aa");
				}
			};
			thread.setName("a");
			thread.start();
			Thread.sleep(1000);
			Thread thread2 = new Thread(){
				@Override
				public void run() {
					super.run();
					object.printusernameandpassword();
				}
			};
			thread2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch01_34_Object {
	private String username= "1";
	private String password ="11";
	
	public void setValue(String u,String p){
		this.username = u;
		if("a".equals(Thread.currentThread().getName())){
			System.out.println("a线程暂停了！");
			Thread.currentThread().suspend();
		}
		this.password = p;
	}
	
	public void printusernameandpassword(){
		System.out.println(username+"="+password);
	}
}
