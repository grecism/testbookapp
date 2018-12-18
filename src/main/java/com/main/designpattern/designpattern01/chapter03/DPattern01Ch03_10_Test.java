package com.main.designpattern.designpattern01.chapter03;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch03_10_Test {
    /**3.10策略模式**/
    //在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
    //在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。策略对象改变 context 对象的执行算法。
    //优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
    //缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。
    public static void main(String[] args) {
        //4.使用Context来查看当它改变策略Strategy时的行为变化。
        DPattern01Ch03_10_Context context = new DPattern01Ch03_10_Context(new DPattern01Ch03_10_OperationAdd());
        System.out.println("10 + 5 ="+context.executeStrategy(10,5));
        DPattern01Ch03_10_Context context2 = new DPattern01Ch03_10_Context(new DPattern01Ch03_10_OperationSubstract());
        System.out.println("10 - 5 ="+context2.executeStrategy(10,5));
        DPattern01Ch03_10_Context context3 = new DPattern01Ch03_10_Context(new DPattern01Ch03_10_OperationMultiply());
        System.out.println("10 * 5 ="+context3.executeStrategy(10,5));
    }
}

//1.创建一个接口。
interface DPattern01Ch03_10_Strategy{
    public int doOperation(int num1,int num2);
}

//2.创建实现接口的实体类。
class DPattern01Ch03_10_OperationAdd implements DPattern01Ch03_10_Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class DPattern01Ch03_10_OperationSubstract implements DPattern01Ch03_10_Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class DPattern01Ch03_10_OperationMultiply implements DPattern01Ch03_10_Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

//3.创建Context类。
class DPattern01Ch03_10_Context{
    private  DPattern01Ch03_10_Strategy strategy;

    public DPattern01Ch03_10_Context(DPattern01Ch03_10_Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1,int num2){
        return strategy.doOperation(num1,num2);
    }
}





























