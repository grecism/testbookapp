package com.main.designpattern.designpattern01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: DPattern01Ch03_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月27日下午1:43:26
 */
public class DPattern01Ch03_02_Test {
	/**3.2命令模式**/
	//命令模式（Command Pattern）是一种数据驱动的设计模式，它属于行为型模式。请求以命令的形式包裹在对象中，并传给调用对象。调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。 
	//优点： 1、降低了系统耦合度。 2、新的命令可以很容易添加到系统中去。
	//缺点：使用命令模式可能会导致某些系统有过多的具体命令类。
	//实现:
	//我们首先创建作为命令的接口 Order，然后创建作为请求的 Stock 类。实体命令类 BuyStock 和 SellStock，实现了 Order 接口，将执行实际的命令处理。创建作为调用对象的类 Broker，它接受订单并能下订单。
	//Broker 对象使用命令模式，基于命令的类型确定哪个对象执行哪个命令。CommandPatternDemo，我们的演示类使用 Broker 类来演示命令模式。
	public static void main(String[] args) {
		//5.使用Broker类来接收并执行命令。
		DPattern01Ch03_02_Stock stock = new DPattern01Ch03_02_Stock();
		DPattern01Ch03_02_BuyStock buyStock = new DPattern01Ch03_02_BuyStock(stock);
		DPattern01Ch03_02_SellStock sellStock = new DPattern01Ch03_02_SellStock(stock);
		DPattern01Ch03_02_Broker broker = new DPattern01Ch03_02_Broker();
		broker.takeOrder(buyStock);
		broker.takeOrder(sellStock);
		broker.placeOrders();
		
	}
}

//1.创建一个命令接口。
interface DPattern01Ch03_02_Order{
	public void execute();
}

//2.创建一个请求类。
class DPattern01Ch03_02_Stock{
	private String name="ABC";
	private int quantity = 10;
	public void buy(){
		System.out.println("Stock[Name:"+name+"		Quantity:"+quantity+"]	bought");
	}
	
	public void sell(){
		System.out.println("Stock[Name:"+name+"		Quantity:"+quantity+"]  sold" );
	}
}

//3.创建实现了Order接口的实体类。
class DPattern01Ch03_02_BuyStock implements DPattern01Ch03_02_Order{
	private DPattern01Ch03_02_Stock stock;
	public DPattern01Ch03_02_BuyStock(DPattern01Ch03_02_Stock stock) {
		this.stock = stock;
	}
	@Override
	public void execute() {
		stock.buy();
	}
	
}
 
class DPattern01Ch03_02_SellStock implements DPattern01Ch03_02_Order{
	private DPattern01Ch03_02_Stock stock;
	public DPattern01Ch03_02_SellStock(DPattern01Ch03_02_Stock stock) {
		this.stock = stock;
	}
	@Override
	public void execute() {
		stock.sell();
	}
	
}

//4.创建命令调用类。
class DPattern01Ch03_02_Broker{
	private List<DPattern01Ch03_02_Order> list = new ArrayList<DPattern01Ch03_02_Order>();
	public void takeOrder(DPattern01Ch03_02_Order order){
		list.add(order);
	}
	public void placeOrders(){
		for (DPattern01Ch03_02_Order dPattern01Ch03_02_Order : list) {
			dPattern01Ch03_02_Order.execute();
		}
		list.clear();
	}
}
































