package com.lagou.zq.homework.code.test5.javabeans;

import java.io.File;
import java.io.Serializable;


public class FileData implements Serializable {
    private static final long serialVersionUID = 1325923903279735415L;
    private File fileInfo;
    private byte[] dataBuffer;
    private boolean isEnd;

    public FileData() {
    }

    public FileData(File fileInfo, byte[] dataBuffer, boolean isEnd) {
        setFileInfo(fileInfo);
        setDataBuffer(dataBuffer);
        setEnd(isEnd);
    }

    public File getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(File fileInfo) {
        this.fileInfo = fileInfo;
    }

    public byte[] getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(byte[] dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
