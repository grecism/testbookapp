package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_40_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午2:54:05
 */
public class Thread01Ch02_40_Test {
	/**2.3.4 volatile非原子的特性**/
	//2.3.4 volatile非原子的特性
	//关键字volatile虽然增加了实例变量在多个线程之间的可见性,但它却不具备同步性,那么也就不具备原子性。
	//-1运行结果值不是10000
	//-2运行结果值是10000
	//-1-2
	//在本示例的意图中,如果在方法private static void addCount()前加入synchronized同步关键字,也就没有必要再使用volatile关键字来声明count变量了。
	//关键字volatile主要使用的场合是在多个线程中可以感知实例变量被更改了,并且可以获得最新的值使用,也就是用多线程读取共享变量时可以获得最新值使用。
	//关键字volatile提示线程每次从共享内存中读取变量,而不是从私有内存中读取,这样就保证了同步数据的可见性。但在这里需要注意的是:如果修改实例变量中的数据,比如
	//i++,也就是i=i+1,则这样的操作其实并不是一个原子操作,也就是非线程安全的。表达式i++的操作步骤分解如下:
	//(1)从内存中取出i的值;(2)计算i的值;(3)将i的值写到内存中。
	//假如在第二步计算值的时候,另外一个线程也修改i的值,那么这个时候就会出现脏数据。解决的办法其实就是使用synchronized关键字。所以说volatile本身并不处理数据
	//的原子性,而是强制对数据的读写及时影响到主内存的。
	//变量在内存中工作的过程:
	//(1)read和load阶段:从主存复制变量到当前线程工作内存;
	//(2)use和assign(赋值)阶段:执行代码,改变共享变量值;
	//(3)store和write阶段:用工作内存数据刷新主存对应变量的值。
	//load加载,use操作,assign赋值,这3步非原子性。
	//在多线程环境中,use和assign是多次出现的,但这一操作并不是原子性,也就是在read和load之后,如果主内存count变量发生修改之后,线程工作内存中的值由于已经加载,
	//不会产生对应的变化,也就是私有内存和公共内存中的变量不同步,所以计算出来的结果会和预期不一样,也就出现了非线程安全问题。
	//对于用volatile修饰的变量,JVM虚拟机只是保证从主内存加载到线程工作内存的值是最新的,例如线程1和线程2在进行read和load的操作中,发现主内存中count的值都是
	//5,那么都会加载这个最新的值。也就是说,volatile解决的是变量读时的可见性问题,但无法保证原子性,对于多个线程访问同一个实例变量还是需要加锁同步。
	public static void main(String[] args) {
		Thread01Ch02_40_Thread[] threadArray = new Thread01Ch02_40_Thread[100];
		for (int i = 0; i < 100; i++) {
			threadArray[i] = new Thread01Ch02_40_Thread();
		}
		for (int i = 0; i < 100; i++) {
			threadArray[i].start();
		}
	}
}




class Thread01Ch02_40_Thread extends Thread{
	volatile private static int count;
	
	/*private static void addCount(){
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("count="+count);
	}*/
	
	//注意一定要添加static关键字
	//synchronized与static锁的内容就是Thread01Ch02_40_Thread.class类了,达到同步效果了
	synchronized private static void addCount(){
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("count="+count);
	}
	
	@Override
	public void run() {
		super.run();
		addCount();
	}
}


















