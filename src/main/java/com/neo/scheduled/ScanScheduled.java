package com.neo.scheduled;

import com.neo.entity.po.SubTaskInfo;
import com.neo.enums.SubTaskStatusEnum;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.thread.SubTaskThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Songcj
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class ScanScheduled {

    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;

    //@Scheduled(initialDelay =5000,fixedRate=5000)
    public void runAllSubTaskInfo(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                //如果采用CallerRunsPolicy，而线程池maximumPoolSize过小，就会阻塞线程的运行，如果采用其他的策略会导致任务的丢弃
                ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
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
    }

    @Scheduled(initialDelay =5000,fixedRate=5000)
    public void runUnfinishedSubTaskInfo(){
        //Thread thread = new Thread(){
        //    @Override
        //    public void run() {
                List<SubTaskInfo> subTaskInfoList = subTaskInfoMapper.getSubTaskInfosByStatus(SubTaskStatusEnum.TYPE_UNTREATED.value);
                //如果采用CallerRunsPolicy，而线程池maximumPoolSize过小，就会阻塞线程的运行，如果采用其他的策略会导致任务的丢弃
                //线程数需要根据实际情况进行变更，以便程序得到更好的性能，最终目标是内存不溢出的前提下更快的完成任务
                ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 20, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                        new ThreadPoolExecutor.CallerRunsPolicy());
                SubTaskInfo subTaskInfo = null;
                for (int i = 0; i < subTaskInfoList.size(); i++) {
                    subTaskInfoMapper.updateStatus(subTaskInfoList.get(i).getId(),SubTaskStatusEnum.TYPE_TREATING.value);
                    subTaskInfo = subTaskInfoList.get(i);
                    SubTaskThread subTaskThread = new SubTaskThread(subTaskInfo.getId(),subTaskInfo.getFilePath(), subTaskInfo.getSheetIndex(), subTaskInfo.getStartRnum(), subTaskInfo.getEndRnum());
                    threadPool.execute(subTaskThread);
                }

        //   }
        //};
        //Start 非阻塞型， Run为阻塞型
        //thread.run();
    }



}
