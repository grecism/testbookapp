package com.main.designpattern.designpattern01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lulu.zou
 * @version 2018/12/11
 * @since 2018/12/11
 */
public class DPattern01Ch03_07_Test {
    /**3.7观察者模式**/
    //当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
    //优点： 1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。
    //缺点： 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
    public static void main(String[] args) {
        //4.使用Subject和实体观察者对象。
        DPattern01Ch03_07_Subject subject = new DPattern01Ch03_07_Subject();
        new DPattern01Ch03_07_BinaryObserver(subject);
        new DPattern01Ch03_07_OctalObserver(subject);
        new DPattern01Ch03_07_HexObserver(subject);

        System.out.println("First State Change:15");
        subject.setState(15);
        System.out.println("Second State Change:10");
        subject.setState(10);
    }
}

//1.创建Subject类。
class DPattern01Ch03_07_Subject{
    private List<DPattern01Ch03_07_Observer> observers = new ArrayList<>();
    private int state;
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
        notifyAllObservers(observers);
    }
    public void attach(DPattern01Ch03_07_Observer observer){
        observers.add(observer);
    }
    public void notifyAllObservers(List<DPattern01Ch03_07_Observer> observers){
        for(DPattern01Ch03_07_Observer observer : observers){
            observer.update();
        }
    }
}

//2.创建Observer类。
abstract class DPattern01Ch03_07_Observer{
    protected DPattern01Ch03_07_Subject subject;
    public abstract void update();
}

//3.创建实体观察者类。
class DPattern01Ch03_07_BinaryObserver extends DPattern01Ch03_07_Observer{
    public DPattern01Ch03_07_BinaryObserver(DPattern01Ch03_07_Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toBinaryString(subject.getState()));
    }
}

class DPattern01Ch03_07_OctalObserver extends DPattern01Ch03_07_Observer{
    public DPattern01Ch03_07_OctalObserver(DPattern01Ch03_07_Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Octal String:"+Integer.toOctalString(subject.getState()));
    }
}

class DPattern01Ch03_07_HexObserver extends DPattern01Ch03_07_Observer{
    public DPattern01Ch03_07_HexObserver(DPattern01Ch03_07_Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Hex String:"+Integer.toHexString(subject.getState()).toUpperCase());
    }
}