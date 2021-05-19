package com.zq.client.view.student;

import com.zq.client.bean.Message;
import com.zq.client.bean.member.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: StudentLogInView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 19:45
 * @Version: V1.0
 */
public class LogInStudentView extends StudentView {
    public LogInStudentView(ObjectOutputStream clientMessageSender,
                            ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public LogInStudentView(ObjectOutputStream clientMessageSender,
                            ObjectInputStream clientMessageReceiver, Scanner scanner,
                            Student student) {
        super(clientMessageSender, clientMessageReceiver, scanner, student);
    }

    public LogInStudentView() {
    }

    /**
     * 开启本界面
     */
    @Override
    public void startView() throws IOException, ClassNotFoundException {
        System.out.println("切换到学员系统登陆界面");

        //要求学员输入账号和密码, 直到学员的账号和密码正确
        outer:
        while (true) {
            //1. 要求用户输入学员账号和密码(当用户输入-1时会退出管理员系统)
            System.out.println("请输入学员账号(输入\"-1\"可退出管理员登陆界面): ");
            String name = getStrInput(getScanner());
            if ("-1".equals(name)) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
                break outer;
            }

            System.out.println("请输入学员密码(输入\"-1\"可退出管理员登录界面): ");
            String password = getStrInput(getScanner());
            if ("-1".equals(password)) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
                break outer;
            }


            // 2. 将管理员账号和密码发送给服务端进行验证
            boolean checkResult = logInCheck(name, password);

            if (checkResult) {
                System.out.println("账号和密码输入正确, 进入客户端的学员系统界面");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                //进入管理员系统界面
                SystemStudentView systemStudentView =
                        new SystemStudentView(getClientMessageSender(),
                                getClientMessageReceiver(), getScanner(), getStudent());
                systemStudentView.startView();
                break outer;
            } else {
                System.out.println("您输入的账号和密码错误,请重新输入...");
            }
        }

        System.out.println("退出学员系统界面, 返回在线考试系统界面");
    }

    private boolean logInCheck(String studentAccount, String studentPassword)
            throws IOException, ClassNotFoundException {
        boolean logInResult = false;
        sendMessage(new Message(Message.STUDENT_CHECK,
                new String[]{studentAccount,
                        studentPassword}));

        receiveMessage();

        if (Message.STUDENT_CHECK_RESULT.equals(getMessageReceived().getType())) {
            Object[] dataReceived = (Object[]) getMessageReceived().getData();
            logInResult = (boolean) dataReceived[0];
            //设置本学员的信息
            setStudent((Student) dataReceived[1]);
        }

        return logInResult;
    }


}
