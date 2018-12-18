package com.main.designpattern.designpattern01.chapter04;

/**
 * @author lulu.zou
 * @version 2018/12/17
 * @since 2018/12/17
 */
public class DPattern01Ch04_01_Test {
    /**4.1mvc模式**/
    //MVC 模式代表 Model-View-Controller（模型-视图-控制器） 模式。这种模式用于应用程序的分层开发。
    //Model（模型） - 模型代表一个存取数据的对象或 JAVA POJO。它也可以带有逻辑，在数据变化时更新控制器。
    //View（视图） - 视图代表模型包含的数据的可视化。
    //Controller（控制器） - 控制器作用于模型和视图上。它控制数据流向模型对象，并在数据变化时更新视图。它使视图与模型分离开。
    public static void main(String[] args) {
        //4.使用StudentController方法来演示mvc模式的用法。
        //从数据库获取学生记录。
        DPattern01Ch04_01_Student student = retriveStudentFromDatabase();
        DPattern01Ch04_01_StudentView studentView = new DPattern01Ch04_01_StudentView();
        DPattern01Ch04_01_StudentController studentController = new DPattern01Ch04_01_StudentController(student,studentView);
        studentController.updateView();
        //更新模型数据。
        student.setName("John");
        studentController.updateView();
    }

    private static DPattern01Ch04_01_Student retriveStudentFromDatabase() {
        DPattern01Ch04_01_Student s = new DPattern01Ch04_01_Student();
        s.setRollNo("A");
        s.setName("Bob");
        return s;
    }
}

//1.创建模型。
class DPattern01Ch04_01_Student{
    private String rollNo;
    private String name;

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//2.创建视图。
class DPattern01Ch04_01_StudentView{
    public void printStudentDetails(String rollNo,String name){
        System.out.println("Student:");
        System.out.println("rollNo:"+rollNo);
        System.out.println("name:"+name);
    }
}

//3.创建控制器。
class DPattern01Ch04_01_StudentController{
    private DPattern01Ch04_01_Student model;
    private DPattern01Ch04_01_StudentView view;

    public DPattern01Ch04_01_StudentController(DPattern01Ch04_01_Student model, DPattern01Ch04_01_StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setRollNo(String rollNo){
        model.setRollNo(rollNo);
    }

    public String getRollNo(){
        return model.getRollNo();
    }

    public void setName(String name){
        model.setName(name);
    }

    public String getName(){
        return model.getName();
    }

    public void updateView(){
        view.printStudentDetails(model.getRollNo(),model.getName());
    }
}






























