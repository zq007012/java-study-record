package com.zq.model.javabean;


import java.io.Serializable;

public class Student implements Serializable{
  public static final String STUDENT_ID = "student_id";
  public static final String NUMBER = "number";
  public static final String NAME = "name";
  public static final String GENDER = "gender";
  public static final String BIRTHDAY = "birthday";
  public static final String EMAIL = "email";
  public static final String NOTES = "notes";
  public static final String CLASS_GRADE_ID = "class_grade_id";
  private static final long serialVersionUID = 6561127529313742175L;

  private String studentId;
  private String number;
  private String name;
  private String gender;
  /**
   * 生日, 必须是"2001-01-01"格式的字符串
   */
  private String birthday;
  private String email;
  private String notes;
  private String classGradeId;
  /**
   * Student与{@link ClassGrade}是多对一关系
   * Student是多, {@link ClassGrade}是一
   * 这是Student对象对应的{@link ClassGrade}对象
   */
  private ClassGrade classGrade;

  public ClassGrade getClassGrade() {
      return classGrade;
  }

  public void setClassGrade(ClassGrade classGrade) {
      this.classGrade = classGrade;
  }
  public Student() {
  }


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getClassGradeId() {
    return classGradeId;
  }

  public void setClassGradeId(String classGradeId) {
    this.classGradeId = classGradeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student)) {
      return false;
    }

    Student student = (Student) o;

    if (!getNumber().equals(student.getNumber())) {
      return false;
    }
    if (!getName().equals(student.getName())) {
      return false;
    }
    return getGender().equals(student.getGender());
  }

  @Override
  public int hashCode() {
    int result = getNumber().hashCode();
    result = 31 * result + getName().hashCode();
    result = 31 * result + getGender().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Student{" +
            "number=" + number +
            ", name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", birthday='" + birthday + '\'' +
            ", email='" + email + '\'' +
            ", notes='" + notes + '\'' +
            ", classGrade=" + classGrade +
            '}';
  }
}
