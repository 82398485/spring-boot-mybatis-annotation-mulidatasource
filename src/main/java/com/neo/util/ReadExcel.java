package com.neo.util;

import com.neo.entity.vo.FileSheetItemInfo;
import com.neo.entity.vo.RowItem;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songcj on 2018/10/8.
 */
public class ReadExcel {


    public static void main(String[] args) {
        /**
        for( int k=0;k<200;k++) {
            System.out.println("线程"+k+"开始");
            Thread test = new Thread() {
                @Override
                public void run() {
                    ReadExcel obj = new ReadExcel();
                    // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
                    File file = new File("D:/temp.xls");
                    List excelList = obj.readExcel(file);
                    System.out.println("list中的数据打印出来");
                    for (int i = 0; i < excelList.size(); i++) {
                        List list = (List) excelList.get(i);
                        for (int j = 0; j < list.size(); j++) {
                            System.out.print(list.get(j) + " ");
                        }
                        System.out.println();
                    }
                }
            };
            test.start();
            System.out.println("线程"+k+"结束");
        }
         **/

        ReadExcel obj = new ReadExcel();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File("D:/temp.xls");
        List excelList = obj.readExcel(file,0,1,1000);
        System.out.println("list中的数据打印出来");
        for (int i = 0; i < excelList.size(); i++) {
            RowItem rowItem = (RowItem) excelList.get(i);
            List list = rowItem.getRowData();
            int rowNum = rowItem.getRowNum();
            System.out.print("第"+rowNum+"行=");
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }

    }


    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
                        //System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    //System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 去读Excel的方法readExcel，该方法的入口参数为一个File对象
     * @param file
     * @param sheetIndex  从0开始计数
     * @param startRnum  从0开始计数
     * @param endRnum  从0开始计数
     * @return
     */
    public static List readExcel(File file, int sheetIndex, int startRnum, int endRnum) {
        InputStream is = null;
        Workbook wb = null;
        String cellinfo = null;
        List<RowItem> outerList = null;
        List innerList = null;
        Sheet sheet = null;
        try {
            // 创建输入流，读取Excel
            is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                //只读取指定sheet的数据
                if(sheetIndex!=index){
                    continue;
                }
                outerList=new ArrayList<RowItem>();
                // 每个页签创建一个Sheet对象
                sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = startRnum; i<sheet.getRows()&&i<=endRnum; i++) {
                    innerList = new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        cellinfo = sheet.getCell(j, i).getContents();
                        innerList.add(cellinfo);
                    }
                    outerList.add(new RowItem(i, innerList));
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(wb!=null){
                    wb.close();
                }
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取EXCEL表格sheet页相关信息
     * @param file
     * @return
     */
    public static List<FileSheetItemInfo> getFileSheetItemInfos(File file) {
        List<FileSheetItemInfo> result = new ArrayList<>();
        FileSheetItemInfo fileSheetItemInfo = null;
        InputStream is = null;
        Workbook wb = null;
        try {
            // 创建输入流，读取Excel
            is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                fileSheetItemInfo = new FileSheetItemInfo();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                fileSheetItemInfo.setFile(file);
                fileSheetItemInfo.setSheetIndex(index);
                fileSheetItemInfo.setTotalRnum(sheet.getRows());
                result.add(fileSheetItemInfo);
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(wb!=null){
                    wb.close();
                }
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
