package com.zq.client.view.manager;

import com.zq.client.bean.Exam;
import com.zq.client.bean.Message;
import com.zq.client.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: ExamManageView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 10:51
 * @Version: V1.0
 */
public class ExamManageView extends View {

    private Exam examReceived;

    public ExamManageView(ObjectOutputStream clientMessageSender,
                          ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public ExamManageView() {
    }

    /**
     * 开启本界面
     */
    @Override
    public void startView() throws IOException, ClassNotFoundException {
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                管理员系统/考题管理模块              |");
            System.out.println("------------------------------------------------------");
            System.out.println("|      1. 添加考题         |     2. 修改考题正确答案  |");
            System.out.println("------------------------------------------------------");
            System.out.println("|      3. 删除考题         |        4.查询考题        |");
            System.out.println("------------------------------------------------------");
            System.out.println("|      5.显示所有考题      |        0. 退出模块       |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 5);
            switch (choose) {
                case 0:
                    System.out.println("正在退出考题管理模块...");
                    break Outer;
                case 1:
                    System.out.println("进入添加考题功能");
                    createExam();
                    break;
                case 2:
                    System.out.println("进入修改考题正确答案功能");
                    updateExamCorrectOption();
                    break;
                case 3:
                    System.out.println("进入删除考题功能");
                    deleteExam();
                    break;
                case 4:
                    System.out.println("进入查询考题功能");
                    retrieveExam();
                    break;
                case 5:
                    System.out.println("显示所有考题");
                    showAllExams();
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出考题管理模块成功, 返回管理员系统界面");
    }

    /**
     * 显示所有考题
     */
    private void showAllExams() throws IOException, ClassNotFoundException {
        Message feedbackMessage = sendAndReceiveMessage(Message.ALL_EXAMS, null);
        if (feedbackMessage.getType().equals(Message.ALL_EXAMS_RESULT)) {
            Exam[] exam = (Exam[]) feedbackMessage.getData();
            System.out.println("以下是所有考题的信息: ");
            System.out.println("-----------------------------------------------------");
            for (int i = 0; i < exam.length; i++) {
                System.out.println((i + 1) + ". " + exam[i].toStringWithCorrectOption());
                System.out.println();
            }
            System.out.println("-----------------------------------------------------");
        }
    }

    /**
     * 查询考题
     */
    private void retrieveExam() throws IOException, ClassNotFoundException {
        while (true) {
            //1. 获取查询的考题的题目
            System.out.println("请输入您要查询的考题的题目(输入\"-1\"可退出本功能): ");
            String question = getStrInput(getScanner());
            if (question.equals("-1")) {
                break;
            }

            if (retrieveExam(question)) {
                System.out.println("查询到该考题");
                System.out.println("该考题的内容为:");
                System.out.println(examReceived.toStringWithCorrectOption());
            } else {
                System.out.println("查询题目为:" + question +
                        "的考题失败, 系统中没有该题目的考题,请重新输入");
            }
        }
        System.out.println("退出查询考题的功能, 返回管理员系统模块");
    }

    private boolean retrieveExam(String question) throws IOException,
            ClassNotFoundException {
        //2. 将查询指令发送给服务器
        Message messageToSend = new Message();
        messageToSend.setType(Message.RETRIEVE_EXAM);
        messageToSend.setData(question);
        sendMessage(messageToSend);


        //3. 接收和分析服务器的反馈信息, 将信息打印出来
        Message messageReceived = receiveMessage();

        boolean retrieveResult = false;
        if (messageReceived.getType().equals(Message.RETRIEVE_EXAM_RESULT)) {
            Object[] obj = (Object[]) messageReceived.getData();

            retrieveResult = (Boolean) obj[0];
            examReceived = (Exam) obj[1];

        }
        return retrieveResult;
    }

    private void deleteExam() throws IOException, ClassNotFoundException {
        while (true) {
            //1. 获取要删除的考题的题目
            System.out.println("请输入您要删除的考题的题目(输入\"-1\"可退出本功能): ");
            String question = getStrInput(getScanner());
            if (question.equals("-1")) {
                break;
            }


            //2. 将删除指令发送给服务器
            Message messageToSend = new Message();
            messageToSend.setType(Message.DELETE_EXAM);
            messageToSend.setData(question);
            sendMessage(messageToSend);


            //3. 接收和分析服务器的反馈信息, 将信息打印出来
            Message messageReceived = receiveMessage();

            if (messageReceived.getType().equals(Message.DELETE_EXAM_RESULT)) {
                Object[] obj = (Object[]) messageReceived.getData();
                boolean deleteResult = (Boolean) obj[0];
                Exam deleteExam = (Exam) obj[1];
                if (deleteResult) {
                    System.out.println("成功删除题目为:" + deleteExam.getQuestion() +
                            "的考题");
                } else {
                    System.out.println("删除题目为:" + question +
                            "的考题失败, 系统中没有该题目的考题,请重新输入");
                }
            }
        }
        System.out.println("退出删除考题的功能, 返回管理员系统模块");
    }

    /**
     * 修改考题的正确答案
     */
    private void updateExamCorrectOption() throws IOException,
            ClassNotFoundException {
        while (true) {
            //1. 获取要修改的信息
            System.out.println("请输入您要修改的考题的题目(输入\"-1\"可退出本功能): ");
            String question = getStrInput(getScanner());
            if (question.equals("-1")) {
                break;
            }
            if (!retrieveExam(question)) {
                System.out.println("系统中没有这个题目的考题, 请重新输入题目");
                continue;
            }

            System.out.println("请输入新的正确答案(输入\"-1\"可退出本功能): ");
            String newCorrectAnswer = getStrInput(getScanner());
            if (newCorrectAnswer.equals("-1")) {
                break;
            }

            System.out.println("请输入正确答案的新编号[A B C D中的任意一个]");
            char newCorrectOption = getCharChoose(getScanner(),"[A-D]");

            //2. 将修改指令发送给服务器
            Message messageToSend = new Message();
            messageToSend.setType(Message.UPDATE_EXMA_CORRECT_OPTION);
            messageToSend.setData(new Object[]{question, newCorrectAnswer,
                    newCorrectOption});
            sendMessage(messageToSend);


            //3. 接收和分析服务器的反馈信息, 将信息打印出来
            Message messageReceived = receiveMessage();

            if (messageReceived.getType().equals(Message.UPDATE_EXMA_CORRECT_OPTION_RESULT)) {
                Object[] obj = (Object[]) messageReceived.getData();
                boolean updateResult = (Boolean) obj[0];
                Exam exam = (Exam) obj[1];
                if (updateResult) {
                    System.out.println("考题正确答案修改成功");
                    System.out.println("修改了正确答案的考题为:");
                    System.out.println(exam.toStringWithCorrectOption());
                } else {
                    System.out.println("修改题目为\"" + question + "\"的考题失败, 系统中" +
                            "没有这个题目的考题");
                }
            }
        }

        System.out.println("退出修改考题正确答案功能, 返回考题管理模块界面");
    }

    /**
     * 添加考题功能
     */
    private void createExam() throws IOException, ClassNotFoundException {
        while (true) {
            //1. 获取用户输入的信息
            System.out.println("请输入问题(例如: 1+1=?)(输入\"-1\"可退出本功能): ");
            String question = getStrInput(getScanner());
            if ("-1".equals(question)) {
                break;
            }

            if (retrieveExam(question)) {
                System.out.println("系统中已有一道问题相同的考题, 请重新输入一个问题");
                continue;
            }



            System.out.println("请输入选项A的内容(输入\"-1\"可退出本功能): ");
            String aOption = getStrInput(getScanner());
            if ("-1".equals(aOption)) {
                break;
            }

            System.out.println("请输入选项B的内容(输入\"-1\"可退出本功能): ");
            String bOption = getStrInput(getScanner());
            if ("-1".equals(bOption)) {
                break;
            }

            System.out.println("请输入选项C的内容(输入\"-1\"可退出本功能): ");
            String cOption = getStrInput(getScanner());
            if ("-1".equals(cOption)) {
                break;
            }

            System.out.println("请输入选项D的内容(输入\"-1\"可退出本功能): ");
            String dOption = getStrInput(getScanner());
            if ("-1".equals(dOption)) {
                break;
            }

            System.out.println("请输入正确的选项的编号[A B C D四个字符中的一个, 也可小写](输入\"-1\"可退出本功能): ");
            char correctOption = getCharChoose(getScanner(), "[A-D]");
            if (question.equals("-1")) {
                break;
            }

            Exam exam = new Exam(question.hashCode(), question,
                    aOption, bOption, cOption, dOption,
                    correctOption);

            //2. 封装信息发送给服务器
            Message messageToSend = new Message();
            messageToSend.setType(Message.ADD_EXAM);
            messageToSend.setData(exam);
            sendMessage(messageToSend);

            //3. 接收服务器反馈回来的消息
            Message messageReceived = receiveMessage();

            //4. 分析反馈信息并打印出来
            if (messageReceived.getType().equals(Message.CREATE_EXAM_RESULT)) {
                Object[] obj = (Object[]) messageReceived.getData();
                boolean addResult = (Boolean) obj[0];
                Exam examReceived = (Exam) obj[1];
                if (addResult) {
                    System.out.println("考题添加成功");
                    System.out.println(exam.toStringWithCorrectOption());
                } else {
                    System.out.println("考题添加失败, 系统中已有一道问题相同的考题");
                }
            }
        }

        System.out.println("退出添加考题的功能, 返回考题管理模块界面");
    }
}
