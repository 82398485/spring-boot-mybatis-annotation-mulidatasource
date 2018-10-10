package com.neo.entity.vo;

import java.util.Date;

/**
 * Created by songcj on 2018/10/8.
 */
public class TaskInfo {

    private int id;
    private String fileName;
    private int sheetIndex;
    private int startRnum;
    private int endRum;
    private int finished;
    private Date startDate;
    private Date endDate;
    private String errorInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getStartRnum() {
        return startRnum;
    }

    public void setStartRnum(int startRnum) {
        this.startRnum = startRnum;
    }

    public int getEndRum() {
        return endRum;
    }

    public void setEndRum(int endRum) {
        this.endRum = endRum;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
