package com.neo.scheduled;

import com.neo.adapters.DataFormatAdapter;
import com.neo.entity.po.SubTaskInfo;
import com.neo.enums.SubTaskStatusEnum;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.service.ThreadPoolService;
import com.neo.thread.SubTaskThread;
import com.neo.util.FileUtils;
import com.neo.util.ReadExcel;
import com.neo.util.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
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

    private static Logger logger = Logger.getLogger(ScanScheduled.class);
    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;
    @Autowired
    private ThreadPoolService threadPoolService;
    private List<SubTaskInfo> subTaskInfoList;

    public List<SubTaskInfo> getSubTaskInfoList() {
        return subTaskInfoList;
    }

    public void setSubTaskInfoList(List<SubTaskInfo> subTaskInfoList) {
        this.subTaskInfoList = subTaskInfoList;
    }

    @Scheduled(initialDelay =5000,fixedRate=5000)
    public void runUnfinishedSubTaskInfo(){
        this.setSubTaskInfoList(subTaskInfoMapper.getSubTaskInfosByStatus(SubTaskStatusEnum.TYPE_UNTREATED.value));
        //如果采用CallerRunsPolicy，而线程池maximumPoolSize过小，就会阻塞线程的运行，如果采用其他的策略会导致任务的丢弃
        //线程数需要根据实际情况进行变更，以便程序得到更好的性能，最终目标是内存不溢出的前提下更快的完成任务
        SubTaskInfo subTaskInfo = null;
        for (int i = 0; this.subTaskInfoList!=null&&i < this.subTaskInfoList.size(); i++) {
            subTaskInfoMapper.updateStatus(this.subTaskInfoList.get(i).getId(), SubTaskStatusEnum.TYPE_TREATING.value);
            subTaskInfo = this.subTaskInfoList.get(i);
            SubTaskThread subTaskThread = new SubTaskThread(subTaskInfo.getId(), subTaskInfo.getFilePath(), subTaskInfo.getSheetIndex(), subTaskInfo.getStartRnum(), subTaskInfo.getEndRnum());
            threadPoolService.getThreadPool().execute(subTaskThread);
        }
    }

}
