package com.main.designpattern.designpattern01.chapter02;

/**
 * 
 *<p>Title	: DPattern01Ch02_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午2:19:50
 */
public class DPattern01Ch02_02_Test {
	/**2.2桥接模式**/
	//桥接（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
	//这种模式涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
	//我们通过下面的实例来演示桥接模式（Bridge Pattern）的用法。其中，可以使用相同的抽象类方法但是不同的桥接实现类，来画出不同颜色的圆。
	//优点： 1、抽象和实现的分离。 2、优秀的扩展能力。 3、实现细节对客户透明。
	//缺点：桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。
	//实现:
	//我们有一个作为桥接实现的 DrawAPI 接口和实现了 DrawAPI 接口的实体类 RedCircle、GreenCircle。Shape 是一个抽象类，将使用 DrawAPI 的对象。BridgePatternDemo，我们的演示类使用 Shape 类来画出不同颜色的圆。
	public static void main(String[] args) {
		//5.使用Shape和DrawAPI类画出不同颜色的矩形。
		DPattern01Ch02_02_Rectangle rectangle = new DPattern01Ch02_02_Rectangle(200,100,new DPattern01Ch02_02_RedRectangle());
		rectangle.draw();
		DPattern01Ch02_02_Shape rectangle2 = new DPattern01Ch02_02_Rectangle(300,100,new DPattern01Ch02_02_GreenRectangle());
		rectangle2.draw();
		DPattern01Ch02_02_Circle circle = new DPattern01Ch02_02_Circle(100,100,100,new DPattern01Ch02_02_RedCircle());
		circle.draw();
		DPattern01Ch02_02_Shape circle2 = new DPattern01Ch02_02_Circle(200,200,200,new DPattern01Ch02_02_GreenCircle());
		circle2.draw();
	}
}

//1.创建桥接实现接口。
interface DPattern01Ch02_02_DrawAPI{
	public void drawRectangle(int x,int y);
	public void drawCircle(int radius,int x,int y);
	
}

//2.创建实现了DrawAPI接口的实体桥接实现类。
class DPattern01Ch02_02_RedRectangle implements DPattern01Ch02_02_DrawAPI{
	@Override
	public void drawRectangle(int x, int y) {
		System.out.println("drawing Rectangle[color:RedRectangle,x="+x+",y="+y+"]");
	}

	@Override
	public void drawCircle(int radius, int x, int y) {
	}
	
}

class DPattern01Ch02_02_GreenRectangle implements DPattern01Ch02_02_DrawAPI{
	@Override
	public void drawRectangle(int x, int y) {
		System.out.println("drawing Rectangle[color:GreenRectangle,x="+x+",y="+y+"]");
	}

	@Override
	public void drawCircle(int radius, int x, int y) {
	}
	
}

class DPattern01Ch02_02_RedCircle implements DPattern01Ch02_02_DrawAPI{
	@Override
	public void drawRectangle(int x, int y) {
	}

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("drawing Circle[color:RedCircle,radius="+radius+",x="+x+",y="+y+"]");
	}
	
}

class DPattern01Ch02_02_GreenCircle implements DPattern01Ch02_02_DrawAPI{
	@Override
	public void drawRectangle(int x, int y) {
	}

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("drawing Circle[color:GreenCircle,radius="+radius+",x="+x+",y="+y+"]");
	}
	
}

//3.使用DrawAPI接口创建抽象类Shape
abstract class DPattern01Ch02_02_Shape{
	protected  DPattern01Ch02_02_DrawAPI drawAPI;
	protected DPattern01Ch02_02_Shape(DPattern01Ch02_02_DrawAPI drawAPI) {
		this.drawAPI = drawAPI;
	}
	public abstract void draw();
	
} 

//4.创建实现了Shape接口的实体类。
class DPattern01Ch02_02_Rectangle extends DPattern01Ch02_02_Shape{
	private int x,y;
	protected DPattern01Ch02_02_Rectangle(int x,int y,DPattern01Ch02_02_DrawAPI drawAPI) {
		super(drawAPI);
		this.x = x;
		this.y = y;
	}
	@Override
	public void draw() {
		drawAPI.drawRectangle(x, y);
	}
	
}

class DPattern01Ch02_02_Circle extends DPattern01Ch02_02_Shape{
	private int radius,x,y;
	protected DPattern01Ch02_02_Circle(int radius,int x,int y,DPattern01Ch02_02_DrawAPI drawAPI) {
		super(drawAPI);
		this.radius = radius;
		this.x = x;
		this.y = y;
		
	}
	@Override
	public void draw() {
		drawAPI.drawCircle(radius, x, y);
	}
	
}








	