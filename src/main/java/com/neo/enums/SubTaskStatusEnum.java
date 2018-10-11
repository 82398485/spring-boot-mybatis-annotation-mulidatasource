package com.neo.enums;

/**
 * Created by songcj on 2018/10/11.
 */
public enum SubTaskStatusEnum {

    TYPE_UNTREATED("UNTREATED",0),                   //未处理
    TYPE_TREATING("TREATING",1),                     //处理中
    TYPE_TREATED_SUCCESS("TREATED_SUCCESS",2),       //处理成功
    TYPE_TREATED_ERROR("TREATED_ERROR",3);           //处理失败

    public String  type;
    public int value;

    private SubTaskStatusEnum(String type,int value){
        this.type=type;
        this.value=value;
    }

}
