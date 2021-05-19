package com.lagou.zq.homework.code.test5.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private ServerSocket serverSocket;
    private LinkedList<ObjectOutputStream> groupSender;
    private LinkedList<String> groupMembers;

    public Server() {
    }

    public Server(ServerSocket serverSocket) {
        groupSender = new LinkedList<>();
        groupMembers = new LinkedList<>();
        setServerSocket(serverSocket);
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public void start() {
        while(true){
            Socket acceptSocket = null;
            try {
                acceptSocket = serverSocket.accept();
                System.out.println("与客户端\"" + acceptSocket.getInetAddress().toString() +
                        ":" + acceptSocket.getPort() + "\"建立了连接");
                new ServerThread(acceptSocket,groupSender,groupMembers).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
