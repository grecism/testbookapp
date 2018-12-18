package com.main.designpattern.designpattern01.chapter04;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_03_Test {
    /**4.3组合实体模式**/
    //组合实体模式（Composite Entity Pattern）用在 EJB 持久化机制中。一个组合实体是一个 EJB 实体 bean，代表了对象的图解。当更新一个组合实体时，内部依赖对象 beans 会自动更新，因为它们是由 EJB 实体 bean 管理的。以下是组合实体 bean 的参与者。
    //组合实体（Composite Entity） - 它是主要的实体 bean。它可以是粗粒的，或者可以包含一个粗粒度对象，用于持续生命周期。
    //粗粒度对象（Coarse-Grained Object） - 该对象包含依赖对象。它有自己的生命周期，也能管理依赖对象的生命周期。
    //依赖对象（Dependent Object） - 依赖对象是一个持续生命周期依赖于粗粒度对象的对象。
    //策略（Strategies） - 策略表示如何实现组合实体。
    public static void main(String[] args) {
        //5.使用Client来演示组合实体设计模式的用法。
        DPattern01Ch04_03_Client client = new DPattern01Ch04_03_Client();
        client.setData("Test1","Data1");
        client.printData();
        client.setData("Test2","Data2");
        client.printData();
    }
}

//1.创建依赖对象。
class DPattern01Ch04_03_DependentObject1{
    private String data;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}

class DPattern01Ch04_03_DependentObject2{
    private String data;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}

//2.创建粗粒度对象。
class DPattern01Ch04_03_CoarseGrainedObject{
    DPattern01Ch04_03_DependentObject1 dependentObject1 = new DPattern01Ch04_03_DependentObject1();
    DPattern01Ch04_03_DependentObject2 dependentObject2 = new DPattern01Ch04_03_DependentObject2();
    public void setData(String data1,String data2){
        dependentObject1.setData(data1);
        dependentObject2.setData(data2);
    }
    public String[] getData(){
        return  new String[]{dependentObject1.getData(),dependentObject2.getData()};
    }
}

//3.创建组合实体。
class DPattern01Ch04_03_CompositeEntity{
    DPattern01Ch04_03_CoarseGrainedObject coarseGrainedObject = new DPattern01Ch04_03_CoarseGrainedObject();
    public void setData(String data1,String data2){
        coarseGrainedObject.setData(data1,data2);
    }
    public String[] getData(){
        return coarseGrainedObject.getData();
    }
}

//4.创建使用组合实体的客户端类。
class DPattern01Ch04_03_Client{
    private DPattern01Ch04_03_CompositeEntity compositeEntity = new DPattern01Ch04_03_CompositeEntity();
    public void printData(){
        for(String s :compositeEntity.getData()){
            System.out.println("Data:"+s);
        }
    }
    public void setData(String data1,String data2){
        compositeEntity.setData(data1,data2);
    }
}
















