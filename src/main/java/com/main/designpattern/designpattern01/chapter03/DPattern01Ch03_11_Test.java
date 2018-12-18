package com.main.designpattern.designpattern01.chapter03;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch03_11_Test {
    /**3.11模板模式**/
    //在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
    //优点： 1、封装不变部分，扩展可变部分。 2、提取公共代码，便于维护。 3、行为由父类控制，子类实现。
    //缺点：每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大。
    public static void main(String[] args) {
        //3.使用Game的模板方法play()来演示游戏的定义方式。
        DPattern01Ch03_11_Game game = new  DPattern01Ch03_11_Cricket();
        game.play();
        game = new  DPattern01Ch03_11_Football();
        game.play();
    }
}

//1.创建一个抽象类，它的模板方法被设置为final。
abstract class DPattern01Ch03_11_Game{
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
}

//2.创建扩展了上述类的实体类。
class DPattern01Ch03_11_Cricket extends DPattern01Ch03_11_Game{
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized. Start Playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game.");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished.");
    }
}

class DPattern01Ch03_11_Football extends DPattern01Ch03_11_Game{
    @Override
    void initialize() {
        System.out.println("Football Game Initialized. Start Playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game.");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished.");
    }
}























