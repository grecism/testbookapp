package com.main.designpattern.designpattern01.chapter03;

import java.util.Date;

/**
 * @author admin
 * @version 2018/12/11
 * @since 2018/12/11
 */
public class DPattern01Ch03_05_Test {
    /**3.5中介者模式**/
    //中介者模式（Mediator Pattern）是用来降低多个对象和类之间的通信复杂性。这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。中介者模式属于行为型模式。
    //优点： 1、降低了类的复杂度，将一对多转化成了一对一。 2、各个类之间的解耦。 3、符合迪米特原则。
    //缺点：中介者会庞大，变得复杂难以维护。
    public static void main(String[] args) {
        //3.使用User对象来显示他们之间的通信。
        DPattern01Ch03_05_User robert = new DPattern01Ch03_05_User("Robert");
        DPattern01Ch03_05_User john = new DPattern01Ch03_05_User("John");
        robert.sendMessage("Hi!John!");
        john.sendMessage("Hello!Robert!");
    }
}

//1.创建中介类。
class DPattern01Ch03_05_ChatRoom{
    public static void showMessage(DPattern01Ch03_05_User user,String message){
        System.out.println(new Date().toString()+"["+user.getName()+"]:"+message);
    }
}

//2.创建User类。
class DPattern01Ch03_05_User{
    private String name;
    public DPattern01Ch03_05_User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void sendMessage(String message){
        DPattern01Ch03_05_ChatRoom.showMessage(this,message);
    }
}