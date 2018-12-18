package com.main.designpattern.designpattern01.chapter03;

/**
 * @author lulu.zou
 * @version 2018/12/11
 * @since 2018/12/11
 */
public class DPattern01Ch03_08_Test {
    /**3.8状态模式**/
    //在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象。
    //优点： 1、封装了转换规则。 2、枚举可能的状态，在枚举状态之前需要确定状态种类。 3、将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。 4、允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。 5、可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。
    //缺点： 1、状态模式的使用必然会增加系统类和对象的个数。 2、状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。 3、状态模式对"开闭原则"的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，而且修改某个状态类的行为也需修改对应类的源代码。
    public static void main(String[] args) {
        //4.使用Context来查看当状态State改变时的行为变化。
        DPattern01Ch03_08_Context context = new DPattern01Ch03_08_Context();
        DPattern01Ch03_08_StartState startState = new DPattern01Ch03_08_StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());
        DPattern01Ch03_08_StopState stopState = new DPattern01Ch03_08_StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}

//1.创建一个接口。
interface DPattern01Ch03_08_State{
    public void doAction(DPattern01Ch03_08_Context context);
}

//2.创建实现接口的实体类。
class DPattern01Ch03_08_StartState implements DPattern01Ch03_08_State{
    @Override
    public void doAction(DPattern01Ch03_08_Context context) {
        System.out.println("doAction in start state");
        context.setState(this);
    }
    @Override
    public String toString() {
        return "start state";
    }
}

class DPattern01Ch03_08_StopState implements DPattern01Ch03_08_State{
    @Override
    public void doAction(DPattern01Ch03_08_Context context) {
        System.out.println("doAction in stop state");
        context.setState(this);
    }
    @Override
    public String toString() {
        return "stop state";
    }
}

//3.创建Context类。
class DPattern01Ch03_08_Context{
    private DPattern01Ch03_08_State state;
    public DPattern01Ch03_08_Context() {
        state = null;
    }
    public DPattern01Ch03_08_State getState() {
        return state;
    }
    public void setState(DPattern01Ch03_08_State state) {
        this.state = state;
    }
}
