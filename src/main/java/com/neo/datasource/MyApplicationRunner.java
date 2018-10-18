package com.neo.datasource;

import com.neo.adapters.DataFormatAdapter;
import com.neo.service.ThreadPoolService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by songcj on 2018/10/10.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    private static Logger logger = Logger.getLogger(MyApplicationRunner.class);

    @Autowired
    private DataFormatAdapter dataFormatAdapter;
    @Autowired
    private ThreadPoolService threadPoolService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("----------------init start-----------------");
        this.dataFormatAdapter.init();
        this.threadPoolService.init();
        logger.info("----------------init end-------------------");
    }
}
