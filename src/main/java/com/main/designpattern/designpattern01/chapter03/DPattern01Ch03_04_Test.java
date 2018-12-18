package com.main.designpattern.designpattern01.chapter03;

/**
 * @author lulu.zou
 * @version 2018/12/10
 * @since 2018/12/10
 */
public class DPattern01Ch03_04_Test {
    /**3.4迭代器模式**/
    //迭代器模式（Iterator Pattern）是 Java 和 .Net 编程环境中非常常用的设计模式。这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
    //优点：1、它支持以不同的方式遍历一个聚合对象。 2、迭代器简化了聚合类。 3、在同一个聚合上可以有多个遍历。 4、在迭代器模式中，增加新的聚合类和迭代器类都很方便，无须修改原有代码。
    //缺点：由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性。
    public static void main(String[] args) {
        //3.使用Repository来获取迭代器，并打印名字。
        DPattern01Ch03_04_NameRepository nameRepository = new DPattern01Ch03_04_NameRepository();
        for(DPattern01Ch03_04_Iterator iter = nameRepository.getInterator();iter.hasNext();){
            String name = (String) iter.next();
            System.out.println("Name:"+name);
        }
    }
}

//1.创建接口。
interface DPattern01Ch03_04_Iterator{
    public boolean hasNext();
    public Object next();
}
interface DPattern01Ch03_04_Container{
    public DPattern01Ch03_04_Iterator getInterator();
}

//2.创建实现了Container接口的实体类，该类有实现了Interator接口的内部类NameIterator。
class DPattern01Ch03_04_NameRepository implements DPattern01Ch03_04_Container{
    public String names[] = {"aaa","bbb","ccc","ddd"};
    @Override
    public DPattern01Ch03_04_Iterator getInterator() {
        return new DPattern01Ch03_04_NameIterator();
    }
    private class DPattern01Ch03_04_NameIterator implements DPattern01Ch03_04_Iterator{
        int index;
        @Override
        public boolean hasNext() {
            if(index < names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}