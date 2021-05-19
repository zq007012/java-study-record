package com.zq.client.view.manager;

import com.zq.client.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: ManagerView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 10:15
 * @Version: V1.0
 */
public class SystemManagerView extends View {
    public SystemManagerView(ObjectOutputStream clientMessageSender,
                             ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public SystemManagerView() {
    }

    @Override
    public void startView() throws IOException, ClassNotFoundException {
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                      管理员系统                     |");
            System.out.println("------------------------------------------------------");
            System.out.println("|    1. 学员管理模块        |     2. 考题管理模块     |");
            System.out.println("------------------------------------------------------");
            System.out.println("|    0. 退出管理员系统      |                         |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 2);
            switch (choose) {
                case 0:
                    System.out.println("正在退出管理员系统中...");
                    break Outer;
                case 1:
                    System.out.println("进入学员管理模块");
                    StudentManageView studentManageView =
                            new StudentManageView(getClientMessageSender(),
                                    getClientMessageReceiver(),getScanner());
                    studentManageView.startView();
                    break;
                case 2:
                    System.out.println("进入考题管理模块");
                    ExamManageView examManageView =
                            new ExamManageView(getClientMessageSender(),
                                    getClientMessageReceiver(),getScanner());
                    examManageView.startView();
                    break;
                default:
                    break;
            }
        }
        System.out.println("成功退出管理员系统");
    }
}
