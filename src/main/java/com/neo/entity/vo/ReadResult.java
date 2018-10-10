package com.neo.entity.vo;

import java.util.List;

/**
 * Created by songcj on 2018/10/9.
 */
public class ReadResult {

    private List commonList;
    private List blankList;

    public ReadResult(List commonList,List blankList){
        this.commonList = commonList;
        this.blankList = blankList;
    }

    public List getCommonList() {
        return commonList;
    }

    public void setCommonList(List commonList) {
        this.commonList = commonList;
    }

    public List getBlankList() {
        return blankList;
    }

    public void setBlankList(List blankList) {
        this.blankList = blankList;
    }
}
