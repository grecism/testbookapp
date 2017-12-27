package com.main.designpattern.designpattern01.chapter01;

/**
 * 
 *<p>Title	: DPattern01Ch01_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月25日上午11:00:50
 */
public class DPattern01Ch01_01_Test {
	/**1创建型模式***/
	/**1.1工厂模式**/
	//工厂模式（Factory Pattern）是 Java 中最常用的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
	//在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。
	//优点： 1、一个调用者想创建一个对象，只要知道其名称就可以了。 2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。
	//3、屏蔽产品的具体实现，调用者只关心产品的接口。
	//缺点：每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。这并不是什么好事。
	//实现:
	//我们将创建一个 Shape 接口和实现 Shape 接口的实体类。下一步是定义工厂类 ShapeFactory。
	//FactoryPatternDemo，我们的演示类使用 ShapeFactory 来获取 Shape 对象。它将向 ShapeFactory 传递信息（CIRCLE / RECTANGLE / SQUARE），以便获取它所需对象的类型。
	public static void main(String[] args) {
		//4.使用该工厂,通过传递类型信息来获取实体类的对象。
		DPattern01Ch01_01_ShapeFactory shapeFactory = new DPattern01Ch01_01_ShapeFactory();
		//获取RECTANGLE的对象,并调用它的draw()方法。
		DPattern01Ch01_01_Shape shapeA = shapeFactory.getShape("RECTANGLE");
		//调用RECTANGLE的draw()方法。
		shapeA.draw();
		DPattern01Ch01_01_Shape shapeB = shapeFactory.getShape("SQUARE");
		shapeB.draw();
		DPattern01Ch01_01_Shape shapeC = shapeFactory.getShape("CIRCLE");
		shapeC.draw();
	}
}

//1.创建一个接口。
interface DPattern01Ch01_01_Shape{
	void draw();
}

//2.创建实现接口的实体类。
class DPattern01Ch01_01_Rectangle implements DPattern01Ch01_01_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Rectangle");
	}
	
}

class DPattern01Ch01_01_Square implements DPattern01Ch01_01_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Square");
	}
	
}

class DPattern01Ch01_01_Circle implements DPattern01Ch01_01_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Circle");
	}
	
}

//3.创建一个工厂,生成基于给定信息的实体类的对象。
class DPattern01Ch01_01_ShapeFactory{
	//使用getShape()方法获取形状类型的对象
	public DPattern01Ch01_01_Shape getShape(String shapeType){
		if(shapeType == null){
			return null;
		}
		if("RECTANGLE".equalsIgnoreCase(shapeType)){
			return new DPattern01Ch01_01_Rectangle();
		}else if("SQUARE".equalsIgnoreCase(shapeType)){
			return new DPattern01Ch01_01_Square();
		}else if("CIRCLE".equalsIgnoreCase(shapeType)){
			return new DPattern01Ch01_01_Circle();
		}
		return null;
	}
}














