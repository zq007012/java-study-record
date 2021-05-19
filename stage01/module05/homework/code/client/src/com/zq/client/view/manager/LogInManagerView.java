package com.zq.client.view.manager;

import com.zq.client.bean.Message;
import com.zq.client.bean.member.Manager;
import com.zq.client.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: ManagerLogInView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 9:54
 * @Version: V1.0
 */
public class LogInManagerView extends View {
    private Manager manager;

    public LogInManagerView(ObjectOutputStream clientMessageSender,
                            ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public LogInManagerView() {
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public void startView() throws IOException, ClassNotFoundException {
        System.out.println("切换到管理员系统登陆界面");

        outer:
        while (true) {
            //1. 要求用户输入管理员账号和密码(当用户输入-1时会退出管理员系统)
            System.out.println("请输入管理员账号(输入\"-1\"可退出管理员登陆界面): ");
            String name = getStrInput(getScanner());
            if ("-1".equals(name)) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
                break outer;
            }


            System.out.println("请输入管理员密码(输入\"-1\"可退出管理员登录界面): ");
            String password = getStrInput(getScanner());
            if ("-1".equals(password)) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
                break outer;
            }


            // 2. 将管理员账号和密码发送给服务端进行验证
            boolean checkResult = logInCheck(name, password);

            if (checkResult) {
                System.out.println("账号和密码输入正确, 打开客户端的管理员系统界面");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                //进入管理员系统界面
                SystemManagerView systemManagerView = new SystemManagerView(getClientMessageSender(),
                        getClientMessageReceiver(), getScanner());
                systemManagerView.startView();

                break outer;
            } else {
                System.out.println("您输入的账号和密码错误,请重新输入...");
            }
        }


        System.out.println("退出管理员系统界面, 返回在线考试系统界面");

    }

    private boolean logInCheck(String managerAccount, String managerPassword)
            throws IOException, ClassNotFoundException {
        sendMessage(new Message(Message.MANAGER_CHECK,
                new String[]{managerAccount, managerPassword}));


        receiveMessage();
        Object[] dataReceived = (Object[]) getMessageReceived().getData();
        boolean logInResult = (boolean) dataReceived[0];
        return logInResult;
    }
}
