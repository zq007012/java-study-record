package com.lagou.zq.homework.code.test4.client;

import java.io.IOException;
import java.net.Socket;

public class ClientStarter {
    public static void main(String[] args) {
        Client client = null;
        try {
            client = new Client(new Socket("127.0.0.1", 10010));
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
