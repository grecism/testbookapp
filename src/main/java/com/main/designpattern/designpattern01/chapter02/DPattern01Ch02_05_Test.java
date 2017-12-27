package com.main.designpattern.designpattern01.chapter02;

/**
 * 
 *<p>Title	: DPattern01Ch02_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午5:45:26
 */
public class DPattern01Ch02_05_Test {
	/**2.5装饰器模式**/
	//装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
	//这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
	//我们通过下面的实例来演示装饰器模式的用法。其中，我们将把一个形状装饰上不同的颜色，同时又不改变形状类。
	//优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
	//缺点：多层装饰比较复杂。
	//实现:
	//我们将创建一个 Shape 接口和实现了 Shape 接口的实体类。然后我们创建一个实现了 Shape 接口的抽象装饰类 ShapeDecorator，并把 Shape 对象作为它的实例变量。
	//RedShapeDecorator 是实现了 ShapeDecorator 的实体类。
	//DecoratorPatternDemo，我们的演示类使用 RedShapeDecorator 来装饰 Shape 对象。
	public static void main(String[] args) {
		//5.使用RedShapeDecorator来装饰Shape对象。
		DPattern01Ch02_05_Rectangle rectangle = new DPattern01Ch02_05_Rectangle();
		DPattern01Ch02_05_Circle circle = new DPattern01Ch02_05_Circle();
		DPattern01Ch02_05_RedShapeDecorator redShapeDecorator = new DPattern01Ch02_05_RedShapeDecorator(new DPattern01Ch02_05_Rectangle());
		DPattern01Ch02_05_RedShapeDecorator redShapeDecorator2 = new DPattern01Ch02_05_RedShapeDecorator(new DPattern01Ch02_05_Circle());
	
		System.out.println("Rectangle with normal border");
		rectangle.draw();
		System.out.println("Circle with normal border");
		circle.draw();
		System.out.println("Rectangle of red border");
		redShapeDecorator.draw();
		System.out.println("Circle of red border");
		redShapeDecorator2.draw();
	}
}

interface DPattern01Ch02_05_Shape{
	public void draw();
}

//2.创建实现接口的实体类。
class DPattern01Ch02_05_Rectangle implements DPattern01Ch02_05_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Rectangle");
	}
	
}

class DPattern01Ch02_05_Circle implements DPattern01Ch02_05_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Circle");
	}
	
}

//3.创建实现了Shape接口的抽象装饰类
abstract class DPattern01Ch02_05_ShapeDecorator implements DPattern01Ch02_05_Shape{
	protected DPattern01Ch02_05_Shape shapeDecorator;
	public DPattern01Ch02_05_ShapeDecorator(DPattern01Ch02_05_Shape shapeDecorator) {
		this.shapeDecorator = shapeDecorator;
	}
	/*@Override
	public void draw() {
		shapeDecorator.draw();
	}*/
	public abstract void draw();
}

//4.创建扩展了ShapeDecorator类的实体装饰类。
class DPattern01Ch02_05_RedShapeDecorator extends DPattern01Ch02_05_ShapeDecorator{
	public DPattern01Ch02_05_RedShapeDecorator(DPattern01Ch02_05_Shape shapeDecorator) {
		super(shapeDecorator);
	}
	@Override
	public void draw() {
		shapeDecorator.draw();
		setRedBorder(shapeDecorator);
	}
	public void setRedBorder(DPattern01Ch02_05_Shape shapeDecorator){
		System.out.println("Border Color: Red");
		
	}
}













