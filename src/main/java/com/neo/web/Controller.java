package com.neo.web;

import com.neo.entity.po.SubTaskInfo;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.service.SubTaskInfoService;
import com.neo.thread.SubTaskThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by songcj on 2018/10/11.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
public class Controller {

    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;
    @Autowired
    private SubTaskInfoService subTaskInfoService;

    @RequestMapping(value="/insertExcel")
    public String test() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                //如果采用CallerRunsPolicy，而线程池maximumPoolSize过小，就会阻塞线程的运行，如果采用其他的策略会导致任务的丢弃
                ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                        new ThreadPoolExecutor.CallerRunsPolicy());
                List<SubTaskInfo> subTaskInfoList = subTaskInfoMapper.getAll();
                SubTaskInfo subTaskInfo = null;
                for (int i = 0; i < subTaskInfoList.size(); i++) {
                    subTaskInfo = subTaskInfoList.get(i);
                    SubTaskThread subTaskThread = new SubTaskThread(subTaskInfo.getId(),subTaskInfo.getFilePath(), subTaskInfo.getSheetIndex(), subTaskInfo.getStartRnum(), subTaskInfo.getEndRnum());
                    threadPool.execute(subTaskThread);
                }
            }
        };
        //Start 非阻塞型， Run为阻塞型
        thread.start();
        return "success";
    }

    //HTTP的每次调用会产生一个http-nio的线程，最大维持10个这样的线程
    @RequestMapping(value="/createAllSubTask")
    public String createAllSubTask(){
        subTaskInfoService.createAllSubTask();
        return "success";
    }


}
