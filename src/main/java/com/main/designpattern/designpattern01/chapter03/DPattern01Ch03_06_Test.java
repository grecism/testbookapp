package com.main.designpattern.designpattern01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 2018/12/11
 * @since 2018/12/11
 */
public class DPattern01Ch03_06_Test {
    /**3.6备忘录模式**/
    //备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。备忘录模式属于行为型模式。
    //优点： 1、给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态。 2、实现了信息的封装，使得用户不需要关心状态的保存细节。
    //缺点：消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。
    public static void main(String[] args) {
        //4.使用CareTaker和Originator对象。
        DPattern01Ch03_06_CareTaker careTaker = new DPattern01Ch03_06_CareTaker();
        DPattern01Ch03_06_Originator originator = new DPattern01Ch03_06_Originator();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State:"+originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State:"+originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State:"+originator.getState());
    }
}

//1.创建Memento类。
class DPattern01Ch03_06_Memento{
    private String state;
    public DPattern01Ch03_06_Memento(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
}

//2.创建Originator类。
class DPattern01Ch03_06_Originator{
    private String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public DPattern01Ch03_06_Memento saveStateToMemento(){
        return new DPattern01Ch03_06_Memento(state);
    }
    public void getStateFromMemento(DPattern01Ch03_06_Memento memento){
        state = memento.getState();
    }
}

//3.创建CareTaker类。
class DPattern01Ch03_06_CareTaker{
    private List<DPattern01Ch03_06_Memento> mementoList = new ArrayList<>();
    public void add(DPattern01Ch03_06_Memento memento){
        mementoList.add(memento);
    }
    public DPattern01Ch03_06_Memento get(int index){
        return mementoList.get(index);
    }
}