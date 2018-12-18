package com.main.designpattern.designpattern01.chapter03;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch03_12_Test {
    /**3.12访问者模式**/
    //在访问者模式（Visitor Pattern）中，我们使用了一个访问者类，它改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。这种类型的设计模式属于行为型模式。根据模式，元素对象已接受访问者对象，这样访问者对象就可以处理元素对象上的操作。
    //优点： 1、符合单一职责原则。 2、优秀的扩展性。 3、灵活性。
    //缺点： 1、具体元素对访问者公布细节，违反了迪米特原则。 2、具体元素变更比较困难。 3、违反了依赖倒置原则，依赖了具体类，没有依赖抽象。
    public static void main(String[] args) {
        //5.使用DisplayVisitor来显示Computor的组成部分。
        DPattern01Ch03_12_ComputerPart computer = new DPattern01Ch03_12_Computer();
        computer.accept(new DPattern01Ch03_12_DisplayVisitor());
    }
}

//1.定义一个表示元素的接口。
interface DPattern01Ch03_12_ComputerPart{
    public void accept(DPattern01Ch03_12_Visitor visitor);
}

//2.创建扩展了上述接口的实体类。
class DPattern01Ch03_12_Keyboard implements DPattern01Ch03_12_ComputerPart{
    @Override
    public void accept(DPattern01Ch03_12_Visitor visitor) {
        visitor.visitor(this);
    }
}

class DPattern01Ch03_12_Mouse implements DPattern01Ch03_12_ComputerPart{
    @Override
    public void accept(DPattern01Ch03_12_Visitor visitor) {
        visitor.visitor(this);
    }
}

class DPattern01Ch03_12_Monitor implements DPattern01Ch03_12_ComputerPart{
    @Override
    public void accept(DPattern01Ch03_12_Visitor visitor) {
        visitor.visitor(this);
    }
}

class DPattern01Ch03_12_Computer implements DPattern01Ch03_12_ComputerPart{
    DPattern01Ch03_12_ComputerPart[] parts;

    public DPattern01Ch03_12_Computer() {
        this.parts = new DPattern01Ch03_12_ComputerPart[]{new DPattern01Ch03_12_Keyboard(),new DPattern01Ch03_12_Mouse(),new DPattern01Ch03_12_Monitor()};
    }

    @Override
    public void accept(DPattern01Ch03_12_Visitor visitor) {
        for(DPattern01Ch03_12_ComputerPart p : parts){
          p.accept(visitor);
        }
        visitor.visitor(this);
    }
}

//3.定义一个表示访问者的接口。
interface DPattern01Ch03_12_Visitor{
    public void visitor(DPattern01Ch03_12_Computer computer);
    public void visitor(DPattern01Ch03_12_Keyboard keyboard);
    public void visitor(DPattern01Ch03_12_Mouse mouse);
    public void visitor(DPattern01Ch03_12_Monitor monitor);
}

//4.创建实现了上述接口的实体访问者。
class DPattern01Ch03_12_DisplayVisitor implements DPattern01Ch03_12_Visitor{
    @Override
    public void visitor(DPattern01Ch03_12_Computer computer) {
        System.out.println("Display computer");
    }

    @Override
    public void visitor(DPattern01Ch03_12_Keyboard keyboard) {
        System.out.println("Display keyboard");
    }

    @Override
    public void visitor(DPattern01Ch03_12_Mouse mouse) {
        System.out.println("Display mouse");
    }

    @Override
    public void visitor(DPattern01Ch03_12_Monitor monitor) {
        System.out.println("Display monitor");
    }
}















