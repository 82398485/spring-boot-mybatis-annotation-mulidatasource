package com.neo.thread;

import com.alibaba.fastjson.JSON;
import com.neo.adapters.DataFormatAdapter;
import com.neo.entity.vo.SheetType;
import com.neo.enums.SubTaskStatusEnum;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.service.SubTaskInfoService;
import com.neo.util.FileUtils;
import com.neo.util.ReadExcel;
import com.neo.util.SpringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * Created by songcj on 2018/10/9.
 */
public class SubTaskThread extends Thread{

    private static Logger logger = Logger.getLogger(SubTaskThread.class);
    private DataFormatAdapter dataFormatAdapter;
    private SubTaskInfoMapper subTaskInfoMapper;
    private int subTaskInfoId;
    private String filePath;
    private int sheetIndex;
    private int startRnum;
    private int endRnum;

    public SubTaskThread(int subTaskInfoId,String filePath,int sheetIndex,int startRnum,int endRnum){
        this.subTaskInfoId = subTaskInfoId;
        this.filePath = filePath;
        this.sheetIndex = sheetIndex;
        this.startRnum = startRnum;
        this.endRnum = endRnum;
    }

    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        logger.info("Start process task. ["+ "id="+this.subTaskInfoId+" sheetIndex="+sheetIndex+" startRnum="+startRnum+" endRnum="+endRnum+" filePath="+filePath+"]");
        subTaskInfoMapper = SpringUtils.getBean(SubTaskInfoMapper.class);
        //更新任务状态为处理中
        subTaskInfoMapper.updateStatus(subTaskInfoId, SubTaskStatusEnum.TYPE_TREATING.value);
        dataFormatAdapter = SpringUtils.getBean(DataFormatAdapter.class);

        long startTime1 = System.currentTimeMillis();
        List excelList = ReadExcel.readExcel(new File(filePath), sheetIndex, startRnum, endRnum);
        long endTime1 = System.currentTimeMillis();

        //默认成功
        int finishedStatus = SubTaskStatusEnum.TYPE_TREATED_SUCCESS.value;
        if(excelList!=null&&excelList.size()>0){
            try {
                this.dataFormatAdapter.processData(FileUtils.getFileName(filePath), sheetIndex, excelList);
            }catch (Exception e){
                e.printStackTrace();
                //抛出异常即表示失败
                finishedStatus = SubTaskStatusEnum.TYPE_TREATED_ERROR.value;
            }
        }else{
            logger.error("excellist selected rows is empty. ["+ "id="+this.subTaskInfoId+" sheetIndex="+sheetIndex+" startRnum="+startRnum+" endRnum="+endRnum+" filePath="+filePath+"]");
        }

        //更新任务状态为处理完成（可能处理成功，也可能处理失败）
        subTaskInfoMapper.updateStatusAndEndDate(subTaskInfoId, finishedStatus);
        long endTime = System.currentTimeMillis();
        logger.info("finish process task. cost "+(endTime-startTime)+"[Read cost "+(endTime1-startTime1)+"]. finished status="+SubTaskStatusEnum.keyOf(finishedStatus) +" ["+ "id="+this.subTaskInfoId+" filePath="+filePath+" sheetIndex="+sheetIndex+" startRnum="+startRnum+" endRnum="+endRnum+" filePath="+filePath+"]");
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

}
