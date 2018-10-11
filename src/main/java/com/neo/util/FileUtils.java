package com.neo.util;

import com.neo.entity.vo.FileSheetItemInfo;
import com.neo.entity.vo.RowItem;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songcj on 2018/10/10.
 */
public class FileUtils {

    public static final String SPLIT_STR_LINUX = "/";

    public static String getFileName(String filePath){
        return filePath.split(SPLIT_STR_LINUX)[filePath.split(SPLIT_STR_LINUX).length-1];
    }

    public static List<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
            }
        }
        return files;
    }

    public static List<File> getFiles(String path,String patternRegx) {
        ArrayList<File> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        Pattern p = Pattern.compile(patternRegx);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                Matcher matcher = p.matcher(tempList[i].getName());
                if(matcher.matches()){
                    files.add(tempList[i]);
                }
            }
            if (tempList[i].isDirectory()) {
            }
        }
        return files;
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




    public static void main(String args[]){
    }

}
