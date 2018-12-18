package com.main.designpattern.designpattern01.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_08_Test {
    /**4.8传输对象模式**/
    //传输对象模式（Transfer Object Pattern）用于从客户端向服务器一次性传递带有多个属性的数据。传输对象也被称为数值对象。传输对象是一个具有 getter/setter 方法的简单的 POJO 类，它是可序列化的，所以它可以通过网络传输。它没有任何的行为。服务器端的业务类通常从数据库读取数据，然后填充 POJO，并把它发送到客户端或按值传递它。对于客户端，传输对象是只读的。客户端可以创建自己的传输对象，并把它传递给服务器，以便一次性更新数据库中的数值。以下是这种设计模式的实体。
    //业务对象（Business Object） - 为传输对象填充数据的业务服务。
    //传输对象（Transfer Object） - 简单的 POJO，只有设置/获取属性的方法。
    //客户端（Client） - 客户端可以发送请求或者发送传输对象到业务对象。
    public static void main(String[] args) {
        //3.使用StudentBo来演示传输对象设计模式。
        DPattern01Ch04_08_StudentBo bo = new DPattern01Ch04_08_StudentBo();
        //获取所有
        for(DPattern01Ch04_08_StudentVo vo: bo.getAllStudents()){
            System.out.println("StudentVo:[RollNo:"+vo.getRollNo()+"，Name:"+vo.getName()+"]");
        }
        //更新
        DPattern01Ch04_08_StudentVo student = bo.getStudent(1);
        student.setName("Make");
        bo.updateStudent(student);
        //获取
        DPattern01Ch04_08_StudentVo student2 = bo.getStudent(1);
        System.out.println("StudentVo:[RollNo:"+student2.getRollNo()+"，Name:"+student2.getName()+"]");
    }
}

//1.创建传输对象。
class DPattern01Ch04_08_StudentVo{
    private int rollNo;
    private String name;
    public DPattern01Ch04_08_StudentVo(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

//2.创建业务对象。
class DPattern01Ch04_08_StudentBo{
    private List<DPattern01Ch04_08_StudentVo> students;
    public DPattern01Ch04_08_StudentBo() {
        this.students = new ArrayList<>();
        DPattern01Ch04_08_StudentVo so = new DPattern01Ch04_08_StudentVo(0,"Joe");
        DPattern01Ch04_08_StudentVo so2 = new DPattern01Ch04_08_StudentVo(1,"Alice");
        students.add(so);
        students.add(so2);
    }
    public List<DPattern01Ch04_08_StudentVo> getAllStudents(){
        return students;
    }
    public DPattern01Ch04_08_StudentVo getStudent(int rollNo){
        return students.get(rollNo);
    }
    public void updateStudent(DPattern01Ch04_08_StudentVo studentVo){
        students.get(studentVo.getRollNo()).setName(studentVo.getName());
        System.out.println("StudentVo："+studentVo.getRollNo()+"，update in the database.");
    }
    public void deleteStudent(DPattern01Ch04_08_StudentVo studentVo){
        students.remove(studentVo.getRollNo());
        System.out.println("StudentVo："+studentVo.getRollNo()+"，delete from database.");
    }
}