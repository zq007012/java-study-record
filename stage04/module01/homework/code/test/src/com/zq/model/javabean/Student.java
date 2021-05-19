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
  private static final long serialVersionUID = 9081124243212876472L;

  private String studentId;
  private String number;
  private String name;
  private String gender;
  private String birthday;
  private String email;
  private String notes;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student)) {
      return false;
    }

    Student student = (Student) o;

    return getNumber().equals(student.getNumber());
  }

  @Override
  public int hashCode() {
    return getNumber().hashCode();
  }

  @Override
  public String toString() {
    return "Student{" +
            "number='" + number + '\'' +
            ", name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", birthday=" + birthday +
            ", email='" + email + '\'' +
            ", notes='" + notes + '\'' +
            '}';
  }
}
