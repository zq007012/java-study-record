package com.lagou.zq.homework.code.test5.javabeans;

public enum DataType{
        MESSAGE(false),FILE(true);
        private boolean dataType;

        DataType(boolean dataType) {
            this.dataType = dataType;
        }
}
