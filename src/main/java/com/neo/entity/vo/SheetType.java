package com.neo.entity.vo;

import org.springframework.context.annotation.Description;

/**
 * Created by songcj on 2018/10/10.
 *
 */
@Description("This used for every sheet of different excels")
public class SheetType {

    private String filePattern;
    private int sheetIndex;

    public SheetType(){}

    public SheetType(String filePattern,int sheetIndex){
        this.filePattern = filePattern;
        this.sheetIndex = sheetIndex;
    }

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
