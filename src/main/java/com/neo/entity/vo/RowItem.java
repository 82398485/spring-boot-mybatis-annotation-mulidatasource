package com.neo.entity.vo;

import java.util.List;

/**
 * Created by songcj on 2018/10/9.
 */
public class RowItem {

    private int rowNum;
    private List rowData;

    public RowItem(int rowNum, List rowData){
        this.rowData = rowData;
        this.rowNum = rowNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public List getRowData() {
        return rowData;
    }

    public void setRowData(List rowData) {
        this.rowData = rowData;
    }
}
