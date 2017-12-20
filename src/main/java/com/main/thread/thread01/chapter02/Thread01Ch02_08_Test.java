package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午5:10:29
 */
public class Thread01Ch02_08_Test {
	//2.1.6 Synchronized锁重入
	//可重入锁也支持在父子类继承的环境中。
	//-2重入到父类中的锁
	//-2此实验说明,当存在父子类继承关系时,子类是完全可以通过"可重入锁"调用父类的同步方法的。
	public static void main(String[] args) {
		Thread01Ch02_08_01 thread01 = new Thread01Ch02_08_01();
		thread01.start();
	}
}

class Thread01Ch02_08_Parent {
	public int i = 10;
	
	synchronized public void operateParentMethod(){
		try {
			i--;
			System.out.println("parent print i="+i);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_08_Sub extends Thread01Ch02_08_Parent{
	public void operateSubMethod(){
		try {
			while(i > 0){
				i--;
				System.out.println("sub print i="+i);
				Thread.sleep(100);
				this.operateParentMethod();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_08_01 extends Thread{
	@Override
	public void run() {
		super.run();
		Thread01Ch02_08_Sub sub = new Thread01Ch02_08_Sub();
		sub.operateSubMethod();
	}
}
