package com.lagou.zq.homework.code.test4.server;


import com.lagou.zq.homework.code.test4.javabean.User;
import com.lagou.zq.homework.code.test4.javabean.UserMessage;
import com.lagou.zq.homework.code.util.Toolkit;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server() {
    }

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            socket = serverSocket.accept();

            System.out.println("客户端\"" + socket.getLocalAddress().toString() + ":" + socket.getPort() + "连接成功");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            /*这里需要注意的是创建ObjectInputStream对象时, 作为参数的输入流必须有能读到的数据,
            ObjectInputStream才会创建成功,否则当前线程会进入等待态,直到作为参数的输入流里有了
            数据, 当前线程
            才会从等待态被唤醒*/
            ois = new ObjectInputStream(inputStream);
            UserMessage userMessage = (UserMessage) ois.readObject();
            System.out.println("读到客户端的数据了");
            System.out.println("客户端发来的数据是: " + userMessage.toString());
            userMessage = checkAndChangSignInInfo(userMessage);

            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(userMessage);
            oos.flush();
            System.out.println("把含有登录状态的UserMessage对象返回给客户端");
        } catch (IOException | ClassNotFoundException e)  {
            e.printStackTrace();
        } finally {
            Toolkit.closeResources(ois,oos,socket,serverSocket);
            /*ois.close();
            oos.close();
            socket.close();
            serverSocket.close();*/
        }

    }

    /**
     * 检查客户端发来的账号和密码是否正确
     * @param userMessage
     * @return 返回的是封装了检查结果的UserMessage对象
     */
    private UserMessage checkAndChangSignInInfo(UserMessage userMessage) {
        User user = userMessage.getUser();
        if ("admin".equalsIgnoreCase(user.getUserName()) && "123456".equals(user.getPassword())) {
            System.out.println("客户端发来的账号和密码是正确的, 登录成功");
            userMessage.setSignInInfo("success");
        }else{
            System.out.println("客户端发来的账号或密码有问题, 登录失败");
            userMessage.setSignInInfo("fail");
        }
        return userMessage;
    }


}
