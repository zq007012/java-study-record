package com.lagou.zq.homework.code.test5.client;

import com.lagou.zq.homework.code.test5.javabeans.DataType;
import com.lagou.zq.homework.code.test5.javabeans.FileData;
import com.lagou.zq.homework.code.test5.javabeans.UserData;
import com.lagou.zq.homework.code.util.Toolkit;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 有三个主要功能:
 * 1. 要求用户输入昵称, 再向服务端确认聊天群里是否已有此昵称,如果有就重新起一个,否则就确定下来用户的
 * 昵称
 * 2. 接收从服务端发来的对象数据, 并将数据解析为可以显示出来的文字或者保存起来的文件
 * 3. 根据用户的输入内容向客户端发送数据
 */
public class ClientWorker {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String clientNickname;
    private Scanner scanner;

    /**
     * 创建客户端工作器对象时,就确定好用户的昵称
     *
     * @param socket
     */
    public ClientWorker(Socket socket) {
        this.socket = socket;
        this.scanner = new Scanner(System.in);
        try {
            clientNickname = getClientNickname();
            printMessage("客户端", nowTime(),
                    "设置昵称完毕,成功加入聊天群.(输入\"bye\"可退出聊天群,输入\"发送文件\"" +
                            "可开始发送文件");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("初始化客户端工作器失败,强制退出程序");
            System.exit(0);
        }
    }

    public void openReceiver() throws IOException, ClassNotFoundException {
        UserData receiveData;

        while (true) {
            receiveData = (UserData) ois.readObject();
            if (DataType.MESSAGE.equals(receiveData.getDataType())) {
                String message = (String) receiveData.getObject();
                if ("bye".equals(message)) {
                    System.out.println("接收到服务端同意关闭连接的命令\"bye\", " +
                            "开始退出接收消息和文件的线程, 以便彻底关闭客户端");
                    break;
                }
                printMessage(receiveData.getClientNickname(),
                        receiveData.getGenerateTime(), message);
            } else {
                String time = nowTime();
                printMessage(receiveData.getClientNickname(), time,
                        "我群发了一个文件,请大家接收一下");
                printMessage("客户端",time,"正在下载文件中...");
                downloadFile(receiveData);
            }
        }
        System.out.println("接收消息和文件的线程终止运行");
        System.out.println("客户端成功关闭");
    }

    /**
     * 关闭所有资源
     */
    public void closeAllResource() {
        Toolkit.closeResources(ois, oos, scanner, socket);
    }

    /**
     * 将群成员发送的文件下载下来, 根本原理是从客户端发来的多个UserData对象中提取出文件的数据, 然后将这
     * 些数据写到一个文件中, 当接收到的UserData封装的FileData对象的属性isEnd的值为true时,说明上一个UserData
     * 对象封装的FileDAta对象就是最后一段数据了, 文件接受结束.
     *
     * @param receiveData
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void downloadFile(UserData receiveData) throws IOException, ClassNotFoundException {
        /*
            向外发送信息与下载文件必须同步, 否则会造成其他客户端接收到的数据受到污染
            比如A上传了一个文件, 服务端将这个文件转发给B和C, B和C正在下载文件, 若没
            有同步,B和C就可以发送数据, 然后B就发送了一条数据, 服务端就把这个数据转发
            给了A和C, 那么C下载的文件就会受到这个数据的影响,或者下载功能直接报异常
             */
        synchronized (socket) {
            FileData fileData = (FileData) receiveData.getObject();
            File fileInfo = fileData.getFileInfo();
            File file =
                    new File("resources/" + clientNickname + '/' + receiveData.getClientNickname(),
                            fileInfo.getName());
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(file));

            while (!fileData.isEnd()) {
                bos.write(fileData.getDataBuffer());
                bos.flush();
                receiveData = (UserData) ois.readObject();
                fileData = (FileData) receiveData.getObject();
            }

            //bos.flush();
            bos.close();
            printMessage("服务端", nowTime(),
                    "文件接收完毕,文件的路径是: " +
                            (char) Character.LETTER_NUMBER + "      " + file.getAbsolutePath());
        }
    }

    /**
     * 打开客户端工作器的发送器, 可以发送信息和文件
     *
     * @throws IOException
     */
    public void openSender() throws IOException {
        while (true) {
            /*
            向外发送信息与下载文件必须同步, 否则会造成其他客户端接收到的数据受到污染
            比如A上传了一个文件, 服务端将这个文件转发给B和C, B和C正在下载文件, 若没
            有同步,B和C就可以发送数据, 然后B就发送了一条数据, 服务端就把这个数据转发
            给了A和C, 那么C下载的文件就会受到这个数据的影响,或者下载功能直接报异常
             */

            String myMessage = getUserMessage();
            synchronized (socket) {
                if ("发送文件".equals(myMessage)) {
                    sendFile();
                } else {
                    sendMessage(myMessage);
                    //如果发送的信息是bye,那么就退出聊天群
                    if ("bye".equals(myMessage)) {
                        System.out.println("不再从控制台获取信息,退出发送消息和文件的线程");
                        break;
                    }
                }
            }
        }
    }

    /**
     * 发送文件,可以使用UserData对象来承载文件的数据, 如果文件大于1KB,那就用多个UserData对象来承载文件数据
     *
     * @throws IOException
     */
    private void sendFile() throws IOException {
        /*
        1. 获取用户要发送的文件对象
         */
        File file = getFile();
        //用户决定发送文件的时间
        String generateTime = nowTime();


        //如果file的值为null,则说明用户决定取消发送文件
        if (null == file) {
            printMessage(clientNickname, generateTime, "您取消了发送文件");
            return;
        }

        //在客户端打印发送文件的信息
        printMessage(clientNickname, generateTime,
                "正在发送文件\"" + file.getName() + "\"中...");


        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(file));
        byte[] dataBuffer = new byte[1024];
        boolean isEnd = false;
        int len;
        /*
        2. 根据文件的大小来决定发送几个用户数据对象,因为如果把一个文件的数据封装到一个对象里的话,会导致
        对象占用的内存跟文件大小挂钩,文件小的时候还好说,若文件很大, 大于64MB,就会导致JVM崩溃,所以最好
        把大文件拆分成多个UserData对象来发送
         */
        while ((len = bis.read(dataBuffer)) != -1) {
            if (len < 1024*1024) {
                dataBuffer = Arrays.copyOf(dataBuffer, len);
            }
            oos.writeObject(new UserData(clientNickname, generateTime, DataType.FILE,
                    new FileData(file, dataBuffer, isEnd)));
            oos.flush();
        }
        bis.close();
        /*
        当把文件的所有数据都发送出去后,再发送一个UserData对象, 这个UserData封装的FileData对象的属性
        isEnd的值为true, 表明已经到了文件的末尾了, 那么接受文件的一端就可以根据这个终止文件数据的
        写入了
         */
        isEnd = true;
        oos.writeObject(new UserData(clientNickname, generateTime, DataType.FILE,
                new FileData(file, new byte[0], isEnd)));

        oos.flush();

        printMessage("客户端", nowTime(), "文件发送结束");
    }

    /**
     * 根据用户的输入内容获取要发送的文件对象, 用户输入"取消发送"则取消发送文件
     *
     * @return 返回值为null时说明用户取消了发送文件
     */
    private File getFile() {
        System.out.println("客户端: 请输入文件路径(输入\"取消发送\"可以退出发送文件)");
        String path = scanner.nextLine();
        if ("取消发送".equals(path)) {
            return null;
        }
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                return file;
            } else {
                System.out.println("客户端: 您输入的是个文件夹的路径,请重新输入");
                return getFile();
            }
        } else {
            System.out.println("客户端: 您输入的路径不存在,请重新输入");
            return getFile();
        }
    }

    /**
     * 将信息数据封装成一个UserData对象,使用对象输出流发送给服务端
     *
     * @param myMessage
     * @throws IOException
     */
    private void sendMessage(String myMessage) throws IOException {
        String time = nowTime();
        printMessage(clientNickname, time, myMessage);
        oos.writeObject(new UserData(clientNickname, time, DataType.MESSAGE, myMessage));
    }

    /**
     * 获取用户输入的文本
     *
     * @return
     */
    private String getUserMessage() {
        String message = scanner.nextLine();
        if ("".equals(message)) {
            System.out.println("客户端: 信息不能为空, 请重新输入");
            return getUserMessage();
        }
        return message;
    }


    /**
     * 设置用户的昵称,并且当聊天群里没有这个昵称时才能设置成功
     *
     * @return
     * @throws IOException
     */
    private String getClientNickname() throws IOException {
        oos = new ObjectOutputStream(socket.getOutputStream());
        String nickName = setClientNickname();
        oos.writeObject(nickName);
        ois = new ObjectInputStream(socket.getInputStream());
        return groupHadTheNickname(ois, nickName);
    }

    /**
     * 向服务端确认群里是否已经有该名字的群成员了
     *
     * @param ois
     * @return 返回的是可以作为本客户端昵称的字符串
     */
    private String groupHadTheNickname(ObjectInputStream ois, String nickname) throws IOException {
        boolean groupHadTheNickname = ois.readBoolean();
        /*如果服务端返回的是true,则说明聊天群里已经有这个名字了,那就递归本方法,直到获取到一个聊天群
        里没有的名字
         */
        if (groupHadTheNickname) {
            printMessage("服务器", nowTime(), "聊天群中已有该名字的成员");
            nickname = setClientNickname();
            oos.writeObject(nickname);
            return groupHadTheNickname(ois, nickname);
        }
        return nickname;
    }

    /**
     * 在控制台上获取用户输入的昵称
     *
     * @return
     */
    private String setClientNickname() throws IOException {
        System.out.println("请输入您的昵称:");
        String nickName = scanner.nextLine();
        if ("".equals(nickName)) {
            System.out.println("昵称不能为空");
            return getClientNickname();
        }
        clientNickname = nickName;
        return nickName;
    }

    /**
     * 可以根据参数在控制台上打印固定格式的文本, 控制台对接收器和发送器来说是个公共资源,所以要同步
     *
     * @param nicknamme
     * @param generateTime
     * @param message
     */
    private synchronized void printMessage(String nicknamme, String generateTime, String message) {
        if (this.clientNickname.equals(nicknamme)) {
            System.out.println("我是" + nicknamme + "(" + generateTime + "): " +
                    (char) Character.LETTER_NUMBER + "    " + message);
        } else {
            System.out.println(nicknamme + "(" + generateTime + "): " +
                    (char) Character.LETTER_NUMBER + "    " + message);
        }

    }

    /**
     * 可以生成当前时间的"2020-11-12 12:22:30"格式的字符串
     *
     * @return
     */
    private String nowTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}
