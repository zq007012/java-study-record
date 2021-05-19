package com.zq;

import com.zq.client.ClientNetInitClose;
import com.zq.client.ClientScanner;
import com.zq.client.view.MainView;

import java.io.IOException;

/**
 * @ClassName: ClientStarter
 * @Description: 服务端的启动器
 * @Author: zq007
 * @Date: 2021/1/18 20:45
 * @Ver2
 * sion: V1.0
 */
public class ClientStarter {
    public static void main(String[] args) {
        ClientNetInitClose clientNetInitClose = null;

        MainView mainView = null;
        try {
            clientNetInitClose = new ClientNetInitClose();

            //1. 初始化客户端的网络功能
            clientNetInitClose.ClientNetInit();

            //2. 打开客户端主界面
            mainView = new MainView(clientNetInitClose.getClientMessageSender(),
                    clientNetInitClose.getClientMessageReceiver(),
                    ClientScanner.getInstance().getScanner());
            mainView.startView();


        } catch (IOException e) {
            System.out.println("连接服务器失败, 可能服务端未开启, 无法开启客户端的功能, " +
                    "强制关闭客户端");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ClientScanner.getInstance().closeScanner();
            clientNetInitClose.ClientNetClose();
        }

    }
}
