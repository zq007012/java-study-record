package com.zq.server;

import com.zq.client.bean.Exam;
import com.zq.client.bean.Message;
import com.zq.client.bean.member.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName: ServerView
 * @Description: 接收客户端发来的消息, 并根据消息的属性做出相应的业务处理后,将处理后的结果
 * 再反馈给客户端
 * @Author: zq007
 * @Date: 2021/1/19 14:13
 * @Version: V1.0
 */
public class ServerView {
    private ServerDAO serverDAO;
    private ObjectOutputStream serverMessageSender;
    private ObjectInputStream serverMessageReceiver;
    private Message messageToSend;

    public ServerView(ServerInitClose serverInitClose, ServerDAO serverDAO) {
        serverMessageReceiver = serverInitClose.getOis();
        serverMessageSender = serverInitClose.getOos();
        this.serverDAO = serverDAO;
    }

    /**
     * 处理信息, 可以接收信息,
     */
    public void handleMessage() throws IOException, ClassNotFoundException {
        outer:
        while (true) {
            //1. 接收消息
            Message messageReceived = (Message) serverMessageReceiver.readObject();
            String type = messageReceived.getType();
            switch (type) {
                case Message.OVER:
                    System.out.println("客户端要下线了, 不需要处理消息了,退出处理消息的功能");
                    break outer;
                case Message.MANAGER_CHECK:
                    managerCheck(messageReceived);
                    break;
                case Message.ADD_STUDENT:
                    createStudent(messageReceived);
                    break;
                case Message.UPDATE_STUDENT_PASSWORD:
                    updateStudentPassword(messageReceived);
                    break;
                case Message.REMOVE_STUDENT:
                    deleteStudent(messageReceived);
                    break;
                case Message.RETRIEVE_STUDENT:
                    retrieveStudent(messageReceived);
                    break;
                case Message.ALL_STUDENTS:
                    allStudents(messageReceived);
                    break;
                case Message.ADD_EXAM:
                    createExam(messageReceived);
                    break;
                case Message.RETRIEVE_EXAM:
                    retrieveExam(messageReceived);
                    break;
                case Message.UPDATE_EXMA_CORRECT_OPTION:
                    updateExamCorrectOption(messageReceived);
                    break;
                case Message.DELETE_EXAM:
                    deleteExam(messageReceived);
                    break;
                case Message.ALL_EXAMS:
                    allExams(messageReceived);
                    break;
                    case Message.UPDATE_STUDENT_SCORE:
                    updateStudentScore(messageReceived);
                    break;
                    case Message.STUDENT_CHECK:
                    studentCheck(messageReceived);
                    break;
                default:
                    break;
            }
        }

    }

    private void studentCheck(Message messageReceived) throws IOException {
        String[] dataReceived = (String[]) messageReceived.getData();
        String studentAccount = dataReceived[0];
        String studentPassword = dataReceived[1];

        Object[] dataToSend = serverDAO.studentCheck(studentAccount,studentPassword);
        sendMessage(new Message(Message.STUDENT_CHECK_RESULT, dataToSend));
    }

    private void updateStudentScore(Message messageReceived) {
        Student student = (Student) messageReceived.getData();
        serverDAO.updateStudentScore(student);
    }

    private void allExams(Message messageReceived) throws IOException {
        Object[] allExams = serverDAO.allExam();

        messageToSend = new Message(Message.ALL_EXAMS_RESULT, allExams);
        sendMessage(messageToSend);
    }

    private void deleteExam(Message messageReceived) throws IOException {
        String question = (String) messageReceived.getData();

        Object[] data = serverDAO.deleteExam(question);

        messageToSend = new Message();
        messageToSend.setType(Message.DELETE_EXAM_RESULT);
        messageToSend.setData(data);
        sendMessage(messageToSend);
    }

    private void updateExamCorrectOption(Message messageReceived) throws IOException {
        //1. 分析信息
        Object[] updateData = (Object[]) messageReceived.getData();
        String question = (String) updateData[0];
        String newCorrectAnswer = (String) updateData[1];
        char newCorrectOption = (char) updateData[2];

        //2. 进行相应的业务处理
        Object[] objs =
                serverDAO.updateExamCorrectOption(question,
                        newCorrectAnswer,
                        newCorrectOption);

        //3. 将处理结果反馈给客户端
        messageToSend = new Message(Message.UPDATE_EXMA_CORRECT_OPTION_RESULT, objs);
        sendMessage(messageToSend);
    }

    /**
     * 查询一个考题
     *
     * @param messageReceived
     */
    private void retrieveExam(Message messageReceived) throws IOException {
        String question = (String) messageReceived.getData();

        Object[] objs = serverDAO.retrieveExam(question);

        messageToSend = new Message();
        messageToSend.setType(Message.RETRIEVE_EXAM_RESULT);
        messageToSend.setData(objs);
        sendMessage(messageToSend);
    }

    /**
     * 向服务器的数据库中添加一个考题
     *
     * @param messageReceived
     */
    private void createExam(Message messageReceived) throws IOException {
        Exam exam = (Exam) messageReceived.getData();

        Object[] objs = serverDAO.createExam(exam);

        messageToSend = new Message(Message.CREATE_EXAM_RESULT, objs);
        sendMessage(messageToSend);
    }

    private void retrieveStudent(Message receiveMessage) throws IOException {
        String studentAccount = (String) receiveMessage.getData();

        Object[] objs = serverDAO.retrieveStudent(studentAccount);

        messageToSend = new Message();
        messageToSend.setType(Message.RETIREVE_STUDENT_RESULT);
        messageToSend.setData(objs);
        sendMessage(messageToSend);

    }

    private void deleteStudent(Message receiveMessage) throws IOException {
        String studentAccount = (String) receiveMessage.getData();

        Object[] objs = serverDAO.deleteStudent(studentAccount);

        messageToSend = new Message();
        messageToSend.setType(Message.DELETE_STUDENT_RESULT);
        messageToSend.setData(objs);
        sendMessage(messageToSend);

    }

    private void allStudents(Message receiveMessage) throws IOException {
        Object[] allStudents = serverDAO.allStudents();

        messageToSend = new Message(Message.ALL_STUDENTS_RESULT, allStudents);
        sendMessage(messageToSend);
    }

    /**
     * 对从客户端接收的信息进行分析, 并进行相应的业务处理, 然后将处理结果反馈给客户端
     *
     * @param receiveMessage
     */
    private void updateStudentPassword(Message receiveMessage) throws IOException {
        //1. 分析信息
        String[] modifyData = (String[]) receiveMessage.getData();
        String studentAccount = modifyData[0];
        String newPassword = modifyData[1];

        //2. 进行相应的业务处理
        Object[] objs = serverDAO.modifyStudentPassword(studentAccount, newPassword);

        //3. 将处理结果反馈给客户端
        messageToSend = new Message(Message.UPDATE_STUDENT_PASSWORD_RESULT, objs);
        sendMessage(messageToSend);
    }

    /**
     * 管理员添加一个学员
     *
     * @param receiveMessage
     */
    private void createStudent(Message receiveMessage) throws IOException {
        String studentAccout = (String) receiveMessage.getData();

        Object[] objs = serverDAO.createStudent(studentAccout);

        messageToSend = new Message(Message.CREATE_STUDENT_RESULT, objs);
        sendMessage(messageToSend);

    }

    /**
     * 检测客户端发来的管理员账号和密码是否正确, 将检测结果封装到信息中反馈给客户端,然后根据
     * 检测结果来判断是否进入服务端的管理员系统功能
     *
     * @param messageReceived
     * @throws IOException
     */
    private void managerCheck(Message messageReceived) throws IOException {
        System.out.println("校验管理员的账号和密码");
        //1. 获取客户端发来要检测的的管理员账号和密码
        String[] dataReceived = (String[]) messageReceived.getData();
        String managerAccount = dataReceived[0];
        String managerPassword = dataReceived[1];

        //2. 检测客户端发来的管理员账号和密码是否正确
        Object[] dataToSend = serverDAO.managerLogInCheck(managerAccount , managerPassword);

        //3. 将检测的结果封装到信息中反馈给客户端
        messageToSend = new Message(Message.MANAGER_CHECK_RESULT, dataToSend);
        sendMessage(messageToSend);
    }

    private void sendMessage(Message messageToSend) throws IOException {
        serverMessageSender.reset();
        serverMessageSender.writeObject(messageToSend);
        serverMessageSender.flush();
        //serverMessageSender.reset();
    }
}
