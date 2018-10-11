package com.neo.entity.po;

/**
 * Created by songcj on 2018/10/9.
 */
public class RelationInfo {

    private int id;               //ID
    private String filePattern;   //文件匹配正则
    private int sheetIndex;       //EXCEL sheet编号
    private int minStartRnum;     //其实行号，下标从0开始
    private int batchCount;       //生成批量任务的数量
    private String entityClass;   //对应实体
    private String mapperClass;   //对应mapper
    private String msg;           //其他信息

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMinStartRnum() {
        return minStartRnum;
    }

    public void setMinStartRnum(int minStartRnum) {
        this.minStartRnum = minStartRnum;
    }

    public int getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(int batchCount) {
        this.batchCount = batchCount;
    }
}
