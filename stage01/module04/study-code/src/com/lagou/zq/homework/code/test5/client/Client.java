package com.lagou.zq.homework.code.test5.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client() {
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void start(){
        ClientWorker clientWorker = new ClientWorker(socket);
        //1. 在另一个线程里打开接收器
        Thread receiver = new Thread(() -> {
            try {
                clientWorker.openReceiver();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        receiver.start();

        //2.打开发送器
        try {
            clientWorker.openSender();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3. 当发送器关闭后, 确保接收器所在的线程结束了,才能进行关闭资源的操作
        try {
            receiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4. 接收器和发送器都关了,那就关闭所有资源退出程序
        clientWorker.closeAllResource();
    }


}

