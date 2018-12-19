package com.main.designpattern.designpattern01.chapter04;

/**
 * @author admin
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_05_Test {
    /**4.5前端控制器模式**/
    //前端控制器模式（Front Controller Pattern）是用来提供一个集中的请求处理机制，所有的请求都将由一个单一的处理程序处理。该处理程序可以做认证/授权/记录日志，或者跟踪请求，然后把请求传给相应的处理程序。以下是这种设计模式的实体。
    //前端控制器（Front Controller） - 处理应用程序所有类型请求的单个处理程序，应用程序可以是基于 web 的应用程序，也可以是基于桌面的应用程序。
    //调度器（Dispatcher） - 前端控制器可能使用一个调度器对象来调度请求到相应的具体处理程序。
    //视图（View） - 视图是为请求而创建的对象。
    public static void main(String[] args) {
        //4.使用FrontController来演示前端控制器设计模式。
        DPattern01Ch04_05_FrontController frontController = new DPattern01Ch04_05_FrontController();
        frontController.dispatcherRequest("student");
        frontController.dispatcherRequest("home");
    }
}

//1.创建视图。
class DPattern01Ch04_05_HomeView{
    public void show(){
        System.out.println("Display Home Page.");
    }
}

class DPattern01Ch04_05_StudentView{
    public void show(){
        System.out.println("Display Student Page.");
    }
}

//2.创建调度器Dispatcher。
class DPattern01Ch04_05_Dispatcher{
    private DPattern01Ch04_05_HomeView homeView;
    private DPattern01Ch04_05_StudentView studentView;
    public DPattern01Ch04_05_Dispatcher() {
        this.homeView = new  DPattern01Ch04_05_HomeView();
        this.studentView = new DPattern01Ch04_05_StudentView();
    }
    public void dispatch(String request){
        if(request.equalsIgnoreCase("student")){
            studentView.show();
        }else{
            homeView.show();
        }
    }
}

//3.创建前端控制器。
class DPattern01Ch04_05_FrontController{
    private DPattern01Ch04_05_Dispatcher dispatcher;
    public DPattern01Ch04_05_FrontController() {
        this.dispatcher = new DPattern01Ch04_05_Dispatcher();
    }
    public void dispatcherRequest(String request){
        trackRequest(request);
        if(isAuthenticUser()){
            dispatcher.dispatch(request);
        }
    }
    private void trackRequest(String request) {
        System.out.println("Paged Request:"+request);
    }
    private boolean isAuthenticUser(){
        System.out.println("User is authenticated successfully.");
        return true;
    }
}


















