package com.zq.task2.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * @ClassName: Pirate
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/9 16:08
 * @Version: V1.0
 */
public class Pirate implements Serializable {

    private static final long serialVersionUID = -3366151182556263934L;
    private int pid;
    private String pname;
    private int age;
    private String gender;
    private Date birthday;
    private double reward;
    private int team_id;

    public Pirate() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    @Override
    public String toString() {
        return "Pirate{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", reward=" + reward +
                ", team_id=" + team_id +
                '}';
    }
}
