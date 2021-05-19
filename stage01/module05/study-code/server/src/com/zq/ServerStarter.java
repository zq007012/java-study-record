package com.zq;

import com.zq.server.ServerDAO;
import com.zq.server.ServerInitClose;
import com.zq.server.ServerView;

import java.io.IOException;

/**
 * @ClassName: ServerStarter
 * @Description: 这是服务器的启动器
 * @Author: zq007
 * @Date: 2021/1/18 20:17
 * @Version: V1.0
 */
public class ServerStarter {
    public static void main(String[] args) {
        ServerInitClose serverInitClose = new ServerInitClose();

        ServerDAO serverDAO = null;
        ServerView serverView = null;
        try {
            //1. 初始化服务器
            serverInitClose.serverInit();

            //2. 接收并处理消息, 根据消息进行相应的业务操作
            serverDAO = new ServerDAO();
            serverView = new ServerView(serverInitClose,serverDAO);

            serverView.handleMessage();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //3. 处理完信息后, 在断开连接前保存数据
            try {
                serverDAO.storeData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 关闭服务器以及相关资源
            serverInitClose.serverClose();
        }
    }
}
