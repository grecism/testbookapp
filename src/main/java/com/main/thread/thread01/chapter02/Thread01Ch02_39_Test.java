package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_39_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午12:51:49
 */
public class Thread01Ch02_39_Test {
	/**2.3.3 解决异步死循环**/
	//2.3.3 解决异步死循环
	//-1出现死循环的效果
	//-1在启动Thread01Ch02_39_Thread线程时,变量private boolean flag = true;存在于公共堆栈及线程的私有堆栈中。在JVM被设置为-server模式时为
	//了线程运行的效率,线程一直在私有堆栈中取得flag的值是true。而代码thread.setFlag(false);虽然被执行,更新的却是公共堆栈中的flag变量值false,所以
	//一直就是死循环的状态。
	//-2不再出现死循环
	//-2通过使用volatile关键字,强制的从公共内存中读取变量的值。	
	//这个问题其实就是私有堆栈中的值和公共堆栈中的值不同步造成的。解决这样的问题就要使用volitale关键字了,它主要的作用就是当线程访问flag这个变量时,强制性
	//从公共堆栈中进行取值。
	//使用volatile关键字增加了实例变量在多个线程之间的可见性。但volatile关键字最致命的缺点是不支持原子性。比较synchronized和volatile:
	//(1)关键字volatile是线程同步的轻量级实现,所以volatile性能肯定比synchronized要好,并且volatile只能修饰于变量,而synchronized可以修饰
	//方法,以及代码块。随着JDK新版本的发布,synchronized关键字在执行效率上得到很大提升,在开发中使用synchronized关键字的比率还是比较大的。
	//(2)多线程访问volatile不会发生阻塞,而synchronized会出现阻塞。
	//(3)volatile能保证数据的可见性,但不能保证原子性;而synchronized可以保证原子性,也可以间接保持可见性,因为它会将私有内存和公共内存中的数据做同步。
	//(4)关键字volatile解决的是变量在多个线程之间的可见性;而synchronized关键字解决的是多个线程之间访问资源的同步性。
	//线程安全包含原子性和可见性两个方面,java的同步机制都是围绕这两个方面来确保线程安全的。
	public static void main(String[] args) {
		try {
			Thread01Ch02_39_Thread thread = new Thread01Ch02_39_Thread();
			thread.start();
			Thread.sleep(5000);
			thread.setFlag(false);
			System.out.println("已经赋值为false");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_39_Thread extends Thread{
	private boolean flag = true;
	//volatile private boolean flag = true;
	public boolean isFlag(){
		return flag;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	/*public void printMethod(){
		try {
			while(flag == true){
				System.out.println("threadname="+Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		printMethod();
	}*/
	
	@Override
	public void run() {
		try {
			System.out.println("线程进入run了");
			while(flag == true){
				System.out.println("threadname="+Thread.currentThread().getName());
				Thread.sleep(1000);
			}
			System.out.println("线程被停止了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}