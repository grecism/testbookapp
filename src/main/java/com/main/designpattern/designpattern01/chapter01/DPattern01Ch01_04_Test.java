package com.main.designpattern.designpattern01.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: DPattern01Ch01_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月25日下午5:44:46
 */
public class DPattern01Ch01_04_Test {
	/**1.4建造者模式**/
	//建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
	//一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
	//优点： 1、建造者独立，易扩展。 2、便于控制细节风险。
	//缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。 
	public static void main(String[] args) {
		//7.使用MealBuider来演示建造者模式。
		DPattern01Ch01_04_MealBuilder mealBuilder = new DPattern01Ch01_04_MealBuilder();
		DPattern01Ch01_04_Meal prepareVegMeal = mealBuilder.prepareVegMeal();
		prepareVegMeal.showItems();
		System.out.println(prepareVegMeal.getCost());
		DPattern01Ch01_04_Meal prepareNonVegMeal = mealBuilder.prepareNonVegMeal();
		prepareNonVegMeal.showItems();
		System.out.println(prepareNonVegMeal.getCost());
	}
}

//1.创建一个表示食物条目和食物包装的接口。
interface DPattern01Ch01_04_Item{
	public String name();
	public float price();
	public DPattern01Ch01_04_Packing packing();
}

interface DPattern01Ch01_04_Packing{
	public String pack();
}

//2.创建实现Packing接口的实体类。
class DPattern01Ch01_04_Wrapper implements DPattern01Ch01_04_Packing{
	@Override
	public String pack() {
		return "Wrapper";
	}

}

class DPattern01Ch01_04_Bottle implements DPattern01Ch01_04_Packing{
	@Override
	public String pack() {
		return "Bottle";
	}
	
}

//3.创建实现Item接口的抽象类,该类提供了默认的功能。
abstract class DPattern01Ch01_04_Burger implements DPattern01Ch01_04_Item{
	@Override
	public DPattern01Ch01_04_Packing packing() {
		return new DPattern01Ch01_04_Wrapper();
	}
	
	@Override
	public abstract float price();
}

abstract class DPattern01Ch01_04_ColdDrink implements DPattern01Ch01_04_Item{
	@Override
	public DPattern01Ch01_04_Packing packing() {
		return new DPattern01Ch01_04_Bottle();
	}
	
	@Override
	public abstract float price();
}

//4.创建扩展了Burger和ColdDrink的实体类。
class DPattern01Ch01_04_VerBurger extends DPattern01Ch01_04_Burger{
	@Override
	public String name() {
		return "VerBurger";
	}

	@Override
	public float price() {
		return 25.0f;
	}
	
}

class DPattern01Ch01_04_ChickenBurger extends DPattern01Ch01_04_Burger{

	@Override
	public String name() {
		return "ChickenBurger";
	}

	@Override
	public float price() {
		return 50.5f;
	}
	
}

class DPattern01Ch01_04_Coke extends DPattern01Ch01_04_ColdDrink{
	@Override
	public String name() {
		return "Coke";
	}

	@Override
	public float price() {
		return 30.0f;
	}
	
}

class DPattern01Ch01_04_Pepsi extends DPattern01Ch01_04_ColdDrink{
	@Override
	public String name() {
		return "Pepsi";
	}

	@Override
	public float price() {
		return 35.0f;
	}
	
}

//5.创建一个Meal类,带有上面定义的Item对象。
class DPattern01Ch01_04_Meal{
	private List<DPattern01Ch01_04_Item> items = new ArrayList<DPattern01Ch01_04_Item>();
	public void addItem(DPattern01Ch01_04_Item item){
		items.add(item);
	}
	
	public float getCost(){
		float cost = 0.0f;
		for (DPattern01Ch01_04_Item dPattern01Ch01_04_Item : items) {
			cost+=dPattern01Ch01_04_Item.price();
		}
		return cost;
	}
	
	public void showItems(){
		for (DPattern01Ch01_04_Item dPattern01Ch01_04_Item : items) {
			System.out.println("Item name:"+dPattern01Ch01_04_Item.name());
			System.out.println("Item packing:"+dPattern01Ch01_04_Item.packing().pack());
			System.out.println("Item price:"+dPattern01Ch01_04_Item.price());
		}
	}
}

//6.创建一个MealBuilder类,实际的builder类负责创建Meal对象。
class DPattern01Ch01_04_MealBuilder{
	public DPattern01Ch01_04_Meal prepareVegMeal(){
		DPattern01Ch01_04_Meal meal = new DPattern01Ch01_04_Meal();
		meal.addItem(new DPattern01Ch01_04_VerBurger());
		meal.addItem(new DPattern01Ch01_04_Coke());
		return meal;
	}
	
	public DPattern01Ch01_04_Meal prepareNonVegMeal(){
		DPattern01Ch01_04_Meal meal = new DPattern01Ch01_04_Meal();
		meal.addItem(new DPattern01Ch01_04_ChickenBurger());
		meal.addItem(new DPattern01Ch01_04_Pepsi());
		return meal;
		
	}
}





















