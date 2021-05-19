package com.lagou.zq.homework.code.test5.starter;

import com.lagou.zq.homework.code.test5.server.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerStarter {
    public static void main(String[] args) {
        try {
            new Server(new ServerSocket(50050)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
