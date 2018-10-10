package com.neo.adapters;

import com.neo.entity.po.RelationInfo;
import com.neo.entity.vo.RowItem;
import com.neo.mapper.test1.RelationInfoMapper;
import com.neo.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songcj on 2018/10/9.
 */
@Component
public class DataFormatAdapter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RelationInfoMapper relationInfoMapper;

    private Map<String,RelationInfo> gsMap;

    public Map<String, RelationInfo> getGsMap() {
        return gsMap;
    }

    public void setGsMap(Map<String, RelationInfo> gsMap) {
        this.gsMap = gsMap;
    }

    public void init(){
        gsMap=new HashMap<String, RelationInfo>();
        List<RelationInfo> relationInfoList =  relationInfoMapper.getAll();
        RelationInfo relationInfoTemp = null;
        for(int i=0;relationInfoList!=null&&i<relationInfoList.size();i++){
            relationInfoTemp = relationInfoList.get(i);
            gsMap.put(relationInfoTemp.getType(),relationInfoTemp);
        }
        System.out.println("--------------------");
        /**
        RelationInfo relationInfo = new RelationInfo();
        relationInfo.setId(1);
        relationInfo.setType("我-0");
        relationInfo.setEntityClass("com.neo.entity.po.CustomerInfo");
        relationInfo.setMapperClass("com.neo.mapper.test1.CustomerInfoMapper");
        gsMap.put("我-0", relationInfo);
         **/
    }

    private List castData(String type, List source) throws Exception{
        RelationInfo relationInfo = this.gsMap.get(type);
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

    private void insertData(String type, List result) throws Exception{
        RelationInfo relationInfo = this.gsMap.get(type);
        String mapperClassName = relationInfo.getMapperClass();
        Class mapperClass = Class.forName(mapperClassName);
        Method method = mapperClass.getMethod("insertBatch",List.class);
        Object object = SpringUtils.getApplicationContext().getBean(mapperClass);
        method.invoke(object,result);
    }

    public void processData(String type, List source){
        try {
            List list = this.castData(type, source);
            this.insertData(type,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
