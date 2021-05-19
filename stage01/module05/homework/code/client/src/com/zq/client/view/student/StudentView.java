package com.zq.client.view.student;

import com.zq.client.bean.member.Student;
import com.zq.client.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @ClassName: StudentView
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/22 20:35
 * @Version: V1.0
 */
public abstract class StudentView extends View {
    private Student student;
    public StudentView(ObjectOutputStream clientMessageSender,
                       ObjectInputStream clientMessageReceiver, Scanner scanner) {
        super(clientMessageSender, clientMessageReceiver, scanner);
    }

    public StudentView(ObjectOutputStream clientMessageSender,
                       ObjectInputStream clientMessageReceiver,
                       Scanner scanner,
                       Student student) {
        super(clientMessageSender, clientMessageReceiver, scanner);
        setStudent(student);
    }

    public StudentView() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
