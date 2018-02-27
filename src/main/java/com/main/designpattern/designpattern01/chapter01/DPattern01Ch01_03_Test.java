package com.main.designpattern.designpattern01.chapter01;

/**
 * 
 *<p>Title	: DPattern01Ch01_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月25日下午4:50:52
 */
public class DPattern01Ch01_03_Test {
	/**1.3单例模式**/
	//单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
	//这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
	//注意：1、单例类只能有一个实例。2、单例类必须自己创建自己的唯一实例。3、单例类必须给所有其他对象提供这一实例。
	//注意事项：getInstance() 方法中需要使用同步锁 synchronized (Singleton.class) 防止多线程同时进入造成 instance 被多次实例化。
	//优点： 1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存）。 2、避免对资源的多重占用（比如写文件操作）。
	//缺点：没有接口，不能继承，与单一职责原则冲突，一个类应该只关心内部逻辑，而不关心外面怎么样来实例化。
	public static void main(String[] args) {
		//2.从singleton类获取唯一的对象。
		//(1)饿汉式。
		DPattern01Ch01_03_SingleObject singleObject = DPattern01Ch01_03_SingleObject.getInstance();
		System.out.println(singleObject.hashCode());
		//(6)枚举
		System.out.println(DPattern01Ch01_03_SingleObject_Enum.SINGLETON.getInstance().hashCode());
	
		
	}
}

//(1)饿汉式,线程安全,效率高,类加载时就初始化,浪费内存。
//它基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 
//getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
//1.创建一个Singleton类。 
class DPattern01Ch01_03_SingleObject{
	//创建SingleObject的一个对象
	private static DPattern01Ch01_03_SingleObject singleObject = new DPattern01Ch01_03_SingleObject();
	//让构造函数为private,这样该类就不会被实例化
	private DPattern01Ch01_03_SingleObject() {
	}
	//获取唯一可用的对象
	public static DPattern01Ch01_03_SingleObject getInstance(){
		return singleObject;
	}
}

//(2)懒汉式,线程不安全。
class DPattern01Ch01_03_SingleObject_A{
	private static DPattern01Ch01_03_SingleObject_A singleObject_A;
	private DPattern01Ch01_03_SingleObject_A() {
	}
	public static DPattern01Ch01_03_SingleObject_A getInstance(){
		if(singleObject_A == null){
			singleObject_A = new DPattern01Ch01_03_SingleObject_A();
		}
		return singleObject_A;
		
	}
}

//(3)懒汉式,线程安全,效率低。
class DPattern01Ch01_03_SingleObject_B{
	private static DPattern01Ch01_03_SingleObject_B singleObject_B;
	private DPattern01Ch01_03_SingleObject_B() {
	}
	synchronized public static DPattern01Ch01_03_SingleObject_B getInstance(){
		if(singleObject_B == null){
			singleObject_B = new DPattern01Ch01_03_SingleObject_B();
		}
		return singleObject_B;
	}
}

//(4)双检锁/双重校验锁(DCL,即double-checked-locking),懒汉式,线程安全,性能较高。
class DPattern01Ch01_03_SingleObject_C{
	private static DPattern01Ch01_03_SingleObject_C singleObject_C;
	private DPattern01Ch01_03_SingleObject_C() {
	}
	public static DPattern01Ch01_03_SingleObject_C getInstance(){
		if(singleObject_C == null){
			synchronized (singleObject_C) {
				if(singleObject_C == null){
					singleObject_C = new DPattern01Ch01_03_SingleObject_C();
				}
			}
		}
		return singleObject_C;
	}
}

//(5)登记式/静态内部类。
//这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，双检锁方式可在实例域
//需要延迟初始化时使用。这种方式同样利用了 classloder 机制来保证初始化 instance 时只有一个线程，它跟第 1 种方式不同的是：第 1 种方式只要 Singleton 
//类被装载了，那么 instance 就会被实例化（没有达到 lazy loading 效果），而这种方式是 Singleton 类被装载了，instance 不一定被初始化。
//因为 SingletonHolder 类没有被主动使用，只有显示通过调用 getInstance 方法时，才会显示装载 SingletonHolder 类，从而实例化 instance。
//想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，另外一方面，又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在
//其他的地方被主动使用从而被加载，那么这个时候实例化 instance 显然是不合适的。这个时候，这种方式相比第 1 种方式就显得很合理。
class DPattern01Ch01_03_SingleObject_D{
	private DPattern01Ch01_03_SingleObject_D() {
	}
	private static class DPattern01Ch01_03_SingleObject_Handler{
		private static DPattern01Ch01_03_SingleObject_D singleObject_D = new DPattern01Ch01_03_SingleObject_D();
	}
	public static DPattern01Ch01_03_SingleObject_D getInstance(){
		return DPattern01Ch01_03_SingleObject_Handler.singleObject_D;
	}
}

//(6)枚举
//这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。这种方式是 Effective Java 作者
//Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
enum DPattern01Ch01_03_SingleObject_Enum{
	SINGLETON;
	private  DPattern01Ch01_03_SingleObject_E singleObject_E;
	private DPattern01Ch01_03_SingleObject_Enum() {
		System.out.println("调用了enum类的构造方法");
		singleObject_E = new DPattern01Ch01_03_SingleObject_E();
	}
	public  DPattern01Ch01_03_SingleObject_E getInstance(){
		return singleObject_E;
	}
}

class DPattern01Ch01_03_SingleObject_E{
	
}





























