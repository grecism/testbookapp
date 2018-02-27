package com.main.designpattern.designpattern01.chapter01;

import java.util.Hashtable;

/**
 * 
 *<p>Title	: DPattern01Ch01_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日上午9:38:16
 */
public class DPattern01Ch01_05_Test {
	/**1.5原型模式**/
	//原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
	//这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。例如，一个对象需要在一个高代价的数据库操作之后
	//被创建。我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。
	//优点： 1、性能提高。 2、逃避构造函数的约束。
	//缺点： 1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候。 2、必须实现 Cloneable 接口。 3、逃避构造函数的约束。 
	public static void main(String[] args) throws CloneNotSupportedException {
		//4.使用ShapeCache类来获取存储在Hashtable中的形状的克隆。
		DPattern01Ch01_05_ShapeCache.loadCache();
		DPattern01Ch01_05_Shape shapeClone1 = DPattern01Ch01_05_ShapeCache.getShapeClone("1");
		System.out.println("shapeClone1:"+shapeClone1.getType());
		DPattern01Ch01_05_Shape shapeClone2 = DPattern01Ch01_05_ShapeCache.getShapeClone("2");
		System.out.println("shapeClone2:"+shapeClone2.getType());
		DPattern01Ch01_05_Shape shapeClone3 = DPattern01Ch01_05_ShapeCache.getShapeClone("3");
		System.out.println("shapeClone3:"+shapeClone3.getType());
		
		DPattern01Ch01_05_Shape shapeOriginal1 = DPattern01Ch01_05_ShapeCache.getShapeOriginal("1");
		System.out.println("shapeOriginal1:"+shapeOriginal1.getType());
		DPattern01Ch01_05_Shape shapeOriginal2 = DPattern01Ch01_05_ShapeCache.getShapeOriginal("2");
		System.out.println("shapeOriginal2:"+shapeOriginal2.getType());
		DPattern01Ch01_05_Shape shapeOriginal3 = DPattern01Ch01_05_ShapeCache.getShapeOriginal("3");
		System.out.println("shapeOriginal3:"+shapeOriginal3.getType());
	}
}

//1.创建一个实现了Clonable接口的抽象类。
abstract class DPattern01Ch01_05_Shape implements Cloneable{
	private String id;
	protected String type;
	public abstract void draw();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

//2.创建扩展了上面抽象类的实体类。
class DPattern01Ch01_05_Rectangle extends DPattern01Ch01_05_Shape{
	public DPattern01Ch01_05_Rectangle() {
		type = "Rectangle";
	}
	@Override
	public void draw() {
		System.out.println("draw() 返回了Rectangle");
	}
	
}

class DPattern01Ch01_05_Square extends DPattern01Ch01_05_Shape{
	public DPattern01Ch01_05_Square() {
		type="Square";
	}
	@Override
	public void draw() {
		System.out.println("draw() 返回了Square");
	}
	
}

class DPattern01Ch01_05_Circle extends DPattern01Ch01_05_Shape{
	public DPattern01Ch01_05_Circle() {
		type="Circle";
	}
	@Override
	public void draw() {
		System.out.println("draw() 返回了Circle");
	}
	
}

//3.创建一个类,从数据库获取实体类,并把它们存储在一个Hashtable中。
class DPattern01Ch01_05_ShapeCache{
	private static Hashtable<String,DPattern01Ch01_05_Shape> shapeMap = new Hashtable<String,DPattern01Ch01_05_Shape>();
	
	//对每种形状都运行数据库查询，并创建该形状shapeMap.put(shapeKey, shape);
	public static void loadCache(){
		DPattern01Ch01_05_Rectangle Rectangle = new DPattern01Ch01_05_Rectangle();
		Rectangle.setId("1");
		shapeMap.put(Rectangle.getId(), Rectangle);
		DPattern01Ch01_05_Square Square = new DPattern01Ch01_05_Square();
		Square.setId("2");
		shapeMap.put("2", Square);
		DPattern01Ch01_05_Circle Circle = new DPattern01Ch01_05_Circle();
		Circle.setId("3");
		shapeMap.put(Circle.getId(), Circle);
		
	}
	
	public static DPattern01Ch01_05_Shape getShapeClone(String shapeId) throws CloneNotSupportedException {
		DPattern01Ch01_05_Shape dPattern01Ch01_05_Shape = shapeMap.get(shapeId);
		DPattern01Ch01_05_Shape clone = (DPattern01Ch01_05_Shape) dPattern01Ch01_05_Shape.clone();
		if("1".equals(shapeId)){
			clone.setType("RectangleClone");
		}else if("2".equals(shapeId)){
			clone.setType("SquareClone");
		}else if("3".equals(shapeId)){
			clone.setType("CircleClone");
		}
		return clone;
	}
	
	public static DPattern01Ch01_05_Shape getShapeOriginal(String shapeId){
		DPattern01Ch01_05_Shape dPattern01Ch01_05_Shape = shapeMap.get(shapeId);
		return dPattern01Ch01_05_Shape;
		
	}
}












