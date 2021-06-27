package com.zq.model.javabean;


import java.io.Serializable;
import java.util.List;

public class ClassGrade implements Serializable {
    public static final String CLASS_GRADE_ID = "class_grade_id";
    public static final String CLASS_GRADE_NAME = "class_grade_name";
    public static final String CLASS_TEACHER = "class_teacher";
    public static final String WATCHWORDS = "watchwords";
    public static final String CLASS_SIZE = "class_size";
    private static final long serialVersionUID = 6921546518497389510L;

    private String classGradeId;
    private String className;
    private String classTeacher;
    private String watchwords;
    private int classSize;
    /**
     * ClassGrade与{@link Student}是一对多关系
     * ClassGrade是一, {@link Student}是多
     * 这是ClassGrade对象对应的所有{@link Student}对象的集合
     */
    private List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public ClassGrade() {
    }


    public String getClassGradeId() {
        return classGradeId;
    }

    public void setClassGradeId(String classGradeId) {
        this.classGradeId = classGradeId;
    }

    public String getClassGradeName() {
        return className;
    }

    public void setClassGradeName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getWatchwords() {
        return watchwords;
    }

    public void setWatchwords(String watchwords) {
        this.watchwords = watchwords;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassGrade)) {
            return false;
        }

        ClassGrade that = (ClassGrade) o;

        if (!getClassGradeId().equals(that.getClassGradeId())) {
            return false;
        }
        if (!getClassGradeName().equals(that.getClassGradeName())) {
            return false;
        }
        return getClassTeacher().equals(that.getClassTeacher());
    }

    @Override
    public int hashCode() {
        int result = getClassGradeId().hashCode();
        result = 31 * result + getClassGradeName().hashCode();
        result = 31 * result + getClassTeacher().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClassGrade{" +
                "className='" + className + '\'' +
                ", classTeacher='" + classTeacher + '\'' +
                ", watchwords='" + watchwords + '\'' +
                '}';
    }


}
