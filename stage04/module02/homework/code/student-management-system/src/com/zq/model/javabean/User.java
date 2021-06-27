package com.zq.model.javabean;


import java.io.Serializable;

public class User implements Serializable{
  public static final String USER_ID = "user_id";
  public static final String USER_NAME = "user_name";
  public static final String PASSWORD = "password";
  public static final String SESSION_ID_OF_LAST_MARK_LOGIN_SUCCESSFULLY = "session_id_of_last_mark_login_successfully";
  public static final String DATE_OF_LAST_MARK_LOGIN_SUCCESSFULLY = "date_of_last_mark_login_successfully";
  private static final long serialVersionUID = -4106835277013589238L;

  private String userId;
  private String userName;
  private String password;
  private String sessionIdOfLastMarkLoginSuccessfully;
  /**
   * 上一次标记登录成功的日期, 必须是"2000-01-01"格式的字符串
   */
  private String dateOfLastMarkLoginSuccessfully;

  public User() {
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSessionIdOfLastMarkLoginSuccessfully() {
    return sessionIdOfLastMarkLoginSuccessfully;
  }

  public void setSessionIdOfLastMarkLoginSuccessfully(String sessionIdOfLastMarkLoginSuccessfully) {
    this.sessionIdOfLastMarkLoginSuccessfully = sessionIdOfLastMarkLoginSuccessfully;
  }

  public String getDateOfLastMarkLoginSuccessfully() {
    return dateOfLastMarkLoginSuccessfully;
  }

  public void setDateOfLastMarkLoginSuccessfully(String dateOfLastMarkLoginSuccessfully) {
    this.dateOfLastMarkLoginSuccessfully = dateOfLastMarkLoginSuccessfully;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }

    User user = (User) o;

    return getUserName().equals(user.getUserName());
  }

  @Override
  public int hashCode() {
    return getUserName().hashCode();
  }

  @Override
  public String toString() {
    return "User{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", sessionIdOfLastMarkLoginSuccessfully='" + sessionIdOfLastMarkLoginSuccessfully + '\'' +
            ", dateOfLastMarkLoginSuccessfully='" + dateOfLastMarkLoginSuccessfully + '\'' +
            '}';
  }
}
