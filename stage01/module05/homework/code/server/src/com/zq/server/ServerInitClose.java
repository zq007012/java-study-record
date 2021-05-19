package com.zq.server;

import com.zq.util.Toolkit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: ServerInitClose
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/18 19:43
 * @Version: V1.0
 */
public class ServerInitClose {

    private ServerSocket serverSocket;
    private Socket acceptSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    /**
     * 用来实现服务器的初始化操作
     */
    public void serverInit() throws IOException {
        //1. 创建ServerSocket类型的对象并提供端口号
        serverSocket = new ServerSocket(65500);
        //2. 等待客户端的连接请求, 调用accept方法
        System.out.println("正在等待客户端的连接请求...");
        acceptSocket = serverSocket.accept();
        System.out.println("与客户端(" + acceptSocket.getInetAddress().getHostAddress() +
                " : " + acceptSocket.getPort() + ")成功建立连接");
        //3. 与客户端建立连接成功, 使用输入输出流进行通信
        ois = new ObjectInputStream(acceptSocket.getInputStream());
        oos = new ObjectOutputStream(acceptSocket.getOutputStream());
        System.out.println("服务器初始化成功");
        //4. 通信结束, 关闭Socket并释放有关资源
    }

    /**
     * 用来关闭服务器及相关的资源
     */
    public void serverClose(){
        //4. 通信结束, 关闭Socket并释放有关资源
        Toolkit.closeResources(ois, oos, acceptSocket);
        System.out.println("成功关闭与客户端\"" +
                acceptSocket.getInetAddress().getHostAddress() + ":" +
                acceptSocket.getPort() + "\"的连接");
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getAcceptSocket() {
        return acceptSocket;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
}
