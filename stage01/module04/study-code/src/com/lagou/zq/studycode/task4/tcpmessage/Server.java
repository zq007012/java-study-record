package com.lagou.zq.studycode.task4.tcpmessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //1. 开启服务端
            server = new ServerSocket(10010);
            //2. 连接一个客户端
            client = server.accept();
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            System.out.println(br.readLine());
            bw.write("收到你发的信息了");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
