package com.lagou.zq.homework.code.test5.server;

import com.lagou.zq.homework.code.test5.javabeans.DataType;
import com.lagou.zq.homework.code.test5.javabeans.UserData;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class ServerThread extends Thread {
    private Socket acceptSocket;
    private String clientNickname;
    private LinkedList<ObjectOutputStream> groupRepeaters;
    private ObjectOutputStream myRepeater;
    private ObjectInputStream receiver;
    private LinkedList<String> groupMembers;
    private UserData receiveUserData;


    public ServerThread(Socket acceptSocket, LinkedList<ObjectOutputStream> groupRepeaters,
                        LinkedList<String> groupMembers) {
        setAcceptSocket(acceptSocket);
        setGroupRepeaters(groupRepeaters);
        setGroupMembers(groupMembers);
    }

    public LinkedList<String> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(LinkedList<String> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Socket getAcceptSocket() {
        return acceptSocket;
    }

    public void setAcceptSocket(Socket acceptSocket) {
        this.acceptSocket = acceptSocket;
    }

    public LinkedList<ObjectOutputStream> getGroupRepeaters() {
        return groupRepeaters;
    }

    public void setGroupRepeaters(LinkedList<ObjectOutputStream> groupRepeaters) {
        this.groupRepeaters = groupRepeaters;
    }

    @Override
    public void run() {

        try {
            //1.设置昵称
            setClientNickName();

            //2.不断地接收并转发数据
            while (true) {
                receiveUserData = (UserData) receiver.readObject();
                if (receiveUserData.getDataType().equals(DataType.MESSAGE) &&
                        "bye".equals((String) receiveUserData.getObject())) {

                    /*
                    当收到客户端断开连接的命令后,把该命令再发给客户端的接收信息的线程,以便客户端
                    可以根据 这个命令终止发送信息的线程
                    */
                    myRepeater.writeObject(receiveUserData);

                    /*关闭本服务端时不能让其他的服务端用本服务端的输出流输出数据, 所以要和群发信息使用同一个锁进行同步
                     */
                    System.out.println("收到了客户端关闭连接的请求,将同意关闭的指令bye发回给客户端后,开始关闭服务端");
                    synchronized (groupMembers) {
                        groupMembers.remove(clientNickname);
                        groupRepeaters.remove(myRepeater);
                        groupSendMessage(new UserData("服务端", nowTime(),
                                DataType.MESSAGE,
                                clientNickname + "退出了聊天群," + groupMembers.toString() +
                                        "正在聊天中..."));
                        System.out.println("与" + clientNickname + "建立连接的服务端退出了接收和转发" +
                                "信息的循环");
                        break;
                    }
                } else {
                    System.out.println(clientNickname + "的服务端接收到了一条数据");
                    groupSendMessage(receiveUserData);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("正在关闭与" + clientNickname + "建立连接的服务端");
            closeResources(receiver, myRepeater, acceptSocket);
        }
        System.out.println("与" + clientNickname + "建立连接的服务端已关闭");
    }

    private void setClientNickName() throws IOException, ClassNotFoundException {
        myRepeater = new ObjectOutputStream(acceptSocket.getOutputStream());
        receiver = new ObjectInputStream(acceptSocket.getInputStream());
        clientNickname = getClientNickName();
        String time = nowTime();
        UserData serverData = new UserData("服务器", time, DataType.MESSAGE,
                "欢迎\"" + clientNickname + "\"加入群聊," +
                        groupMembers.toString() + "正在聊天中...");
        groupSendMessage(serverData);
        myRepeater.writeObject(serverData);
        System.out.println("客户端\"" + acceptSocket.getInetAddress().toString() +
                ":" + acceptSocket.getPort() + "\"的昵称是:\"" + clientNickname + "\"");
        System.out.println(clientNickname + "加入了群聊");
        System.out.println("现在群里有: " + groupMembers.toString());
    }

    private String nowTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    private String getClientNickName() throws IOException,
            ClassNotFoundException {
        String nickName = (String) receiver.readObject();
        synchronized (groupMembers) {
            if (groupMembers.contains(nickName)) {
                myRepeater.writeBoolean(true);
                myRepeater.flush();
                return getClientNickName();
            } else {
                myRepeater.writeBoolean(false);
                myRepeater.flush();
                groupMembers.add(nickName);
                groupRepeaters.add(myRepeater);
                return nickName;
            }
        }

    }

    /**
     * 群发数据的功能会用到groupSender这个公共资源,需用锁进行同步
     *
     * @param receiveData
     */
    private void groupSendMessage(UserData receiveData) throws IOException {
        synchronized (groupMembers) {
            for (int i = 0; i < groupRepeaters.size(); i++) {
                ObjectOutputStream otherRepeater = groupRepeaters.get(i);
                if (otherRepeater == myRepeater) {
                    continue;
                }
                otherRepeater.writeObject(receiveData);
                otherRepeater.flush();
                if (receiveData.getDataType() == DataType.MESSAGE) {
                    System.out.println(groupMembers.get(i) + "的服务端转发了一条消息数据");
                } else {
                    System.out.println(groupMembers.get(i) + "的服务端转发了一条文件数据");
                }

            }
            /*for (ObjectOutputStream member :
                    groupSender) {
                if (repeater == member) {
                    continue;
                }

                member.writeObject(receiveData);
                member.flush();
                System.out.println("客户端将这条数据转发给了" + groupMembers);
            }*/
        }
    }

    /**
     * 这是一个会判断资源是否为空然后关闭资源的方法
     */
    private void closeResources(Closeable... resources) {
        for (Closeable resource :
                resources) {
            if (resource != null) {
                if (resource instanceof Flushable) {
                    try {
                        ((Flushable) resource).flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    resource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
