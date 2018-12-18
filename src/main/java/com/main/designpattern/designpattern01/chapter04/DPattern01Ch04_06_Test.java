package com.main.designpattern.designpattern01.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_06_Test {
    /**4.6拦截过滤器模式**/
    //拦截过滤器模式（Intercepting Filter Pattern）用于对应用程序的请求或响应做一些预处理/后处理。定义过滤器，并在把请求传给实际目标应用程序之前应用在请求上。过滤器可以做认证/授权/记录日志，或者跟踪请求，然后把请求传给相应的处理程序。以下是这种设计模式的实体。
    //过滤器（Filter） - 过滤器在请求处理程序执行请求之前或之后，执行某些任务。
    //过滤器链（Filter Chain） - 过滤器链带有多个过滤器，并在 Target 上按照定义的顺序执行这些过滤器。
    //Target - Target 对象是请求处理程序。
    //过滤管理器（Filter Manager） - 过滤管理器管理过滤器和过滤器链。
    //客户端（Client） - Client 是向 Target 对象发送请求的对象。
    public static void main(String[] args) {
        //7.使用Client来演示拦截过滤器设计模式。
        DPattern01Ch04_06_FilterManager filterManager = new DPattern01Ch04_06_FilterManager(new DPattern01Ch04_06_Target());
        filterManager.setFilter(new DPattern01Ch04_06_AuthenticationFilter());
        filterManager.setFilter(new DPattern01Ch04_06_DebugFilter());
        DPattern01Ch04_06_Client client = new DPattern01Ch04_06_Client(filterManager);
        client.sendRequest("Home");
    }
}

//1.创建过滤器接口。
interface DPattern01Ch04_06_Filter{
    public void execute(String request);
}

//2.创建实体过滤器。
class DPattern01Ch04_06_AuthenticationFilter implements DPattern01Ch04_06_Filter{
    @Override
    public void execute(String request) {
        System.out.println("Authentication Request:"+request);
    }
}

class DPattern01Ch04_06_DebugFilter implements DPattern01Ch04_06_Filter{
    @Override
    public void execute(String request) {
        System.out.println("Debug Request:"+request);
    }
}

//3.创建Target。
class DPattern01Ch04_06_Target{
    public void execute(String request) {
        System.out.println("Target Request:"+request);
    }
}

//4.创建过滤器链。
class DPattern01Ch04_06_FilterChian{
    private List<DPattern01Ch04_06_Filter> filters = new ArrayList<>();
    private DPattern01Ch04_06_Target target;
    public void addFilter(DPattern01Ch04_06_Filter filter){
        filters.add(filter);
    }
    public void setTarget(DPattern01Ch04_06_Target target) {
        this.target = target;
    }
    public void execute(String request){
        for(DPattern01Ch04_06_Filter filter : filters){
            filter.execute(request);
        }
        target.execute(request);
    }
}

//5.创建过滤管理器。
class DPattern01Ch04_06_FilterManager{
    DPattern01Ch04_06_FilterChian filterChian;
    public DPattern01Ch04_06_FilterManager(DPattern01Ch04_06_Target target) {
        this.filterChian = new DPattern01Ch04_06_FilterChian();
        filterChian.setTarget(target);
    }
    public void setFilter(DPattern01Ch04_06_Filter filter){
        filterChian.addFilter(filter);
    }
    public void filterRequest(String request){
        filterChian.execute(request);
    }
}

//6.创建客户端Client。
class DPattern01Ch04_06_Client{
    DPattern01Ch04_06_FilterManager filterManager;
    public DPattern01Ch04_06_Client(DPattern01Ch04_06_FilterManager filterManager) {
        this.filterManager = filterManager;
    }
    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}
















