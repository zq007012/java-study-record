package com.lagou.zq.studycode.task4.tcpmessage;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            socket = new Socket("127.0.0.1", 10010);
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw.write("我是客户端发来的信息");
            bw.newLine();
            bw.flush();

            System.out.println("服务端发来的信息是: " + br.readLine());
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
