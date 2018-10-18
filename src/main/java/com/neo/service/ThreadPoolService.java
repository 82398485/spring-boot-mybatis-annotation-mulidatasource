package com.neo.service;

import com.neo.adapters.DataFormatAdapter;
import com.neo.datasource.MyApplicationRunner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by songcj on 2018/10/18.
 */
@Component
public class ThreadPoolService {

    private static Logger logger = Logger.getLogger(ThreadPoolService.class);
    private int corePoolSize = 2;
    private int maximumPoolSize = 5;
    private int keepAliveTime = 3;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private int capacity = 3;
    private ThreadPoolExecutor threadPool;

    public void  init(){
        if(threadPool==null) {
            threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, new ArrayBlockingQueue<Runnable>(capacity), new ThreadPoolExecutor.CallerRunsPolicy());
        }
        logger.info(ThreadPoolService.class.getName()+" init finished!");
    }

    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
