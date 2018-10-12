package com.neo.util;

import com.neo.entity.vo.FileSheetItemInfo;
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
    public static final String SPLIT_STR_WINDOWS = "\\\\";

    public static String getFileName(String filePath){
        if(filePath.contains(SPLIT_STR_LINUX)){
            return filePath.split(SPLIT_STR_LINUX)[filePath.split(SPLIT_STR_LINUX).length-1];
        }else{
            return filePath.split(SPLIT_STR_WINDOWS)[filePath.split(SPLIT_STR_WINDOWS).length-1];
        }
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





    public static void main(String args[]){
    }

}
