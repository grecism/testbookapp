package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_06_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午3:18:00
 */
public class Thread01Ch02_06_Test {
	/**2.1.5 脏读**/
	//在2.1.4节示例中已经实现多个线程调用同一个方法时,为了避免数据出现交叉的情况,使用synchronized关键字来进行同步。
	//虽然在赋值时进行了同步,但在取值时有可能出现一些意想不到的意外,这种情况就是脏读(dirtyRead),发生脏读的情况时在读取
	//实例变量时,此值已经被其他线程更改过了。
	//2.1.5 脏读
	//-1出现脏读情况
	//-2不再出现脏读了
	//-1出现脏读时因为getValue()方法并不是同步的,所以可以在任意时候进行调用。
	//-2解决办法当然就是加上同步synchronized关键字
	//-1-2 
	//可见,setValue()和getValue()被依次执行。通过这个案例不仅要知道脏读是通过synchronized解决的,还要知道:
	//1)当A线程调用anyObject对象加入synchronized关键字的X方法时,A线程就获得了X方法锁,更准确地讲,是获得了对象的锁,所以其它线程必须等
	//A线程执行完毕才可以调用X方法,但B线程可以随意调用其它的非synchronized同步方法。
	//2)当A线程调用anyObject对象加入synchronized关键字的X方法时,A线程就获得了X方法所在对象的锁,所以其它线程必须等A线程执行完毕才可以调用
	//X方法,而B线程如果调用声明了synchronized关键字的非X方法时,必须等A线程将X方法执行完,也就是释放对象锁后才可以调用。这时A线程已经执行了一个
	//完整的任务,也就是username和password这两个实例变量已经同时被赋值,不存在脏读的基本环境。
	public static void main(String[] args) {
		try {
			Thread01Ch02_06_Object object = new Thread01Ch02_06_Object();
			Thread01Ch02_06_01 thread01 = new Thread01Ch02_06_01(object);
			thread01.start();
			thread01.sleep(2000);//打印结果受此值大小影响
			object.getValue();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_06_Object {
	private String username = "A";
	private String password ="AA";
	
	synchronized public void setValue(String username,String password){
		try {
			this.username = username;
			Thread.sleep(5000);
			this.password = password;
			System.out.println("setValue threadName="+Thread.currentThread().getName()
					+" username="+username+" password="+password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*public void getValue(){
		System.out.println("getValue threadName="+Thread.currentThread().getName()
				+" username="+username+" password="+password);
	}*/
	
	synchronized public void getValue(){
		System.out.println("getValue threadName="+Thread.currentThread().getName()
				+" username="+username+" password="+password);
	}
}

class Thread01Ch02_06_01 extends Thread{
	private Thread01Ch02_06_Object object;
	
	public Thread01Ch02_06_01(Thread01Ch02_06_Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.setValue("B", "BB");
	}
}
