package com.lagou.zq.homework.code.test5.starter;

import com.lagou.zq.homework.code.test5.client.Client;

import java.io.IOException;
import java.net.Socket;

public class ClientStarter2 {
    public static void main(String[] args) {
        try {
            new Client(new Socket("127.0.0.1", 50050)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
