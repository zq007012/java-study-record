package com.zq.client.view;

import com.zq.client.bean.Message;
import com.zq.client.view.manager.LogInManagerView;
import com.zq.client.view.student.LogInStudentView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: MainView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 9:33
 * @Version: V1.0
 */
public class MainView extends View {
    public MainView(ObjectOutputStream clientMessageSender,
                    ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public MainView() {
    }

    @Override
    public void startView() throws IOException, ClassNotFoundException {
        //只有用户选择0. 退出系统, 在线考试系统界面才会关闭
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                     在线考试系统                    |");
            System.out.println("------------------------------------------------------");
            System.out.println("|    1. 学员系统           |      2. 管理员系统       |");
            System.out.println("------------------------------------------------------");
            System.out.println("|   0. 退出在线考试系统    |                          |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 2);
            switch (choose) {
                case 0:
                    System.out.println("正在退出在线考试系统中...");
                    //向服务器发送结束连接的信息
                    sendQuitMessage();
                    //退出循环即可结束客户端
                    break Outer;
                case 1:
                    System.out.println("切换到学员系统");
                    LogInStudentView logInStudentView =
                            new LogInStudentView(getClientMessageSender(),
                                    getClientMessageReceiver(), getScanner());
                    logInStudentView.startView();
                    break;
                case 2:
                    LogInManagerView logInManagerView =
                            new LogInManagerView(getClientMessageSender(),
                                    getClientMessageReceiver(), getScanner());
                    logInManagerView.startView();
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出在线考试系统成功");
    }

    /**
     * 向服务端发送关闭连接的消息
     *
     * @throws IOException
     */
    private void sendQuitMessage() throws IOException {
        sendMessage(new Message(Message.OVER, null));
    }


}
