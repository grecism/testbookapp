package com.main.designpattern.designpattern01.chapter04;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_02_Test {
    /**4.2业务代表模式**/
    //业务代表模式（Business Delegate Pattern）用于对表示层和业务层解耦。它基本上是用来减少通信或对表示层代码中的业务层代码的远程查询功能。在业务层中我们有以下实体。
    //客户端（Client） - 表示层代码可以是 JSP、servlet 或 UI java 代码。
    //业务代表（Business Delegate） - 一个为客户端实体提供的入口类，它提供了对业务服务方法的访问。
    //查询服务（LookUp Service） - 查找服务对象负责获取相关的业务实现，并提供业务对象对业务代表对象的访问。
    //业务服务（Business Service） - 业务服务接口。实现了该业务服务的实体类，提供了实际的业务实现逻辑。
    public static void main(String[] args) {
        //6.使用BusinessDelegate和Client类来演示业务代表模式。
        DPattern01Ch04_02_BusinessDelegate businessDelegate = new DPattern01Ch04_02_BusinessDelegate();
        businessDelegate.setServiceType("EJB");
        DPattern01Ch04_02_Client client = new DPattern01Ch04_02_Client(businessDelegate);
        client.doTask();
        businessDelegate.setServiceType("JMS");
        client.doTask();
    }
}

//1.创建BusinessService接口。
interface DPattern01Ch04_02_BusinessService{
    public void processing();
}

//2.创建实体服务类。
class DPattern01Ch04_02_EjbService implements DPattern01Ch04_02_BusinessService{
    @Override
    public void processing() {
        System.out.println("process task by invoking EjB Service.");
    }
}

class DPattern01Ch04_02_JMSService implements DPattern01Ch04_02_BusinessService{
    @Override
    public void processing() {
        System.out.println("process task by invoking JMS Service.");
    }
}

//3.创建业务查询服务。
class DPattern01Ch04_02_BusinessLookUp{
    public DPattern01Ch04_02_BusinessService getBusinessService(String serviceType){
        if(serviceType.equalsIgnoreCase("EJB")){
            return new DPattern01Ch04_02_EjbService();
        }else{
            return new DPattern01Ch04_02_JMSService();
        }
    }
}

//4.创建业务代表。
class DPattern01Ch04_02_BusinessDelegate{
    private DPattern01Ch04_02_BusinessLookUp businessLookUp = new DPattern01Ch04_02_BusinessLookUp();
    private DPattern01Ch04_02_BusinessService businessService;
    private String serviceType;

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void doTask(){
        businessService = businessLookUp.getBusinessService(serviceType);
        businessService.processing();
    }
}

//5.创建客户端。
class DPattern01Ch04_02_Client{
    DPattern01Ch04_02_BusinessDelegate businessDelegate;

    public DPattern01Ch04_02_Client(DPattern01Ch04_02_BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    public void doTask(){
        businessDelegate.doTask();
    }
}






























