package com.zq.client.view.student;

import com.zq.client.bean.member.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: StudentView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 19:52
 * @Version: V1.0
 */
public class SystemStudentView extends StudentView {
    public SystemStudentView(ObjectOutputStream clientMessageSender,
                             ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public SystemStudentView(ObjectOutputStream clientMessageSender,
                             ObjectInputStream clientMessageReceiver, Scanner scanner,
                             Student student) {
        super(clientMessageSender, clientMessageReceiver, scanner, student);
    }

    public SystemStudentView() {
    }

    /**
     * 开启本界面
     */
    @Override
    public void startView() throws IOException, ClassNotFoundException {
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                      学员系统                     |");
            System.out.println("------------------------------------------------------");
            System.out.println("|        1. 用户模块        |        2. 考试模块      |");
            System.out.println("------------------------------------------------------");
            System.out.println("|      0. 退出学员系统      |                         |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 2);
            switch (choose) {
                case 0:
                    System.out.println("正在退出学员系统中...");
                    break Outer;
                case 1:
                    System.out.println("用户模块");
                    UserStudentView userStudentView =
                            new UserStudentView(getClientMessageSender(),
                                    getClientMessageReceiver(),getScanner(),
                                    getStudent());
                    userStudentView.startView();
                    break;
                case 2:
                    System.out.println("进入考试模块");
                    TestStudentView testStudentView =
                            new TestStudentView(getClientMessageSender(),
                                    getClientMessageReceiver(),
                                    getScanner(),
                                    getStudent());
                    testStudentView.startView();
                    break;
                default:
                    break;
            }
        }
        System.out.println("成功退出学员系统");
    }
}
