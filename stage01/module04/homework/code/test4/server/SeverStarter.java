package com.lagou.zq.homework.code.test4.server;

import java.io.IOException;
import java.net.ServerSocket;

public class SeverStarter {
    public static void main(String[] args) {
        try {
            Server server = new Server(new ServerSocket(10010));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
