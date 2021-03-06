package com.neo.service;

import com.alibaba.fastjson.JSON;
import com.neo.entity.po.RelationInfo;
import com.neo.entity.po.SourceInfo;
import com.neo.entity.po.SubTaskInfo;
import com.neo.entity.vo.FileSheetItemInfo;
import com.neo.enums.SubTaskStatusEnum;
import com.neo.mapper.test1.RelationInfoMapper;
import com.neo.mapper.test1.SourceInfoMapper;
import com.neo.mapper.test1.SubTaskInfoMapper;
import com.neo.util.FileUtils;
import com.neo.util.ReadExcel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songcj on 2018/10/12.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class SubTaskInfoService {

    private static Logger logger = Logger.getLogger(SubTaskInfoService.class);
    @Autowired
    private SourceInfoMapper sourceInfoMapper;
    @Autowired
    private RelationInfoMapper relationInfoMapper;
    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;

    public void createAllSubTask() {

        //获取目录及文件匹配的信息
        List<SourceInfo> sourceInfoList = this.sourceInfoMapper.getAll();
        if(sourceInfoList==null){
            logger.error("SourceInfo is empty, can't create any subTask");
            return;
        }

        //查询出所有的RelastionInfo表
        List<RelationInfo> relationInfoList = relationInfoMapper.getAll();
        SourceInfo sourceInfo = null;
        for(int i=0;i<sourceInfoList.size();i++){
            sourceInfo = sourceInfoList.get(i);
            logger.info("SourceInfo  path="+sourceInfo.getPath()+" filePattern="+sourceInfo.getFilePattern());

            //获取path下所有文件名满足filePattern的文件
            List list = FileUtils.getFiles(sourceInfo.getPath(),sourceInfo.getFilePattern());
            if(list==null||list.size()==0){
                logger.warn("Don't have any file path="+sourceInfo.getPath()+" filePattern="+sourceInfo.getFilePattern());
                continue;
            }

            for(int j=0;j<list.size();j++){

                File file = (File) list.get(0);
                String fileName = file.getName();
                String path = sourceInfo.getPath();

                //获取该文件的所有Sheet及对应的行数
                List<FileSheetItemInfo> fileSheetItemInfoList = ReadExcel.getFileSheetItemInfos(file);
                logger.info("file name="+list.get(j).toString()+"  "+file.getName());
                for(FileSheetItemInfo fileSheetItemInfo: fileSheetItemInfoList){
                    List<SubTaskInfo> subTaskInfoList = this.createSubTaskInfos(fileSheetItemInfo,relationInfoList);
                    if(subTaskInfoList!=null&&subTaskInfoList.size()>0){
                        logger.info("Success create subTask [" + JSON.toJSONString(fileSheetItemInfo) + "]");
                        this.subTaskInfoMapper.insertBatch(subTaskInfoList);
                    }else{
                        logger.warn("Can't create subTask [" + JSON.toJSONString(fileSheetItemInfo) + "]");
                    }
                }
            }

        }

    }

    private List<SubTaskInfo> createSubTaskInfos(FileSheetItemInfo fileSheetItemInfo, List<RelationInfo> relationInfoList){
        List<SubTaskInfo> subTaskInfoList = new ArrayList<>();
        if(relationInfoList==null||relationInfoList.size()<=0){
            return subTaskInfoList;
        }

        for(RelationInfo relationInfo : relationInfoList){
            if(fileSheetItemInfo.getSheetIndex()!=relationInfo.getSheetIndex()){
                continue;
            }
            Pattern p = Pattern.compile(relationInfo.getFilePattern());
            Matcher matcher = p.matcher(fileSheetItemInfo.getFile().getName());
            if(matcher.matches()){

                int nowIndex = -1;
                for(;nowIndex<fileSheetItemInfo.getTotalRnum();){
                    int start = Math.max(nowIndex,relationInfo.getMinStartRnum());
                    int end = Math.min(start+relationInfo.getBatchCount()-1,fileSheetItemInfo.getTotalRnum());

                    SubTaskInfo subTaskInfo = new SubTaskInfo();
                    subTaskInfo.setFilePath(fileSheetItemInfo.getFile().toString());
                    subTaskInfo.setSheetIndex(fileSheetItemInfo.getSheetIndex());
                    subTaskInfo.setStartRnum(start);
                    subTaskInfo.setEndRnum(end);
                    subTaskInfo.setFinished(SubTaskStatusEnum.TYPE_UNTREATED.value);

                    subTaskInfoList.add(subTaskInfo);
                    nowIndex = end + 1;
                }

                break;
            }else{
                continue;
            }
        }
        return subTaskInfoList;
    }

}
