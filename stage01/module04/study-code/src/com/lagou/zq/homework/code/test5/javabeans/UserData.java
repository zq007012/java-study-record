package com.lagou.zq.homework.code.test5.javabeans;

import java.io.Serializable;

public class UserData implements Serializable {
    private static final long serialVersionUID = -817511956623947400L;
    private String clientNickname;
    private String generateTime;
    private DataType dataType;
    private Object object;

    public UserData() {
    }

    public UserData(String clientNickname,String generateTime,DataType dataType, Object object) {
        setClientNickname(clientNickname);
        setGenerateTime(generateTime);
        setDataType(dataType);
        setObject(object);
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public String getClientNickname() {
        return clientNickname;
    }

    public void setClientNickname(String clientNickname) {
        this.clientNickname = clientNickname;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public DataType getDataType() {
        return dataType;
    }


    public Object getObject() {
        return object;
    }

}
