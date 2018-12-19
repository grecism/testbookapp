package com.main.designpattern.designpattern01.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_07_Test {
    /**4.7服务定位器模式**/
    //服务定位器模式（Service Locator Pattern）用在我们想使用 JNDI 查询定位各种服务的时候。考虑到为某个服务查找 JNDI 的代价很高，服务定位器模式充分利用了缓存技术。在首次请求某个服务时，服务定位器在 JNDI 中查找服务，并缓存该服务对象。当再次请求相同的服务时，服务定位器会在它的缓存中查找，这样可以在很大程度上提高应用程序的性能。以下是这种设计模式的实体。
    //服务（Service） - 实际处理请求的服务。对这种服务的引用可以在 JNDI 服务器中查找到。
    //Context / 初始的 Context - JNDI Context 带有对要查找的服务的引用。
    //服务定位器（Service Locator） - 服务定位器是通过 JNDI 查找和缓存服务来获取服务的单点接触。
    //缓存（Cache） - 缓存存储服务的引用，以便复用它们。
    //客户端（Client） - Client 是通过 ServiceLocator 调用服务的对象。
    public static void main(String[] args) {
        //6.使用ServiceLocator来演示服务定位器设计模式。
        DPattern01Ch04_07_Service service1 = DPattern01Ch04_07_ServiceLocator.getService("Serviceone");
        service1.execute();
        DPattern01Ch04_07_Service service2 = DPattern01Ch04_07_ServiceLocator.getService("Servicetwo");
        service2.execute();
        DPattern01Ch04_07_Service service3 = DPattern01Ch04_07_ServiceLocator.getService("Serviceone");
        service3.execute();
        DPattern01Ch04_07_Service service4 = DPattern01Ch04_07_ServiceLocator.getService("Servicetwo");
        service4.execute();
    }
}

//1.创建服务接口。
interface DPattern01Ch04_07_Service{
    public String getName();
    public void execute();
}

//2.创建实体服务。
class DPattern01Ch04_07_Serviceone implements DPattern01Ch04_07_Service{
    @Override
    public String getName() {
        return "Serviceone";
    }
    @Override
    public void execute() {
        System.out.println("Execute Serviceone.");
    }
}

class DPattern01Ch04_07_Servicetwo implements DPattern01Ch04_07_Service{
    @Override
    public String getName() {
        return "Servicetwo";
    }
    @Override
    public void execute() {
        System.out.println("Execute Servicetwo.");
    }
}

//3.为JNDI查询创建初始化InitialContext。
class DPattern01Ch04_07_InitialContext{
    public Object lookup(String jndiName){
        if(jndiName.equalsIgnoreCase("serviceone")){
            System.out.println("look up and create a new servceone object");
            return new DPattern01Ch04_07_Serviceone();
        }else{
            System.out.println("look up and create a new servcetwo object");
            return new DPattern01Ch04_07_Servicetwo();
        }
    }
}

//4.创建缓存Cache。
class DPattern01Ch04_07_Cache{
    private List<DPattern01Ch04_07_Service> services;
    public DPattern01Ch04_07_Cache() {
        this.services = new ArrayList<>();
    }
    public DPattern01Ch04_07_Service getService(String serviceName){
        for(DPattern01Ch04_07_Service service : services){
            if(service.getName().equals(serviceName)){
                System.out.println("Return Cache"+serviceName+"Object.");
                return service;
            }
        }
        return null;
    }
    public void addService(DPattern01Ch04_07_Service newService){
        boolean isExist = false;
        for(DPattern01Ch04_07_Service service : services){
            if(service.getName().equalsIgnoreCase(newService.getName())){
                isExist = true;
            }
        }
        if(!isExist){
            services.add(newService);
        }
    }
}

//5.创建服务定位器。
class DPattern01Ch04_07_ServiceLocator{
    private static DPattern01Ch04_07_Cache cache;
    static {
        cache = new DPattern01Ch04_07_Cache();
    }
    public static  DPattern01Ch04_07_Service getService(String jndiName){
        DPattern01Ch04_07_Service service = cache.getService(jndiName);
        if(service != null){
            return service;
        }
        DPattern01Ch04_07_InitialContext context = new DPattern01Ch04_07_InitialContext();
        DPattern01Ch04_07_Service serviceo = (DPattern01Ch04_07_Service) context.lookup(jndiName);
        cache.addService(serviceo);
        return serviceo;
    }
}


















