package com.neo.entity.po;

import java.util.Date;

/**
 * Created by songcj on 2018/10/10.
 */
public class SubTaskInfo {

    private int id;
    private String filePath;
    private int sheetIndex;
    private int startRnum;
    private int endRnum;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public int getEndRnum() {
        return endRnum;
    }

    public void setEndRnum(int endRnum) {
        this.endRnum = endRnum;
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
