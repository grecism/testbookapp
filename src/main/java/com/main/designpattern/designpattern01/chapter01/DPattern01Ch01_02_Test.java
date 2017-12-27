package com.main.designpattern.designpattern01.chapter01;

import java.awt.Rectangle;

/**
 * 
 *<p>Title	: DPattern01Ch01_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月25日下午3:44:17
 */
public class DPattern01Ch01_02_Test {
	/**1.2抽象工厂模式**/ 	
	//抽象工厂模式（Abstract Factory Pattern）是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂。这种类型的设计模式属于创建型模式，
	//它提供了一种创建对象的最佳方式。
	//在抽象工厂模式中，接口是负责创建一个相关对象的工厂，不需要显式指定它们的类。每个生成的工厂都能按照工厂模式提供对象。
	//优点：当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。
	//缺点：产品族扩展非常困难，要增加一个系列的某一产品，既要在抽象的 Creator 里加代码，又要在具体的里面加代码。
	//实现:
	//我们将创建 Shape 和 Color 接口和实现这些接口的实体类。下一步是创建抽象工厂类 AbstractFactory。接着定义工厂类 ShapeFactory 和 ColorFactory，这两个工厂类都是扩展了 AbstractFactory。然后创建一个工厂创造器/生成器类 FactoryProducer。
	//AbstractFactoryPatternDemo，我们的演示类使用 FactoryProducer 来获取 AbstractFactory 对象。它将向 AbstractFactory 传递形状信息 Shape（CIRCLE / RECTANGLE / SQUARE），以便获取它所需对象的类型。同时它还向 AbstractFactory 传递颜色信息 Color（RED / GREEN / BLUE），以便获取它所需对象的类型。
	public static void main(String[] args) {
		//8.使用FactoryProducer来获取AbstractFactory,通过传递类型信息来获取实体类的对象。
		//获取形状工厂。
		DPattern01Ch01_02_AbstractFactory shapeFactory = DPattern01Ch01_02_FactoryProducer.getFactory("SHAPE");
		//获取形状为RECTANGLE的对象。
		DPattern01Ch01_02_Shape shapeA = shapeFactory.getShape("RECTANGLE");
		//调用RECTANGLE的 	draw方法。
		shapeA.draw();
		DPattern01Ch01_02_Shape shapeB = shapeFactory.getShape("SQUARE");
		shapeB.draw();
		DPattern01Ch01_02_Shape shapeC = shapeFactory.getShape("Circle");
		shapeC.draw();
		//获取颜色工厂
		DPattern01Ch01_02_AbstractFactory colorFactory = DPattern01Ch01_02_FactoryProducer.getFactory("COLOR");
		//获取颜色为RED的对象
		DPattern01Ch01_02_Color colorA = colorFactory.getColor("RED");
		//调用RED的fill方法
		colorA.fill();
		DPattern01Ch01_02_Color colorB = colorFactory.getColor("green");
		colorB.fill();
		DPattern01Ch01_02_Color colorC = colorFactory.getColor("blue");
		colorC.fill();
	}
}

//1.为形状创建一个接口。
interface DPattern01Ch01_02_Shape{
	void draw();
}

//2.创建实现接口的实体类。
class DPattern01Ch01_02_Rectangle implements DPattern01Ch01_02_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Rectangle");
	}
	
}

class DPattern01Ch01_02_Square implements DPattern01Ch01_02_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Square");
	}
	
}

class DPattern01Ch01_02_Circle implements DPattern01Ch01_02_Shape{
	@Override
	public void draw() {
		System.out.println("draw() 返回了Circle");
	}
	
}

//3.为颜色创建一个接口。
interface DPattern01Ch01_02_Color{
	void fill();
}

//4.创建实现接口的实体类。
class DPattern01Ch01_02_Red implements DPattern01Ch01_02_Color{
	@Override
	public void fill() {
		System.out.println("fill() 返回了Red");
	}
	
}

class DPattern01Ch01_02_Green implements DPattern01Ch01_02_Color{
	@Override
	public void fill() {
		System.out.println("fill() 返回了Green");
	}
	
}

class DPattern01Ch01_02_Blue implements DPattern01Ch01_02_Color{
	@Override
	public void fill() {
		System.out.println("fill() 返回了Blue");
	}
	
}

//5.为Color和Shape对象创建抽象类来获取工厂。
abstract class DPattern01Ch01_02_AbstractFactory{
	abstract DPattern01Ch01_02_Shape getShape(String shape);
	abstract DPattern01Ch01_02_Color getColor(String color);
}

//6.创建扩展了AbstractFactory的工厂类,基于给定的信息生成实体类的对象。
class DPattern01Ch01_02_ShapeFactory extends DPattern01Ch01_02_AbstractFactory{
	@Override
	DPattern01Ch01_02_Shape getShape(String shapeType) {
		if(shapeType == null){
			return null;
		}
		if("RECTANGLE".equalsIgnoreCase(shapeType)){
			return new DPattern01Ch01_02_Rectangle();
		}else if("SQUARE".equalsIgnoreCase(shapeType)){
			return new DPattern01Ch01_02_Square();
		}else if("CIRCLE".equalsIgnoreCase(shapeType)){
			return new DPattern01Ch01_02_Circle();
		}
		return null;
	}

	@Override
	DPattern01Ch01_02_Color getColor(String color) {
		return null;
	}
	
}

class DPattern01Ch01_02_ColorFactory extends DPattern01Ch01_02_AbstractFactory{
	@Override
	DPattern01Ch01_02_Shape getShape(String shape) {
		return null;
	}

	@Override
	DPattern01Ch01_02_Color getColor(String colorType) {
		if(colorType == null){
			return null;
		}
		if("RED".equalsIgnoreCase(colorType)){
			return new DPattern01Ch01_02_Red();
		}else if("GREEN".equalsIgnoreCase(colorType)){
			return new DPattern01Ch01_02_Green();
		}else if("BLUE".equalsIgnoreCase(colorType)){
			return new DPattern01Ch01_02_Blue();
		}
		return null;
	}
	
}

//7.创建一个工厂创造器/生成器类,通过传递形状或颜色信息来获取工厂。
class DPattern01Ch01_02_FactoryProducer{
	public static DPattern01Ch01_02_AbstractFactory getFactory(String choice){
		if("SHAPE".equalsIgnoreCase(choice)){
			return new DPattern01Ch01_02_ShapeFactory();
		}else if("COLOR".equalsIgnoreCase(choice)){
			return new DPattern01Ch01_02_ColorFactory();
		}
		return null;
		
	}
}







































