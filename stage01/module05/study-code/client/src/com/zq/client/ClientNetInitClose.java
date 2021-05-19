package com.zq.client;

import com.zq.util.Toolkit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName: ClientInitClose
 * @Description: 用来初始化和关闭客户端的网络功能
 * @Author: zq007
 * @Date: 2021/1/18 20:35
 * @Version: V1.0
 */
public class ClientNetInitClose {

    private Socket socket;
    private ObjectOutputStream clientMessageSender;
    private ObjectInputStream clientMessageReceiver;

    public void ClientNetInit() throws IOException, ClassNotFoundException {
        //1. 创建Socket类型的对象并提供服务端的IP地址和端口号
        socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 65500);
        //2. 使用输入输出流进行通信
        clientMessageSender = new ObjectOutputStream(socket.getOutputStream());
        clientMessageReceiver = new ObjectInputStream(socket.getInputStream());
        System.out.println("客户端的对象输入输出流创建成功, 可以通信了");
        //3. 关闭客户端及相关的资源
    }

    public void ClientNetClose(){
        Toolkit.closeResources(clientMessageReceiver, clientMessageSender, socket);
    }

    public ObjectOutputStream getClientMessageSender() {
        return clientMessageSender;
    }

    public ObjectInputStream getClientMessageReceiver() {
        return clientMessageReceiver;
    }
}
