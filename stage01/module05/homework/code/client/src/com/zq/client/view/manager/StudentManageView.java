package com.zq.client.view.manager;

import com.zq.client.bean.Message;
import com.zq.client.bean.member.Student;
import com.zq.client.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName: StudentManageView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 10:19
 * @Version: V1.0
 */
public class StudentManageView extends View {
    public StudentManageView(ObjectOutputStream clientMessageSender,
                             ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public StudentManageView() {
    }

    @Override
    public void startView() throws IOException, ClassNotFoundException {
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                管理员系统/学员管理模块              |");
            System.out.println("------------------------------------------------------");
            System.out.println("|      1. 添加学员         |       2. 修改学员密码    |");
            System.out.println("------------------------------------------------------");
            System.out.println("|      3. 删除学员         |        4.查询学员        |");
            System.out.println("------------------------------------------------------");
            System.out.println("|    5.显示所有学员信息    |        0. 退出模块       |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 5);
            switch (choose) {
                case 0:
                    System.out.println("正在退出学员管理模块...");
                    break Outer;
                case 1:
                    System.out.println("进入添加学员功能");
                    createStudent();
                    break;
                case 2:
                    System.out.println("进入修改学员密码功能");
                    updateStudentPassword();
                    break;
                case 3:
                    System.out.println("进入删除学员功能");
                    deleteStudent();
                    break;
                case 4:
                    System.out.println("进入查询学员功能");
                    retrieveStudent();
                    break;
                case 5:
                    System.out.println("显示所有学员的信息");
                    showAllStudents();
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出学员管理模块成功, 返回管理员系统界面");
    }

    private void deleteStudent() throws IOException, ClassNotFoundException {
        while (true) {
            //1. 获取要删除的学员的账号
            System.out.println("请输入您要删除的学生的账号(输入\"-1\"可退出本功能): ");
            String studentAccount = getStrInput(getScanner());
            if (studentAccount.equals("-1")) {
                break;
            }

            //2. 将删除指令发送给服务器
            Message message = new Message();
            message.setType(Message.REMOVE_STUDENT);
            message.setData(studentAccount);

            sendMessage(message);


            //3. 接收和分析服务器的反馈信息, 将信息打印出来
            receiveMessage();

            if (getMessageReceived().getType().equals(Message.DELETE_STUDENT_RESULT)) {
                Object[] obj = (Object[]) getMessageReceived().getData();
                boolean modifyResult = (Boolean) obj[0];
                Student removedStudent = (Student) obj[1];
                if (modifyResult) {
                    System.out.println("成功删除账号为:" + removedStudent.getAccount() +
                            "的学员");
                } else {
                    System.out.println("删除账号为:" + studentAccount +
                            "的学员失败, 系统中没有该账号的学员,请重新输入");
                }
            }
        }
        System.out.println("退出删除学员的功能, 返回管理员系统模块");
    }

    private void retrieveStudent() throws IOException, ClassNotFoundException {
        while (true) {
            //1. 获取查询的学员的账号
            System.out.println("请输入您要查询的学生的账号(输入\"-1\"可退出本功能): ");
            String studentAccount = getStrInput(getScanner());
            if (studentAccount.equals("-1")) {
                break;
            }

            //2. 将查询指令发送给服务器
            Message message = new Message();
            message.setType(Message.RETRIEVE_STUDENT);
            message.setData(studentAccount);

            sendMessage(message);


            //3. 接收和分析服务器的反馈信息, 将信息打印出来
            receiveMessage();

            if (getMessageReceived().getType().equals(Message.RETIREVE_STUDENT_RESULT)) {
                Object[] obj = (Object[]) getMessageReceived().getData();
                boolean modifyResult = (Boolean) obj[0];
                Student student = (Student) obj[1];
                if (modifyResult) {
                    System.out.println("查询到该学员");
                    System.out.println("该学员的信息为为:" + student.toString());
                } else {
                    System.out.println("查询账号为:" + studentAccount +
                            "的学员失败, 系统中没有该账号的学员,请重新输入");
                }
            }
        }
        System.out.println("退出查询学员的功能, 返回管理员系统模块");
    }

    /**
     * 显示所有学员信息
     */
    private void showAllStudents() throws IOException, ClassNotFoundException {
        Message messageToSend = new Message(Message.ALL_STUDENTS, null);
        sendMessage(messageToSend);


        Message messageReceived = receiveMessage();
        if (messageReceived.getType().equals(Message.ALL_STUDENTS_RESULT)) {
            Object[] allStudents = (Object[]) messageReceived.getData();
            System.out.println("以下是所有学员的信息: ");
            System.out.println("-----------------------------------------------------");
            System.out.println(Arrays.toString(allStudents));
            System.out.println("-----------------------------------------------------");
        }
    }

    /**
     * 修改学员密码的功能, 要求服务器修改学员密码
     */
    private void updateStudentPassword() throws IOException,
            ClassNotFoundException {
        while (true) {
            //1. 获取要修改的信息
            System.out.println("请输入您要修改密码的学生的账号(输入\"-1\"可退出本功能): ");
            String studentAccount = getStrInput(getScanner());
            if (studentAccount.equals("-1")) {
                break;
            }
            System.out.println("请输入新密码(输入\"-1\"可退出本功能): ");
            String newPassword = getStrInput(getScanner());
            if (newPassword.equals("-1")) {
                break;
            }

            //2. 将修改指令发送给服务器
            Message message = new Message();
            message.setType(Message.UPDATE_STUDENT_PASSWORD);
            message.setData(new String[]{studentAccount, newPassword});

            sendMessage(message);


            //3. 接收和分析服务器的反馈信息, 将信息打印出来
            receiveMessage();

            if (getMessageReceived().getType().equals(Message.UPDATE_STUDENT_PASSWORD_RESULT)) {
                Object[] obj = (Object[]) getMessageReceived().getData();
                boolean modifyResult = (Boolean) obj[0];
                Student student = (Student) obj[1];
                if (modifyResult) {
                    System.out.println("学员密码修改成功");
                    System.out.println("该学员的账号为:" + student.getAccount() +
                            ", 新的密码为" + student.getPassword());
                } else {
                    System.out.println("修改账号为:" + studentAccount +
                            "的学员的密码失败, 系统中没有该账号的学员,请重新输入");
                }
            }
        }

        System.out.println("退出修改学员密码的功能, 返回学员管理模块界面");
    }

    /**
     * 添加学员的功能, 可以服务器进行通信要求服务器添加一个学员
     */
    private void createStudent() throws IOException, ClassNotFoundException {
        while (true) {
            //1. 获取用户输入的信息
            System.out.println("请输入您要添加的学生的账号(输入\"-1\"可退出本功能): ");
            String studentAccount = getStrInput(getScanner());
            if (studentAccount.equals("-1")) {
                break;
            }
            System.out.println("学员的密码默认为000000");

            //2. 封装信息发送给服务器
            Message message = new Message();
            message.setType(Message.ADD_STUDENT);
            message.setData(studentAccount);

            sendMessage(message);

            //3. 接收服务器反馈回来的消息
            receiveMessage();

            //4. 分析反馈信息并打印出来
            if (getMessageReceived().getType().equals(Message.CREATE_STUDENT_RESULT)) {
                Object[] obj = (Object[]) getMessageReceived().getData();
                boolean addResult = (Boolean) obj[0];
                Student student = (Student) obj[1];
                if (addResult) {
                    System.out.println("学员添加成功");
                    System.out.println("该学员的账号为:" + student.getAccount() +
                            ", 密码为" + student.getPassword());
                } else {
                    System.out.println("添加账号为:" + studentAccount +
                            "的学员失败, 系统中已有该账号的学员, 请重新起一个名字");
                }
            }
        }

        System.out.println("退出添加学员的功能, 返回学员管理模块界面");
    }
}
