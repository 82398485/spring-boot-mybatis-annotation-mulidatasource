package com.neo.mapper;

import com.neo.entity.po.CustomerInfo;
import com.neo.entity.po.SubTaskInfo;
import com.neo.mapper.test1.CustomerInfoMapper;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.thread.SubTaskThread;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by songcj on 2018/10/10.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubTaskInfoMapperTest {


    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Test
    public void testGetAllSubTaskInfo() throws Exception {
        List<SubTaskInfo> subTaskInfoList = subTaskInfoMapper.getAll();
    }

    @Test
    public void testRunSubTask() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<SubTaskInfo> subTaskInfoList = subTaskInfoMapper.getAll();
        SubTaskInfo subTaskInfo = null;
        for(int i=0;i<subTaskInfoList.size();i++){
            subTaskInfo = subTaskInfoList.get(i);
            SubTaskThread subTaskThread = new SubTaskThread(subTaskInfo.getId(),subTaskInfo.getFilePath(),subTaskInfo.getSheetIndex(),subTaskInfo.getStartRnum(),subTaskInfo.getEndRnum());
            //subTaskThread.start();
            executor.execute(subTaskThread);
        }

        for(int i=0;i<10;i++) {
            System.out.println(customerInfoMapper.getAll().size());
            Thread.sleep(1000);
        }

    }




}
