package com.main.designpattern.designpattern01.chapter02;

/**
 * 
 *<p>Title	: DPattern01Ch02_07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午6:51:32
 */
public class DPattern01Ch02_07_Test {
	/**2.6外观模式**/
	//外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。这种类型的设计模式属于结构型模式，它向现有的系统添加一个
	//接口，来隐藏系统的复杂性。
	//这种模式涉及到一个单一的类，该类提供了客户端请求的简化方法和对现有系统类方法的委托调用。
	//优点： 1、减少系统相互依赖。 2、提高灵活性。 3、提高了安全性。
	//缺点：不符合开闭原则，如果要改东西很麻烦，继承重写都不合适。
	//实现:
	//我们将创建一个 Shape 接口和实现了 Shape 接口的实体类。下一步是定义一个外观类 ShapeMaker。
	//ShapeMaker 类使用实体类来代表用户对这些类的调用。FacadePatternDemo，我们的演示类使用 ShapeMaker 类来显示结果。
	public static void main(String[] args) {
		//4.使用该外观类画出各种类型的形状。
		DPattern01Ch02_07_ShapeMaker shapeMaker = new DPattern01Ch02_07_ShapeMaker();
		shapeMaker.drawRectangle();
		shapeMaker.drawSquare();
		shapeMaker.drawCircle();
		
	}
}

//1.创建一个接口。
interface DPattern01Ch02_07_Shape{
	public void draw();
}

//2.创建实现接口的实体类。
class DPattern01Ch02_07_Rectangle implements DPattern01Ch02_07_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Rectangle");
	}
	
}

class DPattern01Ch02_07_Square implements DPattern01Ch02_07_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Square");
	}
	
}

class DPattern01Ch02_07_Circle implements DPattern01Ch02_07_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Circle");
	}
	
}

//3.创建一个外观类。
class DPattern01Ch02_07_ShapeMaker{
	private DPattern01Ch02_07_Rectangle rectangle;
	private DPattern01Ch02_07_Square square;
	private DPattern01Ch02_07_Circle circle;
	public DPattern01Ch02_07_ShapeMaker() {
		this.rectangle = new DPattern01Ch02_07_Rectangle();
		this.square = new DPattern01Ch02_07_Square();
		this.circle = new DPattern01Ch02_07_Circle();
	}
	
	public void drawRectangle(){
		rectangle.draw();
	}
	public void drawSquare(){
		square.draw();
	}
	public void drawCircle(){
		circle.draw();
	}
}
























 	