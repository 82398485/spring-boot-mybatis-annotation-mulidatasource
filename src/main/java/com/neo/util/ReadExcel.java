package com.neo.util;

import com.neo.entity.vo.FileSheetItemInfo;
import com.neo.entity.vo.RowItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by songcj on 2018/10/8.
 */
public class ReadExcel {

    public static void main(String[] args) {
        ReadExcel obj = new ReadExcel();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File("D:\\workspaces\\github\\spring-boot-readexcel\\src\\main\\resources\\temp.xls");
        List excelList = ReadExcel.readExcel(file,0,1,5);
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

    public static List readExcel(File file, int sheetIndex, int startRnum, int endRnum){
        if(file.getName().endsWith("xls")){
            return ReadExcel.readExcelXls(file,sheetIndex,startRnum,endRnum);
        }else if(file.getName().endsWith("xlsx")){
            return ReadExcel.readExcelXlsx(file,sheetIndex,startRnum,endRnum);
        }else{
            return null;
        }
    }

    /**
     * 去读Excel的方法readExcel，该方法的入口参数为一个File对象
     * @param file
     * @param sheetIndex  从0开始计数
     * @param startRnum  从0开始计数
     * @param endRnum  从0开始计数
     * @return
     */
    public static List readExcelXls(File file, int sheetIndex, int startRnum, int endRnum) {
        InputStream is = null;
        HSSFWorkbook wb = null;
        String cellinfo = null;
        List<RowItem> outerList = null;
        List innerList = null;
        HSSFSheet sheet = null;
        HSSFCell cell = null;
        try {
            // 创建输入流，读取Excel
            is = new FileInputStream(file.getAbsolutePath());
            wb = new HSSFWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                //只读取指定sheet的数据
                if(sheetIndex!=index){
                    continue;
                }
                outerList=new ArrayList<RowItem>();
                // 每个页签创建一个Sheet对象
                sheet = wb.getSheetAt(index);
                // sheet.getRows()返回该页的总行数
                for (int i = startRnum; i<sheet.getLastRowNum()&&i<=endRnum; i++) {
                    innerList = new ArrayList();
                    //获取列数
                    int columnsNum = (sheet.getRow(0)==null)?0:sheet.getRow(0).getPhysicalNumberOfCells();
                    for (int j = 0; sheet.getRow(i)!=null && j< Math.max(columnsNum,sheet.getRow(i).getPhysicalNumberOfCells()); j++) {
                        cell = sheet.getRow(i).getCell(j);
                        innerList.add(ReadExcel.readCell(cell));
                    }
                    outerList.add(new RowItem(i, innerList));
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
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
     * 去读Excel的方法readExcel，该方法的入口参数为一个File对象
     * @param file
     * @param sheetIndex  从0开始计数
     * @param startRnum  从0开始计数
     * @param endRnum  从0开始计数
     * @return
     */
    public static List readExcelXlsx(File file, int sheetIndex, int startRnum, int endRnum) {
        InputStream is = null;
        XSSFWorkbook wb = null;
        String cellinfo = null;
        List<RowItem> outerList = null;
        List innerList = null;
        XSSFSheet sheet = null;
        XSSFCell cell = null;

        try {
            // 创建输入流，读取Excel
            is = new FileInputStream(file.getAbsolutePath());
            wb = new XSSFWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                //只读取指定sheet的数据
                if(sheetIndex!=index){
                    continue;
                }
                outerList=new ArrayList<RowItem>();
                // 每个页签创建一个Sheet对象
                sheet = wb.getSheetAt(index);
                // sheet.getRows()返回该页的总行数
                for (int i = startRnum; i<sheet.getLastRowNum()&&i<=endRnum; i++) {
                    innerList = new ArrayList();
                    //获取列数
                    int columnsNum = (sheet.getRow(0)==null)?0:sheet.getRow(0).getPhysicalNumberOfCells();
                    for (int j = 0; sheet.getRow(i)!=null && j< Math.max(columnsNum,sheet.getRow(i).getPhysicalNumberOfCells()); j++) {
                        cell = sheet.getRow(i).getCell(j);
                        innerList.add(readCell(cell));
                    }
                    outerList.add(new RowItem(i, innerList));
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
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
        if(file.getName().endsWith("xlsx")){
            return ReadExcel.getFileSheetItemInfosXlsx(file);
        }else if(file.getName().endsWith("xls")){
            return ReadExcel.getFileSheetItemInfosXls(file);
        }else{
            return null;
        }
    }

    public static List<FileSheetItemInfo> getFileSheetItemInfosXls(File file) {
        List<FileSheetItemInfo> result = new ArrayList<>();
        FileSheetItemInfo fileSheetItemInfo = null;
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            // 创建输入流，读取Excel
            is = new FileInputStream(file.getAbsolutePath());
            wb = new HSSFWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                fileSheetItemInfo = new FileSheetItemInfo();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheetAt(index);
                fileSheetItemInfo.setFile(file);
                fileSheetItemInfo.setSheetIndex(index);
                fileSheetItemInfo.setTotalRnum(sheet.getLastRowNum());
                result.add(fileSheetItemInfo);
            }
            return result;
        } catch (FileNotFoundException e) {
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
    public static List<FileSheetItemInfo> getFileSheetItemInfosXlsx(File file) {
        List<FileSheetItemInfo> result = new ArrayList<>();
        FileSheetItemInfo fileSheetItemInfo = null;
        InputStream is = null;
        XSSFWorkbook wb = null;
        try {
            // 创建输入流，读取Excel
            is = new FileInputStream(file.getAbsolutePath());
            wb = new XSSFWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                fileSheetItemInfo = new FileSheetItemInfo();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheetAt(index);
                fileSheetItemInfo.setFile(file);
                fileSheetItemInfo.setSheetIndex(index);
                fileSheetItemInfo.setTotalRnum(sheet.getLastRowNum());
                result.add(fileSheetItemInfo);
            }
            return result;
        } catch (FileNotFoundException e) {
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

    public static String readCell(Cell cell){
        String cellinfo = "";
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if(DateUtil.isCellDateFormatted(cell)){ //日期类型
                Date date = cell.getDateCellValue();
                cellinfo = DateUtils.formatDatetime(date);
            }else{
                short format = cell.getCellStyle().getDataFormat();
                System.out.println("format:"+format+";;;;;value:"+cell.getNumericCellValue());
                SimpleDateFormat sdf = null;
                if (format == 14 || format == 31 || format == 57 || format == 58
                        || (176<=format && format<=178) || (182<=format && format<=196)
                        || (210<=format && format<=213) || (208==format ) ) { // 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    if(date==null||"".equals(date)){
                        cellinfo = "";
                    }else{
                        cellinfo = DateUtils.formatDateByPattern(date,sdf.toPattern());
                    }
                } else if (format == 20 || format == 32 || format==183 || (200<=format && format<=209) ) { // 时间
                    sdf = new SimpleDateFormat("HH:mm");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    if(date==null||"".equals(date)){
                        cellinfo = "";
                    }else{
                        cellinfo = DateUtils.formatDateByPattern(date,sdf.toPattern());
                    }
                }else{
                    //数值型
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellinfo = cell.getStringCellValue();
                    if (cellinfo.contains(".")) {
                        cellinfo = String.valueOf(new Double(cellinfo));
                    } else {
                    }
                }
            }
        }else if(cell.getCellType()==Cell.CELL_TYPE_FORMULA){
            cell.setCellType(CellType.STRING);
            cellinfo = String.valueOf(cell.getStringCellValue());
        }else{
            cellinfo = cell.toString();
        }

        return cellinfo;
    }



}
