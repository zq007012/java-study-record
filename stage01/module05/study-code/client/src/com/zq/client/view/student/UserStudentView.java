package com.zq.client.view.student;

import com.zq.client.bean.Message;
import com.zq.client.bean.member.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: UserView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 19:59
 * @Version: V1.0
 */
public class UserStudentView extends StudentView {
    public UserStudentView(ObjectOutputStream clientMessageSender,
                           ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public UserStudentView(ObjectOutputStream clientMessageSender,
                           ObjectInputStream clientMessageReceiver, Scanner scanner,
                           Student student) {
        super(clientMessageSender, clientMessageReceiver, scanner, student);
    }

    public UserStudentView() {
    }

    /**
     * 开启本界面
     */
    @Override
    public void startView() throws IOException, ClassNotFoundException {
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                学员系统/用户模块                    |");
            System.out.println("-------------------------------------------------------");
            System.out.println("|        1. 修改密码     |        0. 退出用户模块      |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 5);
            switch (choose) {
                case 0:
                    System.out.println("正在退出用户模块...");
                    break Outer;
                case 1:
                    System.out.println("进入修改学员密码功能");
                    updateStudentPassword();
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出学员管理模块成功, 返回管理员系统界面");
    }

    private void updateStudentPassword() throws IOException, ClassNotFoundException {
            System.out.println("请输入新密码(输入\"-1\"可退出本功能): ");
            String newPassword = getStrInput(getScanner());
            if (newPassword.equals("-1")) {
                return;
            }

            //2. 将修改指令发送给服务器
            Message message = new Message();
            message.setType(Message.UPDATE_STUDENT_PASSWORD);
            message.setData(new String[]{getStudent().getAccount(), newPassword});

            sendMessage(message);


            //3. 接收和分析服务器的反馈信息, 将信息打印出来
            receiveMessage();

            if (getMessageReceived().getType().equals(Message.UPDATE_STUDENT_PASSWORD_RESULT)) {
                Object[] dataReceived = (Object[]) getMessageReceived().getData();
                boolean modifyResult = (Boolean) dataReceived[0];
                Student updataStudent = (Student) dataReceived[1];
                if (modifyResult) {
                    getStudent().setPassword(updataStudent.getPassword());
                    getStudent().setScores(updataStudent.getScores());
                    System.out.println("学员密码修改成功");
                    System.out.println("修改后的学员信息为" + getStudent().toString());
                } else {
                    System.out.println("学员密码修改失败");
                }
            }

        System.out.println("退出修改学员密码的功能, 返回用户模块界面");
    }
}
