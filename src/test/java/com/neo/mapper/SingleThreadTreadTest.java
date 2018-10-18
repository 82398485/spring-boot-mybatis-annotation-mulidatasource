package com.neo.mapper;

import com.neo.adapters.DataFormatAdapter;
import com.neo.entity.po.SubTaskInfo;
import com.neo.enums.SubTaskStatusEnum;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.service.ThreadPoolService;
import com.neo.util.FileUtils;
import com.neo.util.ReadExcel;
import com.neo.util.SpringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songcj on 2018/10/18.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleThreadTreadTest {

    private static Logger logger = Logger.getLogger(SingleThreadTreadTest.class);
    private List<SubTaskInfo> subTaskInfoList = new ArrayList<>();
    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;
    @Autowired
    private DataFormatAdapter dataFormatAdapter;

    //使用这种方式内存消耗很稳定
    @Test
    public void runUnfinishedSubTaskInfoInTurn() throws Exception{
        this.setSubTaskInfoList(subTaskInfoMapper.getSubTaskInfosByStatus(SubTaskStatusEnum.TYPE_UNTREATED.value));
        SubTaskInfo subTaskInfo = null;
        for(int i = 0; this.subTaskInfoList!=null&&i < this.subTaskInfoList.size(); i++){
            subTaskInfo = this.subTaskInfoList.get(i);
            this.process(subTaskInfo);
        }
    }

    private void process(SubTaskInfo subTaskInfo){
        int subTaskInfoId = subTaskInfo.getId();
        int sheetIndex = subTaskInfo.getSheetIndex();
        int startRnum = subTaskInfo.getStartRnum();
        int endRnum = subTaskInfo.getEndRnum();
        String filePath = subTaskInfo.getFilePath();

        long startTime = System.currentTimeMillis();
        logger.info("Start process task. ["+ "id="+subTaskInfoId+" sheetIndex="+sheetIndex+" startRnum="+startRnum+" endRnum="+endRnum+" filePath="+filePath+"]");
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
            logger.error("excellist selected rows is empty. ["+ "id="+subTaskInfoId+" sheetIndex="+sheetIndex+" startRnum="+startRnum+" endRnum="+endRnum+" filePath="+filePath+"]");
        }
        //更新任务状态为处理完成（可能处理成功，也可能处理失败）
        subTaskInfoMapper.updateStatusAndEndDate(subTaskInfoId, finishedStatus);
        long endTime = System.currentTimeMillis();
        logger.info("finish process task. cost "+(endTime-startTime)+"[Read cost "+(endTime1-startTime1)+"]. finished status="+SubTaskStatusEnum.keyOf(finishedStatus) +" ["+ "id="+subTaskInfoId+" filePath="+filePath+" sheetIndex="+sheetIndex+" startRnum="+startRnum+" endRnum="+endRnum+" filePath="+filePath+"]");
    }

    private List<SubTaskInfo> getSubTaskInfoList() {
        return subTaskInfoList;
    }

    private void setSubTaskInfoList(List<SubTaskInfo> subTaskInfoList) {
        this.subTaskInfoList = subTaskInfoList;
    }
}
