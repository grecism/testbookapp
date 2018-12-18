package com.main.designpattern.designpattern01.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_04_Test {
    /**4.4数据访问对象模式**/
    //数据访问对象模式（Data Access Object Pattern）或 DAO 模式用于把低级的数据访问 API 或操作从高级的业务服务中分离出来。以下是数据访问对象模式的参与者。
    //数据访问对象接口（Data Access Object Interface） - 该接口定义了在一个模型对象上要执行的标准操作。
    //数据访问对象实体类（Data Access Object concrete class） - 该类实现了上述的接口。该类负责从数据源获取数据，数据源可以是数据库，也可以是 xml，或者是其他的存储机制。
    //模型对象/数值对象（Model Object/Value Object） - 该对象是简单的 POJO，包含了 get/set 方法来存储通过使用 DAO 类检索到的数据。
    public static void main(String[] args) {
        //4.使用StudentDao来演示数据访问对象的模式。
        DPattern01Ch04_04_StudentDao studentDaoImpl = new DPattern01Ch04_04_StudentDaoImpl();
        //获取所有学生
        for(DPattern01Ch04_04_Student student : studentDaoImpl.getAllStudents()){
            System.out.println("Student:[Id="+student.getId()+"Name="+student.getName()+"]");
        }
        //更新学生
        DPattern01Ch04_04_Student student = studentDaoImpl.getAllStudents().get(0);
        student.setName("Mack");
        System.out.println("Student:[Id="+student.getId()+"Name="+student.getName()+"]");
        //获取学生
        DPattern01Ch04_04_Student student2 = studentDaoImpl.getAllStudents().get(1);
        System.out.println("Student:[Id="+student2.getId()+"Name="+student2.getName()+"]");
    }
}

//1.创建数值对象。
class DPattern01Ch04_04_Student{
    private int id;
    private String name;
    public DPattern01Ch04_04_Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

//2.创建数据访问对象接口。
interface DPattern01Ch04_04_StudentDao{
    List<DPattern01Ch04_04_Student> getAllStudents();
    DPattern01Ch04_04_Student getStudent(int id);
    void updateStudent(DPattern01Ch04_04_Student student);
    void deleteStudent(DPattern01Ch04_04_Student student);
}

//3.创建实现了上述接口的实体类。
class DPattern01Ch04_04_StudentDaoImpl implements DPattern01Ch04_04_StudentDao{
    private List<DPattern01Ch04_04_Student> students;

    public DPattern01Ch04_04_StudentDaoImpl() {
        this.students = new ArrayList<>();
        DPattern01Ch04_04_Student student = new DPattern01Ch04_04_Student(0,"Job");
        DPattern01Ch04_04_Student student2 = new DPattern01Ch04_04_Student(1,"Joe");
        students.add(student);
        students.add(student2);
    }
    @Override
    public List<DPattern01Ch04_04_Student> getAllStudents() {
        return students;
    }
    @Override
    public DPattern01Ch04_04_Student getStudent(int id) {
        return students.get(id);
    }

    @Override
    public void updateStudent(DPattern01Ch04_04_Student student) {
        students.get(student.getId()).setName(student.getName());
        System.out.println("Student: id"+student.getId()+",update in the database.");
    }

    @Override
    public void deleteStudent(DPattern01Ch04_04_Student student) {
        students.remove(student.getId());
        System.out.println("Student: id"+student.getId()+",delete from database.");
    }
}

















