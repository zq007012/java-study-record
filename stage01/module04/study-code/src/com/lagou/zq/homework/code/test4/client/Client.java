package com.lagou.zq.homework.code.test4.client;

import com.lagou.zq.homework.code.test4.javabean.User;
import com.lagou.zq.homework.code.test4.javabean.UserMessage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;

    public Client() {
    }

    public Client(Socket socket) {
        setSocket(socket);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public void start() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            User user = getUser();
            UserMessage userMessage = new UserMessage("check", user);

            oos.writeObject(userMessage);
            oos.flush();
            System.out.println("向服务端发送一个用户信息对象, 等待服务端是否允许登录的回复中...");
            System.out.println("这个对象是" + userMessage.toString());
            System.out.println("----------------------------------------------");

            /*
            创建对象输入流时一定要注意: 只有作为参数的输入流里能读到数据, 对象输入流才能创建成功,否则
            当前线程就会进入等待态, 直到作为参数的输入流里能读到数据, 当前线程才会从等待态被唤醒, 完成
            对象输入流的创建
             */
            ois = new ObjectInputStream(socket.getInputStream());
            userMessage = (UserMessage) ois.readObject();
            System.out.println("等到了服务端的回复");
            System.out.println("服务端回复的对象是"+ userMessage.toString());
            System.out.println("---------------------------------------");
            String signInInfo = userMessage.getSignInInfo();
            if ("success".equals(signInInfo)) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeResources(ois, oos, socket);
        }

    }

    /*
    这是一个会判断资源是否为空然后关闭资源的方法
     */
    private void closeResources(Closeable... resources) {
        for (Closeable resource :
                resources) {
            if (resource != null) {
                if (resource instanceof Flushable) {
                    try {
                        ((Flushable) resource).flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    resource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private User getUser() {
        User user = null;
        /*BufferedReader br = null;*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的账号:");
            /*br = new BufferedReader(new InputStreamReader(System.in));
            String userName = br.readLine();*/
        String userName = scanner.next();
        System.out.println("请输入您的密码:");
        /*String password = br.readLine();*/
        String password = scanner.next();
        user = new User(userName, password);
        return user;
    }
}
