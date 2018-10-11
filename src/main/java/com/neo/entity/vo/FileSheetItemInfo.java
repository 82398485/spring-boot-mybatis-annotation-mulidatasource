package com.neo.entity.vo;

import java.io.File;

/**
 * Created by songcj on 2018/10/11.
 */
public class FileSheetItemInfo {

    private File file;
    private int sheetIndex;
    private String sheetName;
    private int totalRnum;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getTotalRnum() {
        return totalRnum;
    }

    public void setTotalRnum(int totalRnum) {
        this.totalRnum = totalRnum;
    }
}
