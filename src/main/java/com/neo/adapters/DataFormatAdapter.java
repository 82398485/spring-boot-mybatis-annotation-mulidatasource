package com.neo.adapters;

import com.neo.datasource.MyApplicationRunner;
import com.neo.entity.po.RelationInfo;
import com.neo.entity.vo.RowItem;
import com.neo.entity.vo.SheetType;
import com.neo.mapper.test1.RelationInfoMapper;
import com.neo.util.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songcj on 2018/10/9.
 */
@Component
public class DataFormatAdapter {

    private static Logger logger = Logger.getLogger(MyApplicationRunner.class);
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RelationInfoMapper relationInfoMapper;
    private Map<SheetType,RelationInfo> gsMap;

    public Map<SheetType, RelationInfo> getGsMap() {
        return gsMap;
    }

    public void setGsMap(Map<SheetType, RelationInfo> gsMap) {
        this.gsMap = gsMap;
    }

    public void init(){
        gsMap=new HashMap<SheetType, RelationInfo>();
        List<RelationInfo> relationInfoList =  relationInfoMapper.getAll();
        RelationInfo relationInfoTemp = null;
        for(int i=0;relationInfoList!=null&&i<relationInfoList.size();i++){
            relationInfoTemp = relationInfoList.get(i);
            gsMap.put(new SheetType(relationInfoTemp.getFilePattern(),relationInfoTemp.getSheetIndex()),relationInfoTemp);
        }
        logger.info(DataFormatAdapter.class.getName()+" init finished!");
    }

    public RelationInfo findSheetType(String fileName,int sheetIndex){
        SheetType matchKey = null;
        for(SheetType sheetTypeTemp : this.gsMap.keySet()){
            if(sheetTypeTemp.getSheetIndex()!=sheetIndex){
                continue;
            }
            Pattern p = Pattern.compile(sheetTypeTemp.getFilePattern());
            Matcher matcher = p.matcher(fileName);
            if(matcher.matches()){
                matchKey = sheetTypeTemp;
                break;
            }
        }

        if(matchKey!=null){
            return this.gsMap.get(matchKey);
        }else{
            return null;
        }
    }

    private List castData(String fileName,int sheetIndex, List source) throws Exception{
        RelationInfo relationInfo = this.findSheetType(fileName,sheetIndex);
        String entityClassName = relationInfo.getEntityClass();
        String mapperClassName = relationInfo.getMapperClass();

        List result = new ArrayList();
        Class entityClass = Class.forName(entityClassName);
        Class mapperClass = Class.forName(mapperClassName);
        Method method = entityClass.getMethod("cast",List.class);

        for(int i=0;source!=null&&i<source.size();i++){
            RowItem rowItem = (RowItem) source.get(i);
            Object object = entityClass.newInstance();
            method.invoke(object,rowItem.getRowData());
            result.add(object);
        }

        return result;
    }

    private void insertData(String fileName,int sheetIndex, List result) throws Exception{
        RelationInfo relationInfo = this.findSheetType(fileName,sheetIndex);;
        String mapperClassName = relationInfo.getMapperClass();
        Class mapperClass = Class.forName(mapperClassName);
        Method method = mapperClass.getMethod("insertBatch",List.class);
        Object object = SpringUtils.getApplicationContext().getBean(mapperClass);
        method.invoke(object,result);
    }

    public void processData(String fileName, int sheetIndex, List source) throws Exception{
        List list = this.castData(fileName,sheetIndex, source);
        this.insertData(fileName,sheetIndex,list);
    }

}
