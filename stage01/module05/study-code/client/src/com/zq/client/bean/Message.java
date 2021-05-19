package com.zq.client.bean;

import java.io.Serializable;

/**
 * @ClassName: Message
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/19 11:40
 * @Version: V1.0
 */
public class Message implements Serializable {
    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 获取所有学员的信息
     */
    public static final String ALL_STUDENTS = "ALL_STUDENTS";
    /**
     * 服务器发送,客户端接收的消息类型之一, 返回所有学员的信息
     */
    public static final String ALL_STUDENTS_RESULT = "ALL_STUDENTS_RESULT";
    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 查询学员
     */
    public static final String RETRIEVE_STUDENT = "SEARCH_STUDENT";
    /**
     * 服务器发送,客户端接收的消息类型之一, 返回所查询学员的信息
     */
    public static final String RETIREVE_STUDENT_RESULT = "SEARCH_STUDENT_RESULT";
    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 删除学员
     */
    public static final String REMOVE_STUDENT = "REMOVE_STUDENT";
    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 删除学员反馈结果
     */
    public static final String DELETE_STUDENT_RESULT = "REMOVE_STUDENT_RESULT";

    public static final String ADD_EXAM = "ADD_EXAM";
    public static final String CREATE_EXAM_RESULT = "ADD_EXAM_RESULT";

    public static final String RETRIEVE_EXAM = "RETRIEVE_QUESTION";
    public static final String RETRIEVE_EXAM_RESULT = "RETRIEVE_QUESTION_RESULT";

    public static final String DELETE_EXAM = "DELETE_EXAM";
    public static final String DELETE_EXAM_RESULT = "DELETE_EXAM_RESULT";

    public static final String UPDATE_EXMA_CORRECT_OPTION = "UPDATE_EXMA_CORRECT_OPTION";
    public static final String UPDATE_EXMA_CORRECT_OPTION_RESULT =
            "UPDATE_EXMA_CORRECT_OPTION_RESULT";

    public static final String ALL_EXAMS = "ALL_EXAMS";
    public static final String ALL_EXAMS_RESULT = "ALL_EXAMS_RESULT";
    public static final String UPDATE_STUDENT_SCORE = "UPDATE_STUDENT_SCORE";
    public static final String STUDENT_CHECK = "STUDENT_CHECK";
    public static final String STUDENT_CHECK_RESULT = "STUDENT_CHECK_RESULT";


    private static final long serialVersionUID = 67470721082795575L;

    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 修改学员的密码
     */
    public static final String UPDATE_STUDENT_PASSWORD = "MODIFY_STUDENT_PASSWORD";
    /**
     * 服务器发送,客户端接收的消息类型之一, 修改学员的密码的反馈结果
     */
    public static final String UPDATE_STUDENT_PASSWORD_RESULT = "MODIFY_STUDENT_PASSWORD_RESULT";

    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 检查管理员账号和密码
     */
    public static final String MANAGER_CHECK = "managerCheck";
    /**
     * 服务器发送,客户端接收的消息类型之一, 管理员账号和密码的检测结果
     */
    public static final String MANAGER_CHECK_RESULT = "managerCheckResult";

    /**
     * 客户端发送, 服务器接收到的消息类型之一 , 要求服务器进行添加学员的操作
     */
    public static final String ADD_STUDENT = "addStudent";
    /**
     * 服务器发送,客户端接收的消息类型之一, 服务器进行添加学院操作的反馈结果
     */
    public static final String CREATE_STUDENT_RESULT = "addStudentResult";


    /**
     * 字段type的值之一, 表明客户端要退出在线考试系统了, 服务器建立的连接可以结束了
     */
    public static final String OVER = "over";

    /**
     * 消息的类型
     */
    private String type;
    /**
     * 消息携带的数据
     */
    private Object data;

    public Message() {
    }

    public Message(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
