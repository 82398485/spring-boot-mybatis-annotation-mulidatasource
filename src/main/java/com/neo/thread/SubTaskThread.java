package com.neo.thread;

import com.neo.adapters.DataFormatAdapter;
import com.neo.entity.vo.SheetType;
import com.neo.enums.SubTaskStatusEnum;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.util.FileUtils;
import com.neo.util.ReadExcel;
import com.neo.util.SpringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by songcj on 2018/10/9.
 */
public class SubTaskThread extends Thread{

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
        subTaskInfoMapper = SpringUtils.getBean(SubTaskInfoMapper.class);
        //更新任务状态为处理中
        subTaskInfoMapper.updateStatus(subTaskInfoId, SubTaskStatusEnum.TYPE_TREATING.value);
        dataFormatAdapter = SpringUtils.getBean(DataFormatAdapter.class);
        List excelList = ReadExcel.readExcel(new File(filePath), sheetIndex, startRnum, endRnum);
        this.dataFormatAdapter.processData(FileUtils.getFileName(filePath),sheetIndex,excelList);
        //更新任务状态为处理完成-成功
        subTaskInfoMapper.updateStatusAndEndDate(subTaskInfoId, SubTaskStatusEnum.TYPE_TREATED_SUCCESS.value);
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
