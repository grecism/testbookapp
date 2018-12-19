package com.main.designpattern.designpattern01.chapter03;

/**
 * @author admin
 * @version 2018/12/11
 * @since 2018/12/11
 */
public class DPattern01Ch03_09_Test {
    /**3.9空对象模式**/
    //在空对象模式（Null Object Pattern）中，一个空对象取代 NULL 对象实例的检查。Null 对象不是检查空值，而是反应一个不做任何动作的关系。这样的 Null 对象也可以在数据不可用的时候提供默认的行为。
    //在空对象模式中，我们创建一个指定各种要执行的操作的抽象类和扩展该类的实体类，还创建一个未对该类做任何实现的空对象类，该空对象类将无缝地使用在需要检查空值的地方。
    public static void main(String[] args) {
        //4.使用CustomerFactory，基于客户传递的名字，来获取RealCustomer或NullCustomer。
        DPattern01Ch03_09_AbstractCustomer customer = DPattern01Ch03_09_CustomerFactory.getCustomer("Rob");
        DPattern01Ch03_09_AbstractCustomer customer2 = DPattern01Ch03_09_CustomerFactory.getCustomer("aaa");
        DPattern01Ch03_09_AbstractCustomer customer3 = DPattern01Ch03_09_CustomerFactory.getCustomer("Joe");
        DPattern01Ch03_09_AbstractCustomer customer4 = DPattern01Ch03_09_CustomerFactory.getCustomer("bbb");
        System.out.println("Customers");
        System.out.println(customer.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}

//1.创建一个抽象类。
abstract class DPattern01Ch03_09_AbstractCustomer{
    protected String name;
    public abstract String getName();
    public abstract boolean isNil();
}

//2.创建扩展了上述类的实体类。
class DPattern01Ch03_09_RealCustomer extends  DPattern01Ch03_09_AbstractCustomer{
    public DPattern01Ch03_09_RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}

class DPattern01Ch03_09_NullCustomer extends DPattern01Ch03_09_AbstractCustomer{
    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}

//3.创建CustomerFactory类。
class DPattern01Ch03_09_CustomerFactory{
    public static final String[] names = {"Rob","Joe","Julie"};

    public static DPattern01Ch03_09_AbstractCustomer getCustomer(String name){
        for(String n: names){
            if(name.equalsIgnoreCase(n)){
                return new DPattern01Ch03_09_RealCustomer(name);
            }
        }
        return new DPattern01Ch03_09_NullCustomer();
    }
}

























