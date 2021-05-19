package com.lagou.zq.homework.code.test1;

import com.lagou.zq.homework.code.test1.exception.AgeException;
import com.lagou.zq.homework.code.test1.exception.NumberException;
import com.lagou.zq.homework.code.util.Toolkit;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagerSys {
    private StudentManager studentManager;

    {
        initStudentManager();
    }

    private void initStudentManager() {
        ArrayList<Student> students = null;
        File file = new File("data/students.data");
        if (file.exists()){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                students = (ArrayList<Student>) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("导入学生数据失败");
                students = new ArrayList<>();
                e.printStackTrace();
            } finally {
                Toolkit.closeResources(ois);
            }
            System.out.println("导入学生数据成功");
        }else{
            System.out.println("没有学生数据需要导入");
            students = new ArrayList<>();
        }

        setStudentManager(new StudentManager(students));
    }

    public StudentManagerSys() {
    }

    /**
     * 开启学生信息管理
     */
    public void start() {
        //1. 首先要有个界面, 用户每完成一个功能就显示一次界面, 方便用户查看各项功能
        printInterface();
        //2. 捕捉用户输入的信息
        Scanner scanner = new Scanner(System.in);
        outer: while (true) {
            System.out.println("请输入您要使用的学生信息管理系统功能, 只要输入0 ~ 5的数字编号即可");
            int number = scanner.nextInt();
            if (number < 0 || number > 5) {
                System.out.println("您的输入有误,您输入的是: " + number +
                        " ,但编号是一个从0到5的数字,请重新输入...");
                continue;
            }

            switch (number) {
                case 1:
                    //开启添加学生的功能
                    addStudent(scanner);
                    break;
                case 2:
                    //根据姓名删除学生
                    deleteStudent(scanner);
                    break;
                case 3:
                    //根据姓名查找学生
                    searchStudent(scanner);
                    break;
                case 4:
                    changeStudent(scanner);
                    break;
                case 5:
                    //遍历所有学生信息
                    showAllStudents();
                    break;
                case 0:
                    System.out.println("您输入了\"0\"这个退出命令, 开始退出学生信息管理系统...");
                    break outer;
                default:
                    break;

            }
        }
        storeStudents();
        System.out.println("成功退出了学生信息管理系统");
    }

    /**
     * 遍历所有的学生信息
     */
    private void showAllStudents() {
        System.out.println("系统中所有学生的信息是: ");
        System.out.println(studentManager.getStudents().toString());
    }

    /**
     * 将系统中第一个名为oldName的学生的名字改为newName
     * @param scanner
     */
    private void changeStudent(Scanner scanner) {
        while (true){
            System.out.println("您正在使用修改学生姓名的功能, 输入\"-1\"可退出本功能");
            System.out.println("请输入您要修改的学生的姓名: ");
            String oldName = scanner.next();
            if (oldName.equals("-1")){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出修改学生姓名的功能...");
                break;
            }

            Student student = studentManager.searchStudent(oldName);
            if (null == student){
                System.out.println("系统中没有叫'" + oldName + "'的学生,请重新输入...");
                continue;
            }

            System.out.println("请输入修改后的姓名: ");
            String newName = scanner.next();
            if (newName.equals("-1")){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出修改学生姓名的功能...");
                break;
            }

            System.out.println("修改姓名前的学生信息是:");
            System.out.println(student);

            student.setName(newName);
            System.out.println("您已成功修改学生的姓名");
            System.out.println("修改姓名后的学生信息是:");
            System.out.println(student);
        }
        System.out.println("您已退出修改学生姓名的功能");
    }

    /**
     * 根据用户输入的名字查找到学生的全部信息,并肩齐打印出来
     * @param scanner
     */
    private void searchStudent(Scanner scanner) {
        while (true){
            System.out.println("您正在使用查找学生的功能, 输入\"-1\"可退出本功能");
            System.out.println("请输入您要查找的学生的姓名: ");
            String name = scanner.next();
            if (name.equals("-1")){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出查找学生的功能...");
                break;
            }

            Student student = studentManager.searchStudent(name);
            if (null == student){
                System.out.println("系统中没有叫'" + name + "'的学生");
            }else{
                System.out.println("找到名字是'" + name + "'的学生了, 他的所有信息是: ");
                System.out.println(student);
            }
        }
        System.out.println("您已成功退出查找学生的功能");
    }

    /**
     * 根据用户输入的名字删除系统中第一个叫这个名字的学生
     * @param scanner
     */
    private void deleteStudent(Scanner scanner) {
        while (true){
            System.out.println("您正在使用删除学生的功能, 输入\"-1\"可退出本功能");
            System.out.println("请输入您要删除的学生的姓名: ");
            String name = scanner.next();
            if (name.equals("-1")){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出删除学生的功能...");
                break;
            }

            Student student = studentManager.deleteStudent(name);
            if (null == student){
                System.out.println("系统中没有名字是: " + name + " 的学生, 请重新输入");
            }else {
                System.out.println("系统中第一个叫" + name + "的学生的全部信息是:");
                System.out.println(student);
                System.out.println("已成功将其从系统中删除");
            }
        }
        System.out.println("您已退出删除学生的功能");
    }

    /**
     * 捕捉控制台输入的字符串,将字符串信息封装成Student对象
     * 然后将student对象添加到studentManager对象的集合中
     * @param scanner
     */
    private void addStudent(Scanner scanner){
        while (true){
            System.out.println("您正在使用添加学生的功能,输入-1可关闭本功能");
            System.out.println("请输入学生的姓名: ");
            String name = scanner.next();
            if ("-1".equals(name)){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出添加学生的功能...");
                break;
            }

            Student student = new Student();
            student.setName(name);

            /*System.out.println("请输入学生的年龄(一个3~24的数字): ");
            int age = scanner.nextInt();
            if (-1 == age){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出添加学生的功能...");
                break;
            }

            if (age < 3 || age > 30){
                System.out.println("您输入的年龄\"" + age + "\"不是一个3~30的数字, 请重新输入学生信息");
                continue;
            }*/

            int age = setStudentAge(student, scanner);
            if (-1 == age){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出添加学生的功能...");
                break;
            }

            /*System.out.println("请输入学生的学号(学号是一个20200001~20209999的数字): ");
            int number = scanner.nextInt();
            if (-1 == number){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出添加学生的功能...");
                break;
            }

            if (number < 20200001 || number > 20209999){
                System.out.println("您输入的学号\"" + number + "\"不是一个20200001~20209999的数字, 请重新输入学生信息");
                continue;
            }*/

            int number = setStudentNumber(student, scanner);

            if (-1 == number){
                System.out.println("您输入了\"-1\"这个退出命令,开始退出添加学生的功能...");
                break;
            }
            if (studentManager.createStudent(student)){
                System.out.println("添加学生成功");
            }else{
                System.out.println("添加学生失败, 请重新输入...");
            }
        }
        System.out.println("您已退出添加学生的功能");
    }

    /**
     * 在退出系统时保存包含学生信息的List对象到本项目下的data文件夹中的Student.data文件中
     */
    private void storeStudents() {
        //1. 先确定是否已经有这个Student.data这个文件, 若已经有了,就把这个文件备份,重命名成Students.data.bak
        File file = new File("data/Students.data");
        File bak = new File("data/Students.data.bak");

        if (file.exists()){
            /*
            如果文件已存在那就把这个文件重命名为备份文件, 以便出现错误时还能通过备份文件找回之前的数据
             */
            if (bak.exists()){
                bak.delete();
            }

            file.renameTo(bak);
        }else {
            file.getParentFile().mkdirs();
        }

        //2. 通过对象输出流将students这个List集合对象保存到Students.data文件中
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("data" +
                    "/Students.data"));
            oos.writeObject(studentManager.getStudents());
            oos.flush();
            System.out.println("学生数据已保留");
        } catch (IOException e) {
            //3. 如果保存失败,就把备份文件重命名成Student.data以便留住上次的学生信息
            System.out.println("保存学生数据失败,将备份文件恢复为学生数据文件");
            file.delete();
            if (bak.exists()){
                bak.renameTo(file);
            }
            e.printStackTrace();
        } finally {
            Toolkit.closeResources(oos);
        }


    }

    /**
     *
     * @param student
     * @param scanner
     * @return 会返回学生输入的年龄, 如果返回-1, 则退出添加学生这个功能
     */
    private int setStudentNumber(Student student, Scanner scanner) {
        System.out.println("请输入学生的学号(学号是一个20200001~20209999的数字): ");
        int number = scanner.nextInt();
        if (-1 == number){
            return -1;
        }

        try {
            student.setNumber(number);
            return number;
        } catch (NumberException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            return setStudentNumber(student,scanner);
        }
    }

    /**
     * 这个方法会根据用户输入的内容设置学生的年龄,如果用户输入的年龄不合理,就会在catch语句里提醒
     * 用户年龄不合理,并递归本方法, 直到用户输入的年龄合理或者输入'-1'为止
     * @param student
     * @param scanner
     * @return 会返回学生输入的年龄, 如果返回-1, 则退出添加学生这个功能
     */
    private int setStudentAge(Student student, Scanner scanner){
        System.out.println("请输入学生的年龄(一个3~30的数字): ");
        int age = scanner.nextInt();
        if (-1 == age){
            return -1;
        }

        //产生异常后重新调用本方法,再次获取用户用户输入的年龄
        try {
            student.setAge(age);
            return age;
        } catch (AgeException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            return setStudentAge(student, scanner);
        }
    }

    /**
     * 这是一个学生信息管理系统的界面
     */
    private void printInterface() {
        System.out.println("--------------------学生信息管理系统--------------------");
        System.out.println("|       1. 添加学生        |    2. 根据姓名删除学生     |");
        System.out.println("-------------------------------------------------------");
        System.out.println("|    3. 根据姓名查找学生    |      4. 修改学生的姓名    |");
        System.out.println("-------------------------------------------------------");
        System.out.println("|    5. 遍历所有学生信息    |        0. 退出系统        |");
        System.out.println("-------------------------------------------------------");

    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

    public void setStudentManager(StudentManager studentManager) {
        this.studentManager = studentManager;
    }
}
