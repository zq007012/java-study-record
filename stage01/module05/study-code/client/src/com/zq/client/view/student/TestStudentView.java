package com.zq.client.view.student;

import com.zq.client.bean.Exam;
import com.zq.client.bean.Message;
import com.zq.client.bean.member.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName: TestStudentView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 21:07
 * @Version: V1.0
 */
public class TestStudentView extends StudentView {

    private ArrayList<Character> myAnswers;
    private int scoreThisTime;
    private Exam[] exams;

    public TestStudentView(ObjectOutputStream clientMessageSender,
                           ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public TestStudentView(ObjectOutputStream clientMessageSender,
                           ObjectInputStream clientMessageReceiver, Scanner scanner,
                           Student student) {
        super(clientMessageSender, clientMessageReceiver, scanner, student);
    }

    public TestStudentView() {
    }

    /**
     * 开启考试模块界面
     */
    @Override
    public void startView() throws IOException, ClassNotFoundException {
        Outer:
        while (true) {
            System.out.println("======================================================");
            System.out.println("|                  学员系统/考试模块                  |");
            System.out.println("------------------------------------------------------");
            System.out.println("|        1. 开始考试       |     2. 查询本次考试情况  |");
            System.out.println("------------------------------------------------------");
            System.out.println("|        3. 导出成绩       |         0. 退出模块      |");
            System.out.println("======================================================");
            int choose = getIntChoose(getScanner(), 0, 5);
            switch (choose) {
                case 0:
                    System.out.println("正在退出考试模块...");
                    break Outer;
                case 1:
                    System.out.println("开始考试");
                    test();
                    break;
                case 2:
                    System.out.println("进入查询本次考试情况功能");
                    showExamSituationThisTime();
                    break;
                case 3:
                    System.out.println("进入导出成绩功能");
                    showAllScore();
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出考试模块成功, 返回管理员系统界面");
    }

    private void showAllScore() {
        System.out.println("-------------------导出所有成绩-------------------");
        ArrayList<Integer> scores = getStudent().getScores();
        if (scores.size() == 0) {
            System.out.println("还未参加一次考试, 没有成绩能够导出");
        }else{
            for (int i =0 ; i < scores.size() ; i++){
                System.out.println("\t第" + (i + 1) +"次考试成绩: " + scores.get(i) + "分");
            }
        }

        System.out.println("---------------------导出结束---------------------");
    }

    private void showExamSituationThisTime() {
        System.out.println("----------------------本次考试情况----------------------");
        if (null == myAnswers){
            System.out.println("本次登录后未参加过考试, 请在参加考试后再显示本次考试情况");
        }else{
            for (int i = 0; i < exams.length ; i ++){
                System.out.println((i + 1) + ". " + exams[i].toStringWithCorrectOption());
                System.out.println("\t您的答案: " + myAnswers.get(i));
            }
        }

        System.out.println("成绩: " + scoreThisTime + "分");
    }

    private void test() throws IOException, ClassNotFoundException {
        System.out.println("------------------开始考试(满分100分)---------------------");
        sendMessage(new Message(Message.ALL_EXAMS, null));

        receiveMessage();

        if (Message.ALL_EXAMS_RESULT.equals(getMessageReceived().getType())) {
            exams = (Exam[]) getMessageReceived().getData();
            int scoreOfOneExam = 100 / exams.length;
            int scoreOfLastExam = 100 - (exams.length -1) * scoreOfOneExam;
            myAnswers = new ArrayList<>();
            scoreThisTime = 0;
            for (int i = 0; i < exams.length; i++) {
                Exam exam = exams[i];
                System.out.println((i + 1) + ". " + exam.toString());
                char optionLetterChoosed = getCharChoose(getScanner(),"[A-D]");
                //把用户输入的答案保存下来
                myAnswers.add(optionLetterChoosed);

                //算出得分
                if (i < exams.length - 1){
                    scoreThisTime = optionLetterChoosed == exam.getCorrectOption() ?
                            scoreThisTime + scoreOfOneExam :
                            scoreThisTime;
                }else{
                    scoreThisTime = optionLetterChoosed == exam.getCorrectOption() ?
                            scoreThisTime + scoreOfLastExam :
                            scoreThisTime;
                }

            }
            System.out.println("------------------考试结束---------------------");
            getStudent().getScores().add(scoreThisTime);
            //修改服务器数据库中中的成绩
            sendMessage(new Message(Message.UPDATE_STUDENT_SCORE, getStudent()));

            System.out.println("您本次的成绩是: " + scoreThisTime + "分");
        }
    }

}
