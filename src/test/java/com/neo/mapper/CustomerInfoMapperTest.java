package com.neo.mapper;

import com.neo.entity.po.CustomerInfo;
import com.neo.adapters.DataFormatAdapter;
import com.neo.mapper.test1.CustomerInfoMapper;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.thread.SubTaskThread;
import com.neo.util.FileUtils;
import com.neo.util.ReadExcel;
import com.neo.entity.vo.RowItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songcj on 2018/10/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerInfoMapperTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DataFormatAdapter dataFormatAdapter;

    @Test
    public void testInsert() throws Exception {
        customerInfoMapper.insert(new CustomerInfo("哈喽11",70.77,88));
        Assert.assertEquals(5, customerInfoMapper.getAll().size());
    }

    @Test
    public void testBatchInsert() throws Exception {
        //customerInfoMapper.insert(new CustomerInfo("哈喽11",70.77,88));
        //Assert.assertEquals(5, customerInfoMapper.getAll().size());

        ReadExcel obj = new ReadExcel();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File("./src/main/resources/temp.xls");
        List excelList = obj.readExcel(file, 0, 3, 1000);
        List<CustomerInfo> customerInfoList = new ArrayList<>();
        CustomerInfo customerInfo = null;
        String userName;
        double weight;
        int age;
        //将list转换韦customerList
        for (int i = 0; excelList != null && i < excelList.size(); i++) {
            userName = (String) ((RowItem) excelList.get(i)).getRowData().get(0);
            weight = Double.parseDouble(((RowItem) excelList.get(i)).getRowData().get(1).toString());
            age = Integer.parseInt(((RowItem) excelList.get(i)).getRowData().get(2).toString());
            customerInfo = new CustomerInfo(userName, weight, age);
            customerInfoList.add(customerInfo);
        }
        customerInfoMapper.insertBatch(customerInfoList);
    }

    @Test
    public void testAdapterInsert() throws Exception {
         String filePath = "./src/main/resources/temp.xlsx";
         int sheetIndex = 0;
         int startRnum = 3;
         int endRnum = 1000;
         List excelList =  ReadExcel.readExcel(new File(filePath), sheetIndex, startRnum, endRnum);
         this.dataFormatAdapter.processData(FileUtils.getFileName(filePath),sheetIndex,excelList);
    }

    @Test
    public void testThreadInsert() throws Exception {
        //SubTaskThread subTaskThread = new SubTaskThread("D:/temp.xls",0,3,1000);
        SubTaskThread subTaskThread = new SubTaskThread(1,"./src/main/resources/temp.xlsx",0,3,1000);
        subTaskThread.start();
        Thread.sleep(10000);
    }

    @Test
    public void readExcel() throws Exception {
    }

}
