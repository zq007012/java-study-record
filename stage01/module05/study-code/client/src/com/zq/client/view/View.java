package com.zq.client.view;

import com.zq.client.bean.Message;

import javax.sound.midi.Receiver;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: View
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 9:22
 * @Version: V1.0
 */
public abstract class View {
    private Scanner scanner;
    private ObjectOutputStream clientMessageSender;
    private ObjectInputStream clientMessageReceiver;
    private Message messageToSend;
    private Message messageReceived;

    public View(ObjectOutputStream clientMessageSender,
                ObjectInputStream clientMessageReceiver, Scanner scanner) {
        setClientMessageSender(clientMessageSender);
        setClientMessageReceiver(clientMessageReceiver);
        setScanner(scanner);
    }

    public View() {
    }
    

    public ObjectOutputStream getClientMessageSender() {
        return clientMessageSender;
    }

    public void setClientMessageSender(ObjectOutputStream clientMessageSender) {
        this.clientMessageSender = clientMessageSender;
    }

    public ObjectInputStream getClientMessageReceiver() {
        return clientMessageReceiver;
    }

    public void setClientMessageReceiver(ObjectInputStream clientMessageReceiver) {
        this.clientMessageReceiver = clientMessageReceiver;
    }

    public Message getMessageToSend() {
        return messageToSend;
    }

    public void setMessageToSend(Message messageToSend) {
        this.messageToSend = messageToSend;
    }

    public Message getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(Message messageReceived) {
        this.messageReceived = messageReceived;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * 向服务端发送消息
     *
     * @param message
     * @throws IOException
     */
    protected void sendMessage(Message message) throws IOException {
        //保存发送的消息到成员变量中
        setMessageToSend(message);
        /*
        1. 调用reset方法可以避免现在发送的信息数据被之前发送的数据污染
         */
        clientMessageSender.reset();
        clientMessageSender.writeObject(getMessageToSend());
        clientMessageSender.flush();

        //clientMessageSender.reset();
    }

    /**
     * 接收服务端发来的消息
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Message receiveMessage() throws IOException, ClassNotFoundException {
        //保存接受的消息到成员变量中
        setMessageReceived((Message) clientMessageReceiver.readObject());
        return getMessageReceived();

    }

    /**
     * 开启本界面
     */
    public abstract void startView() throws IOException, ClassNotFoundException;

    /**
     * 获取用户输入的数字选项
     *
     * @param scanner
     * @param min     数字选项的最小值
     * @param max     数字选项的最大值
     * @return
     */
    protected int getIntChoose(Scanner scanner, int min, int max) {
        String intStr;
        int choose;
        System.out.println("请选择您要进行的操作(只需输入对应的数字即可): ");

        while (true) {
            intStr = scanner.nextLine();
            if (!(intStr.matches("\\d+"))) {
                System.out.println("您的输入有误, 只能输入对应的数字编号");
                continue;
            } else {
                choose = Integer.parseInt(intStr);
                if (choose >= min && choose <= max) {
                    break;
                } else {
                    System.out.println("没有序号为\"" + choose + "\"的操作, 请重新选择操作");
                    continue;
                }
            }

        }
        return choose;
    }

    /**
     * 获取用户输入的字符选项
     *
     * @param scaner
     * @param upperCharRegex 必须是大写字母的正则规则
     * @return
     */
    protected char getCharChoose(Scanner scaner, String upperCharRegex) {
        String charStr;
        char choose;
        System.out.println("请选择您要进行的操作(只需输入对应的字母即可, 可忽略大小写): ");

        while (true) {
            charStr = scanner.nextLine().toUpperCase();
            if (charStr.length() != 1 || !(charStr.matches(upperCharRegex))) {
                System.out.println("您的输入有误, 只能输入对应的字母编号");
            } else {
                choose = charStr.charAt(0);
                break;
            }

        }
        return choose;
    }

    /**
     * 获取一个非空的字符串
     *
     * @param scanner
     * @return
     */
    protected String getStrInput(Scanner scanner) {
        String str;
        while (true) {
            str = scanner.nextLine().trim();
            if (str.length() == 0) {
                System.out.println("输入不能为空, 请重新输入");
            } else {
                break;
            }
        }
        return str;
    }

    /**
     * 向服务端发送一个消息, 然后接收一个服务端的反馈消息
     */
    protected Message sendAndReceiveMessage(String messageType, Object messageData)
            throws IOException, ClassNotFoundException {
        //1. 向服务器发送一个消息
        Message messageToSend = new Message();
        messageToSend.setType(messageType);
        messageToSend.setData(messageData);
        sendMessage(messageToSend);


        //2. 接受一个服务器的反馈消息
        return receiveMessage();
    }
}
