package com.neo.enums;

/**
 * Created by songcj on 2018/10/9.
 */
public enum DataFormatEnum {
    TYPE_1("ABC",1);
    private String  type;
    private int value;

    private DataFormatEnum(String type,int value){
        this.type=type;
        this.value=value;
    }

}
