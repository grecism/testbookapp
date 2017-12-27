package com.main.designpattern.designpattern01.chapter02;

import java.util.HashMap;

/**
 * 
 *<p>Title	: DPattern01Ch02_08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月27日上午10:34:08
 */
public class DPattern01Ch02_08_Test {
	/**2.7享元模式**/
	//享元模式（Flyweight Pattern）主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
	//享元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。我们将通过创建 5 个对象来画出 20 个分布于不同位置的圆来演示这种模式。由于只有 5 种可用的
	//颜色，所以 color 属性被用来检查现有的 Circle 对象。
	//优点：大大减少对象的创建，降低系统的内存，使效率提高。
	//缺点：提高了系统的复杂度，需要分离出外部状态和内部状态，而且外部状态具有固有化的性质，不应该随着内部状态的变化而变化，否则会造成系统的混乱。
	//实现:
	//我们将创建一个 Shape 接口和实现了 Shape 接口的实体类 Circle。下一步是定义工厂类 ShapeFactory。
	//ShapeFactory 有一个 Circle 的 HashMap，其中键名为 Circle 对象的颜色。无论何时接收到请求，都会创建一个特定颜色的圆。ShapeFactory 检查它的 HashMap 中的 circle 对象，如果找到 Circle 对象，则返回该对象，否则将创建一个存储在 hashmap 中以备后续使用的新对象，并把该对象返回到客户端。
	//FlyWeightPatternDemo，我们的演示类使用 ShapeFactory 来获取 Shape 对象。它将向 ShapeFactory 传递信息（red / green / blue/ black / white），以便获取它所需对象的颜色。
	private static final String colors[] ={"Red","Green","Blue","White","Black"};
	public static void main(String[] args) {
		//4.使用该工厂,通过传递颜色信息来获取实体类的对象。
		for (int i = 0; i < 20; i++) {
			DPattern01Ch02_08_Circle circle = (DPattern01Ch02_08_Circle) DPattern01Ch02_08_ShapeFactory.getCircle(getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.setRadius(100);
			circle.draw();
		}
		
	}
	
	private static String getRandomColor(){
		return colors[(int)(Math.random()*colors.length)];
	}
	private static int getRandomX(){
		return (int)(Math.random()*100);
	}
	private static int getRandomY(){
		return (int)(Math.random()*100);
	}
}

//1.创建一个接口。
interface DPattern01Ch02_08_Shape{
	public void draw();
}

//2.创建实现接口的实体类。
class DPattern01Ch02_08_Circle implements DPattern01Ch02_08_Shape{
	private String color;
	private int x;
	private int y;
	private int radius;

	public DPattern01Ch02_08_Circle(String color) {
		this.color = color;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void draw() {
		System.out.println("draw() Circle[Color:"+color+"	x="+x+"	y="+y+" radius="+radius+"]");
	}
	
}

//3.创建一个工厂,生成基于给定信息的实体类的对象。
class DPattern01Ch02_08_ShapeFactory{
	private static final HashMap<String,DPattern01Ch02_08_Shape> circleMap = new HashMap<>();
	
	public static DPattern01Ch02_08_Shape getCircle(String color){
		DPattern01Ch02_08_Circle circle = (DPattern01Ch02_08_Circle) circleMap.get(color);
		
		if(circle == null){
			circle = new DPattern01Ch02_08_Circle(color);
			circleMap.put(color, circle);
			System.out.println("Creating circle of color:"+color);
		}
		return circle;
		
	}
}















